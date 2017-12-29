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
            String origin = "https://howmuchismyadaworth.com";
            String requestOrigin = request.headers("Origin");
            if (requestOrigin == null) {
                requestOrigin = "";
            }
            if (requestOrigin.endsWith("://howmuchismyadaworth.com") || requestOrigin.endsWith("://www.howmuchismyadaworth.com")) {
                origin = requestOrigin;
            }
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", "*");
            response.header("Access-Control-Allow-Headers", "*");
        });
    }

}
