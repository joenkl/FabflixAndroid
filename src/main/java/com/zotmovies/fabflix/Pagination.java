package com.zotmovies.fabflix;

import android.provider.Settings;

import java.util.ArrayList;

/**
 * Created by JosephNguyen on 5/21/2017.
 */

public class Pagination {

    public int TOTAL;
    public static final int ITEMS_PER_PAGE = 8;
    public int ITEMS_REMAINING;
    public int LAST_PAGE;
    public ArrayList <String> listOfMovieTitle;


    public Pagination(int total, ArrayList<String> movieList)
    {
        TOTAL = total;
        ITEMS_REMAINING = TOTAL % ITEMS_PER_PAGE;
        LAST_PAGE = TOTAL/ITEMS_PER_PAGE;
        if (ITEMS_REMAINING == 0)
            LAST_PAGE -= 1;
        listOfMovieTitle = movieList;
    }


    public ArrayList<String> generatePage (int currentPage) {

        int start = currentPage * ITEMS_PER_PAGE;
        int numOfItem = ITEMS_PER_PAGE;



        ArrayList<String> movieListData = new ArrayList<String>();

        if (currentPage == LAST_PAGE && ITEMS_REMAINING > 0) {
            for (int i = start; i < start + ITEMS_REMAINING; i++){
                movieListData.add(listOfMovieTitle.get(i).toString());}
        }
        else
        {
            for (int i = start; i < start + numOfItem; i++)
                movieListData.add(listOfMovieTitle.get(i).toString());
        }

        return movieListData;
    }

    public int getLAST_PAGE(){
        return LAST_PAGE;
    }
}
