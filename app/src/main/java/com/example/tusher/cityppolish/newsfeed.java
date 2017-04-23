package com.example.tusher.cityppolish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.os.Build;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@TargetApi(Build.VERSION_CODES.M)

public class newsfeed extends AppCompatActivity implements RecyclerView.OnScrollChangeListener{

    private List<citypol> citypolList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private RequestQueue requestQueue;
    private int requestCount=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        citypolList=new ArrayList<>();
        requestQueue=Volley.newRequestQueue(this);

        getData();

        recyclerView.setOnScrollChangeListener(this);


        adapter=new CardAdapter(citypolList, this);
        recyclerView.setAdapter(adapter);


    }

    private JsonArrayRequest getDataFromServer(int requestCount){
        final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest( Config.DATA_URL+String.valueOf(requestCount), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {      // gets the JSON response as an array 
                parseData(response); // passes the array as a parameter for the parsedata function
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                //If an error occurs that means end of the list has reached
                Toast.makeText(newsfeed.this, "No More Items Available", Toast.LENGTH_SHORT).show();
            }
        });

        return jsonArrayRequest;

    }
    private void getData(){
        requestQueue.add(getDataFromServer(requestCount)); 
        requestCount++;

    }

    private void parseData(JSONArray array){
        int count=0;
        while(count<array.length()){   // runs the loop till count is less that length of array
            citypol cpol= new citypol(); //new instance of citypol class

            try{
                JSONObject json=array.getJSONObject(count); // int for indexing
                cpol.setImageurl(json.getString(Config.TAG_IMAGE_URL)); // takes  imageurl position by position
                cpol.setName(json.getString(Config.TAG_NAME)); // takes name 
                cpol.setTime(json.getString(Config.TAG_TIME)); // takes time
                cpol.setLocation(json.getString(Config.TAG_LOC)); // takes location
                count++;// count incremented and process iterted

            }catch (JSONException e){
                e.printStackTrace();
            }
            citypolList.add(cpol); // added to list we created 

        }
        adapter.notifyDataSetChanged(); // adapter notified of changes
    }
    private boolean isLastItemDisplaying(RecyclerView recyclerView){
        if(recyclerView.getAdapter().getItemCount()!=0){
            int lastVisibleItem=((LinearLayoutManager)recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItem != RecyclerView.NO_POSITION && lastVisibleItem == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }
        return false;
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if(isLastItemDisplaying(recyclerView)){
            getData();
        }

    }
}
