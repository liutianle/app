package com.example.nene.movie20.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

public class ExpandableListViewHelper extends ExpandableListView implements NestedScrollingChild {
    private NestedScrollingChildHelper scrollingChildHelper;

    public ExpandableListViewHelper(Context context, AttributeSet attrs) {
        super(context, attrs);
        scrollingChildHelper = new NestedScrollingChildHelper(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            setNestedScrollingEnabled(true);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >>2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        scrollingChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return scrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return scrollingChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        scrollingChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return scrollingChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
        return scrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
        return scrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return scrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return scrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }
}
