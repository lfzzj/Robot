package com.hamitao.kids.camera.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hamitao.kids.R;


public class LiteProgressDialog extends AlertDialog {

    private ProgressBar mProgress;
    private TextView mMessageView;
    private CharSequence mMessage;
    private boolean mIndeterminate;
    private boolean mProgressVisiable;

    public LiteProgressDialog(Context context) {
        super(context);
    }

    public LiteProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_progress_dialog);
        mProgress = (ProgressBar) findViewById(android.R.id.progress);
        mMessageView = (TextView) findViewById(R.id.message);

        setMessageAndView();
        setIndeterminate(mIndeterminate);
    }

    private void setMessageAndView() {
        mMessageView.setText(mMessage);

        if (TextUtils.isEmpty(mMessage)) {
            mMessageView.setVisibility(View.GONE);
        }

        mProgress.setVisibility(mProgressVisiable ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setMessage(CharSequence message) {
        mMessage = message;
    }

    public void setProgressAvailable(boolean progressAvailable) {
        mProgressVisiable = progressAvailable;
    }

    public void setIndeterminate(boolean indeterminate) {
        if (mProgress != null) {
            mProgress.setIndeterminate(indeterminate);
        } else {
            mIndeterminate = indeterminate;
        }
    }
}
