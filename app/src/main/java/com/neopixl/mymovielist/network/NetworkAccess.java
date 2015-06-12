package com.neopixl.mymovielist.network;

import android.content.Intent;
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

        String url = "http://api.themoviedb.org/3/discover/movie?api_key=588df436490dd63ffeaaaa8747eda2a2";

        JacksonRequest<MovieResultsJSON> request = new
                JacksonRequest<MovieResultsJSON>(Request.Method.GET, url, MovieResultsJSON.class,
                new Response.Listener<MovieResultsJSON>() {
                    @Override
                    public void onResponse(MovieResultsJSON movieResultsJSON) {
                        //Use or store the object UserSession
                        System.out.println("Nombres de films : "+movieResultsJSON.getTotal_results());
                        ArrayList<MovieJSON> results = movieResultsJSON.getResults();

                        int min = 0; int max = results.size();
                        System.out.println("Nombres de films : "+results.size());
                        Random rand = new Random();
                        int randomNumber = rand.nextInt(max - min + 1) + min;
                        MovieJSON randomMovie = results.get(randomNumber);
                        System.out.println("Nombre aleatoire : "+randomNumber);
                        System.out.println("Titre du film : "+randomMovie.getTitle());

                        /* for(int i=0; i < movieResultsJSON.getTotal_results(); i++) {
                            // System.out.println("AFFICHAGE que je veux : "+results);
                            System.out.println("Résultat : "+results.getString(i).getTitle());
                        } */

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

    public static void searchRandomMovie(String movie){
        // Do a random number between 0 and 100

        String url = "http://api.themoviedb.org/3/discover/movie?api_key=588df436490dd63ffeaaaa8747eda2a2";

        JacksonRequest<MovieResultsJSON> request = new
                JacksonRequest<MovieResultsJSON>(Request.Method.GET, url, MovieResultsJSON.class,
                new Response.Listener<MovieResultsJSON>() {
                    @Override
                    public void onResponse(MovieResultsJSON movieResultsJSON) {
                        //Use or store the object UserSession

                        int min = 0; int max = movieResultsJSON.getTotal_results();
                        Random rand = new Random();
                        int randomNumber = rand.nextInt(max - min + 1) + min;

                        System.out.println("AFFICHAGE que je veux : "+movieResultsJSON);

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
}
