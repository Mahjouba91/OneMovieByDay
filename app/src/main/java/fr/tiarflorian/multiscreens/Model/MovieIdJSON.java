package fr.tiarflorian.multiscreens.Model;


/**
 * Created by Florian TIAR on 17/06/15.
 */
public class MovieIdJSON {
    private String title; // Titre du film
    private Float vote_average; // Note moyenne
    private int vote_count; // Nombre de notes
    private String poster_path; // Image du film
    private String overview; // Synopsys
    private int runtime; // Durée du film
    private String release_date;

    public MovieIdJSON() {
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
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
}
