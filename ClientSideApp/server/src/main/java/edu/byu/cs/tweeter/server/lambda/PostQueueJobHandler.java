package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.google.gson.Gson;

import edu.byu.cs.tweeter.server.dao.FeedDAO;
import edu.byu.cs.tweeter.shared.model.service.request.SQSPostJobBatchRequest;


public class PostQueueJobHandler implements RequestHandler<SQSEvent, Void> {

    @Override
    public Void handleRequest(SQSEvent event, com.amazonaws.services.lambda.runtime.Context context) {
        for (SQSEvent.SQSMessage msg : event.getRecords()) {
            SQSPostJobBatchRequest sqsPostJobBatchRequest = (new Gson()).fromJson(msg.getBody(), SQSPostJobBatchRequest.class);

            try {
                getFeedDAO().createPostBatch(sqsPostJobBatchRequest.getSqsPostRequest(), sqsPostJobBatchRequest.getBatch());
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Updating these User's Feeds:");
            System.out.println(sqsPostJobBatchRequest.getBatch());
            //System.out.println(msg.getBody());
            if (sqsPostJobBatchRequest.getBatch().size() < 25) {
                System.out.println("Finished posting batch");
            }
        }
        return null;
    }

    private FeedDAO getFeedDAO(){ return new FeedDAO(); }
}
