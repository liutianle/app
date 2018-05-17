package com.example.nene.movie20.data;

import com.chad.library.adapter.base.entity.SectionEntity;

public class AdminSection extends SectionEntity<Admin> {
    private boolean isMore;

    public AdminSection(boolean isHeader, String header, boolean isMroe) {
        super(isHeader, header);
        this.isMore = isMroe;
    }

    public AdminSection(Admin t) {
        super(t);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean mroe) {
        isMore = mroe;
    }
}
