package com.neopixl.mymovielist.network;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.navercorp.volleyextensions.request.JacksonRequest;
import com.neopixl.mymovielist.Model.Movie;
import com.neopixl.mymovielist.Model.MovieJSON;
import com.neopixl.mymovielist.Model.MovieResultsJSON;
import com.neopixl.mymovielist.MyApp;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by fdewasmes on 21/05/15.
 */
public class NetworkAccess {

    public static void searchMovie(String movie){

        String url = "http://api.themoviedb.org/3/search/movie?api_key=588df436490dd63ffeaaaa8747eda2a2&query="+movie;

        JacksonRequest<MovieResultsJSON> request = new
                JacksonRequest<MovieResultsJSON>(Request.Method.GET, url, MovieResultsJSON.class,
                new Response.Listener<MovieResultsJSON>() {
                    @Override
                    public void onResponse(MovieResultsJSON movieResultsJSON) {
                        Intent intent = new Intent("searchResultsEvent");
                        intent.putExtra("movieResults", movieResultsJSON);

                        LocalBroadcastManager.getInstance(MyApp.getInstance().getApplicationContext()).sendBroadcast(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        request.setTag("getUserSessionTag");

        MyApp.getInstance().getRequestQueue().add(request);

    }

    public static void searchRandomMovie(){

        String url = "http://api.themoviedb.org/3/discover/movie?api_key=588df436490dd63ffeaaaa8747eda2a2";

        JacksonRequest<MovieResultsJSON> request = new
                JacksonRequest<MovieResultsJSON>(Request.Method.GET, url, MovieResultsJSON.class,
                new Response.Listener<MovieResultsJSON>() {
                    @Override
                    public void onResponse(MovieResultsJSON movieResultsJSON) {

                        Intent intent = new Intent("randomMovieEvent");
                        intent.putExtra("randomMovieResult", movieResultsJSON);

                        System.out.println("On New Intent : Random Movie : ");
                        LocalBroadcastManager.getInstance(MyApp.getInstance().getApplicationContext()).sendBroadcast(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        request.setTag("getUserSessionTag");

        MyApp.getInstance().getRequestQueue().add(request);

    }
}
