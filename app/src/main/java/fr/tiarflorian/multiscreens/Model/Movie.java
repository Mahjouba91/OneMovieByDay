package fr.tiarflorian.multiscreens.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by axel on 24/09/14.
 */
public class Movie implements Parcelable {
    private String title;
    private Float score;

    public Movie(String title, float score)
    {
        this.title=title;
        this.score=score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    //Parcelable Methods

    @Override
    public int describeContents() {
        return title.hashCode()+score.hashCode();
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeFloat(score);
    }

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private Movie(Parcel in) {
        title = in.readString();
        score = in.readFloat();
    }
}
