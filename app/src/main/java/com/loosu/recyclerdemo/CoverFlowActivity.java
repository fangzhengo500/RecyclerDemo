package com.loosu.recyclerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.loosu.recyclerdemo.adapter.base.recyclerview.ARecyclerAdapter;
import com.loosu.recyclerdemo.adapter.base.recyclerview.RecyclerHolder;
import com.loosu.recyclerdemo.utils.ResouceUtil;
import com.loosu.recyclerdemo.widget.MyHorizontalRefreshLayout;

import java.util.List;

import xiao.free.horizontalrefreshlayout.HorizontalRefreshLayout;
import xiao.free.horizontalrefreshlayout.RefreshCallBack;
import xiao.free.horizontalrefreshlayout.refreshhead.LoadingRefreshHeader;

public class CoverFlowActivity extends AppCompatActivity implements RefreshCallBack {

    private RecyclerView mViewList;
    private CarouselLayoutManager mLayoutManager;
    private MyHorizontalRefreshLayout refreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_flow);
        refreshLayout = (MyHorizontalRefreshLayout) findViewById(R.id.refresh);
        mViewList = (RecyclerView) findViewById(R.id.view_list);

        refreshLayout.setRefreshCallback(this);
        refreshLayout.setRefreshHeader(new LoadingRefreshHeader(this), HorizontalRefreshLayout.LEFT);
        refreshLayout.setRefreshHeader(new LoadingRefreshHeader(this), HorizontalRefreshLayout.RIGHT);

        mViewList.addOnScrollListener(new CenterScrollListener());  // CarouselLayoutManager：

        mViewList.setAdapter(new Adapter());

    }

    @Override
    public void onLeftRefreshing() {
        refreshLayout.onRefreshComplete();
    }

    @Override
    public void onRightRefreshing() {
        refreshLayout.onRefreshComplete();
    }

    private static class Adapter extends ARecyclerAdapter<Integer> {

        public Adapter() {
            super(ResouceUtil.getImagesAsList());
        }

        @Override
        protected void onBindViewData(RecyclerHolder holder, int position, List<Integer> datas) {
            //holder.setImageResource(R.id.iv_image, getItem(position));
//            ImageView imageView = holder.getView(R.id.iv_image);
//            Glide.with(imageView)
//                    .load(getItem(position))
//                    .into(imageView);
        }

        @Override
        protected int getItemLayoutId(int viewType) {
            return R.layout.item_cover_flow;
        }

        @Override
        public Integer getItem(int position) {
            return mDatas.get(position);
        }
    }
}
