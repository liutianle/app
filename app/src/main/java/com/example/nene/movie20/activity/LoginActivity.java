package com.example.nene.movie20.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nene.movie20.R;
import com.example.nene.movie20.models.Token;
import com.example.nene.movie20.utils.GetTokenUtils;

import retrofit2.http.GET;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn1 = (Button) this.findViewById(R.id.button);

        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });


        Button btn2 = (Button) this.findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences =getSharedPreferences("Token" , 0);
                String Token = preferences.getString("Token" , "");



                EditText editText_username = findViewById(R.id.editText1);
                EditText editText_password = findViewById(R.id.editText2);
                GetTokenUtils.getToken(editText_username.getText().toString(),editText_password.getText().toString());
                preferences = getSharedPreferences("Token",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Token", GetTokenUtils.Token);
                editor.putBoolean("or", false);
                editor.commit();
            }
        });

    }

}
