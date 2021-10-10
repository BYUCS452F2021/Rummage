package edu.byu.cs.tweeter.client.model.net;

import java.io.IOException;

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.FollowCountRequest;
import edu.byu.cs.tweeter.shared.model.service.request.FollowingFollowersRequest;
import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
import edu.byu.cs.tweeter.shared.model.service.request.RelationshipChangeRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
import edu.byu.cs.tweeter.shared.model.service.request.UserRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowCountResponse;
import edu.byu.cs.tweeter.shared.model.service.response.FollowingFollowersResponse;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.shared.model.service.response.PostResponse;
import edu.byu.cs.tweeter.shared.model.service.response.RelationshipChangeResponse;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;
import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;
import edu.byu.cs.tweeter.shared.model.service.response.UserResponse;

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

    /**
     * Performs a login and if successful, returns the logged in user and an auth token.
     *
     * @param request contains all information needed to perform a login.
     * @return the login response.
     */
    public LoginResponse signIn(SignInRequest request, String urlPath) throws IOException, TweeterRemoteException {
        //Log.i(LOG_TAG, "serverFacade:signIn");
        LoginResponse response = clientCommunicator.doPost(urlPath, request, null, LoginResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }
    }

    /**
     * Performs a signUp and if successful, returns the logged in user and an auth token.
     *
     * @param request contains all information needed to perform a signup.
     * @return the login response.
     */
    public LoginResponse signUp(SignUpRequest request, String urlPath) throws IOException, TweeterRemoteException {
        //Log.i(LOG_TAG, "serverFacade:signUp");
        LoginResponse response = clientCommunicator.doPost(urlPath, request, null, LoginResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }
    }

    /**
     * Performs a signOut operation on our server.
     *
     * @param request contains all information needed to perform the operation.
     * @return the response.
     */
    public SignOutResponse signOut(SignOutRequest request, String urlPath) throws IOException, TweeterRemoteException {
        //Log.i(LOG_TAG, "serverFacade:signOut");
        SignOutResponse response = clientCommunicator.doPost(urlPath, request, null, SignOutResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }
    }

    /**
     * Performs a post operation on our server.
     *
     * @param request contains all information needed to perform the operation.
     * @return the response.
     */
    public PostResponse post(PostRequest request, String urlPath) throws IOException, TweeterRemoteException {
        //Log.i(LOG_TAG, "serverFacade:post");
        PostResponse response = clientCommunicator.doPost(urlPath, request, null, PostResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }
    }

    /**
     * Performs a getFollowCount operation on our server.
     *
     * @param request contains all information needed to perform the operation.
     * @return the response.
     */
    public FollowCountResponse getFollowCount(FollowCountRequest request, String urlPath) throws IOException, TweeterRemoteException {
        //Log.i(LOG_TAG, "serverFacade:getFollowCount");
        FollowCountResponse response = clientCommunicator.doPost(urlPath, request, null, FollowCountResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }
    }

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
    public StatusListResponse getStatuses(StatusListRequest request, String urlPath) throws IOException, TweeterRemoteException {
        //Log.i(LOG_TAG, "serverFacade:getStatuses");
        StatusListResponse response = clientCommunicator.doPost(urlPath, request, null, StatusListResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }
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

    public SignOutResponse checkAuthorized(SignOutRequest request, String urlPath) throws IOException, TweeterRemoteException {
        SignOutResponse response = clientCommunicator.doPost(urlPath, request, null, SignOutResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new TweeterServerException(response.getMessage(), null, null);
        }
    }
}