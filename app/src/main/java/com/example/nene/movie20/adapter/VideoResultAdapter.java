package com.example.nene.movie20.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.nene.movie20.R;
import com.example.nene.movie20.data.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoResultAdapter extends BaseQuickAdapter<Video, BaseViewHolder> {

    public VideoResultAdapter(int layoutResId, @Nullable List<Video> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Video item) {
        ImageView imageView = helper.getView(R.id.movie_cover);
        Picasso.get().load(item.getImg()).into(imageView);
        helper.setText(R.id.video_search_title, item.getName());
        helper.setText(R.id.video_search_content, item.getContent());
        helper.setImageResource(R.id.eye, R.drawable.eye);
        helper.setText(R.id.number, item.getNumber());

    }
}