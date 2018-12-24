package com.loosu.recyclerdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.loosu.recyclerdemo.adapter.base.recyclerview.ARecyclerAdapter;
import com.loosu.recyclerdemo.adapter.base.recyclerview.RecyclerHolder;
import com.loosu.recyclerdemo.layoutmanager.TopLayoutManager;
import com.loosu.recyclerdemo.layoutmanager.TopSnapHelper;
import com.loosu.recyclerdemo.utils.ResouceUtil;

import java.util.List;
import java.util.Random;

public class CustomerLayoutManagerActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mViewList;

    private EditText mEtScrollToPosition;
    private View mBtnScrollToPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout_manager);
        findView(savedInstanceState);
        initView(savedInstanceState);
        initListener(savedInstanceState);
    }

    private void findView(Bundle savedInstanceState) {
        mViewList = findViewById(R.id.view_list);
        mViewList.setAdapter(new Adapter());

        mEtScrollToPosition = findViewById(R.id.et_scroll_to_position);
        mBtnScrollToPosition = findViewById(R.id.btn_scroll_to_position);
    }

    private void initView(Bundle savedInstanceState) {
        mViewList.setLayoutManager(new TopLayoutManager());
        TopSnapHelper snapHelper = new TopSnapHelper();
        snapHelper.attachToRecyclerView(mViewList);
    }

    private void initListener(Bundle savedInstanceState) {
        mBtnScrollToPosition.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scroll_to_position:
                onClickBtnScrollToPosition();
                break;
        }
    }

    private void onClickBtnScrollToPosition() {
        int position = -1;
        try {
            position = Integer.valueOf(mEtScrollToPosition.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mViewList.scrollToPosition(position);
    }

    private static class Adapter extends ARecyclerAdapter<Integer> {

        public Adapter() {
            super(ResouceUtil.getImagesAsList());
        }

        @Override
        protected void onBindViewData(RecyclerHolder holder, int position, List<Integer> datas) {
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
}
