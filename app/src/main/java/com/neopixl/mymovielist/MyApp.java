package com.neopixl.mymovielist;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.neopixl.mymovielist.network.LruBitmapCache;

public class MyApp extends Application {

    private static MyApp sharedInstance;

    private RequestQueue mVolleyRequestQueue;
    private ImageLoader mVolleyImageLoader;


    @Override
    public void onCreate() {
        super.onCreate();

        MyApp.sharedInstance = this;

        //Création de la queue
        mVolleyRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mVolleyImageLoader = new ImageLoader(mVolleyRequestQueue, new LruBitmapCache(1024 * 1024 * 1));

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
