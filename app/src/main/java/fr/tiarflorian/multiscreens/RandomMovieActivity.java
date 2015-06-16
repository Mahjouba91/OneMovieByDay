package fr.tiarflorian.multiscreens;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;
import fr.tiarflorian.multiscreens.Model.MovieJSON;
import fr.tiarflorian.multiscreens.Model.MovieResultsJSON;
import fr.tiarflorian.multiscreens.network.NetworkAccess;
import java.util.ArrayList;
import java.util.Random;

import java.util.ArrayList;
import java.util.Random;


public class RandomMovieActivity extends ActionBarActivity {
    final String EXTRA_WHAT_INTENT = "intent_name"; // action
    final String EXTRA_GENRE = "movie_genre"; // action
    final String EXTRA_RELEASE_DATE_GTE = "movie_release_date_gte"; // exemple : 2005, format date : YYYY-MM-DD
    final String EXTRA_RELEASE_DATE_LTE = "movie_release_date_lte"; // exemple : 2010, format date : YYYY-MM-DD

    private RandomMovieReceiver randomMovieReceiver;
    private FilteredMovieReceiver filteredMovieReceiver;

    private TextView title;
    private TextView score;
    private TextView overview;
    private ImageView poster;

    private int id_genre;
    private String first_year;
    private String last_year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_movie);

        Intent currentIntent = getIntent();
        if (currentIntent != null) {
            // If the user want a random movie
            System.out.println("WTF");
            System.out.println(currentIntent.getStringExtra(EXTRA_WHAT_INTENT));

            final String lastActivity = currentIntent.getStringExtra(EXTRA_WHAT_INTENT);
            if (lastActivity.equals("MainActivity")) {
                System.out.println("Bienvenue dans mon main");
                randomMovieReceiver = new RandomMovieReceiver();
                LocalBroadcastManager.getInstance(this).registerReceiver(randomMovieReceiver, new IntentFilter("randomMovieEvent"));

                NetworkAccess.searchRandomMovie();
            }
            // If the user want to filter the movie
            else if(lastActivity.equals("DateActivity")) {
                System.out.println("Bienvenue dans mon date");
                filteredMovieReceiver = new FilteredMovieReceiver();
                LocalBroadcastManager.getInstance(this).registerReceiver(filteredMovieReceiver, new IntentFilter("filteredMovieEvent"));

                id_genre = currentIntent.getIntExtra(EXTRA_GENRE, 12);
                first_year = currentIntent.getStringExtra(EXTRA_RELEASE_DATE_GTE);
                last_year = currentIntent.getStringExtra(EXTRA_RELEASE_DATE_LTE);

                System.out.println(currentIntent.getIntExtra(EXTRA_GENRE, 12));
                System.out.println(currentIntent.getStringExtra(EXTRA_RELEASE_DATE_GTE));
                System.out.println(currentIntent.getStringExtra(EXTRA_RELEASE_DATE_LTE));

                NetworkAccess.searchFilteredMovie(id_genre, first_year, last_year);
            }
            else {
                System.out.println("Salut toi !!");
            }

        }
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

    class FilteredMovieReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("received a filtered movie");
            MovieResultsJSON resultsJSON = (MovieResultsJSON)intent.getSerializableExtra("filteredMovieResult");

            // Take the results
            ArrayList<MovieJSON> results = resultsJSON.getResults();

            // Do a random number between 0 and 19
            int min = 0; int max = results.size()-1;
            Random rand = new Random();
            int randomNumber = rand.nextInt(max - min + 1) + min;

            // Select the random movie
            MovieJSON randomMovie = results.get(randomNumber);

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
        getMenuInflater().inflate(R.menu.menu_random_movie, menu);
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
