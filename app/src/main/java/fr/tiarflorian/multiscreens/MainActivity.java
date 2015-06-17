package fr.tiarflorian.multiscreens;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;


/* Pour chercher un film par genre : http://docs.themoviedb.apiary.io/#reference/discover/discovermovie/get */
// API : http://api.themoviedb.org/3/discover/movie?api_key=588df436490dd63ffeaaaa8747eda2a2&with_genres=18&primary_release_date.gte=2005-01-01&primary_release_date.lte=2010-12-31&language=fr
public class MainActivity extends ActionBarActivity {

    final String EXTRA_WHAT_INTENT = "intent_name"; // action

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioButton radioRandomMovieButton = (RadioButton) findViewById(R.id.radioRandomMovie);
        final RadioButton radioPreferedMovieButton = (RadioButton) findViewById(R.id.radioPreferedMovie);

        radioRandomMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RandomMovieActivity.class);
                intent.putExtra(EXTRA_WHAT_INTENT, "MainActivity"); // Action
                startActivity(intent);
                radioPreferedMovieButton.setChecked(false);
                radioRandomMovieButton.setChecked(false);
            }
        });

        radioPreferedMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GenreActivity.class);
                startActivity(intent);
                radioRandomMovieButton.setChecked(false);
                radioPreferedMovieButton.setChecked(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
