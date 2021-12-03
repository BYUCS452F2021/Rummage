package edu.byu.cs.tweeter.client.model.net;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.DBObject;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;


import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

import edu.byu.cs.tweeter.shared.model.domain.Sale;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.FollowingFollowersRequest;
import edu.byu.cs.tweeter.shared.model.service.request.FollowedSalesRequest;
import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
import edu.byu.cs.tweeter.shared.model.service.request.RelationshipChangeRequest;
import edu.byu.cs.tweeter.shared.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.shared.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.shared.model.service.request.RegisterUserRequest;
import edu.byu.cs.tweeter.shared.model.service.request.UserRequest;
/*import edu.byu.cs.tweeter.shared.model.service.response.FollowCountResponse;*/
import edu.byu.cs.tweeter.shared.model.service.response.FollowedSalesResponse;
import edu.byu.cs.tweeter.shared.model.service.response.FollowingFollowersResponse;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.shared.model.service.response.PostResponse;
import edu.byu.cs.tweeter.shared.model.service.response.RelationshipChangeResponse;
import edu.byu.cs.tweeter.shared.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.shared.model.service.response.UserResponse;

import static com.mongodb.client.model.Filters.eq;

/**
 * Acts as a Facade to the Tweeter server. All network requests to the server should go through
 * this class.
 * :)
 */
public class ServerFacade {

    private final String LOG_TAG = "Server_Facade";

    // **************************************
    // Properly calling with network requests
    // **************************************
    private static final String SERVER_URL = "https://76p6b1s1de.execute-api.us-west-2.amazonaws.com/integrationStage";
    private final ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
    private final MongoDatabase database;

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

    public ServerFacade() throws UnknownHostException {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://rummage:cH6J7CJHEUpF2ZM@rummage.igpye.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("rummage");
    }

    /**
     * Performs a login and if successful, returns the logged in user and an auth token.
     *
     * @param request contains all information needed to perform a login.
     * @return the login response.
     */
    public LoginResponse signIn(LoginRequest request, String urlPath) throws IOException, TweeterRemoteException {
        //Log.i(LOG_TAG, "serverFacade:signIn");

        MongoCollection<Document> users = database.getCollection("Users");

        Bson projectionFields = Projections.fields(
                Projections.include("Username", "Password"),
                Projections.excludeId());
        Document doc = users.find(eq("Username", request.getUsername()))
                .projection(projectionFields)
                .first();
        if (doc == null) {
            throw new TweeterRemoteException("Incorrect Username or Password","", null);
        } else {
            String s = doc.toJson();
            dbUser temp = JsonSerializer.deserialize(s, dbUser.class);

            return new LoginResponse(new User(temp.getUsername(), temp.getPassword(), "1234"));
        }


        // return new LoginResponse(new User("testUser","dummypass","1234"));

        /*LoginResponse response = clientCommunicator.doPost(urlPath, request, null, LoginResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }*/
    }

    /**
     * Performs a signUp and if successful, returns the logged in user and an auth token.
     *
     * @param request contains all information needed to perform a signup.
     * @return the login response.
     */
    public LoginResponse signUp(RegisterUserRequest request, String urlPath) throws IOException, TweeterRemoteException {
        //Log.i(LOG_TAG, "serverFacade:signUp");

        /*DBCollection collection = database.getCollection("Users");
        DBObject person = new BasicDBObject("Username", request.getUsername()).append("Password", request.getPassword())
                .append("ContactInfo", new BasicDBObject("FirstName", request.getFirstName()).append("LastName", request.getLastName())
                        .append("Email", "exampleemail@email.email").append("Phone", "666-666-6666"));
        collection.insert(person);*/



        return new LoginResponse(new User(request.getUsername(),request.getPassword(),"1234"));

       /* LoginResponse response = clientCommunicator.doPost(urlPath, request, null, LoginResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }*/
    }

    /**
     * Performs a signOut operation on our server.
     *
     * @param request contains all information needed to perform the operation.
     * @return the response.
     */
    public LogoutResponse signOut(LogoutRequest request, String urlPath) throws IOException, TweeterRemoteException {
        //Log.i(LOG_TAG, "serverFacade:signOut");
        return new LogoutResponse(true);

        /*SignOutResponse response = clientCommunicator.doPost(urlPath, request, null, SignOutResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }*/
    }

    /**
     * Performs a post operation on our server.
     *
     * @param request contains all information needed to perform the operation.
     * @return the response.
     */
    public PostResponse post(PostRequest request, String urlPath) throws IOException, TweeterRemoteException {
        //Log.i(LOG_TAG, "serverFacade:post");
        return new PostResponse(true);

        /*PostResponse response = clientCommunicator.doPost(urlPath, request, null, PostResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }*/
    }

    /* *
     * Performs a getFollowCount operation on our server.
     *
     * @param request contains all information needed to perform the operation.
     * @return the response.
     */
    /*public FollowCountResponse getFollowCount(FollowCountRequest request, String urlPath) throws IOException, TweeterRemoteException {
        //Log.i(LOG_TAG, "serverFacade:getFollowCount");
        FollowCountResponse response = clientCommunicator.doPost(urlPath, request, null, FollowCountResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }
    }*/

    /**
     * Performs a changeRelationship operation on our server.
     *
     * @param request contains all information needed to perform the operation.
     * @return the response.
     */
    public RelationshipChangeResponse changeRelationship(RelationshipChangeRequest request, String urlPath) throws IOException, TweeterRemoteException {
        //Log.i(LOG_TAG, "serverFacade:changeRelationship");
        RelationshipChangeResponse response = clientCommunicator.doPost(urlPath, request, null, RelationshipChangeResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }
    }

    /**
     * Performs a getUser operation on our server.
     *
     * @param request contains all information needed to perform the operation.
     * @return the response.
     */
    public UserResponse getUser(UserRequest request, String urlPath) throws IOException, TweeterRemoteException {
        //Log.i(LOG_TAG, "serverFacade:getUser");
        UserResponse response = clientCommunicator.doPost(urlPath, request, null, UserResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }
    }

    /**
     * Performs a getStatuses operation on our server.
     *
     * @param request contains all information needed to perform the operation.
     * @return the response.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public FollowedSalesResponse getFollowedSales(FollowedSalesRequest request, String urlPath) throws IOException, TweeterRemoteException {
        //Log.i(LOG_TAG, "serverFacade:getStatuses");
        List<Sale> demo = new LinkedList<>();
        demo.add(new Sale(1, "UserA", ZonedDateTime.now(), 0, "The best sale ever", "Yard Sale"));
        demo.add(new Sale(2, "testUser", ZonedDateTime.now(), 0, "The bestest sale ever", "Yard Sale"));
        demo.add(new Sale(3, "UserD", ZonedDateTime.now(), 0, "The bester sale ever", "Yard Sale"));
        demo.add(new Sale(4, "testUser", ZonedDateTime.now(), 0, "The bestestest sale ever", "Yard Sale"));
        demo.add(new Sale(5, "UserH", ZonedDateTime.now(), 0, "The besterest sale ever", "Yard Sale"));
        demo.add(new Sale(6, "UserN", ZonedDateTime.now(), 0, "The besterier sale ever", "Yard Sale"));
        demo.add(new Sale(7, "UserB", ZonedDateTime.now(), 0, "The best pie sale ever", "Pie Sale"));
        return new FollowedSalesResponse(demo, false);
        /*FollowedSalesResponse response = clientCommunicator.doPost(urlPath, request, null, FollowedSalesResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }*/
    }

    /**
     * Performs a getFollowingFollowers operation on our server.
     *
     * @param request contains all information needed to perform the operation.
     * @return the response.
     */
    public FollowingFollowersResponse getFollowingFollowers(FollowingFollowersRequest request, String urlPath) throws IOException, TweeterRemoteException {
        //Log.i(LOG_TAG, "serverFacade:getFollowingFollowers");
        FollowingFollowersResponse response = clientCommunicator.doPost(urlPath, request, null, FollowingFollowersResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }
    }

    /*public SignOutResponse checkAuthorized(SignOutRequest request, String urlPath) throws IOException, TweeterRemoteException {
        SignOutResponse response = clientCommunicator.doPost(urlPath, request, null, SignOutResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }
    }*/
}
