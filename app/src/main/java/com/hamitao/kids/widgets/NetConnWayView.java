package com.hamitao.kids.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hamitao.kids.R;

public class NetConnWayView extends RelativeLayout {

    private ImageView ivNetIcon;
    private TextView tvNetWay;

    public NetConnWayView(Context context) {
        super(context);
    }

    public NetConnWayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_net_conn_way, this);
        ivNetIcon = findViewById(R.id.iv_net_icon);
        tvNetWay = findViewById(R.id.tv_net_way);
    }

    public void setNetConnWay(int icon, String name) {
        ivNetIcon.setBackgroundResource(icon);
        tvNetWay.setText(name);
    }



}
