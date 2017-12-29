package com.howmuchismyadaworth.api;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class InstantValueUpdateThread extends Thread {

    private String currency;

    public InstantValueUpdateThread(String currency) {
        this.currency = currency;
    }

    @Override
    public void run() {
        if (!Store.currenciesContains(this.currency)) {
            return;
        }
        Gson gson = new Gson();
        ArrayList<HashMap<String, String>> data;
        data = gson.fromJson(HttpRequest.get("https://api.coinmarketcap.com/v1/ticker/cardano/?convert=" + this.currency).body(),
                new TypeToken<ArrayList<HashMap<String, String>>>(){}.getType());

        String value = data.get(0).get("price_" + this.currency.toLowerCase());
        System.out.println(this.currency + "@" + value);

        Store.putValue(this.currency, Double.parseDouble(value));
    }

}
