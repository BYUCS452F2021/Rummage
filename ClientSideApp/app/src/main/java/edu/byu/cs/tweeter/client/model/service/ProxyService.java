package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;

/**
 * A parent class for services that get data from the {@link ServerFacade}.
 */
public abstract class ProxyService {

    /**
     * Returns an instance of {@link ServerFacade}. Allows mocking of the ServerFacade class for
     * testing purposes. All usages of ServerFacade should get their ServerFacade instance from this
     * method to allow for proper mocking.
     *
     * @return the {@link ServerFacade} instance.
     */
    public ServerFacade getServerFacade() {
        return new ServerFacade();
    }

}
