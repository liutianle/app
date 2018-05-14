package com.example.nene.movie20.utils;

import android.os.Handler;
import android.os.Message;

import com.example.nene.movie20.Interface.LoginInterface;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sam- on 2018/5/3.
 */

public class GetTokenUtils {
    public static String Token;
    public static void getToken(String Username, String Password){
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final LoginInterface request = retrofit.create(LoginInterface.class);

        //对 发送请求 进行封装
        Call<Token> call = request.getCall(Username, Password);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Token>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                // 步骤7：处理返回的数据结果
                if (response.isSuccessful()) {
                    Token = response.body().getToken();
                } else {
                    System.out.println("错误");
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Token> call, Throwable throwable) {
                System.out.println("失败");
            }
        });
    }
}
