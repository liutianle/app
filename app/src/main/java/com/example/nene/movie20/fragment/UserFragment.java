package com.example.nene.movie20.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.nene.movie20.R;
import com.example.nene.movie20.activity.AdminSettingActivity;
import com.example.nene.movie20.adapter.AdminSectionAdapter;
import com.example.nene.movie20.data.AdminSection;
import com.example.nene.movie20.data.DataServer;

import java.util.List;

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
        adminSectionAdapter = new AdminSectionAdapter(R.layout.admin_item, R.layout.admin_head, data);

        adminSectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        recyclerView.setAdapter(adminSectionAdapter);
    }
}
