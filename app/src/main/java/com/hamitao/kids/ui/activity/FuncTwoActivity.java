package com.hamitao.kids.ui.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.utils.PlayHint;
import com.hamitao.kids.R;
import com.hamitao.kids.adapter.FuncPageAdapter;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.callback.OnFuncClickListener;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.model.FuncBean;
import com.hamitao.kids.utils.DataFuncUtil;

import java.util.List;

import butterknife.BindView;

/**
 * @data on 2018/6/2 11:09
 * @describe: 二级界面功能
 */
public class FuncTwoActivity extends BaseMsgActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<FuncBean> mFuncDatas;

    private FuncPageAdapter pageAdapter;

    private PlayHint playHint;

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_func_two);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkUnReadCount();
    }


    @Override
    public void initData() {
        Intent intent = getIntent();
        mFuncDatas = (List<FuncBean>) intent.getSerializableExtra(Constants.FLAG_TWO_FUNC_DATA);
        pageAdapter = new FuncPageAdapter(this, mContext, mFuncDatas);
        viewPager.setAdapter(pageAdapter);
        pageAdapter.setOnFuncClickListener(mOnFuncClickListener);
        viewPager.setOnPageChangeListener(mOnPageChangeListener);
        playHint = new PlayHint(mContext);
        playHint.playFuncTitle(mFuncDatas.get(0).getFuncName());
    }


    private OnFuncClickListener mOnFuncClickListener = new OnFuncClickListener() {
        @Override
        public void OnClick(String funcName) {
            Logger.d(TAG, "点击了=" + funcName);
            if (getStrByRes(R.string.func_poetry_recite).equals(funcName)) {
                Logger.d(TAG, "-----------------国学诗词");
                enterPlayActivity(Constants.FLAG_THREE_POETRY_RECITE);
            } else if (getStrByRes(R.string.func_english_study).equals(funcName)) {
                Logger.d(TAG, "-----------------英语学习");
                if (getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)){
                    enterFuncThreeActivity(DataFuncUtil.getFuncEnglishStudyDatas(mContext));
                }else{
                    enterPlayActivity(Constants.FLAG_THREE_ENGLISH_STUDY);
                }

            } else if (getStrByRes(R.string.func_safety_education).equals(funcName)) {
                Logger.d(TAG, "-----------------安全教育");
                enterPlayActivity(Constants.FLAG_THREE_SAFETY_EDUCATION);
            } else if (getStrByRes(R.string.func_country_learn).equals(funcName)) {
                Logger.d(TAG, "-----------------读绘本");
                enterPlayActivity(Constants.FLAG_THREE_READ_PICTURE_BOOK);
            } else if (getStrByRes(R.string.func_picture_book).equals(funcName)) {
                Logger.d(TAG, "-----------------扫绘本");
                openActivity(ScanBookActivity.class);
            } else if (getStrByRes(R.string.func_nursery_rhymes).equals(funcName)) {
                Logger.d(TAG, "-----------------儿歌");
                enterPlayActivity(Constants.FLAG_THREE_NURSERY_RHYMES);
            } else if (getStrByRes(R.string.func_story).equals(funcName)) {
                Logger.d(TAG, "-----------------故事");
                enterPlayActivity(Constants.FLAG_THREE_STORY);
            } else if (getStrByRes(R.string.func_anim).equals(funcName)) {
                Logger.d(TAG, "-----------------动画");
                enterPlayActivity(Constants.FLAG_THREE_ANIM);
//                openActivity(ContentTreeActivity.class);
            } else if (getStrByRes(R.string.func_picture_books).equals(funcName)) {
                Logger.d(TAG, "-----------------绘本");
                enterPlayActivity(Constants.FLAG_THREE_READ_PICTURE_BOOK);
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


}
