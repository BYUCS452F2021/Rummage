//package edu.byu.cs.tweeter.server.dao;
//
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
//import com.amazonaws.services.dynamodbv2.document.BatchWriteItemOutcome;
//import com.amazonaws.services.dynamodbv2.document.DynamoDB;
//import com.amazonaws.services.dynamodbv2.document.Index;
//import com.amazonaws.services.dynamodbv2.document.Item;
//import com.amazonaws.services.dynamodbv2.document.ItemCollection;
//import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
//import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
//import com.amazonaws.services.dynamodbv2.document.Table;
//import com.amazonaws.services.dynamodbv2.document.TableWriteItems;
//import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
//import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
//import com.amazonaws.services.dynamodbv2.model.WriteRequest;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import edu.byu.cs.tweeter.shared.model.service.request.RelationshipChangeRequest;
//import edu.byu.cs.tweeter.shared.model.service.response.RelationshipChangeResponse;
//
//public class FollowDAO {
//
//    private final Table followTable;
//    private final String followTableName = "Follow";
//    public final static int ALIAS_PAGE_SIZE = 10;
//    private DynamoDB dynamoDB;
//
//    /**
//     * Creates an instance, setting up the dynamoDB table object.
//     */
//    public FollowDAO() {
//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
//                .withRegion("us-west-2")
//                .build();
//        dynamoDB = new DynamoDB(client);
//        followTable = dynamoDB.getTable(followTableName);
//    }
//
//    /**
//     * Creates a follow record and adds it to the follow table.
//     *
//     * @return a list of strings containing the partition key and sort key on success,
//     * or an empty list on failure
//     */
//    public List<String> createRecord(String followerAlias, String followeeAlias) {
//        List<String> returnVal = new ArrayList<>();
//        try {
//            System.out.println("Adding a new item...");
//            PutItemOutcome outcome = followTable.putItem(new Item()
//                    .withPrimaryKey("followerAlias", followerAlias,
//                            "followeeAlias", followeeAlias));
//
//            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
//            returnVal.add(followerAlias);
//            returnVal.add(followeeAlias);
//        } catch (Exception e) {
//            System.err.println("Unable to add item: " + followerAlias + " " + followeeAlias);
//            System.err.println(e.getMessage());
//        }
//
//        return returnVal;
//    }
//
//    /**
//     * Returns a list of aliases, the last of which contains either the most recent alias or null if
//     * no more aliases to retrieve.
//     *
//     * @param followerAlias the alias to find the followees of
//     * @param lastAliasRetrieved null if requesting first page, otherwise the last alias from the prior page
//     * @return a list of strings containing the page of aliases,
//     * followed by one more element, either the lastAlias, or null if there are no more to grab
//     */
//    public List<String> getPagedFolloweeAliases(String followerAlias, String lastAliasRetrieved) {
//        HashMap<String, String> nameMap = new HashMap<>();
//        nameMap.put("#fh", "followerAlias");
//
//        HashMap<String, Object> valueMap = new HashMap<>();
//        valueMap.put(":mF", followerAlias);
//
//        QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#fh = :mF")
//                .withNameMap(nameMap)
//                .withValueMap(valueMap)
//                .withScanIndexForward(true)
//                .withMaxResultSize(ALIAS_PAGE_SIZE);
//        if(lastAliasRetrieved != null) {
//            querySpec = querySpec.withExclusiveStartKey("followerAlias", followerAlias,
//                    "followeeAlias", lastAliasRetrieved);
//        }
//
//
//        ItemCollection<QueryOutcome> items;
//        Iterator<Item> iterator;
//        Item item = null;
//
//        List<String> returnAliases = new ArrayList<>();
//        try {
//            System.out.println("Users being followed by " + followerAlias);
//            items = followTable.query(querySpec);
//
//            iterator = items.iterator();
//            while (iterator.hasNext()) {
//                item = iterator.next();
//                System.out.println(item.getString("followerAlias") + ": " + item.getString("followeeAlias"));
//                returnAliases.add(item.getString("followeeAlias"));
//            }
//            if(items.getLastLowLevelResult().getQueryResult().getLastEvaluatedKey() != null) {
//                returnAliases.add(item.getString("followeeAlias"));
//            } else {
//                returnAliases.add(null);
//            }
//
//        } catch (Exception e) {
//            System.err.println("Unable to query followees of " + followerAlias);
//            System.err.println(e.getMessage());
//        }
//
//        return returnAliases;
//    }
//
//    /**
//     * Returns a list of aliases, the last of which contains either the most recent alias or null if
//     * no more aliases to retrieve.
//     *
//     * @param followeeAlias the alias to find the followers of
//     * @param lastAliasRetrieved null if requesting first page, otherwise the last alias from the prior page
//     * @return a list of strings containing the page of aliases,
//     * followed by one more element, either the lastAlias, or null if there are no more to grab
//     */
//    public List<String> getPagedFollowerAliases(String followeeAlias, String lastAliasRetrieved) {
//
//        Index index = followTable.getIndex("followeeAlias-index");
//
//        HashMap<String, String> nameMap = new HashMap<>();
//        nameMap.put("#fh", "followeeAlias");
//
//        HashMap<String, Object> valueMap = new HashMap<>();
//        valueMap.put(":mF", followeeAlias);
//
//        QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#fh = :mF")
//                .withNameMap(nameMap)
//                .withValueMap(valueMap)
//                .withScanIndexForward(false)
//                .withMaxResultSize(ALIAS_PAGE_SIZE);
//        if(lastAliasRetrieved != null) {
//            querySpec = querySpec.withExclusiveStartKey("followerAlias", lastAliasRetrieved,
//                    "followeeAlias", followeeAlias);
//        }
//
//        ItemCollection<QueryOutcome> items;
//        Iterator<Item> iterator;
//        Item item = null;
//
//        List<String> returnAliases = new ArrayList<>();
//        try {
//            System.out.println("Users following " + followeeAlias);
//            items = index.query(querySpec);
//
//            iterator = items.iterator();
//            while (iterator.hasNext()) {
//                item = iterator.next();
//                System.out.println(item.getString("followerAlias") + ": " + item.getString("followeeAlias"));
//                returnAliases.add(item.getString("followerAlias"));
//            }
//            if(items.getLastLowLevelResult().getQueryResult().getLastEvaluatedKey() != null) {
//                returnAliases.add(item.getString("followeeAlias"));
//            } else {
//                returnAliases.add(null);
//            }
//
//        } catch (Exception e) {
//            System.err.println("Unable to query followers of " + followeeAlias);
//            System.err.println(e.getMessage());
//        }
//
//        return returnAliases;
//    }
//
//    /**
//     * Deletes a record based on its composite key.
//     *
//     * @param followerAlias the partition key
//     * @param followeeAlias the sort key
//     */
//    void deleteRecord(String followerAlias, String followeeAlias) {
//        System.out.println("deleting: " + followerAlias + " with their followed person" + followeeAlias);
//        followTable.deleteItem("followerAlias", followerAlias, "followeeAlias", followeeAlias);
//    }
//
//    /**
//     * Changes the relationship of a follow based on the request.
//     * Does this by adding or deleting a record in the follow table.
//     *
//     * @param relationshipChangeRequest a request detailing the aliases
//     *                                  and whether it is follow or unfollow.
//     * @return the response with the status of currently following
//     */
//    public RelationshipChangeResponse changeRelationship(RelationshipChangeRequest relationshipChangeRequest) {
//        System.out.println("Changing relationship");
//        boolean isRequestToFollow = !relationshipChangeRequest.isFollowRelationship();
//
//        boolean isNowFollowing;
//        if(isRequestToFollow) {
//            System.out.println("Adding " + relationshipChangeRequest.getCurrUserAlias() + " -> "
//                    + relationshipChangeRequest.getOtherUser() + " to follow table.");
//            this.createRecord(relationshipChangeRequest.getCurrUserAlias(), relationshipChangeRequest.getOtherUser());
//            isNowFollowing = true;
//        } else {
//            System.out.println("Removing " + relationshipChangeRequest.getCurrUserAlias() + " -> "
//                    + relationshipChangeRequest.getOtherUser() + " from follow table.");
//            this.deleteRecord(relationshipChangeRequest.getCurrUserAlias(), relationshipChangeRequest.getOtherUser());
//            isNowFollowing = false;
//        }
//
//
//        return new RelationshipChangeResponse(relationshipChangeRequest.getAuthToken(), !isNowFollowing);
//    }
//
//    public void addFollowersBatch(List<String> users, String followed) {
//
//        // Constructor for TableWriteItems takes the name of the table, which I have stored in TABLE_USER
//        TableWriteItems items = new TableWriteItems(followTableName);
//
//        // Add each user into the TableWriteItems object
//        for (String user : users) {
//            Item item = new Item()
//                    .withPrimaryKey("followerAlias", user)
//                    .withString("followeeAlias", followed);
//            items.addItemToPut(item);
//
//            // 25 is the maximum number of items allowed in a single batch write.
//            // Attempting to write more than 25 items will result in an exception being thrown
//            if (items.getItemsToPut() != null && items.getItemsToPut().size() == 25) {
//                loopBatchWrite(items);
//                items = new TableWriteItems(followTableName);
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
//        System.out.println("Wrote Follow Batch");
//
//        // Check the outcome for items that didn't make it onto the table
//        // If any were not added to the table, try again to write the batch
//        while (outcome.getUnprocessedItems().size() > 0) {
//            Map<String, List<WriteRequest>> unprocessedItems = outcome.getUnprocessedItems();
//            outcome = dynamoDB.batchWriteItemUnprocessed(unprocessedItems);
//            System.out.println("Wrote more Follows");
//        }
//    }
//
//    public boolean recordExists(String followerAlias, String followeeAlias) {
//
//
//        GetItemSpec spec = new GetItemSpec().withPrimaryKey("followerAlias", followerAlias,
//                "followeeAlias", followeeAlias);
//
//        try {
//            System.out.println("Attempting to read the item...");
//            Item outcome = followTable.getItem(spec);
//            System.out.println("GetItem succeeded: " + outcome);
//            return true;
//        } catch (Exception e) {
//            System.err.println("Unable to read item: " + followerAlias + " " + followeeAlias);
//            System.err.println(e.getMessage());
//            return false;
//        }
//    }
//
//}
