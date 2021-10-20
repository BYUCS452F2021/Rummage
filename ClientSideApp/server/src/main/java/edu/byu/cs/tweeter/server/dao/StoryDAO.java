//package edu.byu.cs.tweeter.server.dao;
//
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
//import com.amazonaws.services.dynamodbv2.document.DynamoDB;
//import com.amazonaws.services.dynamodbv2.document.Item;
//import com.amazonaws.services.dynamodbv2.document.ItemCollection;
//import com.amazonaws.services.dynamodbv2.document.KeyAttribute;
//import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
//import com.amazonaws.services.dynamodbv2.document.Table;
//import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
//import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
//import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
//import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
//import com.amazonaws.services.dynamodbv2.model.AttributeValue;
//
//import java.time.ZoneOffset;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import edu.byu.cs.tweeter.shared.model.domain.Status;
//import edu.byu.cs.tweeter.shared.model.domain.User;
//import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
//import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
//import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;
//
//public class StoryDAO {
//    private static final String tableName = "Story";
//
//    private final Table userDynamoTable;
//
//    public StoryDAO() {
//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
//                .withRegion("us-west-2")
//                .build();
//        DynamoDB dynamoDB = new DynamoDB(client);
//        this.userDynamoTable = dynamoDB.getTable(tableName);
//    }
//
//    /**
//     * Creates a new table entry from the info from request.
//     *
//     *
//     * @return the user if successful
//     */
//    public boolean createPost(PostRequest request, String authorFirstName, String authorLastName, String authorImageURL, String dateString, long postDate) throws Exception {
//
//        Item itemToAdd = new Item().withPrimaryKey("authorAlias", request.getAuthorAlias())
//                .withLong("postDate", postDate)
//                .withString("firstName", authorFirstName)
//                .withString("imageUrl", authorImageURL)
//                .withString("lastName", authorLastName)
//                .withString("dateString", dateString)
//                .withString("message", request.getMessage());
//
//        PutItemSpec spec =
//                new PutItemSpec().withItem(itemToAdd);
//
//        try {
//        userDynamoTable.putItem(spec);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            throw new Exception(exception.getMessage());
//        }
//
//        return true;
//    }
//
//    public StatusListResponse readStatuses(StatusListRequest request) {
//
//
//        HashMap<String, String> nameMap = new HashMap<String, String>();
//        nameMap.put("#aa", "authorAlias");
//
//        HashMap<String, Object> valueMap = new HashMap<String, Object>();
//        valueMap.put(":uar", request.getUserAlias());
//        QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#aa = :uar")
//                .withNameMap(nameMap)
//                .withValueMap(valueMap)
//                .withMaxPageSize(request.getLimit()+1)
//                .withScanIndexForward(true);
//
//        if(request.getLastPageBottom() != null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
//            ZonedDateTime theTime = ZonedDateTime.parse(request.getLastPageBottom().getDate(), formatter);
//            long dateNum = theTime.toInstant().atZone(ZoneOffset.UTC).toEpochSecond();
//            System.out.println(dateNum);
//            querySpec = querySpec.withExclusiveStartKey("authorAlias", request.getUserAlias(),
//                    "postDate", dateNum);
//        }
//
//
//
//        ItemCollection<QueryOutcome> items;
//
//        List<Status> statusList = new ArrayList<>();
//        boolean hasMorePages = false;
//        try {
//            items = userDynamoTable.query(querySpec);
//            int pagesSoFar = 0;
//            for (Item item : items) {
//                pagesSoFar++;
//                if (pagesSoFar < request.getLimit()) {
//                    User user = new User(item.getString("authorFirstName"), item.getString("authorLastName"), item.getString("authorAlias"), item.getString("authorImageUrl"));
//                    String dateString = item.getString("dateString");
//
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
//                    ZonedDateTime theTime = ZonedDateTime.parse(dateString, formatter);
//
//                    Status status = new Status(item.getString("message"), user, theTime);
//                    statusList.add(status);
//                }
//                else {
//                    hasMorePages = true;
//                }
//            }
//
//        } catch (Exception e) {
//            return new StatusListResponse(e.getMessage());
//        }
//
//
//
//        return new StatusListResponse(statusList, hasMorePages);
//    }
//
//    public void deleteStatus(Status status) {
//        DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("authorAlias", status.getPoster().getAlias())
//                .withConditionExpression("dateString == :val")
//                .withValueMap(new ValueMap().withString(":val", status.getDate()));
//        try {
//            userDynamoTable.deleteItem(deleteItemSpec);
//        } catch (Exception e) {
//            System.out.println("Unable to delete " + status.toString());
//            System.out.println(e.getMessage());
//        }
//    }
//}
