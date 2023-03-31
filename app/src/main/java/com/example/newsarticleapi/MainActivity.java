package com.example.newsarticleapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView mrecyclerview;
    ArrayList<ArticleModel>list = new ArrayList<>();
    NewsArticleAdapter articleAdapter;
   // String  url = "https://newsapi.org/v2/everything?q=tesla&from=2023-02-17&sortBy=publishedAt&apiKey=a99da10496994bef923774f6901042cb";
    String url = "https://newsapi.org/v2/everything?q=tesla&from=2023-02-28&sortBy=publishedAt&apiKey=ad5ac31814c647abbeb9b503343b5844";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mrecyclerview = findViewById(R.id.RV_reycler);
        articleAdapter = new NewsArticleAdapter(MainActivity.this,list);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerview.setAdapter(articleAdapter);
        
        getData();
    }

    private void getData() {
        RequestQueue mRequestQueue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray getArticle = response.getJSONArray("articles");

                    for (int i = 0 ; i<getArticle.length(); i ++){
                        JSONObject object = getArticle.getJSONObject(i);

                        String author = object.getString("author");
                        String title = object.getString("title");
                        String desc = object.getString("description");
                        String url = object.getString("urlToImage");

                        ArticleModel model = new ArticleModel(title,author,desc,url);
                        list.add(model);



                    }
                    articleAdapter = new NewsArticleAdapter(MainActivity.this,list);
                    mrecyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    mrecyclerview.setAdapter(articleAdapter);


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String>header=new HashMap<String, String>();
                header.put("User-Agent","Mozilla/5.0");
                return header;
            }
        };
        mRequestQueue.add(objectRequest);


    }
}