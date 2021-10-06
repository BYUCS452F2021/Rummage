package edu.byu.cs.tweeter.shared.model.service.request;

import java.util.List;

public class SQSPostJobBatchRequest {
    SQSPostRequest sqsPostRequest;
    List<String> batch;

    public SQSPostJobBatchRequest() {

    }

    public SQSPostJobBatchRequest(SQSPostRequest sqsPostRequest, List<String> batch) {
        this.sqsPostRequest = sqsPostRequest;
        this.batch = batch;
    }

    public SQSPostRequest getSqsPostRequest() {
        return sqsPostRequest;
    }

    public void setSqsPostRequest(SQSPostRequest sqsPostRequest) {
        this.sqsPostRequest = sqsPostRequest;
    }

    public List<String> getBatch() {
        return batch;
    }

    public void setBatch(List<String> batch) {
        this.batch = batch;
    }
}
