package com.example.nene.movie20.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.nene.movie20.R;
import com.example.nene.movie20.data.Video;
import com.example.nene.movie20.data.MySection;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nene on 2018/4/16.
 */

public class VideoSectionAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder>{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public VideoSectionAdapter(int layoutResId, int sectionHeadResId, List<MySection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MySection item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.moreVideo, item.isMore());
        helper.addOnClickListener(R.id.moreVideo);
    }

    @Override
    protected void convert(BaseViewHolder helper, MySection item) {
        Video video = item.t;
//        helper.setImageResource(R.id.movie_header,R.drawable.video);
        ImageView imageView = helper.getView(R.id.movie_cover);
        Picasso.get().load(video.getImg()).into(imageView);
//        helper.setImageResource(R.id.movie_cover, video.getImg());
        helper.setText(R.id.construction, video.getName());
        helper.setImageResource(R.id.eye, R.drawable.eye);
        helper.setText(R.id.number, video.getNumber());
    }

}
