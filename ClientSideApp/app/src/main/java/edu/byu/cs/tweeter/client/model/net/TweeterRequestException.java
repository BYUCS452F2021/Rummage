package edu.byu.cs.tweeter.client.model.net;
import java.util.List;

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;

public class TweeterRequestException extends TweeterRemoteException {

    public TweeterRequestException(String message, String remoteExceptionType, List<String> remoteStakeTrace) {
        super(message, remoteExceptionType, remoteStakeTrace);
    }
}