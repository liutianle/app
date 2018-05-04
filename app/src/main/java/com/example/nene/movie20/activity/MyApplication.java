package com.example.nene.movie20.activity;

import android.app.Application;
import android.content.Context;

/**
 * Created by nene on 2018/5/2.
 */

public class MyApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }

}
