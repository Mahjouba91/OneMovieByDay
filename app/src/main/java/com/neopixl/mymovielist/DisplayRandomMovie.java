package com.neopixl.mymovielist;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.neopixl.mymovielist.Model.MovieJSON;
import com.neopixl.mymovielist.Model.MovieResultsJSON;
import com.neopixl.mymovielist.network.NetworkAccess;

import java.util.ArrayList;
import java.util.Random;


public class DisplayRandomMovie extends Activity {

    private RandomMovieReceiver randomMovieReceiver;
    private TextView title;
    private TextView score;
    private TextView overview;
    private ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_random_movie);

        randomMovieReceiver = new RandomMovieReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(randomMovieReceiver, new IntentFilter("randomMovieEvent"));

        NetworkAccess.searchRandomMovie();
    }

    class RandomMovieReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("received the best populates movies to take one randomnly");
            MovieResultsJSON resultsJSON = (MovieResultsJSON)intent.getSerializableExtra("randomMovieResult");

            // Take the results
            ArrayList<MovieJSON> results = resultsJSON.getResults();

            // Do a random number between 0 and 19
            int min = 0; int max = results.size()-1;
            Random rand = new Random();
            int randomNumber = rand.nextInt(max - min + 1) + min;

            // Select the random movie
            MovieJSON randomMovie = results.get(randomNumber);
            System.out.println("Nombre aleatoire : "+randomNumber);
            System.out.println("Titre du film : " + randomMovie.getTitle());
            System.out.println("Synopsys du film : " + randomMovie.getOverview());
            System.out.println("Poster du film : https://image.tmdb.org/t/p/original" + randomMovie.getPoster_path());

            title = (TextView) findViewById(R.id.RandomMovie_TextViewTitle);
            score = (TextView) findViewById(R.id.RandomMovie_TextViewScore);
            overview = (TextView) findViewById(R.id.RandomMovie_Overview);
            poster = (ImageView) findViewById(R.id.RandomMovie_ImageViewPoster);

            title.setText(randomMovie.getTitle());
            score.setText(String.format("%.2f", randomMovie.getVote_average()));
            overview.setText(randomMovie.getOverview());
            Ion.with(poster).load("https://image.tmdb.org/t/p/original" + randomMovie.getPoster_path());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_random_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
