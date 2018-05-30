package com.example.nene.movie20.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.example.nene.movie20.Interface.UserInfInterface;
import com.example.nene.movie20.R;
import com.example.nene.movie20.data.CardBean;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.User;
import com.example.nene.movie20.models.User_profile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminSettingActivity extends AppCompatActivity {
    private final int IS_GET_USER_INFORMATION = 1;
    private Intent intent;
    private LinearLayout exit_login;
    private ImageView back;
    private TimePickerView pvTime;
    private TextView admin_birth;
    private TextView admin_adddress;
    private TextView admin_sex;
    private TextView admin_nickname;
    private CircleImageView imageView;
    private OptionsPickerView pvOptions;
    private ArrayList<String> sex;
    private ArrayList<CardBean> cardItem = new ArrayList<>();
    private BottomSheetDialog dialog;
    private SharedPreferences sharedPreferences;
    private Handler handler;
    private User_profile user_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        admin_birth = findViewById(R.id.admin_birth);
        admin_adddress = findViewById(R.id.admin_address);
        admin_nickname = findViewById(R.id.admin_nickname);
        admin_sex = findViewById(R.id.admin_sex);
        imageView = findViewById(R.id.user_img);


        getUserInf();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        //先加载数据
                        getCardData();
                        admin_birth.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.show(v);
                            }
                        });

                        admin_adddress.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDialog();
                            }
                        });

                        admin_nickname.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showNameDialog();
                            }
                        });


                        admin_sex.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.show();
                            }
                        });

                        initView();
                        initTime();
                        initSex();
                        break;
                }
                return true;
            }
        });

    }

    private void showNameDialog() {
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.review_dialog, null);
        final EditText text = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button submit = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);

        View parent = (View) commentView.getParent();
        BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0, 0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserInf();
                String name = text.getText().toString().trim();
                user_profile.setNick_name(name);
                admin_nickname.setText(name);
                modifyUserInf(user_profile);
            }
        });

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!TextUtils.isEmpty(s) && s.length() > 2) {
                    submit.setBackgroundColor(Color.parseColor("#FFB568"));
                } else {
                    submit.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dialog.show();
    }

    private void showDialog() {
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.review_dialog, null);
        final EditText text = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button submit = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);

        View parent = (View) commentView.getParent();
        BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0, 0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserInf();
                String address = text.getText().toString().trim();
                admin_adddress.setText(address);
                user_profile.setAddress(address);
                modifyUserInf(user_profile);
            }
        });

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!TextUtils.isEmpty(s) && s.length() > 2) {
                    submit.setBackgroundColor(Color.parseColor("#FFB568"));
                } else {
                    submit.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dialog.show();
    }

    private void initSex() {
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                getUserInf();
                String tx = cardItem.get(options1).getPickerViewText();
                user_profile.setSex(tx);
                admin_sex.setText(tx);
                modifyUserInf(user_profile);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);

                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.returnData();
                                pvOptions.dismiss();
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.dismiss();
                            }
                        });
                    }
                })
                .isDialog(true)
                .build();

        pvOptions.setPicker(cardItem);//添加数据


    }

    public void initTime() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                user_profile.setBirth(getTime(date));
                admin_birth.setText(format.format(getTime(date)));
                Log.i("pvTime", "onTimeSelect");
            }

        }).build();
    }

    public Date getTime(Date date) {
        Log.d("getTime", "getTime: " + date.getTime());
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return date;
    }


    private void getCardData() {
        int i = 1;
        cardItem.add(new CardBean(i, "男"));
        cardItem.add(new CardBean(i, "女"));
        cardItem.add(new CardBean(i, "保密"));
    }

    private void initView() {
        exit_login = findViewById(R.id.exit_login);
        exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AdminSettingActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void modifyUserInf(User_profile user_profile) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInfInterface userInfInterface = retrofit.create(UserInfInterface.class);

        sharedPreferences = getSharedPreferences("Token", 0);

        Call<User_profile> call = userInfInterface.getModifyInformation("JWT " + sharedPreferences.getString("Token", ""),
                new File(user_profile.image), user_profile.birth, user_profile.sex, user_profile.address, user_profile.nick_name);

        call.enqueue(new Callback<User_profile>() {
            @Override
            public void onResponse(Call<User_profile> call, Response<User_profile> response) {

            }

            @Override
            public void onFailure(Call<User_profile> call, Throwable t) {

            }
        });
    }

    public void getUserInf() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SharedPreferences sharedPreferences = getSharedPreferences("Token", 0);
        final UserInfInterface userInfInterface = retrofit.create(UserInfInterface.class);
        Call<User> call = userInfInterface.getinformation("JWT " + sharedPreferences.getString("Token", ""), "1");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                admin_birth.setText(format.format(response.body().getUser_profile().getBirth()));
                admin_nickname.setText(response.body().getUser_profile().getNick_name());
                admin_adddress.setText(response.body().getUser_profile().getAddress());
                admin_sex.setText(response.body().getUser_profile().getSex());
                user_profile = new User_profile();
                user_profile = response.body().getUser_profile();
                Glide.with(AdminSettingActivity.this).load(response.body().getUser_profile().getImage()).into(imageView);

                Message msg = new Message();
                msg.what = IS_GET_USER_INFORMATION;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

}



