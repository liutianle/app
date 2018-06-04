package com.example.nene.movie20.Interface;

import com.example.nene.movie20.models.Token;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Upload_VideoInterface {
    @GET("upload_video/")
    Call<Token> getUpload_Video();
}
