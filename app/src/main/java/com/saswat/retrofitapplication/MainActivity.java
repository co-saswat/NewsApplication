package com.saswat.retrofitapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Article> articles;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rc_view_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getNews();
    }

    private void getNews() {
        final APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<String> news = apiInterface.getNews("google-news-in", "dd411b383145472ca9bbff28d0cfe05f");
        news.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseValue = response.body();
                try {
                    JSONObject responseObject = new JSONObject(responseValue);
                    JSONArray responseArray = responseObject.getJSONArray("articles");

                    articles = new ArrayList<>();
                    for (int i = 0; i < responseArray.length(); i++) {
                        Article newsArticle = Article.parseJsonObject(responseArray.optJSONObject(i));
                        articles.add(newsArticle);
                        adapter = new CustomAdapter(MainActivity.this, getApplicationContext(), articles);
                        recyclerView.setAdapter(adapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}