package com.developer.alienapps.networkingex;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by AMIT on 24-Sep-17.
 */

public class OkHttpWay {
    MainActivity mainActivity;
    DownloadCompleteListener downloadCompleteListener;

    public OkHttpWay(MainActivity mainActivity, DownloadCompleteListener downloadCompleteListener) {
        this.mainActivity = mainActivity;
        this.downloadCompleteListener = downloadCompleteListener;
        DownloadFromOk("https://api.github.com/repositories");
    }

    /**
     * Creates an OkHttpClient object.
     * Builds an OkHttpClient request with the URL you want to connect to.
     * Queues the request call.
     * Retrieves the response as a string.
     * Converts and passes the results to the main thread.
     *
     * @param urlstr
     */
    public void DownloadFromOk(String urlstr) {
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder().url(urlstr).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String result = response.body().string();  // 4

                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            downloadCompleteListener.downloadComplete(Util.retrieveRepositoriesFromResponse(result));  // 5
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}

