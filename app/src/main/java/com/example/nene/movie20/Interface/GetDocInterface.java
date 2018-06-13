package com.example.nene.movie20.Interface;

import com.example.nene.movie20.models.DocInf;
import com.example.nene.movie20.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDocInterface {
//    @GET("user/{id}/")
//    Call<User> getinformation(@Header("Authorization") String token, @Path("id") String id);
    @GET("text/")
    Call<DocInf> getPage (@Header("Authorization") String token,
                          @Query("page") String page,
                          @Query("page_size") String page_size);
    @HTTP(method = "GET", path = "text/{id}/", hasBody = false)
    Call<DocInf> getId(@Path("id") int id);
    @GET("text/")
    Call<DocInf> getSearch(@Query("search") String search);
    @GET("text/")
    Call<DocInf> getForm(@Query("file_type") String file_type);
    @GET("text/")
    Call<DocInf> getKind(@Query("text_type") String text_type);

}
