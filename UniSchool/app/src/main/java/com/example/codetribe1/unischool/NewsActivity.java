package com.example.codetribe1.unischool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.codetribe1.unischool.adaptors.NewsAdaptor;
import com.example.codetribe1.unischool.app.MyApplication;
import com.example.codetribe1.unischool.dto.NewsDTO;
import com.example.codetribe1.unischool.dto.transfer.ResponseDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener {

    Context ctx;
    private String TAG = NewsActivity.class.getSimpleName();
    private String url = "http://geoff.coolpage.biz/all_news.php";
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private NewsAdaptor adapter;
    private List<NewsDTO> news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listView = (ListView) findViewById(R.id.newslistView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        news = new ArrayList<>();
        ctx = getApplicationContext();
        adapter = new NewsAdaptor(ctx,news);
        listView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        fetchNews();
                                    }
                                }
        );

    }

    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {
        fetchNews();
    }

    /**
     * Fetching movies json by making http call
     */
    private void fetchNews() {

        // showing refresh animation before making http call
        swipeRefreshLayout.setRefreshing(true);


        // Volley's json array request object

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray NewsJSONArray = response.getJSONArray("News");
                    ResponseDTO responseDTO = new ResponseDTO();

                    for (int i=0; i<NewsJSONArray.length();i++){
                        //NewsTitleArray.add(NewsJSONArray.getJSONObject(i).getString("title"));
                        NewsDTO N = new NewsDTO();
                        N.setTitle(NewsJSONArray.getJSONObject(i).getString("title"));
                        N.setSubTitle(NewsJSONArray.getJSONObject(i).getString("subTitle"));
                        N.setDetails(NewsJSONArray.getJSONObject(i).getString("details"));
                        N.setLatitude(Double.parseDouble(NewsJSONArray.getJSONObject(i).getString("latitude")));
                        N.setLongitude(Double.parseDouble(NewsJSONArray.getJSONObject(i).getString("longitude")));
                        N.setSchoolID(Integer.parseInt(NewsJSONArray.getJSONObject(i).getString("schoolID")));
                        N.setDatePublished(NewsJSONArray.getJSONObject(i).getString("datePubliched"));
                        news.add(N);
                    }




                } catch (JSONException e) {
                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                }
                adapter.notifyDataSetChanged();

                // stopping swipe refresh
                swipeRefreshLayout.setRefreshing(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Server Error: " + error.getMessage());

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                // stopping swipe refresh
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(jsonObjectRequest);
        //----------------------------------------------------------------------------------------------------

        //--------------------handling the onclick of a button------------------------------------------------
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsDTO item = (NewsDTO) adapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(),DetailedNewsActivity.class);
                //-------get the current date-----------
                Time today = new Time(Time.getCurrentTimezone());
                today.setToNow();
                String Date = today.monthDay + "/" + today.month + "/" + today.year;

                Bundle b = new Bundle();
                b.putString("title",item.getTitle());
                b.putString("subtitle",item.getSubTitle());
                b.putString("date",Date);
                b.putString("details",item.getDetails());
                intent.putExtra("NewsBundle",b);
                startActivity(intent);
            }
        });
        //------------------------------------------------------------------------------------------------
    }



}