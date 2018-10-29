package com.loosu.recyclerdemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
        }
    }

    private void jump2Activity(Class<? extends Activity> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }
}
