package com.example.nene.movie20.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.nene.movie20.R;
import com.example.nene.movie20.adapter.StudyFragmentAdapter;

import java.lang.reflect.Field;

import static com.example.nene.movie20.R.dimen.*;

/**
 * Created by nene on 2018/4/13.
 */

public class StudyFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_study, container, false);
        tabLayout = view.findViewById(R.id.top_tab);
        viewPager = view.findViewById(R.id.top_viewpager);

        StudyFragmentAdapter studyFragmentAdapter = new StudyFragmentAdapter(getActivity().getSupportFragmentManager());
        studyFragmentAdapter.addFragment(frag_movie.newInstance(),"学习视频");
        studyFragmentAdapter.addFragment(frag_doc.newInstance(),"学习文档");
        studyFragmentAdapter.addFragment(frag_exam.newInstance(),"在线考试");
        viewPager.setAdapter(studyFragmentAdapter);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText("学习视频"));
        tabLayout.addTab(tabLayout.newTab().setText("学习文档"));
        tabLayout.addTab(tabLayout.newTab().setText("在线考试"));
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}