package com.example.nene.movie20.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.nene.movie20.R;
import com.example.nene.movie20.data.Doc;

import java.util.List;

public class DocAdapter extends BaseQuickAdapter <Doc, BaseViewHolder> {
    public DocAdapter(int layoutResId, @Nullable List<Doc> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Doc item) {
        helper.setText(R.id.docName, item.getDocName());
        helper.setText(R.id.docClass, item.getDocClass());
        switch (item.getDocType()){
            case "ppt":
                helper.setImageResource(R.id.docImg, R.drawable.doc_ppt);
                break;
            case "excel":
                helper.setImageResource(R.id.docImg, R.drawable.doc_excel);
                break;
            case "pdf":
                helper.setImageResource(R.id.docImg, R.drawable.doc_pdf);
                break;
            case "word":
                helper.setImageResource(R.id.docImg, R.drawable.doc_word);
                break;
        }

    }
}
