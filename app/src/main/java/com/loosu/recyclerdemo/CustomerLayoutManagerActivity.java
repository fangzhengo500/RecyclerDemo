package com.loosu.recyclerdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.loosu.recyclerdemo.adapter.base.recyclerview.ARecyclerAdapter;
import com.loosu.recyclerdemo.adapter.base.recyclerview.RecyclerHolder;
import com.loosu.recyclerdemo.layoutmanager.CoverFlowLayoutManager;
import com.loosu.recyclerdemo.layoutmanager.RickRightLayoutManager;
import com.loosu.recyclerdemo.layoutmanager.TopLayoutManager;
import com.loosu.recyclerdemo.utils.ResouceUtil;

import java.util.List;
import java.util.Random;

public class CustomerLayoutManagerActivity extends AppCompatActivity {

    private RecyclerView mViewList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout_manager);
        findView(savedInstanceState);

    }

    private void findView(Bundle savedInstanceState) {
        mViewList = findViewById(R.id.view_list);
        mViewList.setAdapter(new Adapter());

        //mViewList.setLayoutManager(new LinearLayoutManager(this));
        mViewList.setLayoutManager(new TopLayoutManager());
    }

    private static class Adapter extends ARecyclerAdapter<Integer> {

        public Adapter() {
            super(ResouceUtil.getImagesAsList());
        }

        @Override
        protected void onBindViewData(RecyclerHolder holder, int position, List<Integer> datas) {
            //holder.setImageResource(R.id.iv_image, getItem(position));
            Random random = new Random();
            CardView cardView = holder.getView(R.id.card_view);
            cardView.setCardBackgroundColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            holder.setText(R.id.tv_text, String.valueOf(position));
        }

        @Override
        protected int getItemLayoutId(int viewType) {
            return R.layout.item_custom_layout_manager;
        }

        @Override
        public Integer getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    private static class LayoutManager extends RecyclerView.LayoutManager {
        private static final String TAG = "LayoutManager";

        @Override
        public RecyclerView.LayoutParams generateDefaultLayoutParams() {
            return new RecyclerView.LayoutParams(100, 100);
        }

        int mTotleHeight;

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            detachAndScrapAttachedViews(recycler);

            int offsetY = 0;
            int itemCount = getItemCount();
            for (int i = 0; i < itemCount; i++) {
                View scrap = recycler.getViewForPosition(i);
                addView(scrap);

                measureChildWithMargins(scrap, 0, 0);

                int perItemWidth = getDecoratedMeasuredWidth(scrap);
                int perItemHeight = getDecoratedMeasuredHeight(scrap);

                int left = 0;
                int top = offsetY;
                int right = left + perItemWidth;
                int bottom = top + perItemHeight;

                layoutDecorated(scrap, left, top, right, bottom);

                offsetY += perItemHeight;
            }
            mTotleHeight = offsetY;
        }

        @Override
        public boolean canScrollVertically() {
            return true;
        }

        int mVerticallScrollOffset = 0;

        @Override
        public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
            // 实际滑动的距离
            int travel = dy;

            if (mVerticallScrollOffset + travel < 0) {
                travel = -mVerticallScrollOffset;
            } else if (mVerticallScrollOffset + travel > mTotleHeight - getHeight()) {
                travel = mTotleHeight - getHeight() - mVerticallScrollOffset;
            }
            offsetChildrenVertical(-travel);
            mVerticallScrollOffset += travel;
            return travel;
        }
    }
}
