package com.zotmovies.fabflix;

import java.util.ArrayList;

/**
 * Created by JosephNguyen on 5/21/2017.
 */

public class Pagination {
    public static final int TOTAL = 42;
    public static final int ITEMS_PER_PAGE = 8;
    public static final int ITEMS_REMAINING = TOTAL % ITEMS_PER_PAGE;
    public static final int LAST_PAGE = TOTAL/ITEMS_PER_PAGE;



    public ArrayList<String> generatePage (int currentPage) {
        int start = currentPage * ITEMS_PER_PAGE + 1;
        int numOfItem = ITEMS_PER_PAGE;

        ArrayList<String> movieListData = new ArrayList<String>();

        if (currentPage == LAST_PAGE && ITEMS_REMAINING > 0) {
            for (int i = start; i < start + ITEMS_REMAINING; i++)
                movieListData.add("Movie " + i);
        }
        else
        {
            for (int i = start; i < start + numOfItem; i++)
                movieListData.add("Movie " + i);
        }

        return movieListData;
    }
}
