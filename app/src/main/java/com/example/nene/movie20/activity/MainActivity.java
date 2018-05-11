package com.example.nene.movie20.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import com.example.nene.movie20.R;
import com.example.nene.movie20.adapter.ViewPagerAdapter;
import com.example.nene.movie20.fragment.AdminFragment;
import com.example.nene.movie20.fragment.ForumFragment;
import com.example.nene.movie20.fragment.StudyFragment;
import com.example.nene.movie20.fragment.UserFragment;
import com.example.nene.movie20.utils.BottomNavigationViewHelper;
import com.example.nene.movie20.utils.ButtonNavigationSwiping;
import com.jaeger.library.StatusBarUtil;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ButtonNavigationSwiping viewPager;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.colorPrimary));
        navigationView = findViewById(R.id.navigation);
        viewPager = findViewById(R.id.main_viewpager);

        initView();
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            BottomNavigationViewHelper.disableShiftMode(navigationView);
        }

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new StudyFragment());
        adapter.addFrag(new ForumFragment());
        adapter.addFrag(new AdminFragment());
        adapter.addFrag(new UserFragment());

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);
        //一定要预加载3项，不然切换后会出现空白

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_study:
                        viewPager.setCurrentItem(0);//学堂
                        return true;
                    case R.id.navigation_forum:
                        viewPager.setCurrentItem(1);//论坛
                        return true;
                    case R.id.navigation_admin:
                        viewPager.setCurrentItem(2);//管理
                        return true;
                    case R.id.navigation_user:
                        viewPager.setCurrentItem(3);//用户
                        return true;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null){
                    menuItem.setChecked(false);
                }else {
                    navigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //禁止滑动
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });


    }


}