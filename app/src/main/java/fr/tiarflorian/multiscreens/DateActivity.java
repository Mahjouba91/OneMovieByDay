package fr.tiarflorian.multiscreens;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class DateActivity extends ActionBarActivity  {

    final String EXTRA_GENRE = "movie_genre"; // action
    final String EXTRA_RELEASE_DATE_GTE = "movie_release_date_gte"; // exemple : 2005, format date : YYYY-MM-DD
    final String EXTRA_RELEASE_DATE_LTE = "movie_release_date_lte"; // exemple : 2010, format date : YYYY-MM-DD
    final String EXTRA_WHAT_INTENT = "intent_name"; // action

    private Spinner spinner;
    private Button nextButton;
    private int genre = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);


        Intent currentIntent = getIntent();
        if (currentIntent != null) {

            genre = currentIntent.getIntExtra(EXTRA_GENRE, 12);
        }



        spinner = (Spinner) findViewById(R.id.DateActivity_datepicker);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.date_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // We pick the selected date by the user
                final String date = String.valueOf(spinner.getSelectedItem());
                // We split the date in two years
                final String[] dates = date.split("-");
                // We want this format : YYYY-MM-DD
                final String firstDate = dates[0]+"-01-01";
                final String lastDate = dates[1]+"-12-31";

                Intent intent = new Intent(DateActivity.this, RandomMovieActivity.class);
                intent.putExtra(EXTRA_WHAT_INTENT, "DateActivity"); // Action
                intent.putExtra(EXTRA_GENRE, genre); // Action
                intent.putExtra(EXTRA_RELEASE_DATE_GTE, firstDate); // Action
                intent.putExtra(EXTRA_RELEASE_DATE_LTE, lastDate); // Action
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_date, menu);
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
