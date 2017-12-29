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
    }

}
