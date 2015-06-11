package com.neopixl.mymovielist.Fragment;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neopixl.mymovielist.Model.Movie;
import com.neopixl.mymovielist.R;


public class DetailFragment extends Fragment {
    public static String MOVIE_PARAM = "movie";

    private Movie movie;

    private TextView textViewTitle;
    private TextView textViewScore;

    public static DetailFragment newInstance(Movie movie) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(MOVIE_PARAM, movie);
        fragment.setArguments(args);
        return fragment;
    }
    public DetailFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        textViewTitle = (TextView)rootView.findViewById(R.id.FragmentDetail_TextViewTitle);
        textViewScore = (TextView)rootView.findViewById(R.id.FragmentDetail_TextViewScore);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(movie!=null)
        {
            textViewTitle.setText(movie.getTitle());
            textViewScore.setText(String.format("%.2f",movie.getScore()));
        }
    }
}
