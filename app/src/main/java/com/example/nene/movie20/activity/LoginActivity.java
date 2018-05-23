package com.example.nene.movie20.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nene.movie20.Interface.LoginInterface;
import com.example.nene.movie20.Interface.UserInfInterface;
import com.example.nene.movie20.R;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.Token;
import com.example.nene.movie20.models.User;
import com.example.nene.movie20.utils.GetTokenUtils;
import com.jaeger.library.StatusBarUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class LoginActivity extends AppCompatActivity {
    public final int IS_LOGIN = 1;
    SharedPreferences preferences;
    private TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setColor(LoginActivity.this, getResources().getColor(R.color.white));
        TextView btn1 = this.findViewById(R.id.button);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        Button btn2 = (Button) this.findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = getSharedPreferences("Token", 0);
                EditText editText_username = findViewById(R.id.editText1);
                EditText editText_password = findViewById(R.id.editText2);
//                GetTokenUtils.getToken(editText_username.getText().toString(),editText_password.getText().toString()); //步骤4:创建Retrofit对象
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constant.BaseUrl) // 设置 网络请求 Url
                        .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                        .build();

                // 步骤5:创建 网络请求接口 的实例
                final LoginInterface request = retrofit.create(LoginInterface.class);

                //对 发送请求 进行封装
                final Call<Token> call = request.getCall(editText_username.getText().toString(), editText_password.getText().toString());

                call.enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        if (response.isSuccessful()) {
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.remove("Token");
                            editor.putString("Token", response.body().getToken());
                            editor.putBoolean("is_Login", false);
                            editor.commit();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            LoginActivity.this.startActivity(intent);
                            LoginActivity.this.finish();
                        } else {
                            System.out.println("登陆失败");
                        }
                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {

                    }
                });
            }
        });
    }
}
