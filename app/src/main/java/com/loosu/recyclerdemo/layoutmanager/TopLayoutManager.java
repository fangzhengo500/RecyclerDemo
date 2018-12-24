package com.loosu.recyclerdemo.layoutmanager;

import android.support.v7.widget.RecyclerView;
import android.view.View;


public class TopLayoutManager extends RecyclerView.LayoutManager {
    private static final String TAG = "TopLayoutManager";

    private int mOffset = 0;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout() || state.getItemCount() == 0) {
            return;
        }

        layoutChildrenWithOffset(recycler);
    }

    private void layoutChildrenWithOffset(RecyclerView.Recycler recycler) {
        detachAndScrapAttachedViews(recycler);

        int currentPosition = findCurrentPosition();
        if (currentPosition != RecyclerView.NO_POSITION) {
            layoutChildWithOffset(recycler.getViewForPosition(currentPosition), mOffset);
        }

        int nextPosition = findNextPosition();
        if (nextPosition != RecyclerView.NO_POSITION) {
            layoutChildWithOffset(recycler.getViewForPosition(nextPosition), 0);
        }
    }

    private void layoutChildWithOffset(View child, int offset) {
        addView(child, 0);
        measureChildWithMargins(child, 0, 0);

        int decoratedMeasuredWidth = getDecoratedMeasuredWidth(child);
        int decoratedMeasuredHeight = getDecoratedMeasuredHeight(child);

        int left = (getWidth() - decoratedMeasuredWidth) / 2;
        int top = (getHeight() - decoratedMeasuredHeight) / 2 - offset % decoratedMeasuredHeight;
        int right = left + decoratedMeasuredWidth;
        int bottom = top + decoratedMeasuredHeight;
        layoutDecoratedWithMargins(child, left, top, right, bottom);
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int travel = dy;

        if (travel + mOffset < 0) {
            travel = -mOffset;
        } else if (travel + mOffset > getItemCount() * getHeight() - getHeight()) {
            travel = getItemCount() * getHeight() - getHeight() - mOffset;
        }
        mOffset += travel;
        layoutChildrenWithOffset(recycler);
        return travel;
    }

    @Override
    public void scrollToPosition(int position) {
        super.scrollToPosition(position);
    }

    public int findCurrentPosition() {
        int position = mOffset / getHeight();
        if (position < 0 || position >= getItemCount()) {
            return RecyclerView.NO_POSITION;
        }
        return position;
    }

    public int findNextPosition() {
        int currentPosition = findCurrentPosition();
        if (currentPosition == RecyclerView.NO_POSITION) {
            return RecyclerView.NO_POSITION;
        }
        int nextPosition = currentPosition + 1;
        if (nextPosition < 0 || nextPosition >= getItemCount()) {
            return RecyclerView.NO_POSITION;
        }
        return nextPosition;
    }
}