package com.hamitao.kids.camera.view;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface.OnCancelListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hamitao.kids.R;
import com.hamitao.kids.camera.widget.LiteProgressDialog;

public class DialogHelper {

    private Activity mActivity;
    private AlertDialog mAlertDialog;
    private Toast mToast;

    public DialogHelper(Activity activity) {
        mActivity = activity;
    }

    public void toast(final String msg, final int period) {
        mActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mToast!=null){
                    mToast.cancel();
                }
                mToast = new Toast(mActivity);
                View view = LayoutInflater.from(mActivity).inflate(
                        R.layout.view_transient_notification, null);
                TextView tv = (TextView) view.findViewById(android.R.id.message);
                tv.setText(msg);
                mToast.setView(view);
                mToast.setDuration(period);

                mToast.setGravity(Gravity.CENTER, 0, 0);
                mToast.show();
            }
        });
    }

    public void showProgressDialog(boolean showProgressBar, String msg) {
        showProgressDialog(msg, true, null, showProgressBar);
    }

    public void showProgressDialog(final String msg) {
        showProgressDialog(msg, true, null, true);
    }

    public void showProgressDialog(final String msg, final boolean cancelable,
                                   final OnCancelListener cancelListener,
                                   final boolean showProgressBar) {
        dismissProgressDialog();

        mActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mActivity == null || mActivity.isFinishing()) {
                    return;
                }

                mAlertDialog = new LiteProgressDialog(mActivity);
                mAlertDialog.setMessage(msg);
                ((LiteProgressDialog) mAlertDialog).setProgressAvailable(showProgressBar);
                mAlertDialog.setCancelable(cancelable);
                mAlertDialog.setOnCancelListener(cancelListener);

                mAlertDialog.show();

                mAlertDialog.setCanceledOnTouchOutside(false);
            }
        });
    }

    public void dismissProgressDialog() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mAlertDialog != null && mAlertDialog.isShowing() && !mActivity.isFinishing()) {
                    mAlertDialog.dismiss();
                    mAlertDialog = null;
                }
            }
        });
    }

}
