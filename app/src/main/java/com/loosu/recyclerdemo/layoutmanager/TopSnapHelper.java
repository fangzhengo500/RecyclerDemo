package com.loosu.recyclerdemo.layoutmanager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import com.loosu.recyclerdemo.utils.KLog;

public class TopSnapHelper extends SnapHelper {
    private static final String TAG = "TopSnapHelper";

    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view) {
        KLog.d(TAG, "view.top = " + view.getTop() + ", view.bottom = " + view.getBottom());
        if (layoutManager instanceof TopLayoutManager) {
            int centerY = (view.getTop() + view.getBottom()) / 2;
            int standerY = view.getHeight() / 2;

            if (centerY > standerY) {
                return new int[]{0, view.getTop()};
            } else if (centerY < standerY) {
                return new int[]{0, view.getTop()};
            } else {
                return new int[]{0, 0};
            }
        }
        return new int[]{0, 0};
    }

    @Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        KLog.d(TAG, "layoutManager = " + layoutManager);
        if (layoutManager instanceof TopLayoutManager) {
            int currentPosition = ((TopLayoutManager) layoutManager).findCurrentPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                return layoutManager.findViewByPosition(currentPosition);
            }
        }
        return null;
    }

    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        KLog.d(TAG, "velocityX = " + velocityX + ", velocityY = " + velocityY);
        return 0;
    }
}
