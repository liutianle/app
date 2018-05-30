package com.example.nene.movie20.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.nene.movie20.R;
import com.example.nene.movie20.data.Exam;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ExamHomeAdapter extends BaseQuickAdapter<Exam, BaseViewHolder> {
    public ExamHomeAdapter(int layoutResId, @Nullable List<Exam> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Exam item) {
        ImageView imageView = helper.getView(R.id.exam_title);
        Picasso.get().load(item.getExam_logo()).into(imageView);
        helper.setText(R.id.exam_name, item.getExam_name());
        helper.setText(R.id.test_have_done, item.getTest_finish_number());
        helper.setText(R.id.test_total, item.getTest_number());
    }
}
