package com.neopixl.mymovielist.Fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.neopixl.mymovielist.Model.MovieJSON;
import com.neopixl.mymovielist.R;

public class MovieFragment extends Fragment {
    public static String MOVIE_PARAM = "movie";

    private MovieJSON movie;
    private TextView textViewTitle;
    private ImageView imageViewPoster;
    private TextView textViewScore;

    public static MovieFragment newInstance(MovieJSON movie) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putParcelable(MOVIE_PARAM, movie);
        fragment.setArguments(args);
        return fragment;
    }

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movie = getArguments().getParcelable(MOVIE_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        textViewTitle = (TextView)rootView.findViewById(R.id.FragmentMovie_TextViewTitle);
        textViewScore = (TextView)rootView.findViewById(R.id.FragmentMovie_TextViewScore);
        imageViewPoster = (ImageView)rootView.findViewById(R.id.FragmentMovie_ImageViewPoster);
        return rootView;    }

    @Override
    public void onStart() {
        super.onStart();
        if(movie!=null)
        {
            textViewTitle.setText(movie.getTitle());
            textViewScore.setText(String.format("%.2f", movie.getVote_average()));
            Ion.with(imageViewPoster).load("https://image.tmdb.org/t/p/original" + movie.getPoster_path());
        }
    }
}