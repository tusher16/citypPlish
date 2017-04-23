package com.example.tusher.cityppolish;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Acer on 4/17/2017.
 */

public class MySingleton {
    private static MySingleton mInstances;   // creates an instance of the MySingleton instance
    private RequestQueue requestQueue;  //  instance of requestQueue
    private static Context mCtx;  // instance of Context

    private MySingleton(Context context) {
        mCtx = context;  
        requestQueue = getRequestQueue(); // requestQueue is set to the RequestQueue from server

    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) { 
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext()); // if the requestQueue is empty then create a new requestQueue 

        }
        return requestQueue;


    }
    public static synchronized  MySingleton getInstance(Context context){
        if(mInstances==null){
            mInstances=new MySingleton(context);
        }
        return mInstances;
    }

    public<T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request); // add the current request to the requestQueue
    }

}