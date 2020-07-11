package com.saswat.retrofitapplication;

import org.json.JSONObject;

public class Article {
    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public String authpublishedAtor;
    public String content;

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
