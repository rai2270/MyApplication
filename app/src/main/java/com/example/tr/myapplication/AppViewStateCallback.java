package com.example.tr.myapplication;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.tr.myapplication.utility.Constants;
import com.example.tr.myapplication.utility.LumberJack;


public class AppViewStateCallback implements Application.ActivityLifecycleCallbacks {

    private static AppViewStateCallback instance;

    private Handler mainThreadHandler = null;
    private Runnable stageChangeNotifyProcess = null;

    private AppViewStateCallback() {
        mainThreadHandler = new Handler(Looper.getMainLooper());
        stageChangeNotifyProcess = () -> MyApplication.getInstance().appIsInBackground();


//        stageChangeNotifyProcess = new Runnable() {
//            @Override
//            public void run() {
//                MyApplication.getInstance().appIsInBackground();
//            }
//        };
//
//        stageChangeNotifyProcess = () -> MyApplication.getInstance().appIsInBackground();
    }

    public static void init(Application app) {
        if (instance == null) {
            instance = new AppViewStateCallback();
            app.registerActivityLifecycleCallbacks(instance);
        }
    }

    public static AppViewStateCallback get() {
        return instance;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        mainThreadHandler.removeCallbacks(stageChangeNotifyProcess);
        MyApplication.getInstance().appIsInForeground();
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LumberJack.logGeneric(activity.toString() + " is paused");
        //LocalJobQueue.getInstance().addJobInBackground(...);
        mainThreadHandler.postDelayed(stageChangeNotifyProcess, Constants.APP_BACKGROUND_DECISION_TIMEOUT);
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

}
