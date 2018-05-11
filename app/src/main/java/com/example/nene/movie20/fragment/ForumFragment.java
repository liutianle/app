package com.example.nene.movie20.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nene.movie20.R;

/**
 * Created by nene on 2018/4/13.
 */

public class ForumFragment extends Fragment {
    private static ForumFragment instance = null;
    private View view;

    public static Fragment newInstance() {
        if (instance == null){
            instance = new ForumFragment();
        }
        return instance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_test, container, false);

        return view;
    }
}
