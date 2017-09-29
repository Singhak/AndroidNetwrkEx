package com.developer.alienapps.networkingex;

import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AMIT on 24-Sep-17.
 */

public class RectrofitWay {

    DownloadCompleteListener downloadCompleteListener;
    MainActivity mainActivity;

    public RectrofitWay(MainActivity mainActivity, DownloadCompleteListener downloadCompleteListener) {
        this.downloadCompleteListener = downloadCompleteListener;
        this.mainActivity = mainActivity;
        makeRetrofitCalls();
    }

    /**
     * Retrofit is an Android and Java library which is great at retrieving and
     * uploading structured data such as JSON and XML.
     * Retrofit makes HTTP requests using OkHttp. Unlike the case of Volley,
     * where you have to convert a JSON string to a Repository object, Retrofit does that conversion for you.
     */
    private void makeRetrofitCalls() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com") // 1
                .addConverterFactory(GsonConverterFactory.create()) // 2
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class); // 3

        Call<ArrayList<Repository>> call = retrofitAPI.retrieveRepositories(); // 4

        call.enqueue(new Callback<ArrayList<Repository>>() {  // 5
            @Override
            public void onResponse(Call<ArrayList<Repository>> call,
                                   Response<ArrayList<Repository>> response) {
                downloadCompleteListener.downloadComplete(response.body());  // 6
            }

            @Override
            public void onFailure(Call<ArrayList<Repository>> call, Throwable t) {
                Toast.makeText(mainActivity, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
