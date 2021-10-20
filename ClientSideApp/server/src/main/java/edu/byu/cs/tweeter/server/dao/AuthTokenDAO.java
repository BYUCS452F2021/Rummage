//package edu.byu.cs.tweeter.server.dao;
//
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
//import com.amazonaws.services.dynamodbv2.document.DynamoDB;
//import com.amazonaws.services.dynamodbv2.document.Item;
//import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
//import com.amazonaws.services.dynamodbv2.document.Table;
//import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
//
//import java.util.Random;
//
//import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
//
//public class AuthTokenDAO {
//    private final String tableName = "AuthToken";
//
//    public AuthToken create(String creationDate, String userAlias) {
//        assert(creationDate != null);
//        assert(userAlias != null);
//
//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
//                .withRegion(Regions.US_WEST_2)
//                .build();
//
//        DynamoDB dynamoDB = new DynamoDB(client);
//
//        Table table = dynamoDB.getTable(this.tableName);
//
//        Random rand = new Random((creationDate+userAlias).hashCode());
//        String key = String.valueOf(rand.nextLong());
//
//        //table.putItem(new Item().with("keyId", key).with("creationDate", creationDate).with("userAlias", userAlias));
//
//        try {
//            System.out.println("Adding a new item...");
//            PutItemOutcome outcome = table
//                    .putItem(new PutItemSpec().withItem(new Item().withPrimaryKey("keyId", key).withString("creationDate", creationDate).withString("userAlias", userAlias)));
//
//            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
//
//        }
//        catch (Exception e) {
//            System.err.println("Unable to add item: " + key + " " + creationDate + " " + userAlias);
//            System.err.println(e.getMessage());
//        }
//
//        AuthToken authToken = new AuthToken();
//        authToken.setKeyId(key);
//        return authToken;
//    }
//
//    public String read(AuthToken authToken) throws Exception {
//        //if not in table returns empty string
//        if (authToken == null) {
//            throw new Exception("Authtoken was null");
//        }
//        if (authToken.getKeyId() == null) {
//            throw new Exception("Authtoken's key was null");
//        }
//        assert(authToken.getKeyId() != null);
//
//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
//                .withRegion("us-west-2")
//                .build();
//
//        DynamoDB dynamoDB = new DynamoDB(client);
//
//        Table table = dynamoDB.getTable(this.tableName);
//
//        Item tokenItem = table.getItem("keyId", authToken.getKeyId());
//        Object creationDate = tokenItem.get("creationDate");
//        if (creationDate == null) {
//            return "";
//        }
//        return (String) creationDate;
//    }
//
//    public void delete(AuthToken authToken) {
//        assert(authToken.getKeyId() != null);
//
//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
//                .withRegion("us-west-2")
//                .build();
//
//        DynamoDB dynamoDB = new DynamoDB(client);
//
//        Table table = dynamoDB.getTable(this.tableName);
//
//        table.deleteItem("keyId", authToken.getKeyId());
//    }
//}
