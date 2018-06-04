package com.example.nene.movie20.activity;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.media.RemoteController;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.nene.movie20.R;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class VideoUploadActivity extends AppCompatActivity {
    private ImageView btn_upload_video;
    private static final int REQUEST_CODE_CHOOSE = 23;
    private MaterialEditText materialEditText;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_video_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        materialEditText = findViewById(R.id.input);
        materialEditText.clearFocus();
        btn_upload_video = findViewById(R.id.upload_video);
        btn_upload_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadVideo();
            }
        });
    }

    private void uploadVideo() {
        RxPermissions rxPermissions = new RxPermissions(VideoUploadActivity.this);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        Matisse.from(VideoUploadActivity.this)
                                .choose(MimeType.ofVideo())
                                .countable(true)
                                .maxSelectable(1)
                                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85f)
                                .imageEngine(new GlideEngine())
                                .forResult(REQUEST_CODE_CHOOSE);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
