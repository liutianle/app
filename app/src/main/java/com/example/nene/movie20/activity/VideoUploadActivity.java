package com.example.nene.movie20.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.RemoteController;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.nene.movie20.Interface.Upload_VideoInterface;
import com.example.nene.movie20.R;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.Token;
import com.example.nene.movie20.models.VideoInf;
import com.example.nene.movie20.utils.VideoUtils;
import com.qiniu.android.common.AutoZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoUploadActivity extends AppCompatActivity {

    private ImageView btn_upload_video;
    private static final int REQUEST_CODE_CHOOSE = 23;
    private MaterialEditText materialEditText;
    private List<Uri> videoResult;
    private String token;
    private final int IS_GET_UPLOAD_VIDEO_TOKEN = 1;
    private Handler handler;
    private VideoInf videoInf;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK){
            videoResult = Matisse.obtainResult(data);
            String key = getRealPathFromUri(this, videoResult.get(0)).substring(getRealPathFromUri(this, videoResult.get(0)).lastIndexOf("/") + 1);

            uploadVideo(getRealPathFromUri(this, videoResult.get(0)), key);

        }
    }

    public static String getRealPathFromUri(Context context, Uri contentUri){
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Video.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            //获得视频路径
            int column_index = cursor.getColumnIndex(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            //android中数据库处理使用cursor时，游标不是放在为0的下标，而是放在为-1的下标处开始的。
            return cursor.getString(column_index);
        }finally {
            if (cursor != null){
                cursor.close();
            }
        }
    }

    public void uploadVideo(final String data, final String key){
        getUploadToken();
        Configuration configuration = new Configuration.Builder()
                .zone(AutoZone.autoZone)
                .build();
        final UploadManager uploadManager = new UploadManager(configuration);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        uploadManager.put(data, key, token, new UpCompletionHandler() {
                            @Override
                            public void complete(String key, ResponseInfo info, JSONObject response) {
                                if (info.isOK()){
                                    Log.i("qiniuiyun", "complete: done");
                                }else {
                                    Log.i("qiniuyun", "complete: fail");
                                }
                                Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + response);
                            }
                        }, null);
                        break;
                }
                return false;
            }
        });
    }

    private void getUploadToken() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Upload_VideoInterface upload_videoInterface = retrofit.create(Upload_VideoInterface.class);

        Call<Token> call = upload_videoInterface.getUpload_Video();
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                token = response.body().getToken();
                System.out.println(token);
                Message msg = new Message();
                msg.what = IS_GET_UPLOAD_VIDEO_TOKEN;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });
    }
}
