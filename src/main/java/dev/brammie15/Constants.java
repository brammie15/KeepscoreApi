package dev.brammie15;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.brammie15.response.StandardResponse;
import dev.brammie15.response.StatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Filter;
import spark.Request;
import spark.Response;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Constants {
    public static final byte GET = 1;
    public static final byte POST = 2;
    public static final byte PUT = 4;
    public static final byte DELETE = 8;

    public static final String[] IGNORED_FIELDS = new String[] {
            "id",
            "createdAt",
            "updatedAt"
    };
    //TODO: Move validation logic to separate class
    public static Field[] filterFields(Field[] fields) {
        return Arrays.stream(fields).filter(field -> {
            for (String ignoredField : IGNORED_FIELDS) {
                if (field.getName().equals(ignoredField)) {
                    return false;
                }
            }
            return true;
        }).toArray(Field[]::new);
    }

    public static <T> Object validateFields(T obj, String body) {
        JsonObject json = Constants.GSON.fromJson(body, JsonObject.class);
        Field[] fields = obj.getClass().getDeclaredFields();
        fields = filterFields(fields);
        for(Field f : fields) {
            if(!json.has(f.getName())) {
                Logger logger = LoggerFactory.getLogger("ValidateLogger");
                logger.info("Missing field: " + f.getName());
                return new StandardResponse(StatusResponse.ERROR, "Missing field: " + f.getName());
            }
        }
        return null;
    }


    private Constants() {}

    public static final String API_VERSION = "v1";
    //TODO: Make the version actually mean something

    public static final Gson GSON = new Gson();

    public static Filter requestToJson = (Request request, Response response) -> {
        Logger logger = LoggerFactory.getLogger("RequestLogger");
        logger.info(request.requestMethod() + " request to " + request.pathInfo());
        String jsonData = GSON.toJson(request.body());
        request.attribute("jsonBody", jsonData);
        response.type("application/json");
    };
}
