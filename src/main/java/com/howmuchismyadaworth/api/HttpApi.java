package com.howmuchismyadaworth.api;

import com.howmuchismyadaworth.api.routes.GetValueRoute;
import spark.Spark;

public class HttpApi {

    public static void main(String[] args) {
        int port = 4567;
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }
        new ValueUpdateThread().start();
        Spark.port(port);
        Spark.get("/", ((request, response) -> "<a href=\"https://howmuchismyadaworth.com/\">https://howmuchismyadaworth.com/</a>"));
        Spark.get("/v1/value/:currency", new GetValueRoute());
        Spark.options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        Spark.before((request, response) -> {
            String origin = "https://howmuchismyadaworth.com/";
            if (request.headers("Origin").contains("://howmuchismyadaworth.com/") || request.headers("Origin").contains("://www.howmuchismyadaworth.com/")) {
                origin = request.headers("Origin");
            }
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", "*");
            response.header("Access-Control-Allow-Headers", "*");
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }

}
