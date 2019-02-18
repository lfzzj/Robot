package com.hamitao.kids.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.kids.R;
import com.hamitao.kids.utils.ResUtil;
import com.hamitao.kids.utils.WindowUtils;

/**
 * 来电提醒
 */
public class PhoneCallRemindDialog extends Dialog {
    private Context context;

    private ImageView ivHead;//头像
    private TextView tvName;
    private LinearLayout llRefuse;
    private LinearLayout llAnswer;
    private ImageView ivRefusePhone;
    private ImageView ivAnswer;
    private RelativeLayout rlBg;

    public PhoneCallRemindDialog(@NonNull Context context) {
        super(context, R.style.remind_dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        WindowUtils.hidePopupWindow();
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_video_chat_before_conn);
        ivHead = findViewById(R.id.iv_video_chat_head);
        tvName = findViewById(R.id.tv_video_chat_call_name);
        llRefuse = findViewById(R.id.ll_refuse_phone);
        llAnswer = findViewById(R.id.ll_answer);
        rlBg = findViewById(R.id.rl_bg);
        ivRefusePhone = findViewById(R.id.iv_refuse_phone);
        ivAnswer = findViewById(R.id.iv_answer);

        rlBg.setBackgroundResource(R.drawable.bg2);
        ivRefusePhone.setBackgroundResource(R.drawable.icon_phone_refuse);
        ivAnswer.setBackgroundResource(R.drawable.icon_phone_answer);

        llRefuse.setOnClickListener(mOnClickListener);
        llAnswer.setOnClickListener(mOnClickListener);
    }

    public void setPhoneCallRemindInfo(String callName, String chatType) {
        ResUtil.setCallheaderByName(callName, ivHead);
        tvName.setText(chatType.equals(BaseConstant.INSTRUCT_ACTION_PHONE_VIDEO_DEVICE)
                ? callName + context.getResources().getString(R.string.calling_from_video)
                : callName + context.getResources().getString(R.string.calling_from_voice));
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ll_refuse_phone:
                    if (onCallDialogClicklistener != null) {
                        onCallDialogClicklistener.onRefuse();
                    }
                    break;
                case R.id.ll_answer:
                    if (onCallDialogClicklistener != null) {
                        onCallDialogClicklistener.onAnswer();
                    }
                    break;
            }
        }
    };

    public interface OnCallDialogClicklistener {
        void onRefuse();

        void onAnswer();
    }

    private OnCallDialogClicklistener onCallDialogClicklistener;

    public void setOnDialogClickListener(OnCallDialogClicklistener onCallDialogClicklistener) {
        this.onCallDialogClicklistener = onCallDialogClicklistener;
    }

    @Override
    public void onBackPressed() {
        //屏蔽掉返回键
    }

}
