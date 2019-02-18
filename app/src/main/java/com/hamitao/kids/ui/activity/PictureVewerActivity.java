package com.hamitao.kids.ui.activity;

import android.content.Intent;
import android.widget.ImageView;

import com.hamitao.kids.R;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.utils.GlideUtil;

import butterknife.BindView;

/**
 * @data on 2018/6/13 14:54
 * @describe: 图片展示
 */

public class PictureVewerActivity extends BaseMsgActivity {
    @BindView(R.id.iv_pic_viewer)
    ImageView ivPicViewer;

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_picture_viewer);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String picUrl = intent.getStringExtra(Constants.FLAG_ALBUM_URL);
        GlideUtil.loadIntoUseFitWidth(mActivity, picUrl, ivPicViewer);
}







}
