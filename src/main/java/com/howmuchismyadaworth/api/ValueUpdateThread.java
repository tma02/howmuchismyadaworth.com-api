package com.howmuchismyadaworth.api;

import static com.howmuchismyadaworth.api.Store.CURRENCIES;

public class ValueUpdateThread extends Thread {

    @Override
    public void run() {
        for (;;) {
            for (int i = 0; i < CURRENCIES.length; i++) {
                new InstantValueUpdateThread(CURRENCIES[i]).start();
                try {
                    Thread.sleep(15L * 1000L);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
