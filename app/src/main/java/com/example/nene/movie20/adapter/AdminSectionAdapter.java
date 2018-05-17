package com.example.nene.movie20.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.nene.movie20.R;
import com.example.nene.movie20.data.Admin;
import com.example.nene.movie20.data.AdminSection;

import java.util.List;

public class AdminSectionAdapter extends BaseSectionQuickAdapter<AdminSection, BaseViewHolder>{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public AdminSectionAdapter(int layoutResId, int sectionHeadResId, List<AdminSection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, AdminSection item) {
        helper.setText(R.id.admin_title, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdminSection item) {
        Admin admin = item.t;
        helper.setImageResource(R.id.admin_logo, admin.getLogo_img());
        helper.setText(R.id.admin_item, admin.getLogo_text());

    }
}
