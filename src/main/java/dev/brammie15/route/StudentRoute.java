package dev.brammie15.route;

import dev.brammie15.Constants;
import dev.brammie15.Data;
import dev.brammie15.objects.Student;
import dev.brammie15.response.StandardResponse;
import dev.brammie15.response.StatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

import static dev.brammie15.route.RouteUtils.standardGetMethod;

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
    public Object GET(Request req, Response res) {
        return standardGetMethod(req, res, data.getStudentDao());
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
