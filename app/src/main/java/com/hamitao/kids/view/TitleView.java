package com.hamitao.kids.view;

import android.app.Activity;
import android.widget.TextView;

import com.hamitao.kids.R;

/**
 * @data on 2018/6/5 10:09
 * @describe: 标题栏
 */

public class TitleView {
    private TextView mTitle;

    public TitleView(Activity activity) {
        mTitle = activity.findViewById(R.id.tv_title);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }
}
