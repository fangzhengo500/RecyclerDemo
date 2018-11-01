package com.loosu.recyclerdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import xiao.free.horizontalrefreshlayout.HorizontalRefreshLayout;

public class MyHorizontalRefreshLayout extends HorizontalRefreshLayout {

    private boolean isRefreshAble = true;

    public MyHorizontalRefreshLayout(Context context) {
        super(context);
    }

    public MyHorizontalRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorizontalRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return isRefreshAble && super.onInterceptHoverEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return isRefreshAble && super.onTouchEvent(event);
    }

    public boolean isRefreshAble() {
        return isRefreshAble;
    }

    public void setRefreshAble(boolean refreshAble) {
        isRefreshAble = refreshAble;
    }
}
