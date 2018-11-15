package com.loosu.recyclerdemo.adapter;

import android.support.annotation.Nullable;
import android.widget.RadioButton;

import com.loosu.recyclerdemo.R;
import com.loosu.recyclerdemo.adapter.base.recyclerview.ARecyclerAdapter;
import com.loosu.recyclerdemo.adapter.base.recyclerview.RecyclerHolder;
import com.loosu.recyclerdemo.widget.dialog.ReportDialog;

import java.util.List;


public class ReportDialogTypeItemAdapter extends ARecyclerAdapter<ReportDialog.Item> {


    private int mSelectedIndex;

    public ReportDialogTypeItemAdapter(@Nullable List<ReportDialog.Item> datas) {
        super(datas);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_report_dialog_type;
    }

    @Override
    protected void onBindViewData(RecyclerHolder holder, int position, List<ReportDialog.Item> datas) {
        ReportDialog.Item item = getItem(position);
        holder.setText(R.id.rb, item.getTile());

        RadioButton rb = holder.getView(R.id.rb);
        rb.setChecked(mSelectedIndex == position);
    }

    @Override
    public ReportDialog.Item getItem(int position) {
        return mDatas.get(position);
    }

    public void setItemSelected(int position) {
        mSelectedIndex = position;
        notifyDataSetChanged();
    }
}
