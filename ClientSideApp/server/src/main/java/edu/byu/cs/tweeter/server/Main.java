package edu.byu.cs.tweeter.server;

import static spark.Spark.*;
import com.google.gson.Gson;

import edu.byu.cs.tweeter.server.lambda.SignInHandler;
import edu.byu.cs.tweeter.shared.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

public class Main {
    public static void main(String[] args) {
        // initialize server objects
        Gson gson = new Gson();

        // handlers
        SignInHandler signInHandler = new SignInHandler();

        post("/login", (request, response) -> {
            SignInRequest signInRequest = gson.fromJson(request.body(), SignInRequest.class);
            LoginResponse userResponse = signInHandler.handleRequest(signInRequest);
            return gson.toJson(userResponse);
        });
    }
}