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
import com.example.nene.movie20.adapter.DocAdapter;
import com.example.nene.movie20.data.DataServer;
import com.example.nene.movie20.data.Doc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nene on 2018/4/13.
 */

public class frag_doc extends Fragment{
    private static frag_doc instance = null;
    private RecyclerView docRecyclerView;
    private DocAdapter docAdapter;
    private List<Doc> data = DataServer.getDocData();

    public static Fragment newInstance() {
        if (instance == null){
            instance = new frag_doc();
        }
        return instance;
    }
    public frag_doc(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_doc, container, false);

        docRecyclerView = view.findViewById(R.id.doc_list);
        docRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        docAdapter = new DocAdapter(R.layout.content_doc_home, data);

        docRecyclerView.setAdapter(docAdapter);
        return view;
    }
}
