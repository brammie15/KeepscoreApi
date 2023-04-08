package dev.brammie15.route;

import spark.Request;
import spark.Response;

public interface KeepscoreRoute {

    String PATH();

    byte FUNCTIONS();

    Object GET(Request req, Response res);

    Object POST(Request req, Response res);

    Object PUT(Request req, Response res);

    Object DELETE(Request req, Response res);
}
