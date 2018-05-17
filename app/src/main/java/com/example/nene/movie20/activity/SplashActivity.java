package com.example.nene.movie20.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.nene.movie20.R;

public class SplashActivity extends Activity {
    boolean isFirstIn = false;
    private Intent intent;
    public static String Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final SharedPreferences sharedPreferences = getSharedPreferences("is_first_in_data",MODE_PRIVATE);
        isFirstIn = sharedPreferences.getBoolean("isFirstIn", true);

        SharedPreferences sharedPreferences1 = getSharedPreferences("Token" , 0);
        Token = sharedPreferences1.getString("Token", "");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFirstIn){
                    intent = new Intent(SplashActivity.this,intro.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }else {
                    intent = new Intent(SplashActivity.this,MainActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }
            }
        },2000);

//        new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message message) {
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
//                return false;
//            }
//        }).sendEmptyMessageDelayed(0, 2000);
    }
}
