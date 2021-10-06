package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.client.util.ByteArrayUtils;
import edu.byu.cs.tweeter.shared.model.domain.Status;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.StatusListService;
import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;

/**
 * Contains the business logic to support the story and feed operations
 */
public class StatusListProxyService extends ProxyService implements StatusListService {
    public static final String LOG_TAG = "Status_List_Proxy";

    /*
     * Returns the StatusListResponse given by a StatusListRequest
     * @param statusListRequest
     * @ret StatusListResponse
     */
    public StatusListResponse getStatuses(StatusListRequest statusListRequest) throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        StatusListResponse statusListResponse;
        //Log.i(LOG_TAG, statusListRequest.toString());
        statusListResponse = serverFacade.getStatuses(statusListRequest, "/getstatuslist");

        if(statusListResponse.isSuccess()) {
            loadImages(statusListResponse);
        }

        return statusListResponse;
    }
    
    /**
     * Loads the profile image data for each status included in the response.
     *
     * @param response the response from the StatusListResponse request.
     */
    private void loadImages(StatusListResponse response) throws IOException {
        for(Status status : response.getStatusList()) {
            byte [] bytes = ByteArrayUtils.bytesFromUrl(status.getPoster().getImageUrl());
            status.getPoster().setImageBytes(bytes);
        }
    }

    /**
     * Returns an instance of {@link ServerFacade}. Allows mocking of the ServerFacade class for
     * testing purposes. All usages of ServerFacade should get their ServerFacade instance from this
     * method to allow for proper mocking.
     *
     * @return the instance.
     */
    public ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
