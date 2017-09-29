package com.developer.alienapps.networkingex;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

/**
 * Created by AMIT on 24-Sep-17.
 */


public class VolleyWay {

    MainActivity mainActivity;
    DownloadCompleteListener downloadCompleteListener;

    VolleyWay(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.downloadCompleteListener = (DownloadCompleteListener) mainActivity;
        makeRequestWithVolley("https://api.github.com/repositories");
    }

    /**
     * Creates a new request queue.
     * Creates a new instance of StringRequest with the URL you want to connect to.
     * Converts and passes the results to downloadComplete().
     * Adds the string request to the request queue.
     *
     * @param url
     */

    private void makeRequestWithVolley(String url) {
        RequestQueue queue = Volley.newRequestQueue(mainActivity); // 1

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() { // 2
                    @Override
                    public void onResponse(String response) {
                        try {
                            downloadCompleteListener.downloadComplete(Util.retrieveRepositoriesFromResponse(response)); // 3
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);  // 4

    }
}
