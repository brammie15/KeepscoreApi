package dev.brammie15;

import dev.brammie15.route.*;

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
        //TODO: Maybe merge the Any routes into the other routes?
        KeepscoreRoute[] routes = {
                new HealthRoute(),
                new ClassIdRoute(d),
                new ClassAnyRoute(d),
                new StudentRoute(d),
                new StudentAnyRoute(d),
        };
        for (KeepscoreRoute route : routes) {
            before(route.PATH(), Constants.requestToJson);
            if((route.FUNCTIONS() & Constants.GET) == Constants.GET)
                get(route.PATH(), route::GET, Constants.GSON::toJson);
            if((route.FUNCTIONS() & Constants.POST) == Constants.POST)
                post(route.PATH(), route::POST, Constants.GSON::toJson);
            if((route.FUNCTIONS() & Constants.PUT) == Constants.PUT)
                put(route.PATH(), route::PUT, Constants.GSON::toJson);
            if((route.FUNCTIONS() & Constants.DELETE) == Constants.DELETE)
                delete(route.PATH(), route::DELETE, Constants.GSON::toJson);
        }
    }
}
