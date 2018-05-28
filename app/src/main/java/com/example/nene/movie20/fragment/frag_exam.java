package com.example.nene.movie20.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nene.movie20.R;
import com.example.nene.movie20.adapter.ExamHomeAdapter;
import com.example.nene.movie20.data.DataServer;
import com.example.nene.movie20.data.Exam;

import java.util.List;

/**
 * Created by nene on 2018/4/13.
 */

public class frag_exam extends Fragment{
    private RecyclerView recyclerView;
    private ExamHomeAdapter examHomeAdapter;
    private List<Exam> data = DataServer.getExamData();

    private static frag_exam instance = null;
    public static Fragment newInstance(){
        if (instance == null){
            instance = new frag_exam();
        }
        return instance;
    }
    public frag_exam(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_exam, container, false);

        recyclerView = view.findViewById(R.id.test_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        examHomeAdapter = new ExamHomeAdapter(R.layout.content_exam_home, data);

        recyclerView.setAdapter(examHomeAdapter);
        return view;

    }
}
