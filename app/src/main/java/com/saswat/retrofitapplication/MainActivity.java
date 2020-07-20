package com.saswat.retrofitapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CategoryAdaptor.NewsCategoryOnClickListener, AdapterView.OnItemSelectedListener {

    RecyclerView recyclerView;
    RecyclerView horizontalRecyclerView;
    //    ArrayList<Article> articles;
//    String country = "";
    Spinner spinner;
    CustomAdapter adapter;
    ArrayList<String> news_country;
    CategoryAdaptor categoryAdaptor;
    ProgressDialog dialog;
    EditText ed_search;
    ImageView img_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String[] newsCountry = getResources().getStringArray(R.array.country_name);
//        news_country = new ArrayList<String>(Arrays.asList(newsCountry));


        ed_search = findViewById(R.id.et_search);
        img_delete = findViewById(R.id.igv_delete);

        spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);


        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Getting News");

        horizontalRecyclerView = findViewById(R.id.horizontal_rc_view);
        horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));
        categoryAdaptor = new CategoryAdaptor(MainActivity.this);
        categoryAdaptor.setListener(this);
        horizontalRecyclerView.setAdapter(categoryAdaptor);

        recyclerView = findViewById(R.id.rc_view_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    img_delete.setVisibility(View.VISIBLE);
                    adapter.getFilter().filter(editable);
                } else {
                    img_delete.setVisibility(View.GONE);
                }
            }
        });

        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_search.setText("");
                adapter.clearSearch();
                img_delete.setVisibility(View.GONE);
            }
        });
        getNews();
    }

    private void getNews() {
        dialog.show();
        final APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

//        Call<String> news = apiInterface.getNews("google-news", "dd411b383145472ca9bbff28d0cfe05f");
//        news.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                String responseValue = response.body();
//                try {
//                    JSONObject responseObject = new JSONObject(responseValue);
//                    JSONArray responseArray = responseObject.getJSONArray("articles");
//
//                    articles = new ArrayList<>();
//                    for (int i = 0; i < responseArray.length(); i++) {
//                        Article newsArticle = Article.parseJsonObject(responseArray.optJSONObject(i));
//                        articles.add(newsArticle);
//                        adapter = new CustomAdapter(MainActivity.this, getApplicationContext(), articles);
//                        recyclerView.setAdapter(adapter);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Error!!!", Toast.LENGTH_SHORT).show();
//            }
//        });
        Map<String, Object> params = new HashMap<>();
        params.put("sources", "google-news");
        params.put("apiKey", "dd411b383145472ca9bbff28d0cfe05f");
        Call<Result> news = apiInterface.getNews(params);
        news.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result responseValue = response.body();
                ArrayList<Article> newsArticles = responseValue.articles;
                adapter = new CustomAdapter(MainActivity.this, MainActivity.this, newsArticles);
                recyclerView.setAdapter(adapter);
                dialog.hide();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                dialog.hide();
            }
        });
    }

    private void getNewsByCategory(String category) {
        dialog.show();
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Map<String, Object> paremeters = new HashMap<>();
        paremeters.put("category", category);
        paremeters.put("apiKey", "dd411b383145472ca9bbff28d0cfe05f");
        Call<Result> newsBYCategories = apiInterface.getNews(paremeters);
        newsBYCategories.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result responseValue = response.body();
                ArrayList<Article> newsArticles = responseValue.articles;
                adapter = new CustomAdapter(MainActivity.this, MainActivity.this, newsArticles);
                recyclerView.setAdapter(adapter);
                dialog.hide();

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                dialog.hide();
            }
        });


    }

    @Override
    public void onNewsCategoryClick(String category) {
        getNewsByCategory(category);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int pos = spinner.getSelectedItemPosition();
        String country_item = String.valueOf(news_country.get(pos));
        getCountryPostion(country_item);

    }

    private void getCountryPostion(String country) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Map<String, Object> paremeters = new HashMap<>();
        paremeters.put("country", country);
        paremeters.put("apiKey", "dd411b383145472ca9bbff28d0cfe05f");
        Call<Result> newsBYCategories = apiInterface.getNews(paremeters);
        newsBYCategories.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result responseValue = response.body();
                ArrayList<Article> newsArticles = responseValue.articles;
                adapter = new CustomAdapter(MainActivity.this, MainActivity.this, newsArticles);
                recyclerView.setAdapter(adapter);
                dialog.hide();

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                dialog.hide();
            }
        });
        ArrayAdapter<CharSequence> list_country = ArrayAdapter.createFromResource(MainActivity.this, R.array.country_name, android.R.layout.simple_spinner_item);
        list_country.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(list_country);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}