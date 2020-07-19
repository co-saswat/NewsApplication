package com.saswat.retrofitapplication;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class Source {
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;

    public static Source parseSourceObject(JSONObject object) {
        Source sourceitem = new Source();
        sourceitem.id = object.optString("id");
        sourceitem.name = object.optString("name");
        return sourceitem;
    }
}
