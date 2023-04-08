package dev.brammie15.route;

import com.google.gson.Gson;
import dev.brammie15.Constants;
import dev.brammie15.response.StandardResponse;
import dev.brammie15.response.StatusResponse;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class RouteUtils {

    public static <T> Object standardGetMethod(Request req, Response res, SessionFactory session) {
        Logger logger = LoggerFactory.getLogger(RouteUtils.class);
        T obj = null;
        logger.info("Getting object with id " + req.params(":id"));
        int id = Integer.parseInt(req.params(":id"));

        try {
            logger.info("Querying for id " + id);
//            TODO: approach the service layer here
//            obj = dao.queryForId(id);
            logger.info("Object found: " + obj);

        } catch (Exception e) {
            e.printStackTrace();
            return new StandardResponse(StatusResponse.ERROR, e.getMessage());
        }

        if (obj == null)
            return new StandardResponse(StatusResponse.ERROR, "No object found with id " + req.params(":id"));
        logger.info(Constants.GSON.toJsonTree(obj).toString());
        return new StandardResponse(StatusResponse.SUCCESS, Constants.GSON.toJsonTree(obj));
//        logger.info(obj.toString());
//        return new StandardResponse(StatusResponse.SUCCESS, "no");
    }


    public static <T> Object standardPostMethod(Request req, Response res, T dao){
        Logger logger = LoggerFactory.getLogger(RouteUtils.class);
//        T newClass = new Gson().fromJson(req.body(), (Class<T>) dao.getDataClass());
//        try {
//            dao.create(newClass);
//        } catch (SQLException e) {
//            logger.error(e.getMessage());
//            return new StandardResponse(StatusResponse.ERROR, e.getMessage());
//        }
        return new StandardResponse(StatusResponse.SUCCESS);
    }



}
