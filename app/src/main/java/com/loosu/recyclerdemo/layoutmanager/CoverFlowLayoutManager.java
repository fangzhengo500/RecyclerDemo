package com.loosu.recyclerdemo.layoutmanager;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;


public class CoverFlowLayoutManager extends RecyclerView.LayoutManager {
    private static final String TAG = "CoverFlowLayoutManager";

    private int mVisibleItemCount = 2;

    private SparseArray<Rect> mAllItemFrames = new SparseArray<>();
    private SparseBooleanArray mHasAttachedItems = new SparseBooleanArray();

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d(TAG, "onLayoutChildren: recycler=" + recycler + ", state=" + state);
        if (getItemCount() <= 0 || state.isPreLayout()) {
            return;
        }

        mAllItemFrames.clear();
        mHasAttachedItems.clear();
        detachAndScrapAttachedViews(recycler);

        View scrap = recycler.getViewForPosition(0);
        addView(scrap);
        measureChildWithMargins(scrap, 0, 0);

        int decoratedMeasuredWidth = getDecoratedMeasuredWidth(scrap);
        int decoratedMeasuredHeight = getDecoratedMeasuredHeight(scrap);
        Log.i(TAG, "decoratedMeasuredWidth = " + decoratedMeasuredWidth);
        Log.i(TAG, "decoratedMeasuredHeight = " + decoratedMeasuredHeight);

        int startX = (getHorizontalSpace() - decoratedMeasuredWidth) / 2;
        int startY = (getVerticalSpace() - decoratedMeasuredHeight) / 2;

        int offset = startX;
        for (int i = 0; i < getItemCount(); i++) {
            Rect frame = mAllItemFrames.get(i);
            if (frame == null) {
                frame = new Rect();
            }

            int left = offset;
            int top = startY;
            int right = left + decoratedMeasuredWidth;
            int bottom = top + decoratedMeasuredHeight;
            frame.set(left, top, right, bottom);

            mAllItemFrames.put(i, frame);
            mHasAttachedItems.put(i, false);

            offset += getIntervalDistance();
        }

        layoutItems(recycler, state);
    }

    private void layoutItems(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state) {
        Log.d(TAG, "layoutItems: recycler = " + recycler + " , state = " + state);
        Rect displayFrame = new Rect(0, 0, getHorizontalSpace(), getVerticalSpace());
        Log.d(TAG, "displayFrame = " + displayFrame);

        int position = 0;
        int childCount = getChildCount();
        Log.d(TAG, "childCount = " + childCount);
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            position = getPosition(childAt);
            Log.d(TAG, "position = " + position);

            Rect frame = mAllItemFrames.get(i);
            if (!Rect.intersects(displayFrame, frame)) {
                removeAndRecycleView(childAt, recycler);
                mHasAttachedItems.put(i, false);
            } else {
                layoutItem(childAt, frame);
                mHasAttachedItems.put(i, true);
            }
        }

        int itemCount = getItemCount();
        for (int i = 0; i < itemCount; i++) {
            Rect rect = mAllItemFrames.get(i);
            if (Rect.intersects(displayFrame, rect) && !mHasAttachedItems.get(i)) {
                View scrap = recycler.getViewForPosition(i);
                measureChildWithMargins(scrap, 0, 0);
                addView(scrap,0);
                layoutItem(scrap, rect);
                mHasAttachedItems.put(i, true);
            }
        }
    }

    private void layoutItem(View child, Rect frame) {
        layoutDecorated(child,
                frame.left,
                frame.top,
                frame.right,
                frame.bottom);
    }

    /**
     * 获取item间的间隔
     */
    private float getIntervalDistance() {
        return 50;
    }

    private int getHorizontalSpace() {
        return getWidth() - getPaddingRight() - getPaddingLeft();
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }
}
