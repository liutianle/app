package com.example.nene.movie20.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by nene on 2018/4/13.
 */

public class AdminFragment extends Fragment {
    private static AdminFragment instance = null;

    public static Fragment newInstance() {
        if (instance == null){
            instance = new AdminFragment();
        }
        return instance;
    }
}
