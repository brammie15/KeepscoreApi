package dev.brammie15.route;

import com.google.gson.reflect.TypeToken;
import dev.brammie15.Constants;
import dev.brammie15.objects.Class;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class HealthRoute implements KeepscoreRoute {

    @Override
    public String PATH() {
        return "/health";
    }

    @Override
    public Object GET(Request req, Response res) {
        return "OK";
    }

    @Override
    public Object POST(Request req, Response res) {
        return "OK";
    }

    @Override
    public Object PUT(Request req, Response res) {
        return null;
    }

    @Override
    public Object DELETE(Request req, Response res) {
        return null;
    }
}
