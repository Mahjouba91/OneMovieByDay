package com.neopixl.mymovielist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.neopixl.mymovielist.Adapter.MovieAdapter;
import com.neopixl.mymovielist.Fragment.DetailFragment;
import com.neopixl.mymovielist.Model.Movie;
import com.neopixl.mymovielist.Model.MovieJSON;
import com.neopixl.mymovielist.Model.MovieResultsJSON;
import com.neopixl.mymovielist.network.NetworkAccess;

import org.w3c.dom.Text;

import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText editTextTitle;
    private EditText editTextScore;
    private ListView listViewMovies;
    private MovieAdapter adapter;
    private Button buttonSave;

    private Text randomMovie;

    private SearchResultReceiver receiver;
    private displayRandomMovieReceiver displayRandomMovieEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get the editTextTitle from the xml.
        editTextTitle = (EditText)findViewById(R.id.ActivityMain_EditTextTitle);
        editTextScore = (EditText)findViewById(R.id.ActivityMain_EditTextScore);
        listViewMovies = (ListView)findViewById(R.id.ActivityMain_ListViewMovies);
        buttonSave = (Button)findViewById(R.id.ActivityMain_ButtonSave);

        Button testButton = (Button)findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkAccess.searchMovie(editTextTitle.getText().toString());
            }
        });
    }

    public void pushRandomMovie(View view) {
        Intent intent = new Intent(this, DisplayRandomMovie.class);
        startActivity(intent);

        NetworkAccess.searchMovie(editTextTitle.getText().toString());
    }


    @Override
    protected void onStart() {
        super.onStart();
        //add this class as OnClickListener.
        buttonSave.setOnClickListener(this);
        //init the MovieAdapter.
        this.adapter = new MovieAdapter(this);
        //set the listViewMovies adapter.
        this.listViewMovies.setAdapter(adapter);
        //add this class as OnItemClickListener.
        this.listViewMovies.setOnItemClickListener(this);

        receiver =  new SearchResultReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("searchResultsEvent"));
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("DisplayRandomMovie"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Intent searchActivity = new Intent(this, SearchActivity.class);
            startActivity(searchActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //verifi if the caller is the buttonSave
        if(v == buttonSave)
        {
            //verify if the title and score are not empty.
            if(editTextTitle.getText().length() > 0 && editTextScore.getText().length() > 0)
            {
                //try to convert the editTextScore text to float, if fail, go to catch.
                try {
                    float score = Float.parseFloat(editTextScore.getText().toString());
                    //create a new movie
                    Movie movie = new Movie(editTextTitle.getText().toString(),score);
                    //add the new movie to the adapter array.
                   // adapter.addMovie(movie);
                }
                catch (NumberFormatException ex)
                {
                    //call custom Error Method showError
                    showError("Le score doit être un nombre décimal.");
                }
            }
            else
            {
                //call custom Error Method showError
                showError("Un des champs est vide.");
            }
        }
    }

    private void showError(String errorMessage) {
        //create a new alertDialog Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //set the alert title.
        builder.setTitle("Erreur");
        //set the body text
        builder.setMessage(errorMessage);
        //add a OK button who do nothing.
        builder.setPositiveButton("OK", null);
        //create the AlertDialog from the builder
        AlertDialog alertDialog = builder.create();
        //show the alert dialog
        alertDialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //get the movie at position "position" from the adapter.
        Movie selectedMovie = (Movie)adapter.getItem(position);
        //create a new fragment with selected movie.
        DetailFragment detailFragment = DetailFragment.newInstance(selectedMovie);
        //start a new Fragment transaction.
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        //add the new detailFragment to the transaction in container "container" and add it to the backstack.
        fragmentTransaction.add(R.id.container, detailFragment).addToBackStack(null);
        //commit the transaction.
        fragmentTransaction.commit();
    }

    class SearchResultReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("received results");
            MovieResultsJSON resultsJSON = (MovieResultsJSON)intent.getSerializableExtra("movieResults");
            adapter.setMovieList(resultsJSON.getResults());

        }
    }

    class displayRandomMovieReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            MovieJSON randomMovie = (MovieJSON)intent.getSerializableExtra("randomMovieResult");
            System.out.println("received random movie : " + randomMovie.getTitle());

            // adapter.setMovieList(randomMovie.getResults());

        }
    }
}
