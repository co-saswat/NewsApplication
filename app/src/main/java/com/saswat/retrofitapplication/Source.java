package com.saswat.retrofitapplication;

import org.json.JSONObject;

public class Source {
    public String id;
    public String name;

    public static Source parseSourceObject(JSONObject object) {
        Source sourceitem = new Source();
        sourceitem.id = object.optString("id");
        sourceitem.name = object.optString("name");
        return sourceitem;
    }
}
