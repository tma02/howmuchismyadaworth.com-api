package com.howmuchismyadaworth.api;

import java.util.HashMap;

public class Store {

    public static final String[] CURRENCIES = { "AUD", "BRL", "CAD", "CHF", "CLP", "CNY", "CZK", "DKK", "EUR", "GBP", "HKD", "HUF", "IDR", "ILS",
            "INR", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PKR", "PLN", "RUB", "SEK", "SGD", "THB", "TRY", "TWD", "USD", "ZAR" };

    public static boolean currenciesContains(String currency) {
        for (int i = 0; i < CURRENCIES.length; i++) {
            if (currency.equals(CURRENCIES[i])) {
                return true;
            }
        }
        return false;
    }

    public static final HashMap<String, Double> VALUE_STORE = new HashMap<>();

    public static void putValue(String currency, double value) {
        synchronized (VALUE_STORE) {
            VALUE_STORE.put(currency, value);
        }
    }

    public static double getValue(String currency) {
        synchronized (VALUE_STORE) {
            return VALUE_STORE.get(currency) == null ? -1d : VALUE_STORE.get(currency);
        }
    }

}
