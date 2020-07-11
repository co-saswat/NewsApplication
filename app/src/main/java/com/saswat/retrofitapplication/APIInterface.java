package com.saswat.retrofitapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/v2/top-headlines")
    Call<String> getNews(@Query("sources") String sourceValue, @Query("apiKey") String apiKey);

}
