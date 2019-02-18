package com.hamitao.kids.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.view.TitleView;

import butterknife.BindView;
import butterknife.OnClick;

public class TimeActivity extends BaseMsgActivity {
    @BindView(R.id.btn_switch_data_time)
    ImageView btnSwitchDataTime;
    @BindView(R.id.rl_auto_data_time)
    RelativeLayout rlAutoDataTime;

    private boolean isSwitch = false;
    private TitleView mTitle;

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_time);
    }

    @Override
    public void initData() {
        mTitle = new TitleView(this);
        mTitle.setTitle(getStrByRes(R.string.time));

        isSwitch = MyApp.getInstance().getSpManager().getAutoDataTime();
        setBtnSwitchDataTime(isSwitch);

    }


    //设置自动日期和时间的开关
    private void setBtnSwitchDataTime(boolean autoSwitch) {
        btnSwitchDataTime.setBackgroundResource(autoSwitch ? R.drawable.icon_turn_on : R.drawable.icon_turn_off);

    }

    @OnClick({R.id.btn_switch_data_time, R.id.tv_alarm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_switch_data_time://自动日期和时间开关
                isSwitch = isSwitch ? false : true;
                setBtnSwitchDataTime(isSwitch);
                MyApp.getInstance().getSpManager().putAutoDataTime(isSwitch);
                break;
            case R.id.tv_alarm://闹钟
                openActivity(AlarmActivity.class);
                break;
        }
    }

}
