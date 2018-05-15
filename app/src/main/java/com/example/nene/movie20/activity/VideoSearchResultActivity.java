package com.example.nene.movie20.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.nene.movie20.R;
import com.example.nene.movie20.adapter.VideoListAdapter;
import com.example.nene.movie20.adapter.VideoResultAdapter;
import com.example.nene.movie20.data.DataServer;
import com.example.nene.movie20.data.Video;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class VideoSearchResultActivity extends AppCompatActivity {

    private Intent intent;
    private RecyclerView recyclerView;
    private VideoResultAdapter videoResultAdapter;
    private TextView setText;
    private List<Video> data = DataServer.getVideoResultData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_search_result);

        initView();
        initText();
    }

    private void initText() {
        setText = findViewById(R.id.search_bar_result);
        intent = getIntent();
        String result = intent.getStringExtra("search_result");
        setText.setText(result);
    }

    private void initView() {
        recyclerView = findViewById(R.id.video_search_result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        videoResultAdapter = new VideoResultAdapter(R.layout.content_video_search_result, data);
        recyclerView.setAdapter(videoResultAdapter);
    }

}
