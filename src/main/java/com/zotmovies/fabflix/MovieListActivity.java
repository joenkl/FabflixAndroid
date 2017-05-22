package com.zotmovies.fabflix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuanNguyen on 5/21/2017.
 */

public class MovieListActivity extends AppCompatActivity{

    ListView movieList;
    ArrayList<String> listOfMovieTitle;
    Button next, prev;
    Pagination p;
    private int totalPages; //p.getTOTAL() / p.ITEMS_PER_PAGE;
    /*Pagination.TOTAL / Pagination.ITEMS_PER_PAGE;*/
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list);

        listOfMovieTitle = getIntent().getStringArrayListExtra("movieList");
        p = new Pagination(listOfMovieTitle.size(), listOfMovieTitle);

        movieList = (ListView) findViewById(R.id.movieList);
        next = (Button) findViewById(R.id.next);
        prev = (Button) findViewById(R.id.prev);
        prev.setEnabled(false);


        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Toast.makeText(MovieListActivity.this, p.generatePage(currentPage).get(i), Toast.LENGTH_SHORT).show();
            }
        });


        movieList.setAdapter(new ArrayAdapter<String>(MovieListActivity.this,
                android.R.layout.simple_list_item_1, p.generatePage(currentPage)));

        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                currentPage++;
                movieList.setAdapter(new ArrayAdapter<String>(MovieListActivity.this,
                        android.R.layout.simple_list_item_1, p.generatePage(currentPage)));
                buttonEnable();
            }
        });


        prev.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                currentPage--;
                movieList.setAdapter(new ArrayAdapter<String>(MovieListActivity.this,
                        android.R.layout.simple_list_item_1, p.generatePage(currentPage)));
                buttonEnable();
            }
        });
    }

    private void buttonEnable(){
        if (currentPage == totalPages){
            prev.setEnabled(true);
            next.setEnabled(false);
        }
        else if (currentPage == 0){
            prev.setEnabled(false);
            next.setEnabled(true);
        }
        else if (currentPage >= 1 &&  currentPage <= totalPages ){
            prev.setEnabled(true);
            next.setEnabled(true);
        }
    }
}
