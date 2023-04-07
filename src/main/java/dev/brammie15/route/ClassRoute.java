package dev.brammie15.route;

import com.google.gson.Gson;
import dev.brammie15.Constants;
import dev.brammie15.Data;
import dev.brammie15.objects.Class;
import dev.brammie15.response.StandardResponse;
import dev.brammie15.response.StatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.Objects;

public class ClassRoute implements KeepscoreRoute {
    public Data data;

    Logger logger = LoggerFactory.getLogger(ClassRoute.class);

    public ClassRoute(Data d) {
        data = d;
    }

    @Override
    public String PATH() {
        return "/class/:id";
    }

    @Override
    public Object GET(Request req, Response res) {
        return RouteUtils.standardGetMethod(req, res, data.getClassDao());
    }

    @Override
    public Object POST(Request req, Response res) {
        return RouteUtils.standardPostMethod(req, res, data.getClassDao());
    }

    @Override
    public Object PUT(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        Class newClass = new Gson().fromJson(req.body(), Class.class);
        newClass.setId(id);
        try {
            data.getClassDao().update(newClass);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return new StandardResponse(StatusResponse.ERROR, e.getMessage());
        }
        return new StandardResponse(StatusResponse.SUCCESS);
    }

    @Override
    public Object DELETE(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        try {
            data.getClassDao().deleteById(id);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return new StandardResponse(StatusResponse.ERROR, e.getMessage());
        }
        return new StandardResponse(StatusResponse.SUCCESS);
    }
}
