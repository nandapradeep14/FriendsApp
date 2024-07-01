package com.nanda.friendsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    AppCompatButton b1,b2;
    String apiurl="https://friendsapi-re5a.onrender.com/adddata";


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ed1=(EditText) findViewById(R.id.name);
        ed2=(EditText) findViewById(R.id.fname);
        ed3=(EditText) findViewById(R.id.fnick);
        ed4=(EditText) findViewById(R.id.des);
        b1=(AppCompatButton) findViewById(R.id.sub);
        b2=(AppCompatButton) findViewById(R.id.vieww);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent();
                i=new Intent(getApplicationContext(),view.class);
                startActivity(i);

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName=ed1.getText().toString();
                String getFname=ed2.getText().toString();
                String getFnickname=ed3.getText().toString();
                String getDesc=ed4.getText().toString();
                Toast.makeText(getApplicationContext(),getName+getFname+getFnickname+getDesc,Toast.LENGTH_LONG).show();
                JSONObject friend=new JSONObject();
                try {
                    friend.put("name", getName);
                    friend.put("friendName",getFname);
                    friend.put("friendNickName",getFnickname);
                    friend.put("DescribeYourFriend",getDesc);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST,
                        apiurl,
                        friend,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                ed1.setText("");
                                ed2.setText("");
                                ed3.setText("");
                                ed4.setText("");
                                Toast.makeText(getApplicationContext(), "succesfully added", Toast.LENGTH_LONG).show();

                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
                            }
                        });


                            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(jsonObjectRequest);


            }

        });




    }
}





                
                
