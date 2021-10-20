//package edu.byu.cs.tweeter.server.lambda;
//
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//import com.amazonaws.services.lambda.runtime.events.SQSEvent;
//import com.amazonaws.services.sqs.AmazonSQS;
//import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
//import com.amazonaws.services.sqs.model.SendMessageRequest;
//import com.amazonaws.services.sqs.model.SendMessageResult;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import edu.byu.cs.tweeter.server.dao.FeedDAO;
//import edu.byu.cs.tweeter.server.dao.FollowDAO;
//import edu.byu.cs.tweeter.shared.model.service.request.SQSPostJobBatchRequest;
//import edu.byu.cs.tweeter.shared.model.service.request.SQSPostRequest;
//
//import static java.util.Objects.isNull;
//
//public class PostQueueProcessor implements RequestHandler<SQSEvent, Void> {
//
//    @Override
//    public Void handleRequest(SQSEvent event, com.amazonaws.services.lambda.runtime.Context context) {
//        for (SQSEvent.SQSMessage msg : event.getRecords()) {
//            SQSPostRequest sqsPostRequest = (new Gson()).fromJson(msg.getBody(), SQSPostRequest.class);
//
//            FollowDAO followDAO = getFollowDAO();
//
//            List<String> followees = new ArrayList<>();
//            List<String> toAdd;
//            String lastRetreived = null;
//            do {
//                toAdd = followDAO.getPagedFolloweeAliases(sqsPostRequest.request.getAuthorAlias(), lastRetreived);
//                lastRetreived = toAdd.get(toAdd.size()-1);
//                followees.addAll(toAdd.subList(0, toAdd.size()-1));
//
//            } while (!isNull(lastRetreived));
//
//
//
//            while (followees.size() > 25) {
//                List<String> batch = followees.subList(0, 25);
//                sendBatch(sqsPostRequest, batch);
//                followees = followees.subList(25, followees.size());
//            }
//            sendBatch(sqsPostRequest, followees);
//
//
//            System.out.println("sent all post batches to queue");
//            //System.out.println(msg.getBody());
//        }
//        return null;
//    }
//
//    private void sendBatch(SQSPostRequest sqsPostRequest, List<String> batch) {
//        SQSPostJobBatchRequest messageRequest = new SQSPostJobBatchRequest(sqsPostRequest, batch);
//
//        GsonBuilder builder = new GsonBuilder();
//        builder.serializeNulls();
//        Gson gsonObject = builder.create();
//        String messageBody = gsonObject.toJson(messageRequest);
//
//        String queueUrl = "https://sqs.us-west-2.amazonaws.com/921827367645/PostQueueJobHandler";
//
//        SendMessageRequest send_msg_request = new SendMessageRequest()
//                .withQueueUrl(queueUrl)
//                .withMessageBody(messageBody)
//                .withDelaySeconds(5);
//
//        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
//        SendMessageResult send_msg_result = sqs.sendMessage(send_msg_request);
//
//        String msgId = send_msg_result.getMessageId();
//        System.out.println("Message ID: " + msgId);
//    }
//
//    private FollowDAO getFollowDAO() { return new FollowDAO(); }
//}
