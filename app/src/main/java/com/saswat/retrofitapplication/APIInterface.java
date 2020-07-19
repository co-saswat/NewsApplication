package com.saswat.retrofitapplication;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface APIInterface {

//    @GET("/v2/top-headlines")
//    Call<String> getNews(@Query("sources") String sourceValue, @Query("apiKey") String apiKey);
//Todo static accessing using gson.

//    @GET("/v2/top-headlines")
//    Call<Result> getNews(@Query("sources") String sourceValue, @Query("apiKey") String apiKey);

//Todo Dynamic access using gson

    @GET("/v2/top-headlines")
    Call<Result> getNews(@QueryMap Map<String, Object> options);
}
