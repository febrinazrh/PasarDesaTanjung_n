package com.example.pasardesatanjung;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class KoneksiVolley {
    private static KoneksiVolley koneksiVolley;
    private RequestQueue requestQueue;
    private static Context mContext;

    private KoneksiVolley(Context context){
        mContext = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized KoneksiVolley getInstance(Context context){
        if (koneksiVolley == null){
            koneksiVolley = new KoneksiVolley(context);
        }
        return koneksiVolley;
    }

    public<T> void addToRequestQue (Request<T> request){
        getRequestQueue().add(request);
    }
}
