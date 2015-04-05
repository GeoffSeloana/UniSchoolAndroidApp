package com.example.codetribe1.unischool;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailedNewsActivity extends Activity {
    TextView D_titleView;
    TextView D_subTitleView;
    TextView D_details;
    TextView D_date;
    ImageView imageView;

    Context mCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);

        D_titleView = (TextView) findViewById(R.id.D_titleTextView);
        D_subTitleView = (TextView) findViewById(R.id.D_subtitleTextView);
        D_details = (TextView) findViewById(R.id.D_descriptionTextView);
        D_date = (TextView) findViewById(R.id.D_date);
        imageView = (ImageView) findViewById(R.id.D_image);

        //---------Logo Animation--------------Logo Animation-----
        Animation fadeIn = new AlphaAnimation(0f,10f);
        //Animation fadeIn = AnimationUtils.loadAnimation(mCtx, R.anim.grow_fade_in_center);
        fadeIn.setDuration(5000);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                imageView.setVisibility(View.VISIBLE);
            }
        });

        imageView.startAnimation(fadeIn);
        imageView.setVisibility(View.VISIBLE);

        //set from intent
        Bundle b = this.getIntent().getBundleExtra("NewsBundle");
        D_titleView.setText(b.getString("title"));
        D_subTitleView.setText(b.getString("subtitle"));
        D_details.setText(b.getString("details"));
        D_date.setText(b.getString("date"));





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detailed_news, menu);
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
