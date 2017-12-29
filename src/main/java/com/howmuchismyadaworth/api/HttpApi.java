package com.howmuchismyadaworth.api;

import com.howmuchismyadaworth.api.routes.GetValueRoute;
import spark.Spark;

public class HttpApi {

    public static void main(String[] args) {
        new ValueUpdateThread().start();
        Spark.get("/", ((request, response) -> "<a href=\"https://howmuchismyadaworth.com/\">https://howmuchismyadaworth.com/</a>"));
        Spark.get("/v1/value/:currency", new GetValueRoute());
    }

}
