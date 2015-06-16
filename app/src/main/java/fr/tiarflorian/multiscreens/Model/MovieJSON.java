package fr.tiarflorian.multiscreens.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fdewasmes on 21/05/15.
 */
public class MovieJSON implements Parcelable {
    private String title; // Titre du film
    private Float vote_average; // Note moyenne
    private int vote_count; // Nombre de notes
    private String poster_path; // Image du film
    private String overview; // Synopsys
    private int id; // ID du film dans l'API

    public MovieJSON() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return title.hashCode()+poster_path.hashCode()+vote_average.hashCode()+overview.hashCode();
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeString(poster_path);
        out.writeFloat(vote_average);
        out.writeString(overview);
    }

    public static final Parcelable.Creator<MovieJSON> CREATOR
            = new Parcelable.Creator<MovieJSON>() {
        public MovieJSON createFromParcel(Parcel in) {
            return new MovieJSON(in);
        }

        public MovieJSON[] newArray(int size) {
            return new MovieJSON[size];
        }
    };

    private MovieJSON(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        vote_average = in.readFloat();
        overview = in.readString();
    }
}
