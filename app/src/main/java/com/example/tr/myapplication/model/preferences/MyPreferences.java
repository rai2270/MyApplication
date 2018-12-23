package com.example.tr.myapplication.model.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tr.myapplication.MyApplication;
import com.example.tr.myapplication.utility.Util;

import java.util.UUID;


public class MyPreferences {
    private static final String PREFERENCES_ID = "my.preferences";

    /* Preference keys stored */
    private static final String DEVICE_ID = "deviceId";

    private SharedPreferences _preferences;

    private static volatile MyPreferences sSoleInstance;

    //private constructor.
    private MyPreferences(){
        _preferences = MyApplication.getInstance().getApplicationContext().getSharedPreferences(PREFERENCES_ID, Context.MODE_PRIVATE);
    }

    public static MyPreferences getInstance() {
        //Double check locking pattern
        if (sSoleInstance == null) { //Check for the first time
            synchronized (MyPreferences.class) {   //Check for the second time.
                //if there is no instance available... create new one
                if (sSoleInstance == null) sSoleInstance = new MyPreferences();
            }
        }
        return sSoleInstance;
    }

    public void clearAll() {
        _preferences.edit().clear().apply();
    }

    public String getDeviceId() {
        String deviceId = _preferences.getString(DEVICE_ID, "");
        if (Util.isEmpty(deviceId)) {
            deviceId = UUID.randomUUID().toString();
            setDeviceId(deviceId);
        }
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        _preferences.edit().putString(DEVICE_ID, deviceId).apply();
    }

}