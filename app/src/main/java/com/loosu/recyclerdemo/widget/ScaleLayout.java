package com.loosu.recyclerdemo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class ScaleLayout extends FrameLayout {

    private int mWidthScale = 0;
    private int mHeightScale = 0;

    public ScaleLayout(@NonNull Context context) {
        super(context);
    }

    public ScaleLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScaleLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAspectScale(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Size cannot be negative.");
        }

        mWidthScale = width;
        mHeightScale = height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (0 == mWidthScale || 0 == mHeightScale) {
            setMeasuredDimension(width, height);

        } else {
            if (width < height * mWidthScale / mHeightScale) {
                int finalHeight = width * mHeightScale / mWidthScale;
                setMeasuredDimension(width, finalHeight);
                measureChildren(MeasureSpec.makeMeasureSpec(width, widthMode), MeasureSpec.makeMeasureSpec(finalHeight, heightMode));

            } else {
                int finalWidth = height * mWidthScale / mHeightScale;
                setMeasuredDimension(finalWidth, height);
                measureChildren(MeasureSpec.makeMeasureSpec(finalWidth, widthMode), MeasureSpec.makeMeasureSpec(height, heightMode));

            }
        }
    }
}