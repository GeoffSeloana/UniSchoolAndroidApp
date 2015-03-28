package com.example.codetribe1.unischool;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.codetribe1.unischool.adaptors.NewsAdaptor;
import com.example.codetribe1.unischool.dto.NewsDTO;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends ActionBarActivity {
    ListView newslistView;
    Context ctx;
    NewsAdaptor newsAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newslistView = (ListView) findViewById(R.id.newslistView);

        List<NewsDTO> news = new ArrayList<>();

        for(int i=0;i<10;i++){
            NewsDTO DTO = new NewsDTO(i,"String Title "+i,"String subTitle "+i,"School Trip to the far North of South Where everyone is going to enjoy. School Trip to the far North of South Where everyone is going to enjoy School Trip to the far North of South Where everyone is going to enjoy. School Trip to the far North of South Where everyone is going to enjoy",0.23,0.23,1);
            news.add(DTO);
        }

        if(news ==null){
            news = new ArrayList<>();
        }

        ctx = getApplicationContext();
        newsAdaptor = new NewsAdaptor(ctx,news);
        newslistView.setAdapter(newsAdaptor);




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
