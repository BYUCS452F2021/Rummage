package edu.byu.cs.tweeter.client.model.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.net.UnknownHostException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.YardSaleService;
import edu.byu.cs.tweeter.shared.model.service.request.FollowedSalesRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowedSalesResponse;

/**
 * Contains the business logic to support the story and feed operations
 */
public class FollowedSalesProxyService extends ProxyService implements YardSaleService {
    public static final String LOG_TAG = "YardSale_List_Proxy";

    /*
     * Returns the StatusListResponse given by a StatusListRequest
     * @param yardSaleRequest
     * @ret StatusListResponse
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public FollowedSalesResponse getYardSales(FollowedSalesRequest yardSaleRequest) throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        FollowedSalesResponse yardSaleResponse;
        //Log.i(LOG_TAG, yardSaleRequest.toString());
        yardSaleResponse = serverFacade.getFollowedSales(yardSaleRequest, "/getstatuslist");

        /*if(yardSaleResponse.isSuccess()) {
            loadImages(yardSaleResponse);
        }*/

        return yardSaleResponse;
    }
    
    /* *
     * Loads the profile image data for each status included in the response.
     *
     * @param response the response from the StatusListResponse request.
     */
    /*private void loadImages(StatusListResponse response) throws IOException {
        for(Status status : response.getStatusList()) {
            byte [] bytes = ByteArrayUtils.bytesFromUrl(status.getPoster().getImageUrl());
            status.getPoster().setImageBytes(bytes);
        }
    }*/

    /**
     * Returns an instance of {@link ServerFacade}. Allows mocking of the ServerFacade class for
     * testing purposes. All usages of ServerFacade should get their ServerFacade instance from this
     * method to allow for proper mocking.
     *
     * @return the instance.
     */
    public ServerFacade getServerFacade() {
        try {
            return new ServerFacade();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
