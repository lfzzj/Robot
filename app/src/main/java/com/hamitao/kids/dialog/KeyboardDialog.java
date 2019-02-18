package com.hamitao.kids.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hamitao.kids.view.KeyboardView;
import com.hamitao.kids.R;

public class KeyboardDialog extends Dialog {
    private Activity activity;
    private KeyboardView keyboardView;

    public KeyboardDialog(@NonNull Activity activity) {
        super(activity, R.style.remind_dialog);
        this.activity = activity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_keyboard);
        keyboardView = findViewById(R.id.keyboard);
        keyboardView.setOnClickListener(new KeyboardView.onClickListener() {
            @Override
            public void onClick(String keyValue) {
                if (mListener != null) {
                    mListener.onBackPressed(keyValue);
                }
            }
        });
    }

    /**
     * 设置编辑框的初始值
     *
     * @param key
     */
    public void setEditValue(String key) {
        keyboardView.setEditKey(key);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mListener != null) {
            mListener.onBackPressed(keyboardView.getEditPsw());
        }
    }

    public OnKeyBoardClickListener mListener;

    public void setOnClickListener(OnKeyBoardClickListener onListener) {
        this.mListener = onListener;
    }

    public interface OnKeyBoardClickListener {
        void onBackPressed(String keyValue);

    }
}
