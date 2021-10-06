package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableWriteItems;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.byu.cs.tweeter.shared.model.domain.Status;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SQSPostRequest;
import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;

public class FeedDAO {
    private static final String tableName = "Feed";

    private final Table userDynamoTable;
    DynamoDB dynamoDB;

    public FeedDAO() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion("us-west-2")
                .build();
        dynamoDB = new DynamoDB(client);
        this.userDynamoTable = dynamoDB.getTable(tableName);
    }

    public StatusListResponse readStatuses(StatusListRequest request) {


        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#fa", "followerAlias");

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":uar", request.getUserAlias());


        QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#fa = :uar")
                .withNameMap(nameMap)
                .withValueMap(valueMap)
                .withMaxPageSize(request.getLimit()+1)
                .withScanIndexForward(true);

        if(request.getLastPageBottom() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
            ZonedDateTime theTime = ZonedDateTime.parse(request.getLastPageBottom().getDate(), formatter);
            long dateNum = theTime.toInstant().atZone(ZoneOffset.UTC).toEpochSecond();
            System.out.println(dateNum);
            querySpec = querySpec.withExclusiveStartKey("followerAlias", request.getUserAlias(),
                    "postDate", dateNum);
        }

        //QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#fa = :uar")
        //        .withNameMap(nameMap)
        //        .withValueMap(valueMap).withExclusiveStartKey("postDate", request.getLastPageBottom().getDate())
        //        .withMaxPageSize(request.getLimit() + 1)
        //        .withScanIndexForward(true);

        ItemCollection<QueryOutcome> items;

        List<Status> statusList = new ArrayList<>();
        boolean hasMorePages = false;
        try {
            items = userDynamoTable.query(querySpec);
            int pagesSoFar = 0;
            for (Item item : items) {
                pagesSoFar++;
                if (pagesSoFar < request.getLimit()) {
                    User user = new User(item.getString("authorFirstName"), item.getString("authorLastName"), item.getString("authorAlias"), item.getString("authorImageUrl"));
                    String dateString = item.getString("dateString");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
                    ZonedDateTime theTime = ZonedDateTime.parse(dateString, formatter);

                    Status status = new Status(item.getString("message"), user, theTime);
                    statusList.add(status);
                } else {
                    hasMorePages = true;
                }
            }

        } catch (Exception e) {
            return new StatusListResponse(e.getMessage());
        }


        return new StatusListResponse(statusList, hasMorePages);
    }

    /**
     * Creates a new table entry from the info from request.
     *
     * @return the user if successful
     */
    public boolean createPost(PostRequest request, String authorFirstName, String authorLastName, String authorImageURL, String dateString, long postDate) throws Exception {

        SQSPostRequest messageRequest = new SQSPostRequest(request, authorFirstName, authorLastName, authorImageURL, dateString, postDate);

        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gsonObject = builder.create();
        String messageBody = gsonObject.toJson(messageRequest);

        String queueUrl = "https://sqs.us-west-2.amazonaws.com/921827367645/PostQueue";

        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody)
                .withDelaySeconds(5);

        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        SendMessageResult send_msg_result = sqs.sendMessage(send_msg_request);

        String msgId = send_msg_result.getMessageId();
        System.out.println("Message ID: " + msgId);


        /*Item itemToAdd = new Item().withPrimaryKey("authorAlias", request.getAuthorAlias())
                .withLong("postDate", postDate)
                .withString("firstName", authorFirstName)
                .withString("imageUrl", authorImageURL)
                .withString("lastName", authorLastName)
                .withString("dateString", dateString)
                .withString("message", request.getMessage());

        PutItemSpec spec =
                new PutItemSpec().withItem(itemToAdd);

        try {
            userDynamoTable.putItem(spec);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception(exception.getMessage());
        }*/

        return true;
    }

    public void createPostBatch(SQSPostRequest sqsPostRequest, List<String> batch) throws Exception {
        //FollowerAlias is the batch of strings 
        long postDate = sqsPostRequest.postDate;
        String authorAlias = sqsPostRequest.request.getAuthorAlias();
        String authorFirstName = sqsPostRequest.authorFirstName;
        String authorImageURL = sqsPostRequest.authorImageURL;
        String authorLastName = sqsPostRequest.authorLastName;
        String dateString = sqsPostRequest.dateString;
        String message = sqsPostRequest.request.getMessage();

        List<Item> items = new ArrayList<>();

        for (String followerAlias : batch) {
            items.add(new Item().withPrimaryKey("followerAlias", followerAlias)
                    .withLong("postDate", postDate)
                    .withString("authorAlias", authorAlias)
                    .withString("firstName", authorFirstName)
                    .withString("imageUrl", authorImageURL)
                    .withString("lastName", authorLastName)
                    .withString("dateString", dateString)
                    .withString("message", message));
        }

        TableWriteItems tableWriteItems = new TableWriteItems(tableName).withItemsToPut(items);

        try {
            dynamoDB.batchWriteItem(tableWriteItems);
            System.out.println("wrote to table");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception(exception.getMessage());
        }
    }
}
