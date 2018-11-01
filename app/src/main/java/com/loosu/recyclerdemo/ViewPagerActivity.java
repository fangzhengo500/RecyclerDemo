package com.loosu.recyclerdemo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.loosu.recyclerdemo.adapter.base.recyclerview.ARecyclerAdapter;
import com.loosu.recyclerdemo.adapter.base.recyclerview.RecyclerHolder;
import com.loosu.recyclerdemo.utils.ResouceUtil;

import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private RecyclerView mViewList;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        findView(savedInstanceState);
        initView(savedInstanceState);
    }

    private void findView(Bundle savedInstanceState) {
        mViewList = (RecyclerView) findViewById(R.id.view_list);
    }

    private void initView(Bundle savedInstanceState) {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mViewList.setLayoutManager(mLayoutManager);
        mViewList.setAdapter(new Adapter());
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mViewList);
    }

    private static class Adapter extends ARecyclerAdapter<Integer> {

        public Adapter() {
            super(ResouceUtil.getImagesAsList());
        }

        @Override
        protected void onBindViewData(RecyclerHolder holder, int position, List<Integer> datas) {
            //holder.setImageResource(R.id.iv_image, getItem(position));
            ImageView imageView = holder.getView(R.id.iv_image);
            Glide.with(imageView)
                    .load(getItem(position))
                    .into(imageView);
        }

        @Override
        protected int getItemLayoutId(int viewType) {
            return R.layout.item_viewpager;
        }

        @Override
        public Integer getItem(int position) {
            return mDatas.get(position);
        }
    }
}
