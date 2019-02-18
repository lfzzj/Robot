package com.hamitao.kids.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.hamitao.kids.R;

public class BatteryShowView extends RelativeLayout {
    private Context context;

    public BatteryShowView(Context context) {
        super(context, null);
    }

    public BatteryShowView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public BatteryShowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_power_connect_view, this);

    }
}
