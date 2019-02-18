package com.hamitao.kids.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hamitao.kids.R;

/**
 * @data on 2018/6/29 17:30
 * @describe:
 */

public class VideoChatBeforeConnView extends RelativeLayout {
    private ImageView ivHead;
    private TextView tvCallName;
    private LinearLayout llRefusePhone;
    private LinearLayout llAnswer;
    private TextView tvRefusePhone;

    private ImageView ivRefusePhone;
    private ImageView ivAnswer;

    private OnVideoChatClick listener;

    public VideoChatBeforeConnView(Context context) {
        super(context);
    }

    public VideoChatBeforeConnView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_video_chat_before_conn, this);
        ivHead = view.findViewById(R.id.iv_video_chat_head);
        tvCallName = view.findViewById(R.id.tv_video_chat_call_name);
        llRefusePhone = view.findViewById(R.id.ll_refuse_phone);
        llAnswer = view.findViewById(R.id.ll_answer);
        tvRefusePhone = view.findViewById(R.id.tv_refuse_phone);
        ivRefusePhone = view.findViewById(R.id.iv_refuse_phone);
        ivAnswer = view.findViewById(R.id.iv_answer);

        ivRefusePhone.setOnClickListener(mOnClickListener);
        ivAnswer.setOnClickListener(mOnClickListener);
    }


    public void initConnView(boolean isCall, String callName) {
        if (isCall) {//来电
            llAnswer.setVisibility(View.VISIBLE);
            tvCallName.setText(callName + " 正在呼叫你…");
            tvRefusePhone.setText("拒绝");
        } else {
            llAnswer.setVisibility(View.GONE);
            tvCallName.setText("正在呼叫 " + callName + "…");
            tvRefusePhone.setText("挂断");
        }
    }

    public OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_refuse_phone:
                    if (listener != null) {
                        listener.onRefusePhone();
                    }
                    break;
                case R.id.iv_answer:
                    if (listener != null) {
                        listener.onAnswer();
                    }
                    break;
            }
        }
    };

    public interface OnVideoChatClick {
        void onRefusePhone();//挂断

        void onAnswer();//接听
    }

    public void setOnCommonBottomListener(OnVideoChatClick listener) {
        this.listener = listener;
    }

}
