package mobile.gaming;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VotingHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        try {
            return handleVotingRequest(input, response);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setBody(e.getMessage() + " " + e.getLocalizedMessage());
            return response;
        }
    }

    private static APIGatewayProxyResponseEvent handleVotingRequest(APIGatewayProxyRequestEvent input,
                                                                    APIGatewayProxyResponseEvent response)
            throws JsonProcessingException {
        String httpMethod = input.getHttpMethod();
        String resource = input.getResource();
        String body = input.getBody();
        String username = "user1"; // TODO: Username should be retrieved from the request and validated

        if ("POST".equals(httpMethod) && "/vote".equals(resource)) {
            JsonNode jsonNode = objectMapper.readTree(body);
            return voteForPlayer(response, jsonNode, username);
        }

        if ("GET".equals(httpMethod) && "/vote".equals(resource)) {
            response.setStatusCode(200);
            response.setBody(objectMapper.writeValueAsString(Voting.getVoteResults()));
            return response;
        }

        // TODO: Implement other required features

        return null;
    }

    /*
        * Example request body:
        * {
        *   "playerId": 1
        * }
     */
    private static APIGatewayProxyResponseEvent voteForPlayer(APIGatewayProxyResponseEvent response, JsonNode jsonNode,
                                                              String username)
            throws JsonProcessingException {
        JsonNode playerIdNode = jsonNode.get("playerId");
        if (playerIdNode == null || !playerIdNode.isInt()) {
            response.setStatusCode(400);
            response.setBody("Missing playerId");
            return response;
        }

        Voting.castVote(playerIdNode.intValue(), username);

        String result = objectMapper.writeValueAsString(Voting.getVoteResults());
        response.setStatusCode(200);
        response.setBody("Updated votes: " + result);
        return response;
    }

//    private void printAllInputs(APIGatewayProxyRequestEvent input, Context context) throws JsonProcessingException {
//        LambdaLogger logger = context.getLogger();
//        String httpMethod = input.getHttpMethod();
//        String resource = input.getResource();
//        String path = input.getPath();
//        String body = input.getBody();
//        Map<String, String> pathParameters = input.getPathParameters();
//        Map<String, String> queryStringParameters = input.getQueryStringParameters();
//
//        logger.log("httpMethod: " + httpMethod);
//        logger.log("resource: " + resource);
//        logger.log("path: " + path);
//        logger.log("body: " + body);
//        if (pathParameters != null)
//            for (Map.Entry<String, String> entry : pathParameters.entrySet())
//                logger.log("pathParameters: " + entry.getKey() + " = " + entry.getValue());
//        if (queryStringParameters != null)
//            for (Map.Entry<String, String> entry : queryStringParameters.entrySet())
//                logger.log("queryStringParameters: " + entry.getKey() + " = " + entry.getValue());
//    }
//
//    private void printAllInputs2(APIGatewayProxyRequestEvent input) throws JsonProcessingException {
//        String httpMethod = input.getHttpMethod();
//        String resource = input.getResource();
//        String path = input.getPath();
//        String body = input.getBody();
//        Map<String, String> pathParameters = input.getPathParameters();
//        Map<String, String> queryStringParameters = input.getQueryStringParameters();
//
//        System.out.println("httpMethod: " + httpMethod);
//        System.out.println("resource: " + resource);
//        System.out.println("path: " + path);
//        System.out.println("body: " + body);
//        if (pathParameters != null)
//            for (Map.Entry<String, String> entry : pathParameters.entrySet())
//                System.out.println("pathParameters: " + entry.getKey() + " = " + entry.getValue());
//        if (queryStringParameters != null)
//            for (Map.Entry<String, String> entry : queryStringParameters.entrySet())
//                System.out.println("queryStringParameters: " + entry.getKey() + " = " + entry.getValue());
//
//        JsonNode jsonNode = objectMapper.readTree(body);
//        if (jsonNode != null)
//            for (JsonNode node : jsonNode)
//                if (node != null && node.isTextual())
//                    System.out.println("jsonNode: " + node.asText());
//    }

}
