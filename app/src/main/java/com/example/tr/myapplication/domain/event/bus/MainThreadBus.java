package com.example.tr.myapplication.domain.event.bus;

import android.os.Handler;
import android.os.Looper;

import com.example.tr.myapplication.utility.LumberJack;
import com.squareup.otto.Bus;

public class MainThreadBus extends Bus {
    private final Handler mainThread = new Handler(Looper.getMainLooper());

    private static volatile MainThreadBus sSoleInstance;

    //private constructor.
    private MainThreadBus(){

    }

    public static MainThreadBus getInstance() {
        //Double check locking pattern
        if (sSoleInstance == null) { //Check for the first time
            synchronized (MainThreadBus.class) {   //Check for the second time.
                //if there is no instance available... create new one
                if (sSoleInstance == null) sSoleInstance = new MainThreadBus();
            }
        }
        return sSoleInstance;
    }

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    LumberJack.logEvent(event.toString());
                    MainThreadBus.super.post(event);
                }
            });
        }
    }
}
