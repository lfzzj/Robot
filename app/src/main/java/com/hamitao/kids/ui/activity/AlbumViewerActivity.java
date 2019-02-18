package com.hamitao.kids.ui.activity;

import android.content.Intent;

import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.adapter.AlbumViewerAdapter;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.model.AlbumInfo;
import com.hamitao.kids.view.ViewPagerCustomDuration;

import java.util.List;

import butterknife.BindView;

public class AlbumViewerActivity extends BaseMsgActivity {
    @BindView(R.id.view_pager_pic)
    ViewPagerCustomDuration viewPager;
    private AlbumViewerAdapter mAdapter;

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_album_viewer);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int position = intent.getIntExtra(Constants.FLAG_ALBUM_POSITION, 0);
        List<AlbumInfo> albumInfos = (List<AlbumInfo>) intent.getSerializableExtra(Constants.FLAG_ALBUM_DATA);

        Logger.d(TAG, "position=" + position + "   albumInfos=" + albumInfos.get(position).getAlbum());
        viewPager.setScrollDurationFactor(0.8);
        mAdapter = new AlbumViewerAdapter(mContext, this, albumInfos, MyApp.getInstance().getOssManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(position);
    }

}
