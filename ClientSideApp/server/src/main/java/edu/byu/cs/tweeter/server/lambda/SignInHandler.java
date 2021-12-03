package edu.byu.cs.tweeter.server.lambda;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

import org.bson.Document;
import org.bson.conversions.Bson;

import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

import static com.mongodb.client.model.Filters.eq;

/**
 * An AWS lambda function that logs a user in and returns the user object and an auth code for
 * a successful login.
 */

public class SignInHandler {

    private class dbUser {
        public String Username;
        public String Password;

        public String getUsername() {
            return Username;
        }

        public void setUsername(String username) {
            Username = username;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String password) {
            Password = password;
        }
    }

    public LoginResponse handleRequest(LoginRequest loginRequest) {
        try {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://rummage:cH6J7CJHEUpF2ZM@rummage.igpye.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("rummage");

        MongoCollection<Document> users = database.getCollection("Users");

        Bson projectionFields = Projections.fields(
                Projections.include("Username", "Password"),
                Projections.excludeId());
        Document doc = users.find(eq("Username", loginRequest.getUsername()))
                .projection(projectionFields)
                .first();
        if (doc == null) {
            return new LoginResponse("Incorrect Username or Password");
        } else {
            String s = doc.toJson();
            dbUser temp = JsonSerializer.deserialize(s, dbUser.class);

            return new LoginResponse(new User(temp.getUsername(), temp.getPassword(), "1234"));
        }
        } catch (Exception e) {
            e.printStackTrace();
            return new LoginResponse(e.getMessage());
        }
    }
}

