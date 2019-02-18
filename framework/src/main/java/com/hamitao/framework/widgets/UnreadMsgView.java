package com.hamitao.framework.widgets;

import android.content.Context;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hamitao.framework.R;

/**
 * 未读消息
 */
public class UnreadMsgView extends RelativeLayout {

    private ImageView iv;
    private TextView tv;

    public UnreadMsgView(Context context) {
        super(context);
        initView(context);
    }

    public UnreadMsgView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnreadMsgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_unread_msg, this);
        iv = view.findViewById(R.id.iv_msg_num_total);
        tv = view.findViewById(R.id.tv_msg_num_total);
        iv.setOnClickListener(mOnclickListener);
    }

    public void setUnReadMsgIsShow(boolean isShow, int num) {
        String numStr = num + "";
        if (isShow) {
            if (num > 99) {
                numStr = 99 + "+";
            }
            tv.setText(numStr);
            tv.setVisibility(View.VISIBLE);
            iv.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(View.GONE);
            iv.setVisibility(View.GONE);
        }
    }

    private OnClickListener mOnclickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.iv_msg_num_total) {
                if (mOnUnReadMsgListener != null) {
                    mOnUnReadMsgListener.onClick();
                }
            }
        }
    };


    private OnUnReadMsgListener mOnUnReadMsgListener;

    public interface OnUnReadMsgListener {
        void onClick();
    }

    public void setOnUnReadMsgListener(OnUnReadMsgListener onUnReadMsgListener) {
        this.mOnUnReadMsgListener = onUnReadMsgListener;
    }
}