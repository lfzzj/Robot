package com.hamitao.kids.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.kids.R;
import com.hamitao.kids.camera.Constant;

/**
 * @data on 2018/6/29 10:23
 * @describe:
 */

public class DelPhotoDialog extends Dialog {
    private OnPhotoDelDialogListener photoDelDialogListener;


    public DelPhotoDialog(Context context, OnPhotoDelDialogListener photoDelDialogListener) {
        super(context, R.style.Dialogstyle);
        this.photoDelDialogListener = photoDelDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_del_photo);

        Button clickBtnCancal = findViewById(R.id.btn_photo_del_cancal);
        Button clickBtnComfirm = findViewById(R.id.btn_photo_del_comfirm);
        clickBtnCancal.setOnClickListener(clickListener);
        clickBtnComfirm.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (photoDelDialogListener != null) {
                switch (v.getId()) {
                    case R.id.btn_photo_del_cancal:
                        photoDelDialogListener.OnCancal();
                        DelPhotoDialog.this.dismiss();
                        break;
                    case R.id.btn_photo_del_comfirm:
                        photoDelDialogListener.OnComfirm();
                        DelPhotoDialog.this.dismiss();
                        break;
                }
            }
        }
    };

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    //定义回调事件，用于dialog的点击事件
    public interface OnPhotoDelDialogListener {
        void OnCancal();

        void OnComfirm();
    }
}
