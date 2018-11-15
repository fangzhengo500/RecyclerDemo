package com.loosu.recyclerdemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.loosu.recyclerdemo.widget.dialog.ReportDialog;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_viewpager:
                jump2Activity(ViewPagerActivity.class);
                break;
            case R.id.btn_douyin:
                jump2Activity(DouYinActivity.class);
                break;
            case R.id.btn_layout_manager:
                jump2Activity(CustomerLayoutManagerActivity.class);
                break;
            case R.id.btn_cover_flow:
                jump2Activity(CoverFlowActivity.class);
                break;
            case R.id.btn_show_dialog:
                showDialog();
                break;
        }
    }

    private void jump2Activity(Class<? extends Activity> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }


    private ReportDialog mReportDialog;
    /**
     * 举报对话框事件监听
     */
    private final ReportDialog.OnContentListener mContentListener = new ReportDialog.OnContentListener() {
        @Override
        public void onDismiss(DialogFragment dialog) {
            mReportDialog = null;
        }

        @Override
        public void onBtnSubmitClick(DialogFragment dialog, ReportDialog.Item item, CharSequence others) {
            dialog.dismiss();
            int type = 0;
            if (item != null) {
                type = item.getId();
            }
        }
    };

    private void showDialog() {

        Map<Integer, String> items = new HashMap<Integer, String>();
        items.put(1, "侵犯版权");
        items.put(2, "色情低俗");
        items.put(3, "垃圾内容");
        items.put(4, "传播邪教");
        items.put(5, "素质低下");
        items.put(6, "违法犯罪");
        items.put(7, "政治敏感");
        ReportDialog.Builder builder = new ReportDialog.Builder();
        if (items != null) {
            for (Map.Entry<Integer, String> entry : items.entrySet()) {
                builder.addItem(new ReportDialog.Item(entry.getKey(), entry.getValue()));
            }
        }
        mReportDialog = builder.build();
        mReportDialog.setContentListener(mContentListener);
        mReportDialog.show(getSupportFragmentManager(), "");
    }
}
