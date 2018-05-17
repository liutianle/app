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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
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

public class VideoSearchActivity extends AppCompatActivity{

    private TagFlowLayout flowLayout;
    private EditText searchBar;
    private Intent intent;
    private String search_result;
    private String[] mtags = new String[]{"种植业", "水产业", "农副业", "畜牧业", "农资业"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movie);

        flowLayout = findViewById(R.id.tag);
        searchBar = findViewById(R.id.searchBar);


        initView();
        initSearch();
    }

    private void initSearch() {
        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    search_result = searchBar.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("text", search_result);
                    intent = new Intent(VideoSearchActivity.this, VideoSearchResultActivity.class);
                    intent.putExtras(bundle);
                    intent.putExtra("search_result", search_result);
                    startActivity(intent);

                    return true;
                }
                return false;
            }
        });
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
                Bundle bundle = new Bundle();

                switch (position) {
                    case 0: bundle.putString("kind", "plant");; break;
                    case 1: bundle.putString("kind", "aquaculture"); ; break;
                    case 2: bundle.putString("kind", "agri_and_sideline_industries"); break;
                    case 3: bundle.putString("kind", "animal"); break;
                    case 4: bundle.putString("kind", "agri_industry"); break;
                }

                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
        });
    }


}


