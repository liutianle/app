package com.example.nene.movie20.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nene.movie20.Interface.GetDocInterface;
import com.example.nene.movie20.Interface.UserInfInterface;
import com.example.nene.movie20.R;
import com.example.nene.movie20.adapter.DocAdapter;
import com.example.nene.movie20.data.DataServer;
import com.example.nene.movie20.data.Doc;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.DocInf;
import com.example.nene.movie20.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nene on 2018/4/13.
 */

public class frag_doc extends Fragment{
    private static frag_doc instance = null;
    private RecyclerView docRecyclerView;
    private DocAdapter docAdapter;
    private List<Doc> data;

    public static Fragment newInstance() {
        if (instance == null){
            instance = new frag_doc();
        }
        return instance;
    }
    public frag_doc(){

    }

    @Override
    public void onResume() {
        super.onResume();

        data = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDocInterface getDocInterface = retrofit.create(GetDocInterface.class);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Token", Context.MODE_PRIVATE);
//        Call<DocInf> call = getDocInterface.getPage("1","15");

        Call<DocInf> call = getDocInterface.getPage("JWT " + sharedPreferences.getString("Token", ""), "1", "15");

        call.enqueue(new Callback<DocInf>() {
            @Override
            public void onResponse(Call<DocInf> call, Response<DocInf> response) {
                for (DocInf.DocResultBean v : response.body().getResults()){
                    data.add(new Doc(v.getFile_name(), v.getFile_type(), v.getText_type()));
                }
                docAdapter.setNewData(data);
            }

            @Override
            public void onFailure(Call<DocInf> call, Throwable t) {

            }
        });
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
