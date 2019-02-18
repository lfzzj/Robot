package com.hamitao.kids.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.kids.R;
import com.hamitao.kids.model.WifiBean;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * @data on 2018/6/27 11:36
 * @describe: wifi
 */

public class WifiAdapter extends BGARecyclerViewAdapter<WifiBean> {

    public WifiAdapter(RecyclerView recyclerView ) {
        super(recyclerView, R.layout.item_wifi);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, WifiBean model) {

        int level = model.getWifiLevel();
        String SSID = model.getWifiName();
        boolean isConn = model.isConn();
        setWifiLevel(level, helper.getView(R.id.iv_level));
        helper.getTextView(R.id.tv_ssid).setText(SSID);
        if (isConn) {
            helper.getTextView(R.id.tv_conn_state).setVisibility(View.VISIBLE);
        } else {
            helper.getTextView(R.id.tv_conn_state).setVisibility(View.GONE);
        }
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClickListener(position);
                }
            }
        });
    }

    public interface OnWifiClickListener {
        void onClickListener(int position);
    }

    private OnWifiClickListener onClickListener;

    public void setWifiItemClickListener(OnWifiClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * 设置WiFi信号等级 根据信号值来判断
     *
     * @param level
     * @return 2-->很强   1-->正常   0-->无信号
     */
    private void setWifiLevel(int level, ImageView imageView) {
        if (level >= -40) {//很强
            imageView.setBackgroundResource(R.drawable.icon_connect_wifi_3);
        } else if (level < -40 && level > -85) {//正常
            imageView.setBackgroundResource(R.drawable.icon_connect_wifi_2);
        } else {//很弱
            imageView.setBackgroundResource(R.drawable.icon_connect_wifi_1);
        }

    }
}
