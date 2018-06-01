package com.example.nene.movie20.utils;

import com.example.nene.movie20.Interface.UserFavVideoInterface;
import com.example.nene.movie20.Interface.UserInfInterface;
import com.example.nene.movie20.R;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.VideoUrlInf;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserfavVideoUtils {
    public void addFavVideo(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserFavVideoInterface userFavVideoInterface = retrofit.create(UserFavVideoInterface.class);

        Call<String> call = userFavVideoInterface.getVideoId("JWT", id);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void deleteFavVideo (int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserFavVideoInterface userFavVideoInterface =  retrofit.create(UserFavVideoInterface.class);

        Call<String> call = userFavVideoInterface.getDelVideoid("JWT", id);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

//    public void getFavList(){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constant.BaseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        UserFavVideoInterface userFavVideoInterface = retrofit.create(UserFavVideoInterface.class);
//
//        Call<List<VideoUrlInf>> call = userFavVideoInterface.getFavVideoList("JWT ");
//
//        call.enqueue(new Callback<List<VideoUrlInf>>() {
//            @Override
//            public void onResponse(Call<List<VideoUrlInf>> call, Response<List<VideoUrlInf>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<List<VideoUrlInf>> call, Throwable t) {
//
//            }
//        });
//    }
}
