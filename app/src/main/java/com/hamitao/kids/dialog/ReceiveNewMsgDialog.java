package com.hamitao.kids.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hamitao.kids.R;
import com.hamitao.framework.callback.OnCompleteCallBack;
import com.hamitao.framework.utils.GifDrawableUtil;

import pl.droidsonroids.gif.GifImageView;

public class ReceiveNewMsgDialog extends Dialog {
    private Context context;
    private GifImageView gifImageView;

    public ReceiveNewMsgDialog(@NonNull Context context) {
        super(context, R.style.transparent);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.view_receive_new_msg);
        gifImageView = findViewById(R.id.img_new_msg);
    }

    public void setReceiveNewMsgImageView() {
        GifDrawableUtil.playGifAnim(context, R.drawable.anim_receive_new_msg, gifImageView, new OnCompleteCallBack() {
            @Override
            public void onComplete() {
                if (onClicklistener != null) {
                    onClicklistener.onReceiveNewMsg();
                }
            }
        });
    }

    public interface OnClicklistener {
        void onReceiveNewMsg();
    }

    private OnClicklistener onClicklistener;

    public void setOnDialogClickListener(OnClicklistener onClicklistener) {
        this.onClicklistener = onClicklistener;
    }

}
