package fr.tiarflorian.multiscreens.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fdewasmes on 21/05/15.
 */
public class MovieResultsJSON implements Serializable{
    private int page;
    private ArrayList<MovieJSON> results;
    private int total_pages;
    private int total_results;

    public MovieResultsJSON() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<MovieJSON> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieJSON> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
