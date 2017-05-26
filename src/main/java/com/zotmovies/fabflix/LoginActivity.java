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

import java.util.HashMap;
import java.util.Map;

import static com.zotmovies.fabflix.Constants.LOGIN_URL;

/**
 * Created by JosephNguyen on 5/18/2017.
 */


public class LoginActivity extends AppCompatActivity{

    EditText email, password;
    TextView res_msg;
    Button loginButton;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //get info from text view
        email = (EditText) findViewById(R.id.email_login);
        password = (EditText) findViewById(R.id.password_login);
        res_msg = (TextView) findViewById(R.id.login_res_msg);
        loginButton = (Button) findViewById(R.id.login_submit);

        intent = new Intent(this, SearchActivity.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(intent);
                loginProcess(email.getText().toString(), password.getText().toString());
            }
        });
    }


    void loginProcess(final String cusEmail, final String cusPassword){
        //VOLLEY REQUEST
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = LOGIN_URL;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Boolean result = new Boolean(response);
                            if(!result){
                                res_msg.setText("Invalid combination of username and password. \nPlease try again!");
                                password.setText("");
                            }
                            else{
                                password.setText("");
                               startActivity(intent);
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", cusEmail);
                params.put("password", cusPassword);
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
