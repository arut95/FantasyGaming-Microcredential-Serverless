package mobile.gaming;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

//        // Just for testing
//        VotingHandler votingHandler = new VotingHandler();
//        APIGatewayProxyRequestEvent input = new APIGatewayProxyRequestEvent();
//        input.setBody("{\"playerId\": 1}");
//        input.setHttpMethod("POST");
//        input.setResource("/vote");
////        Context context = (Context) new APIGatewayProxyRequestEvent.ProxyRequestContext();
//        Context context = null;
//        APIGatewayProxyResponseEvent output = votingHandler.handleRequest(input, context);
//        System.out.println(output.getBody());
    }

}