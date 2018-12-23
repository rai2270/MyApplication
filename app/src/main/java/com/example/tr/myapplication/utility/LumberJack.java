package com.example.tr.myapplication.utility;

import android.os.Build;
import android.util.Log;

import com.example.tr.myapplication.MyApplication;
import com.example.tr.myapplication.BuildConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class LumberJack {
    private static final String APPLICATION_LOG_TAG = "myapp";
    private static final String EVENT_LOG_TAG = "myapp-Event";

    public static void logGeneric(String msg) {
        if (BuildConfig.ADB_LOGGING && msg != null) {
            Log.i(APPLICATION_LOG_TAG, msg);
        }
    }

    public static void logGeneric(String tag, String msg) {
        if (BuildConfig.ADB_LOGGING && msg != null) {
            Log.i(tag, msg);
        }
    }

    public static void logEvent(String msg) {
        if (BuildConfig.ADB_LOGGING && msg != null) {
            Log.i(EVENT_LOG_TAG, msg);
        }
    }

    public static void logFile(String msg) {
        if (BuildConfig.ADB_LOGGING && msg != null) {
            logGeneric(msg);
            try {
                File dir = MyApplication.getInstance().getExternalFilesDir(null);
                if (dir == null) {
                    return; // external dir not provided, stopping
                }

                File file = new File(dir, APPLICATION_LOG_TAG + ".log");
                if (!file.exists()) {
                    if (!file.createNewFile()) {
                        return; // file not created stop here
                    }
                }

                PrintWriter pw = new PrintWriter(new FileWriter(file, true));
                pw.println((new Date()) + " " + msg);
                pw.close();
            } catch (IOException ex) {
                // ignore the exception for devices that cannot save the file
            }
        }
    }


    private static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return Util.capitalizeFirst(model);
        } else {
            return Util.capitalizeFirst(manufacturer) + "-" + model;
        }
    }

}
