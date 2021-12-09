package edu.byu.cs.tweeter.shared.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.FollowedSalesRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowedSalesResponse;

/**
 * Contains the business logic to support the story and feed operations
 */
public interface YardSaleService {

    /*
     * Returns the statuses associated with a given user
     *
     * @param statusListRequest contains the data required to fulfill the request.
     * @return the status list
     */
    FollowedSalesResponse getYardSales(FollowedSalesRequest yardSaleRequest) throws IOException, TweeterRemoteException;
}
