package com.saswat.retrofitapplication;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class Article {
    @SerializedName("author")
    public String author;
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("url")
    public String url;
    @SerializedName("urlToImage")
    public String urlToImage;
    @SerializedName("publishedAt")
    public String authpublishedAtor;
    @SerializedName("content")
    public String content;

    @SerializedName("source")
    public Source source;

    public static Article parseJsonObject(JSONObject object) {
        Article item = new Article();
        item.author = object.optString("author");
        item.title = object.optString("title");
        item.description = object.optString("description");
        item.url = object.optString("url");
        item.urlToImage = object.optString("urlToImage");
        item.authpublishedAtor = object.optString("authpublishedAtor");
        item.content = object.optString("content");

        JSONObject jsonObject = object.optJSONObject("source");
        item.source = Source.parseSourceObject(jsonObject);
        return item;
    }

}
