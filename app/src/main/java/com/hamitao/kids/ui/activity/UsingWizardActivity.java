package com.hamitao.kids.ui.activity;


import android.widget.TextView;

import com.hamitao.framework.utils.StringUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.view.TitleView;

import butterknife.BindView;

/**
 * 使用向导
 */
public class UsingWizardActivity extends BaseMsgActivity {
    @BindView(R.id.tv_wizard)
    TextView mTv;

    private TitleView mTitle;

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_using_wizard);
    }

    @Override
    public void initData() {
        mTv.setText(StringUtil.ToDBC(getStrByRes(R.string.hint_using_wizard)));
        mTitle = new TitleView(this);
        mTitle.setTitle(getStrByRes(R.string.func_operating_video));
    }
}
