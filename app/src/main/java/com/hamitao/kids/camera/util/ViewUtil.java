package com.hamitao.kids.camera.util;


import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.camera.view.DialogHelper;

public final class ViewUtil {

    private Activity mActivity;

    private DialogHelper mDialogHelper;

    public ViewUtil(Activity activity) {
        mActivity = activity;
        mDialogHelper = new DialogHelper(mActivity);
    }

    public void toast(String msg, int period) {
        mDialogHelper.toast(msg, period);
    }

    public void toast(String msg) {
        mDialogHelper.toast(msg, Toast.LENGTH_SHORT);
    }


    public void showShort(Context context, int message) {
        Toast toast = new Toast(context);
        TextView view1 = new TextView(context);
        view1.setText(context.getResources().getString(R.string.delect_success));
        view1.setBackgroundResource(message);
        view1.setGravity(Gravity.CENTER);
        view1.setTextSize(14);
        view1.setTextColor(context.getResources().getColor(R.color.common_color));
        toast.setView(view1);
        toast.show();
    }

    public void showProgressDialog(String msg) {
        mDialogHelper.showProgressDialog(msg);
    }

    public void dismissProgressDialog() {
        mDialogHelper.dismissProgressDialog();
    }

}
