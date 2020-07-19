package com.saswat.retrofitapplication;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

//Todo when your using the gson you have create an additional class to access the data . In gson you have to give the correct name nor it will throw some error to program
//Todo , or you can you SerializedName method to access the correct data.

public class Result {
    @SerializedName("status")
    public String status;

    @SerializedName("totalResults")
    public int totalResults;

    @SerializedName("articles")
    public ArrayList<Article> articles;
}
