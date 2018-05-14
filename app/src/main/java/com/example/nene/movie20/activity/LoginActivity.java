package com.example.nene.movie20.activity;

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
import com.example.nene.movie20.utils.GetTokenUtils;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button bt_username_clear;
    private Button bt_pwd_clear;
    private Button forgive_pwd;
    private Button bt_pwd_eye;
    private Button login;
    private Button register;
    private boolean isOpen = false;

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
                EditText editText_username = (EditText)findViewById(R.id.editText1);
                EditText editText_password = (EditText)findViewById(R.id.editText2);
                GetTokenUtils.getToken(editText_username.getText().toString(),editText_password.getText().toString());
            }
        });

    }

}
