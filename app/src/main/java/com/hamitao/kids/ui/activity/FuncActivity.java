package com.hamitao.kids.ui.activity;

import android.support.v4.view.ViewPager;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.utils.PlayHint;
import com.hamitao.kids.R;
import com.hamitao.kids.adapter.FuncPageAdapter;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.callback.OnFuncClickListener;
import com.hamitao.kids.model.FuncBean;
import com.hamitao.kids.utils.DataFuncUtil;
import com.hamitao.kids.view.ViewPagerCustomDuration;

import java.util.List;

import butterknife.BindView;

/**
 * @data on 2018/5/29 9:19
 * @describe: 主功能
 */

public class FuncActivity extends BaseMsgActivity {
    @BindView(R.id.view_pager)
    ViewPagerCustomDuration viewPager;

    private List<FuncBean> mFuncDatas;
    private PlayHint playHint;

    private FuncPageAdapter pageAdapter;


    @Override
    protected void onResume() {
        super.onResume();
        checkUnReadCount();
    }


    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_func);
    }

    @Override
    public void initData() {
        mFuncDatas = DataFuncUtil.getFuncDatas(mContext);
        viewPager.setScrollDurationFactor(0.8);
        pageAdapter = new FuncPageAdapter(this, mContext, mFuncDatas);
        viewPager.setAdapter(pageAdapter);
        pageAdapter.setOnFuncClickListener(mOnFuncClickListener);
        viewPager.setOnPageChangeListener(mOnPageChangeListener);
        playHint = new PlayHint(mContext);
        playHint.playFuncTitle(mFuncDatas.get(0).getFuncName());
    }


    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            playHint.playFuncTitle(mFuncDatas.get(position).getFuncName());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private OnFuncClickListener mOnFuncClickListener = new OnFuncClickListener() {
        @Override
        public void OnClick(String funcName) {
            Logger.d(TAG, "点击了=" + funcName);
            if (getStrByRes(R.string.main_func_study).equals(funcName)) {//学习
                enterTwoFuncActivity(DataFuncUtil.getTwoFuncStudyDatas(mContext));
            } else if (getStrByRes(R.string.main_func_intelligent_reading).equals(funcName)) {//智慧阅读
                enterTwoFuncActivity(DataFuncUtil.getTwoFuncIntelligentReadingDatas(mContext));
            } else if (getStrByRes(R.string.main_func_entertainment).equals(funcName)) {//娱乐
                enterTwoFuncActivity(DataFuncUtil.getTwoFuncPlayDatas(mContext));
            } else if (getStrByRes(R.string.main_func_small_talk).equals(funcName)) {//聊天
                openActivity(SessionListActivity.class);
            } else if (getStrByRes(R.string.main_func_call_phone).equals(funcName)) {//打电话
                openActivity(ContactActivity.class);
            } else if (getStrByRes(R.string.main_func_album).equals(funcName)) {//相册
                openActivity(AlbumActivity.class);
            } else if (getStrByRes(R.string.main_func_setting).equals(funcName)) {//设置
                openActivity(SettingActivity.class);
            }
        }
    };


    /**
     * 拨打紧急电话
     */
//    private void callPhoneSOS(int index) {
//        if (contactsInfos != null && contactsInfos.size() > index) {
//            String phoneNum = contactsInfos.get(index).getPhonenum();
//            Logger.d(TAG, "phoneNum=" + phoneNum);
////            publicPresenter.callPhone(loginName);
//            publicPresenter.callPhone("18566254285");
//        }
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (cloudTTSManager != null) {
//            cloudTTSManager.onDestroy();
//        }
//    }
}
