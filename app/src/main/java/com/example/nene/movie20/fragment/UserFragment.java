package com.example.nene.movie20.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.nene.movie20.Interface.UserInfInterface;
import com.example.nene.movie20.R;
import com.example.nene.movie20.activity.AdminSettingActivity;
import com.example.nene.movie20.activity.SplashActivity;
import com.example.nene.movie20.adapter.AdminSectionAdapter;
import com.example.nene.movie20.data.AdminSection;
import com.example.nene.movie20.data.DataServer;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nene on 2018/4/13.
 */

public class UserFragment extends Fragment {
    private static UserFragment instance = null;
    private View view;
    private RecyclerView recyclerView;
    private List<AdminSection> data = DataServer.getAdminData();
    private AdminSectionAdapter adminSectionAdapter;
    private Intent intent;

    public static Fragment newInstance() {
        if (instance == null){
            instance = new UserFragment();
        }
        return instance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
        recyclerView = view.findViewById(R.id.user_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        initView();
        initClick();
        return view;
    }

    private void initClick() {
        ImageView setting = view.findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), AdminSettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        getUserInf();
        adminSectionAdapter = new AdminSectionAdapter(R.layout.admin_item, R.layout.admin_head, data);

        adminSectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        recyclerView.setAdapter(adminSectionAdapter);
    }

    public void getUserInf() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Token", Context.MODE_PRIVATE);
        UserInfInterface userInfInterface = retrofit.create(UserInfInterface.class);
        Call<User> call = userInfInterface.getinformation("JWT " + sharedPreferences.getString("Token", ""), "1");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                TextView user_nickname = view.findViewById(R.id.user_nickname1);
                TextView user_role = view.findViewById(R.id.user_rowid);
                CircleImageView user_img = view.findViewById(R.id.user_image);
                 user_nickname.setText(response.body().getUser_profile().getNick_name());
                switch (response.body().getUser_profile().getNick_name()) {
                    case "student" : user_role.setText("学生");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
