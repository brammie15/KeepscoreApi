package dev.brammie15.route;

import com.google.gson.JsonObject;
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

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class StudentAnyRoute implements KeepscoreRoute{
    Data data;
    Logger logger = LoggerFactory.getLogger(StudentAnyRoute.class);
    @Override
    public String PATH() {
        return "/student";
    }

    @Override
    public byte FUNCTIONS() {
        return Constants.GET | Constants.POST;
    }

    public StudentAnyRoute(Data d) {
        this.data = d;
    }

    @Override
    public Object GET(Request req, Response res) {
        List<Student> students = null;
        try {
            students = ServiceUtils.getAll(Student.class, data.getConnection().openSession());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new StandardResponse(StatusResponse.ERROR, e.getMessage());
        }
        return new StandardResponse(StatusResponse.SUCCESS, Constants.GSON.toJsonTree(students));
    }

    @Override
    public Object POST(Request req, Response res) {
        Object o = Constants.validateFields(new Student(), req.body());
        if(o != null) {
            return o;
        }

        Student newStudent = Constants.GSON.fromJson(req.body(), Student.class);
        if(newStudent == null) {
            return new StandardResponse(StatusResponse.ERROR, "Invalid JSON");
        }

        try {
            ServiceUtils.add(newStudent, Student.class, data.getConnection().openSession());
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
