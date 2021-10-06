package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.BatchWriteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableWriteItems;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.byu.cs.tweeter.shared.model.domain.FollowCount;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.service.request.SignUpRequest;

public class UserDAO {

    private static final String tableName = "User";

    DynamoDB dynamoDB;

    private final Table userDynamoTable;

    public UserDAO() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion("us-west-2")
                .build();
        dynamoDB = new DynamoDB(client);
        this.userDynamoTable = dynamoDB.getTable(tableName);
    }

    /**
     * Creates a new table entry from the info from request.
     *
     * @return the user if successful
     */
    public List<Object> createUser(SignUpRequest request) throws Exception {
        byte[] hashedPass = DaoUtil.hash(request.getPassword());

        Item itemToAdd = new Item().withPrimaryKey("userAlias", request.getAlias())
                .withString("firstName", request.getFirstName())
                .withNumber("followeeCount", 0)
                .withNumber("followerCount", 0)
                .withBinary("hashedPass", hashedPass)
                .withString("imageUrl", request.getImageUrl()) // FIXME use S3
                .withString("lastName", request.getLastName());

        PutItemSpec spec =
                new PutItemSpec().withConditionExpression("attribute_not_exists(userAlias)")
                        .withItem(itemToAdd);

        try {
            userDynamoTable.putItem(spec);
        } catch (ConditionalCheckFailedException exception) {
            throw new Exception("User Alias " + request.getAlias() + " already exists!");
        }

        User userRetVal = new User(request.getFirstName(),
                request.getLastName(),
                request.getAlias(),
                request.getImageUrl()
        );
        FollowCount countRetVal = new FollowCount(request.getAlias());
        countRetVal.setNumFollowers(0);
        countRetVal.setNumFollowing(0);

        return makePair(userRetVal, countRetVal);
    }

    /**
     * Pulls out the record for use with followCount, signIn, and userView.
     *
     * @param aliasToPull the alias of the user that is being requested to read
     * @return a User object containing the relevant data from the table.
     */
    public List<Object> readUser(String aliasToPull) throws Exception {
        // GetItemSpec getItemSpec = new GetItemSpec().withPrimaryKey("userAlias", aliasToPull).withExpressionSpec();

        Item outcome = userDynamoTable.getItem("userAlias", aliasToPull);
        if (outcome == null) { // todo add conditional expression to detect a missing userAlias
            throw new Exception("User '" + aliasToPull + "' not found.");
        }
        Map<String, Object> tableEntryData = outcome.asMap();

        User userRetVal = new User((String) tableEntryData.get("firstName"),
                (String) tableEntryData.get("lastName"),
                (String) tableEntryData.get("userAlias"),
                (String) tableEntryData.get("imageUrl"));

        FollowCount countRetVal = new FollowCount((String) tableEntryData.get("userAlias"));
        countRetVal.setNumFollowers(DaoUtil.dynamoDBNumToInt(tableEntryData.get("followerCount")));
        countRetVal.setNumFollowing(DaoUtil.dynamoDBNumToInt(tableEntryData.get("followeeCount")));

        List<Object> returnList = makePair(userRetVal, countRetVal);
        returnList.add(tableEntryData.get("hashedPass"));
        return returnList;
    }

    public FollowCount updateUserCounts(String userAlias, int newNumFollowers, int newNumFollowing) {
        // ValueMap vMap = new ValueMap().withNumber("followerCount", newNumFollowers)
        //         .withNumber("followeeCount", newNumFollowing);

        UpdateItemSpec spec = new UpdateItemSpec().withPrimaryKey("userAlias", userAlias)
                .withUpdateExpression("set followerCount = :a, followeeCount = :b")
                .withValueMap(new ValueMap().withNumber(":a", newNumFollowers).withNumber(":b", newNumFollowing))
                .withReturnValues(ReturnValue.UPDATED_NEW);

        UpdateItemOutcome outcome = userDynamoTable.updateItem(spec);
        Map<String, Object> tableEntryData = outcome.getItem().asMap();

        FollowCount retCount = new FollowCount(userAlias);
        retCount.setNumFollowers(DaoUtil.dynamoDBNumToInt(tableEntryData.get("followerCount")));
        retCount.setNumFollowing(DaoUtil.dynamoDBNumToInt(tableEntryData.get("followeeCount")));

        return retCount;
    }

    /**
     * A delete operation for testing.
     *
     * @param userAlias the key name for the user to be deleted.
     */
    public void deleteUser(String userAlias) {
        userDynamoTable.deleteItem("userAlias", userAlias);
    }


    /**
     * Bundles a user and follow count object.
     *
     * @param user  the user return object
     * @param count the count return object
     * @return the pair
     */
    private List<Object> makePair(User user, FollowCount count) {
        List<Object> pair = new ArrayList<>();
        pair.add(user);
        pair.add(count);

        return pair;
    }

    public void addUserBatch(List<User> users) throws InvalidKeySpecException, NoSuchAlgorithmException {

        // Constructor for TableWriteItems takes the name of the table, which I have stored in TABLE_USER
        TableWriteItems items = new TableWriteItems(tableName);
        byte[] hashedPass = DaoUtil.hash("password");

        // Add each user into the TableWriteItems object
        for (User user : users) {
            Item item = new Item()
                    .withPrimaryKey("userAlias", user.getAlias())
                    .withString("firstName", user.getFirstName())
                    .withNumber("followeeCount", 0)
                    .withNumber("followerCount", 0)
                    .withBinary("hashedPass", hashedPass)
                    .withString("imageUrl", user.getImageUrl()) // FIXME use S3
                    .withString("lastName", user.getLastName());
            items.addItemToPut(item);

            // 25 is the maximum number of items allowed in a single batch write.
            // Attempting to write more than 25 items will result in an exception being thrown
            if (items.getItemsToPut() != null && items.getItemsToPut().size() == 25) {
                loopBatchWrite(items);
                items = new TableWriteItems(tableName);
            }
        }

        // Write any leftover items
        if (items.getItemsToPut() != null && items.getItemsToPut().size() > 0) {
            loopBatchWrite(items);
        }
    }

    private void loopBatchWrite(TableWriteItems items) {

        // The 'dynamoDB' object is of type DynamoDB and is declared statically in this example
        BatchWriteItemOutcome outcome = dynamoDB.batchWriteItem(items);
        System.out.println("Wrote User Batch");

        // Check the outcome for items that didn't make it onto the table
        // If any were not added to the table, try again to write the batch
        while (outcome.getUnprocessedItems().size() > 0) {
            Map<String, List<WriteRequest>> unprocessedItems = outcome.getUnprocessedItems();
            outcome = dynamoDB.batchWriteItemUnprocessed(unprocessedItems);
            System.out.println("Wrote more Users");
        }
    }
}
