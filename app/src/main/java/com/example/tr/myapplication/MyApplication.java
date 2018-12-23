package com.example.tr.myapplication;

import android.app.Application;
import android.content.Context;

import com.example.tr.myapplication.di.AppComponent;
import com.example.tr.myapplication.di.DaggerAppComponent;
import com.facebook.soloader.SoLoader;

import net.sqlcipher.database.SQLiteDatabase;

import androidx.multidex.MultiDex;

public class MyApplication extends Application {

    private static MyApplication _appContext = null;

    public MyApplication() {
        _appContext = this;
    }

    public static MyApplication getInstance() {
        return _appContext;
    }

    private AppComponent mAppComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        SoLoader.init(this, false);
        SQLiteDatabase.loadLibs(this);
        AppViewStateCallback.init(this);

        mAppComponent = createMyComponent();

        //InitializeSQLCipher();
    }

    private AppComponent createMyComponent() {
        // Dagger%COMPONENT_NAME%
        return DaggerAppComponent
                .builder()
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

//    private void InitializeSQLCipher() {
//        SQLiteDatabase.loadLibs(this);
//        File databaseFile = getDatabasePath("demo.db");
//        databaseFile.mkdirs();
//        databaseFile.delete();
//        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "test123", null);
//        database.execSQL("create table t1(a, b)");
//        database.execSQL("insert into t1(a, b) values(?, ?)", new Object[]{"one for the money",
//                "two for the show"});
//    }


    public String getVersionString() {
        return BuildConfig.VERSION_NAME;
    }

    public int getVersion() {
        return BuildConfig.VERSION_CODE;
    }


    /* We start a timer for network shutdown to occur bat some later time */
    public void appIsInBackground() {

    }


    public void appIsInForeground() {

    }

}
