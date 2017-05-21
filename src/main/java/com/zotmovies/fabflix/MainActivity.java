package com.zotmovies.fabflix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zotmovies.fabflix.Datasource.Datasource;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  int offset = 0;
    private Button first, last;
    private Datasource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        datasource = Datasource.getMovieList();
        ListView listView =(ListView) findViewById(R.id.listView);

        /*setListAdapter(new MovieListAdapter(this, new ArrayList<String>()));*/
    }





}
