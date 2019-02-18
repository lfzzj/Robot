package com.hamitao.kids.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.aispeech.model.AlarmInfo;
import com.hamitao.kids.utils.AlarmUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/8/15.
 */
public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.Holder> {
    private List<AlarmInfo> list = new ArrayList<AlarmInfo>();
    private Context context;

    public AlarmAdapter(Context context, List<AlarmInfo> list) {
        this.list = list;
        this.context = context;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_alarm_view, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        AlarmInfo alarmInfo = list.get(position);
        holder.tvAlarmTime.setText(alarmInfo.getTime());
        String data = alarmInfo.getDate();
        String repeatData = AlarmUtil.getRepeatAlarmData(data);
        String desc = alarmInfo.getEvent() + (TextUtils.isEmpty(repeatData) ? "" : "," + repeatData);
        holder.tvAlarmDesc.setText(desc);
//        switchAlarm = alarmInfo.isOpen();
        switchAlarm = alarmInfo.getStatus().equals(BaseConstant.ENABLE);
        holder.btnSwitch.setBackgroundResource(switchAlarm ? R.drawable.icon_turn_on : R.drawable.icon_turn_off);
        holder.btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchAlarm = switchAlarm ? false : true;
                holder.btnSwitch.setBackgroundResource(switchAlarm ? R.drawable.icon_turn_on : R.drawable.icon_turn_off);
                if (listener != null) {
                    listener.onSwitch(position, switchAlarm);
                }
            }
        });
    }

    private boolean switchAlarm = false;

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView tvDelete;
        public LinearLayout llLayout;
        public TextView tvAlarmTime;
        public TextView tvAlarmDesc;
        public ImageView btnSwitch;

        public Holder(View itemView) {
            super(itemView);
            tvDelete = itemView.findViewById(R.id.tvDelete);
            llLayout = itemView.findViewById(R.id.llLayout);
            tvAlarmTime = itemView.findViewById(R.id.tv_alarm_time);
            tvAlarmDesc = itemView.findViewById(R.id.tv_alarm_desc);
            btnSwitch = itemView.findViewById(R.id.btn_switch_alarm);

        }
    }

    private OnSwitchClickListener listener;

    public interface OnSwitchClickListener {
        void onSwitch(int position, boolean isOpen);
    }

    public void setSwitchClickListener(OnSwitchClickListener clickListener) {
        this.listener = clickListener;
    }
}
