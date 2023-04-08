package dev.brammie15.route;

import com.google.gson.Gson;
import dev.brammie15.Constants;
import dev.brammie15.Data;
import dev.brammie15.objects.Class;
import dev.brammie15.response.StandardResponse;
import dev.brammie15.response.StatusResponse;
import dev.brammie15.service.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.List;

public class ClassAnyRoute implements KeepscoreRoute {
    Data data;
    Logger logger = LoggerFactory.getLogger(ClassAnyRoute.class);

    public ClassAnyRoute(Data d) {
        this.data = d;
    }

    @Override
    public String PATH() {
        return "/class";
    }

    @Override
    public byte FUNCTIONS() {
        return Constants.GET | Constants.POST;
    }


    @Override
    public Object GET(Request req, Response res) {
        List<Class> classes = null;
        try {
            classes = ServiceUtils.getAll(Class.class, data.getConnection().openSession());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new StandardResponse(StatusResponse.ERROR, e.getMessage());
        }
        return new StandardResponse(StatusResponse.SUCCESS, Constants.GSON.toJsonTree(classes));
    }

    @Override
    public Object POST(Request req, Response res) {
        Object o = Constants.validateFields(new Class(), req.body());
        if(o != null) {
            return o;
        }

        Class newClass = new Gson().fromJson(req.body(), Class.class);
        if(newClass == null) {
            return new StandardResponse(StatusResponse.ERROR, "Invalid JSON");
        }
        try {
            ServiceUtils.add(newClass, data.getConnection().openSession());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new StandardResponse(StatusResponse.ERROR, e.getMessage());
        }
        return new StandardResponse(StatusResponse.SUCCESS);
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
