package com.zotmovies.fabflix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
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
    private Button logoutBtn;

    Intent intent;
    Intent logoutIntent;
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
                if(searchBar.getText().toString().length() == 0)
                    searchResp.setText("Search field cannot be empty!!");
                else
                    searchProcess(searchBar.getText().toString());
            }
        });



        logoutBtn = (Button) findViewById((R.id.logout));

        logoutIntent = new Intent(this, LoginActivity.class);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(logoutIntent);
            }
        });


        intent = new Intent(this, MovieListActivity.class);

    }

    protected void searchProcess(final String searchBar){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = SEARCH_URL+"?title=" + searchBar;

        url = url.replaceAll(" ", "+");

        // Request a string response from the provided URL.

        JsonArrayRequest stringRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray json) {
                        Gson gson=new Gson();
                        TypeToken<List<String>> token = new TypeToken<List<String>>(){};
                        List<String> movieList = gson.fromJson(String.valueOf(json), token.getType());

                        if(movieList.size() == 0)
                            searchResp.setText("No result found");
                        else{
                            searchResp.setText("");
                            intent.putStringArrayListExtra("movieList", new ArrayList<String>(movieList));
                            startActivity(intent);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                searchResp.setText("Invalid Search");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
