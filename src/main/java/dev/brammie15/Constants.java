package dev.brammie15;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Filter;
import spark.Request;
import spark.Response;

public class Constants {
    private Constants() {}

    public static final String API_VERSION = "v1";

    public static final Gson GSON = new Gson();

    public static Filter requestToJson = (Request request, Response response) -> {
        Logger logger = LoggerFactory.getLogger("RequestLogger");
        logger.info(request.requestMethod() + " request to " + request.pathInfo());
        String jsonData = GSON.toJson(request.body());
        request.attribute("jsonBody", jsonData);
        response.type("application/json");
    };
}
