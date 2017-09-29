package com.developer.alienapps.networkingex;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by AMIT on 24-Sep-17.
 */

public interface RetrofitAPI {
    @GET("/repositories")
    Call<ArrayList<Repository>> retrieveRepositories();
}
