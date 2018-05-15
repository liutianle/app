package com.example.nene.movie20.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.nene.movie20.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder;
import com.jaeger.library.StatusBarUtil;

public class intro extends AppIntro implements ISlideBackgroundColorHolder {
    private LinearLayout mainLayout;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFadeAnimation();
        StatusBarUtil.setTransparent(intro.this);
        mainLayout = findViewById(R.id.main);

        addSlide(AppIntroFragment.newInstance("在线课堂","专业知识在线培训",R.drawable.intro_1,Color.parseColor("#FFD39B")));
        addSlide(AppIntroFragment.newInstance("论坛广场","专家坐镇论坛讨论",R.drawable.intro_2,Color.parseColor("#4F94CD")));
        addSlide(AppIntroFragment.newInstance("农业管理","实时环境信息监控",R.drawable.intro_3,Color.parseColor("#CC9999")));
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(intro.this,MainActivity.class);
        startActivity(intent);

        sharedPreferences = this.getSharedPreferences("is_first_in_data", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean("isFirstIn",false);
        editor.commit();
        SharedPreferences preferences = this.getSharedPreferences("Token", 0);
        boolean is_login = preferences.getBoolean("is_Login", true);
        if(is_login){
            Intent intent1 = new Intent(intro.this, LoginActivity.class);
            intro.this.startActivity(intent1);
            intro.this.finish();
        } else {
            Intent intent2 = new Intent(intro.this, MainActivity.class);
            intro.this.startActivity(intent2);
            intro.this.finish();
        }

        this.finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(intro.this,MainActivity.class);
        startActivity(intent);

        sharedPreferences = this.getSharedPreferences("is_first_in_data", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean("isFirstIn",false);
        editor.commit();
        SharedPreferences preferences = this.getSharedPreferences("Token", 0);
        boolean is_login = preferences.getBoolean("is_Login", true);
        if(is_login){
            Intent intent1 = new Intent(intro.this, LoginActivity.class);
            intro.this.startActivity(intent1);
        } else {
            Intent intent2 = new Intent(intro.this, MainActivity.class);
            intro.this.startActivity(intent2);
        }

        this.finish();
    }

    @Override
    public int getDefaultBackgroundColor() {
        return getResources().getColor(R.color.white);
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        if (mainLayout != null) {
            mainLayout.setBackgroundColor(backgroundColor);
        }
    }
}
