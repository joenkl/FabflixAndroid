package com.zotmovies.fabflix.Datasource;

import android.support.annotation.NonNull;

import com.zotmovies.fabflix.model.Movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by LuanNguyen on 5/20/2017.
 */

public class Datasource {
    private static Datasource datasource = null;
    private List<String> movieList;

    public static Datasource getMovieList(){
        if (datasource == null)
            datasource = new Datasource();

        return datasource;
    }


    private Datasource(){
        movieList = new ArrayList<String>();
        movieList.add("Movie 1");
        movieList.add("Movie 2");
        movieList.add("Movie 3");
        movieList.add("Movie 4");
        movieList.add("Movie 5");
        movieList.add("Movie 6");
        movieList.add("Movie 7");
        movieList.add("Movie 8");
        movieList.add("Movie 9");
        movieList.add("Movie 10");
        movieList.add("Movie 11");
        movieList.add("Movie 12");
        movieList.add("Movie 13");
        movieList.add("Movie 14");
        movieList.add("Movie 15");
    }

    public int getSize(){
        return movieList.size();
    }

    public List<String> getMovies(int offset, int limit){
        List<String> newMovieList = new ArrayList<String>(limit);
        int end = offset + limit;
        if (end > movieList.size()){
            end = movieList.size();
        }

        newMovieList.addAll(movieList.subList(offset, end));
        return newMovieList;
    }


}
