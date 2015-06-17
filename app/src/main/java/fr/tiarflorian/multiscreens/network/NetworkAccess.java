package fr.tiarflorian.multiscreens.network;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.navercorp.volleyextensions.request.JacksonRequest;

import fr.tiarflorian.multiscreens.Model.MovieIdJSON;
import fr.tiarflorian.multiscreens.Model.MovieResultsJSON;
import fr.tiarflorian.multiscreens.MyApp;

/**
 * Created by fdewasmes on 21/05/15.
 * Edited by Florian TIAR on 17/06/15.
 */
public class NetworkAccess {

    public static void searchRandomMovie(int randomPage){
        String url = "http://api.themoviedb.org/3/discover/movie?api_key=588df436490dd63ffeaaaa8747eda2a2&language=fr&page="+randomPage;

        JacksonRequest<MovieResultsJSON> request = new
                JacksonRequest<MovieResultsJSON>(Request.Method.GET, url, MovieResultsJSON.class,
                new Response.Listener<MovieResultsJSON>() {
                    @Override
                    public void onResponse(MovieResultsJSON movieResultsJSON) {

                        Intent intent = new Intent("randomMovieEvent");
                        intent.putExtra("randomMovieResult", movieResultsJSON);
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

    public static void searchFilteredMovie(int movie_genre, String movie_first_year, String movie_last_year){
        String url = "http://api.themoviedb.org/3/discover/movie?api_key=588df436490dd63ffeaaaa8747eda2a2&language=fr&with_genres=" + movie_genre + "&primary_release_date.gte=" + movie_first_year + "&primary_release_date.lte=" + movie_last_year;
        JacksonRequest<MovieResultsJSON> request = new
                JacksonRequest<MovieResultsJSON>(Request.Method.GET, url, MovieResultsJSON.class,
                new Response.Listener<MovieResultsJSON>() {
                    @Override
                    public void onResponse(MovieResultsJSON movieResultsJSON) {

                        Intent intent = new Intent("filteredMovieEvent");
                        intent.putExtra("filteredMovieResult", movieResultsJSON);
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

    public static void searchMovieById(int movie_id){
        String url = "http://api.themoviedb.org/3/movie/"+movie_id+"?api_key=588df436490dd63ffeaaaa8747eda2a2&language=fr";
        JacksonRequest<MovieIdJSON> request = new
                JacksonRequest<MovieIdJSON>(Request.Method.GET, url, MovieIdJSON.class,
                new Response.Listener<MovieIdJSON>() {
                    @Override
                    public void onResponse(MovieIdJSON movieIdJSON) {

                        Intent intent = new Intent("idMovieEvent");
                        intent.putExtra("idMovieResult", movieIdJSON);
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
