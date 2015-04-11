package com.example.codetribe1.unischool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.codetribe1.unischool.adaptors.NewsAdaptor;
import com.example.codetribe1.unischool.dto.NewsDTO;
import com.example.codetribe1.unischool.dto.transfer.ResponseDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends ActionBarActivity {
    ListView newslistView;
    Context ctx;
    NewsAdaptor newsAdaptor;
    //Url address
    String feedUrl = "http://10.50.75.94:8080/usp/StudentServlet?JSON={requestType:2,schoolID:4}";
    List<NewsDTO> news = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newslistView = (ListView) findViewById(R.id.newslistView);



//        for(int i=0;i<5000;i++){
//            NewsDTO DTO = new NewsDTO(i,"String Title "+i,"String subTitle "+i,"School Trip to the far North of South Where everyone is going to enjoy. School Trip to the far North of South Where everyone is going to enjoy School Trip to the far North of South Where everyone is going to enjoy. School Trip to the far North of South Where everyone is going to enjoy",0.23,0.23,1);
//            news.add(DTO);
//        }

        if(news ==null){
            news = new ArrayList<>();
        }

        ctx = getApplicationContext();
        newsAdaptor = new NewsAdaptor(ctx,news);
        newslistView.setAdapter(newsAdaptor);

        //-----------------------------Volley to get a list of News-----------------------------------------

        RequestQueue rq = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, feedUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray NewsJSONArray = response.getJSONArray("newsList");
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
                        news.add(N);
                    }




                } catch (JSONException e) {

                }
                newsAdaptor.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(ctx, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(jsonObjectRequest);
        //----------------------------------------------------------------------------------------------------



        newslistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsDTO item = (NewsDTO) newsAdaptor.getItem(position);

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




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
