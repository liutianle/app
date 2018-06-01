package com.example.nene.movie20.utils;

import com.example.nene.movie20.Interface.UserFavVideoInterface;
import com.example.nene.movie20.Interface.UserInfInterface;
import com.example.nene.movie20.R;
import com.example.nene.movie20.models.Constant;

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

        Call<Response> call = userFavVideoInterface.getVideoId(id);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    public void deleteFavVideo (int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserFavVideoInterface userFavVideoInterface =  retrofit.create(UserFavVideoInterface.class);

        Call<Response> call = userFavVideoInterface.getDelVideoid(id);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    public void getFavList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserFavVideoInterface userFavVideoInterface = retrofit.create(UserFavVideoInterface.class);

        Call<Response> call = userFavVideoInterface.getFavVideoList();

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}
