package dev.brammie15.route;

import dev.brammie15.Constants;
import dev.brammie15.Data;
import dev.brammie15.objects.Student;
import dev.brammie15.response.StandardResponse;
import dev.brammie15.response.StatusResponse;
import dev.brammie15.service.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;


public class StudentRoute implements KeepscoreRoute{
    public Data data;

    Logger logger = LoggerFactory.getLogger(StudentRoute.class);
    public StudentRoute(Data d) {
        data = d;
    }
    @Override
    public String PATH() {
        return "/student/:id";
    }

    @Override
    public byte FUNCTIONS() {
        return Constants.GET | Constants.PUT | Constants.DELETE;
    }

    @Override
    public Object GET(Request req, Response res) {
        Student s = null;
        try {
            s = ServiceUtils.getById(req.params(":id"), Student.class, data.getConnection().openSession());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new StandardResponse(StatusResponse.ERROR, e.getMessage());
        }
        return new StandardResponse(StatusResponse.SUCCESS, Constants.GSON.toJsonTree(s));
    }

    @Override
    public Object POST(Request req, Response res) {
        return null;
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
