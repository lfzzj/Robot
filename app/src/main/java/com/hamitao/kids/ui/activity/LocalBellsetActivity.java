package com.hamitao.kids.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.adapter.LocalBellAdapter;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.model.LocalBellInfo;
import com.hamitao.kids.view.TitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 本地铃声
 */
public class LocalBellsetActivity extends BaseMsgActivity {
    @BindView(R.id.rv_local_bell)
    RecyclerView mRecyclerView;

    private LocalBellAdapter mAdapter;
    private List<LocalBellInfo> bellInfos;

    private TitleView mTitle;
    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_local_bell);
    }

    @Override
    public void initData() {
        mTitle = new TitleView(this);
        mTitle.setTitle(getStrByRes(R.string.func_setting_bellset));

        bellInfos = getLocalBellList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new LocalBellAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLocalBellListener(mOnBellListener);
        mAdapter.setData(bellInfos);
        mAdapter.notifyDataSetChanged();
    }

    private LocalBellAdapter.OnLocalBellListener mOnBellListener = new LocalBellAdapter.OnLocalBellListener() {
        @Override
        public void onClick(int position) {
            Logger.d(TAG, "position=" + position);
            toast(bellInfos.get(position).getBellName());
        }
    };

    private List<LocalBellInfo> getLocalBellList() {
        List<LocalBellInfo> infos = new ArrayList<>();
        infos.add(new LocalBellInfo("铃声一"));
        infos.add(new LocalBellInfo("铃声二"));
        infos.add(new LocalBellInfo("铃声三"));

        return infos;
    }
}
