package com.howmuchismyadaworth.api.routes;

import com.google.gson.Gson;
import com.howmuchismyadaworth.api.InstantValueUpdateThread;
import com.howmuchismyadaworth.api.Store;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;

public class GetValueRoute implements Route {

    @Override
    public Object handle(Request req, Response res) throws Exception {
        res.header("Content-Type", "application/json");
        String currency = req.params(":currency");
        if (currency == null) {
            currency = "USD";
        }
        else {
            currency = currency.toUpperCase();
        }
        Gson gson = new Gson();
        HashMap<String, Object> responseMap = new HashMap<>();
        if (!Store.currenciesContains(currency)) {
            responseMap.put("response", "error");
            responseMap.put("error", "currency_not_found");
            return gson.toJson(responseMap);
        }
        if (Store.getValue(currency) == -1d) {
            new InstantValueUpdateThread(currency).run();
        }
        responseMap.put("value", Store.getValue(currency));
        return gson.toJson(responseMap);
    }

}
