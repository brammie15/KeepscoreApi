package dev.brammie15.route;

import dev.brammie15.Constants;
import spark.Request;
import spark.Response;

public class HealthRoute implements KeepscoreRoute {

    @Override
    public String PATH() {
        return "/health";
    }

    @Override
    public byte FUNCTIONS() {
        return Constants.GET | Constants.POST | Constants.PUT | Constants.DELETE;
    }

    @Override
    public Object GET(Request req, Response res) {
        return "GET OK";
    }

    @Override
    public Object POST(Request req, Response res) {
        return "POST OK";
    }

    @Override
    public Object PUT(Request req, Response res) {
        return "PUT OK";
    }

    @Override
    public Object DELETE(Request req, Response res) {
        return "DELETE OK";
    }
}
