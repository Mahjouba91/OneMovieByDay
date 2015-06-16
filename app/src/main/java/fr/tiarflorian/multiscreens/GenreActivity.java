package fr.tiarflorian.multiscreens;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class GenreActivity extends ActionBarActivity implements View.OnClickListener {

    final String EXTRA_GENRE = "movie_genre"; // action
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        ImageButton action = (ImageButton) findViewById(R.id.action);
        action.setOnClickListener(this); // calling onClick() method
        ImageButton aventure = (ImageButton) findViewById(R.id.aventure);
        aventure.setOnClickListener(this);
        ImageButton comedie = (ImageButton) findViewById(R.id.comedie);
        comedie.setOnClickListener(this);
        ImageButton animation = (ImageButton) findViewById(R.id.animation);
        animation.setOnClickListener(this);
        ImageButton documentaire = (ImageButton) findViewById(R.id.documentaire);
        documentaire.setOnClickListener(this);
        ImageButton romance = (ImageButton) findViewById(R.id.romance);
        romance.setOnClickListener(this);
        ImageButton fantasy = (ImageButton) findViewById(R.id.fantasy);
        fantasy.setOnClickListener(this);
        ImageButton war = (ImageButton) findViewById(R.id.war);
        war.setOnClickListener(this);
        ImageButton history = (ImageButton) findViewById(R.id.history);
        history.setOnClickListener(this);
        ImageButton scifi = (ImageButton) findViewById(R.id.scifi);
        scifi.setOnClickListener(this);
        ImageButton western = (ImageButton) findViewById(R.id.western);
        western.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.action:
                intent = new Intent(GenreActivity.this, DateActivity.class);
                intent.putExtra(EXTRA_GENRE, 28); // Action
                startActivity(intent);
                break;

            case R.id.aventure:
                intent = new Intent(GenreActivity.this, DateActivity.class);
                intent.putExtra(EXTRA_GENRE, 12);
                startActivity(intent);
                break;

            case R.id.animation:
                intent = new Intent(GenreActivity.this, DateActivity.class);
                intent.putExtra(EXTRA_GENRE, 16);
                startActivity(intent);
                break;

            case R.id.comedie:
                intent = new Intent(GenreActivity.this, DateActivity.class);
                intent.putExtra(EXTRA_GENRE, 35);
                startActivity(intent);
                break;

            case R.id.documentaire:
                intent = new Intent(GenreActivity.this, DateActivity.class);
                intent.putExtra(EXTRA_GENRE, 99);
                startActivity(intent);
                break;

            case R.id.romance:
                intent = new Intent(GenreActivity.this, DateActivity.class);
                intent.putExtra(EXTRA_GENRE, 10749);
                startActivity(intent);
                break;

            case R.id.fantasy:
                intent = new Intent(GenreActivity.this, DateActivity.class);
                intent.putExtra(EXTRA_GENRE, 14);
                startActivity(intent);
                break;

            case R.id.war:
                intent = new Intent(GenreActivity.this, DateActivity.class);
                intent.putExtra(EXTRA_GENRE, 10752);
                startActivity(intent);
                break;

            case R.id.history:
                intent = new Intent(GenreActivity.this, DateActivity.class);
                intent.putExtra(EXTRA_GENRE, 36);
                startActivity(intent);
                break;

            case R.id.scifi:
                intent = new Intent(GenreActivity.this, DateActivity.class);
                intent.putExtra(EXTRA_GENRE, 878);
                startActivity(intent);
                break;

            case R.id.western:
                intent = new Intent(GenreActivity.this, DateActivity.class);
                intent.putExtra(EXTRA_GENRE, 37);
                startActivity(intent);
                break;

            default:
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_genre, menu);
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
