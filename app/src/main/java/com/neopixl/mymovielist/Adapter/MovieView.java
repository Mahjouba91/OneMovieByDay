package com.neopixl.mymovielist.Adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neopixl.mymovielist.Model.Movie;
import com.neopixl.mymovielist.Model.MovieJSON;
import com.neopixl.mymovielist.R;



/**
 * Created by fdewasmes on 21/05/15.
 */
public class MovieView extends RelativeLayout{
    private TextView mTitleTextView;
    private TextView mScoreTextView;

    public MovieView(Context c) {
        this(c, null);
    }

    public MovieView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public static MovieView inflate(ViewGroup parent) {
        MovieView movieView = (MovieView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_view, parent, false);
        return movieView;
    }

    public MovieView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.movie_view_children, this, true);
        setupChildren();
    }

    private void setupChildren() {
        mTitleTextView = (TextView) findViewById(R.id.row_movie_title);
        mScoreTextView = (TextView) findViewById(R.id.row_movie_score);
    }

    public void setMovie(MovieJSON movie) {
        mTitleTextView.setText(movie.getTitle());
        mScoreTextView.setText(String.format("%.2f",movie.getVote_average()));
    }

}
