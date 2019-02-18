package com.hamitao.kids.ui.activity;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.hamitao.aispeech.engine.TTSEngine;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.adapter.FollowMeAdapter;
import com.hamitao.kids.adapter.FollowMeContentAdapter;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.dialog.AlarmDialog;
import com.hamitao.kids.model.FollowMeBean;
import com.hamitao.kids.model.FuncBean;
import com.hamitao.kids.view.ViewPagerCustomDuration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 跟我学具体内容
 */
public class FollowMeContentActivity extends BaseMsgActivity {
    @BindView(R.id.view_pager)
    ViewPagerCustomDuration viewPager;

    public int curPosition;

    private List<FollowMeBean> mFuncDatas = new ArrayList<>();
    private FollowMeContentAdapter mAdapter;

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_func_follow_me_content);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int position = intent.getIntExtra(BaseConstant.BUNDLE_FOLLOW_ME_POSITION, 0);
        mFuncDatas = (List<FollowMeBean>) intent.getSerializableExtra(BaseConstant.BUNDLE_FOLLOW_ME_DATAS);
        Logger.d(TAG, "当前名称：" + mFuncDatas.get(position).getChineseName());

        viewPager.setScrollDurationFactor(0.8);
        mAdapter = new FollowMeContentAdapter(mContext, this, mFuncDatas);
        viewPager.setAdapter(mAdapter);
        viewPager.setOnPageChangeListener(mOnPageChangeListener);
        viewPager.setCurrentItem(position);
        translation(position);

    }


    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            curPosition = position;
            translation(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @OnClick({R.id.btn_replay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_replay:
                Logger.d(TAG, "curPosition=" + curPosition);
                translation(curPosition);
                break;
        }
    }


    private void translation(int position) {
        FollowMeBean funcBean = mFuncDatas.get(position);
        String name = funcBean.getEnglishName();
        Logger.d(TAG, "需要翻译的：" + name);
//        ttsManager.speak(name);
        speakTTS(name, TTSEngine.TTS_FLAG_COMP_NO_RESULT);
    }
}
