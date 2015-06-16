package fr.tiarflorian.multiscreens.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import fr.tiarflorian.multiscreens.Model.Movie;
import fr.tiarflorian.multiscreens.Model.MovieJSON;
import fr.tiarflorian.multiscreens.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by axel on 24/09/14.
 * Modified to apply this pattern : https://www.bignerdranch.com/blog/customizing-android-listview-rows-subclassing/
 */

public class MovieAdapter extends BaseAdapter {

    private List<MovieJSON> movieList;
    private LayoutInflater layoutInflater;

    public MovieAdapter(Context context, List<MovieJSON> movieList) {
        this.movieList = new ArrayList<MovieJSON>(movieList);
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public MovieAdapter(Context context) {
        this.movieList = new ArrayList<MovieJSON>();
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //return the list Size;
    @Override
    public int getCount() {
        return movieList.size();
    }

    //return the movie object a position "position"
    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }


    //return the id of the object at position
    @Override
    public long getItemId(int position) {
        return 0;
    }

    public List<MovieJSON> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieJSON> movieList) {
        this.movieList = movieList;
        this.notifyDataSetChanged();
    }

    //add a movie to the current list
    public void addMovie(MovieJSON movie)
    {
        this.movieList.add(movie);
        //notify that the list has changed and the UI need to be refreshed.
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieView movieView = (MovieView)convertView;
        if (null == movieView)
            movieView = MovieView.inflate(parent);
        MovieJSON movie = this.movieList.get(position);
        movieView.setMovie(movie);
        return movieView;
    }
}