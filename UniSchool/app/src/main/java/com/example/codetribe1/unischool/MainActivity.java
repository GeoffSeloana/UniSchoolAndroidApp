package com.example.codetribe1.unischool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {
    Button signin ;
    Button register;
    Button existing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //---------Logo Animation--------------Logo Animation-----
        final View logo= findViewById(R.id.logo);
        Animation fadeIn = new AlphaAnimation(0f,10f);
        fadeIn.setDuration(10000);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                logo.setVisibility(View.VISIBLE);
            }
        });

        logo.startAnimation(fadeIn);
        logo.setVisibility(View.VISIBLE);
        //--------***------***-----***-------***------***-------
        Button btnSignIn = (Button)findViewById(R.id.REG_btnExisting);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        existing = (Button) findViewById(R.id.REG_btnExisting);
        existing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main);
                RelativeLayout login_form = (RelativeLayout) findViewById(R.id.login_form);

                mainLayout.setVisibility(View.GONE);
                login_form.setVisibility(View.VISIBLE);

            }
        });

        register = (Button) findViewById(R.id.REG_btnNewGroup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main);
                RelativeLayout reg_form = (RelativeLayout) findViewById(R.id.register_form);

                mainLayout.setVisibility(View.GONE);
                reg_form.setVisibility(View.VISIBLE);

            }
        });

        signin = (Button) findViewById(R.id.login_BTN);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NewsActivity.class));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
