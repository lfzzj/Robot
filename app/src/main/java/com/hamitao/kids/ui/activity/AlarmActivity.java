package com.hamitao.kids.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.hamitao.aispeech.model.AlarmInfo;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.kids.R;
import com.hamitao.kids.adapter.AlarmAdapter;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.callback.OnItemActionListener;
import com.hamitao.kids.utils.DataUtil;
import com.hamitao.kids.view.TitleView;
import com.hamitao.kids.widgets.LeftSwipeMenuRecyclerView;
import com.hamitao.requestframe.entity.QueryTimerAlarmInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 闹钟
 */
public class AlarmActivity extends BaseMsgActivity {
    private TitleView mTitle;

    @BindView(R.id.rv_alarm)
    LeftSwipeMenuRecyclerView rvAlarm;
    private List<AlarmInfo> mDatas;
    private AlarmAdapter mAdapter;

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_alarm);
    }

    @Override
    public void initData() {
        mTitle = new TitleView(this);
        mTitle.setTitle(getStrByRes(R.string.alarm));

        publicPresenter.queryTimerAlarm(MyApp.getInstance().getSpManager().getTerminalId());
        mDatas = new ArrayList<>();
        rvAlarm.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AlarmAdapter(mContext, mDatas);
        rvAlarm.setAdapter(mAdapter);

        rvAlarm.setOnItemActionListener(new OnItemActionListener() {
            //点击
            @Override
            public void OnItemClick(int position) {

            }

            //删除
            @Override
            public void OnItemDelete(int position) {
                String timerid = mDatas.get(position).getTimerid();
                publicPresenter.delTimerAlarm(MyApp.getInstance().getSpManager().getTerminalId(), timerid);
                mDatas.remove(position);
                //更新数据源
                mAdapter.notifyDataSetChanged();


            }
        });

        mAdapter.setSwitchClickListener(new AlarmAdapter.OnSwitchClickListener() {
            @Override
            public void onSwitch(int position, boolean isOpen) {
                AlarmInfo info = mDatas.get(position);
                publicPresenter.UpdateTimerAlarmStatus(DataUtil.getAlarmInfo(info.getTimerid(),isOpen,info.getEvent(),MyApp.getInstance().getSpManager().getTerminalId()));
            }
        });
    }


    @Override
    public void checkAlarmClockData(List<QueryTimerAlarmInfo.ResponseDataObjBean.TimerAlarmsBean> timerAlarmsBeans) {
        List<AlarmInfo> alarmInfos = new ArrayList<>();
        for (int i = 0; i < timerAlarmsBeans.size(); i++) {
            QueryTimerAlarmInfo.ResponseDataObjBean.TimerAlarmsBean timerAlarmsBean = timerAlarmsBeans.get(i);
            String type = timerAlarmsBean.getType();
            if (!TextUtils.isEmpty(type) && BaseConstant.devicedoit.equals(type)) {//设备设置的闹钟
                AlarmInfo alarmInfo = loadDeviceDoIt(timerAlarmsBean);
                alarmInfos.add(alarmInfo);
            }
        }
        mDatas.clear();
        mDatas.addAll(alarmInfos);
        mAdapter.notifyDataSetChanged();
    }

    public AlarmInfo loadDeviceDoIt(QueryTimerAlarmInfo.ResponseDataObjBean.TimerAlarmsBean timerAlarmsBean) {
        String time = timerAlarmsBean.getTimers().get(0).getStarttime();
        String data = timerAlarmsBean.getTimers().get(0).getDay();
        String timerid = timerAlarmsBean.getId();//闹钟的Id
        return new AlarmInfo(time, data, timerAlarmsBean.getName(), "", timerid,timerAlarmsBean.getStatus());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        publicPresenter.queryTimerAlarm(MyApp.getInstance().getSpManager().getTerminalId());
    }
}
