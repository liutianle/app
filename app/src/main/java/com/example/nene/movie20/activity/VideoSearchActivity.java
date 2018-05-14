package com.example.nene.movie20.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nene.movie20.R;
import com.example.nene.movie20.data.Video;
import com.example.nene.movie20.models.VideoUrlInf;
import com.example.nene.movie20.utils.VideoUtils;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;


/**
 * Created by nene on 2018/4/16.
 */

public class VideoSearchActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener, MaterialSearchBar.OnSearchActionListener{

    private TagFlowLayout flowLayout;
    private MaterialSearchBar searchBar;
    private Intent intent;
    private String[] mtags = new String[]{"种植业", "水产业", "农副业", "畜牧业", "农资业"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movie);

        flowLayout = findViewById(R.id.tag);

        searchBar = findViewById(R.id.searchBar);
        VideoUtils.getSearchVideo(searchBar.getText());
        searchBar.setOnSearchActionListener(this);
        searchBar.setCardViewElevation(10);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("LOG_TAG", getClass().getSimpleName() + " text changed " + searchBar.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        initView();
    }

    private void initView() {
        final LayoutInflater mInflater = LayoutInflater.from(VideoSearchActivity.this);

        flowLayout.setAdapter(new TagAdapter<String>(mtags) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) mInflater.inflate(R.layout.search_tag, flowLayout, false);
                textView.setText(s);
                return textView;
            }
        });
        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                intent = new Intent(VideoSearchActivity.this, VideoListActivity.class);

                startActivity(intent);
                return true;
            }
        });
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {

    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode){
            case MaterialSearchBar.BUTTON_BACK:
            searchBar.disableSearch();
            break;
        }
    }
}


