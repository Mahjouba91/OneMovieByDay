package com.neopixl.mymovielist;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyApp extends Application {

    private static MyApp sharedInstance;

    private RequestQueue mVolleyRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        MyApp.sharedInstance = this;

        //Création de la queue
        mVolleyRequestQueue = Volley.newRequestQueue(getApplicationContext());

        //Démarrage de la queue
        mVolleyRequestQueue.start();

    }

    public static MyApp getInstance() {
        return sharedInstance;
    }

    public RequestQueue getRequestQueue() {
        return mVolleyRequestQueue;
    }
}
