package dev.brammie15;

import com.google.gson.JsonObject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import dev.brammie15.objects.Class;
import dev.brammie15.route.ClassRoute;
import dev.brammie15.route.HealthRoute;
import dev.brammie15.route.KeepscoreRoute;

import dev.brammie15.route.StudentRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static spark.Spark.*;

public class Main {
//    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        Logger logger = LoggerFactory.getLogger(Main.class);
        Data d = new Data();
        logger.info("Welcome to keepscore");
        logger.info("Starting server");
        KeepscoreRoute[] routes = {
                new HealthRoute(),
                new ClassRoute(d),
                new StudentRoute(d)
        };
        for (KeepscoreRoute route : routes) {
            before(route.PATH(), Constants.requestToJson);
            get(route.PATH(), route::GET, Constants.GSON::toJson);
            post(route.PATH(), route::POST, Constants.GSON::toJson);
            put(route.PATH(), route::PUT, Constants.GSON::toJson);
            delete(route.PATH(), route::DELETE, Constants.GSON::toJson);
        }
    }
}
