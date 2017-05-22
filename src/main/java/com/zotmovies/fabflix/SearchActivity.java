package com.zotmovies.fabflix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zotmovies.fabflix.Constants.LOGIN_URL;
import static com.zotmovies.fabflix.Constants.SEARCH_URL;

public class SearchActivity extends AppCompatActivity {

    private List<String> movieList;
    private EditText searchBar;
    private TextView searchResp;
    private Button searchBtn;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        //get info from search bar
        searchBar = (EditText) findViewById(R.id.search_bar);
        searchResp =(TextView) findViewById(R.id.search_resp);
        searchBtn = (Button) findViewById(R.id.search_btn);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchProcess(searchBar.getText().toString());
            }
        });

        //TODO: get movieList, send to new activity
        intent = new Intent(this, MovieListActivity.class);
        //send to new activity
        movieList = new ArrayList<String>();
        intent.putStringArrayListExtra("movieList", (ArrayList<String>) movieList);

        //to retrieve
        //ArrayList<String> movieList = getIntent().getStringArrayListExtra("movieList");
    }

    protected void searchProcess(final String searchBar){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = SEARCH_URL+"?title=" + searchBar;


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        searchResp.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                searchResp.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
