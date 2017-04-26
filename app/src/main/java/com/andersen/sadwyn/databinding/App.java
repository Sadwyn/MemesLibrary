package com.andersen.sadwyn.databinding;

import android.app.Application;

import com.andersen.sadwyn.databinding.data.Api;
import com.andersen.sadwyn.databinding.data.ApiFactory;


public class App extends Application {

    private static Api api;

    public static Api getApi() {
        return api;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (api == null) {
            api = ApiFactory.create();
        }
    }
}
