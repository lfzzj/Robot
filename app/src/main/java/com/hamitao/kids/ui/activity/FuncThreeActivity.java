package com.hamitao.kids.ui.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.hamitao.aispeech.view.TTSView;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.utils.PlayHint;
import com.hamitao.kids.R;
import com.hamitao.kids.adapter.FuncPageAdapter;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.callback.OnFuncClickListener;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.model.FuncBean;

import java.util.List;

import butterknife.BindView;

/**
 * 三级功能公共界面
 */
public class FuncThreeActivity extends BaseMsgActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<FuncBean> mFuncDatas;
    private PlayHint playHint;
    private FuncPageAdapter pageAdapter;
    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_func);
    }


    @Override
    public void initData() {
        Intent intent = getIntent();
        mFuncDatas = (List<FuncBean>) intent.getSerializableExtra(Constants.FLAG_ENTER_FUNC_THREE_ACTIVITY);

        pageAdapter = new FuncPageAdapter(this, mContext, mFuncDatas);
        viewPager.setAdapter(pageAdapter);
        pageAdapter.setOnFuncClickListener(mOnFuncClickListener);
        viewPager.setOnPageChangeListener(mOnPageChangeListener);
//        ttsManager.speak(mFuncDatas.get(1).getFuncName());
        playHint = new PlayHint(mContext);
        playHint.playFuncTitle(mFuncDatas.get(0).getFuncName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkUnReadCount();
    }



    private OnFuncClickListener mOnFuncClickListener = new OnFuncClickListener() {
        @Override
        public void OnClick(String funcName) {
//            String funcName = mFuncDatas.get(position).getFuncName();
            if (getStrByRes(R.string.func_english_study_follow_me).equals(funcName)) {
                Logger.d(TAG, "-----------------跟我学");
                openActivity(EnglishStudyFollowMeActivity.class);

            } else if (getStrByRes(R.string.func_english_study_translation).equals(funcName)) {
                Logger.d(TAG, "-----------------中译英");
                openActivity(EnglishStudyTranslationActivity.class);
            }
        }
    };

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
//            ttsManager.speak(mFuncDatas.get(position).getFuncName());
            playHint.playFuncTitle(mFuncDatas.get(position).getFuncName());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private TTSView mTtsView = new TTSView() {
        @Override
        public void onInitSuccess() {
            Logger.d(TAG, "onInitSuccess");
        }

        @Override
        public void onInitError() {
            Logger.d(TAG, "onInitError");
        }

        @Override
        public void onCompletion() {
            Logger.d(TAG, "onCompletion");

        }
    };

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (cloudTTSManager != null) {
//            cloudTTSManager.onDestroy();
//        }
//    }
}
