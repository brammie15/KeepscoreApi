package dev.brammie15.route;

import dev.brammie15.Data;
import spark.Request;
import spark.Response;

public interface KeepscoreRoute {

    public String PATH();

    public byte FUNCTIONS();

    public Object GET(Request req, Response res);

    public Object POST(Request req, Response res);

    public Object PUT(Request req, Response res);

    public Object DELETE(Request req, Response res);
}
