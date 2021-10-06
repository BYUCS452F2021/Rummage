package edu.byu.cs.tweeter.shared.model.service.response;

public class PostResponse extends Response {

    public PostResponse() {}

    public PostResponse(boolean success) {
        super(success);
    }

    public PostResponse(boolean success, String message) {
        super(success, message);
    }

    public PostResponse(String message) {
        super(false, message);
    }

}
