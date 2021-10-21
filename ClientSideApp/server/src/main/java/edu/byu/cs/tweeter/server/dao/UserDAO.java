package edu.byu.cs.tweeter.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.byu.cs.tweeter.server.DBManager;
import edu.byu.cs.tweeter.shared.model.domain.User;

public class UserDAO {

    private DBManager dbManager;

    public UserDAO() {
        dbManager = DBManager.getInstance();
    }

    public User getUser(String username) throws Exception {
        Connection conn = dbManager.getConnection();
        String query = "select * from users where username = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        ResultSet result = stmt.executeQuery();

        return new User(username, result.getString("password"), result.getString("contactID"));
    }

//    public List<Object> createUser(SignUpRequest request) throws Exception {
//        byte[] hashedPass = DaoUtil.hash(request.getPassword());
//
//        Item itemToAdd = new Item().withPrimaryKey("userAlias", request.getAlias())
//                .withString("firstName", request.getFirstName())
//                .withNumber("followeeCount", 0)
//                .withNumber("followerCount", 0)
//                .withBinary("hashedPass", hashedPass)
//                .withString("imageUrl", request.getImageUrl()) // FIXME use S3
//                .withString("lastName", request.getLastName());
//
//        PutItemSpec spec =
//                new PutItemSpec().withConditionExpression("attribute_not_exists(userAlias)")
//                        .withItem(itemToAdd);
//
//        try {
//            userDynamoTable.putItem(spec);
//        } catch (ConditionalCheckFailedException exception) {
//            throw new Exception("User Alias " + request.getAlias() + " already exists!");
//        }
//
//        User userRetVal = new User(request.getFirstName(),
//                request.getLastName(),
//                request.getAlias(),
//                request.getImageUrl()
//        );
//        FollowCount countRetVal = new FollowCount(request.getAlias());
//        countRetVal.setNumFollowers(0);
//        countRetVal.setNumFollowing(0);
//
//        return makePair(userRetVal, countRetVal);
//    }

//    public FollowCount updateUserCounts(String userAlias, int newNumFollowers, int newNumFollowing) {
//        // ValueMap vMap = new ValueMap().withNumber("followerCount", newNumFollowers)
//        //         .withNumber("followeeCount", newNumFollowing);
//
//        UpdateItemSpec spec = new UpdateItemSpec().withPrimaryKey("userAlias", userAlias)
//                .withUpdateExpression("set followerCount = :a, followeeCount = :b")
//                .withValueMap(new ValueMap().withNumber(":a", newNumFollowers).withNumber(":b", newNumFollowing))
//                .withReturnValues(ReturnValue.UPDATED_NEW);
//
//        UpdateItemOutcome outcome = userDynamoTable.updateItem(spec);
//        Map<String, Object> tableEntryData = outcome.getItem().asMap();
//
//        FollowCount retCount = new FollowCount(userAlias);
//        retCount.setNumFollowers(DaoUtil.dynamoDBNumToInt(tableEntryData.get("followerCount")));
//        retCount.setNumFollowing(DaoUtil.dynamoDBNumToInt(tableEntryData.get("followeeCount")));
//
//        return retCount;
//    }
//
//    /**
//     * A delete operation for testing.
//     *
//     * @param userAlias the key name for the user to be deleted.
//     */
//    public void deleteUser(String userAlias) {
//        userDynamoTable.deleteItem("userAlias", userAlias);
//    }
//
//
//    /**
//     * Bundles a user and follow count object.
//     *
//     * @param user  the user return object
//     * @param count the count return object
//     * @return the pair
//     */
//    private List<Object> makePair(User user, FollowCount count) {
//        List<Object> pair = new ArrayList<>();
//        pair.add(user);
//        pair.add(count);
//
//        return pair;
//    }
//
//    public void addUserBatch(List<User> users) throws InvalidKeySpecException, NoSuchAlgorithmException {
//
//        // Constructor for TableWriteItems takes the name of the table, which I have stored in TABLE_USER
//        TableWriteItems items = new TableWriteItems(tableName);
//        byte[] hashedPass = DaoUtil.hash("password");
//
//        // Add each user into the TableWriteItems object
//        for (User user : users) {
//            Item item = new Item()
//                    .withPrimaryKey("userAlias", user.getAlias())
//                    .withString("firstName", user.getFirstName())
//                    .withNumber("followeeCount", 0)
//                    .withNumber("followerCount", 0)
//                    .withBinary("hashedPass", hashedPass)
//                    .withString("imageUrl", user.getImageUrl()) // FIXME use S3
//                    .withString("lastName", user.getLastName());
//            items.addItemToPut(item);
//
//            // 25 is the maximum number of items allowed in a single batch write.
//            // Attempting to write more than 25 items will result in an exception being thrown
//            if (items.getItemsToPut() != null && items.getItemsToPut().size() == 25) {
//                loopBatchWrite(items);
//                items = new TableWriteItems(tableName);
//            }
//        }
//
//        // Write any leftover items
//        if (items.getItemsToPut() != null && items.getItemsToPut().size() > 0) {
//            loopBatchWrite(items);
//        }
//    }
//
//    private void loopBatchWrite(TableWriteItems items) {
//
//        // The 'dynamoDB' object is of type DynamoDB and is declared statically in this example
//        BatchWriteItemOutcome outcome = dynamoDB.batchWriteItem(items);
//        System.out.println("Wrote User Batch");
//
//        // Check the outcome for items that didn't make it onto the table
//        // If any were not added to the table, try again to write the batch
//        while (outcome.getUnprocessedItems().size() > 0) {
//            Map<String, List<WriteRequest>> unprocessedItems = outcome.getUnprocessedItems();
//            outcome = dynamoDB.batchWriteItemUnprocessed(unprocessedItems);
//            System.out.println("Wrote more Users");
//        }
//    }
}
