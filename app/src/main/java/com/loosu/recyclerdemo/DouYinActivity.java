package com.loosu.recyclerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.loosu.recyclerdemo.adapter.base.recyclerview.ARecyclerAdapter;
import com.loosu.recyclerdemo.adapter.base.recyclerview.RecyclerHolder;
import com.loosu.recyclerdemo.utils.ResouceUtil;
import com.loosu.recyclerdemo.widget.myplayer.MyPlayer;

import java.util.List;

public class DouYinActivity extends AppCompatActivity {
    private RecyclerView mViewList;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douyin);
        findView(savedInstanceState);
        initView(savedInstanceState);
    }

    private void findView(Bundle savedInstanceState) {
        mViewList = (RecyclerView) findViewById(R.id.view_list);
    }

    private void initView(Bundle savedInstanceState) {
        mLayoutManager = new LinearLayoutManager(this);
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
            MyPlayer myPlayer = holder.getView(R.id.video_view);
            String url = "https://res.exexm.com/cw_145225549855002";
            myPlayer.setUp(url, true, "");
        }

        @Override
        protected int getItemLayoutId(int viewType) {
            return R.layout.item_douyin;
        }

        @Override
        public Integer getItem(int position) {
            return mDatas.get(position);
        }
    }
}
