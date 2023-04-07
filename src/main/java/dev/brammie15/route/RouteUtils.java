package dev.brammie15.route;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import dev.brammie15.Constants;
import dev.brammie15.response.StandardResponse;
import dev.brammie15.response.StatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class RouteUtils {

    public static <T> Object standardGetMethod(Request req, Response res, Dao<T, Integer> dao) {
        Logger logger = LoggerFactory.getLogger(RouteUtils.class);
        T obj = null;
        int id = Integer.parseInt(req.params(":id"));

        try {
            obj = dao.queryForId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new StandardResponse(StatusResponse.ERROR, e.getMessage());
        }

        if (obj == null)
            return new StandardResponse(StatusResponse.ERROR, "No object found with id " + req.params(":id"));
        return new StandardResponse(StatusResponse.SUCCESS, Constants.GSON.toJsonTree(obj));
    }

    public static <T> Object standardPostMethod(Request req, Response res, Dao<T, Integer> dao) {
        Logger logger = LoggerFactory.getLogger(RouteUtils.class);
        T newClass = new Gson().fromJson(req.body(), (Class<T>) dao.getDataClass());
        try {
            dao.create(newClass);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return new StandardResponse(StatusResponse.ERROR, e.getMessage());
        }
        return new StandardResponse(StatusResponse.SUCCESS);
    }

}
