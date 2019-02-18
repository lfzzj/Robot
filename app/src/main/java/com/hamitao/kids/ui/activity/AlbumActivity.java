package com.hamitao.kids.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.framework.network.NetUtil;
import com.hamitao.framework.network.NetworkStatus;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.adapter.AlbumAdapter;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.camera.Constant;
import com.hamitao.kids.camera.core.CameraManager;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.dialog.DelPhotoDialog;
import com.hamitao.kids.model.AlbumInfo;
import com.hamitao.kids.mvp.ipresenter.IDevicePresenter;
import com.hamitao.kids.mvp.presenter.DevicePresenterImpl;
import com.hamitao.requestframe.entity.QueryPhotoInfo;
import com.hamitao.requestframe.view.DelPhotoView;
import com.hamitao.requestframe.view.QueryPhotoView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;

/**
 * @data on 2018/6/6 18:48
 * @describe: 相册
 */

public class AlbumActivity extends BaseMsgActivity {
    @BindView(R.id.rv_album)
    RecyclerView mRecyclerView;

    @BindView(R.id.btn_photograph)
    Button btnPhotograph;

    @BindView(R.id.rl_photo_del)
    RelativeLayout rlPhotoDel;

    private AlbumAdapter albumAdapter;
    private List<AlbumInfo> albumInfos = new ArrayList<>();

    private List<QueryPhotoInfo.ResponseDataObjBean.PhotosBean> photosBeanList = new ArrayList<>();

    private IDevicePresenter devicePresenter;

    @Override
    protected void onResume() {
        super.onResume();
        checkUnReadCount();
    }

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_album);
    }

    @Override
    public void initData() {
        devicePresenter = new DevicePresenterImpl(mContext, mQueryPhotoView, mDelPhotoView);
        if (!isNetworkAvailable()){
            speak(FuncTitle.CONTENT_VOICE_HINT_NET_DISCONNTCT.toString());
        }
        initAlbum();

        btnPhotograph.setOnClickListener(mOnClickListener);
        rlPhotoDel.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_photograph:
                    Logger.d(TAG, "网络状态=" + NetUtil.getNetWorkState(mContext));
                    if (NetUtil.getNetWorkState(mContext) != NetworkStatus.NETWORK_NONE) {
                        CameraManager.getInstance().openCamera(AlbumActivity.this);
                    } else {
                        toast(getStrByRes(R.string.hint_net_unconnect));
                        speak(FuncTitle.CONTENT_VOICE_HINT_NET_DISCONNTCT.toString());
                    }
                    break;
                case R.id.rl_photo_del:
                    dealDel();
                    break;
            }
        }
    };
    private boolean isEditState = false;

    private void dealDel() {
        if (!isEditState) {
            if (albumInfos != null && albumInfos.size() > 0) {
                //进入编辑状态
                setAlbumInfoNotifyData(true);
            }
        } else {
            Logger.d(TAG, "删除操作");
            boolean isEdit = false;
            if (albumInfos != null && albumInfos.size() > 0) {
                for (int i = 0; i < albumInfos.size(); i++) {
                    if (albumInfos.get(i).isDel()) {
                        isEdit = true;
                        continue;
                    }
                }
            }
            if (isEdit) {
                DelPhotoDialog delPhotoDialog = new DelPhotoDialog(AlbumActivity.this, mOnPhotoDelDialogListener );
                delPhotoDialog.show();
            }
        }
    }

    private DelPhotoDialog.OnPhotoDelDialogListener mOnPhotoDelDialogListener = new DelPhotoDialog.OnPhotoDelDialogListener() {
        @Override
        public void OnCancal() {
        }

        @Override
        public void OnComfirm() {
            if (albumInfos != null && albumInfos.size() > 0) {
                Iterator<AlbumInfo> it = albumInfos.iterator();
                while (it.hasNext()) {
                    AlbumInfo albumInfo = it.next();
                    if (albumInfo.isDel()) {
                        it.remove();
                        devicePresenter.delPhoto(MyApp.getInstance().getSpManager().getTerminalId(), albumInfo);
                    }
                }
            }
            toast(R.drawable.icon_delete_success);
            setAlbumInfoNotifyData(false);
        }
    };


    private void initAlbum() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        albumAdapter = new AlbumAdapter(mRecyclerView, mActivity );
        mRecyclerView.setAdapter(albumAdapter);
        albumAdapter.setOnAlbumListener(mOnAlbumListener);
        devicePresenter.queryPhoto(MyApp.getInstance().getSpManager().getTerminalId());

    }

    private AlbumAdapter.OnAlbumListener mOnAlbumListener = new AlbumAdapter.OnAlbumListener() {
        @Override
        public void onClickListener(int position) {
            if (albumInfos != null && albumInfos.size() > 0) {
                AlbumInfo info = albumInfos.get(position);
                if (info.isEditState()) {
                    if (info.isDel()) {
                        info.setDel(false);
                    } else {
                        info.setDel(true);
                    }
                    albumAdapter.notifyDataSetChanged();
                } else {
//                    String albumUrl = info.getAlbum();
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.FLAG_ALBUM_POSITION, position);
                    bundle.putSerializable(Constants.FLAG_ALBUM_DATA, (Serializable) albumInfos);
                    openActivity(AlbumViewerActivity.class, bundle);
                }
            }
        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_CROP && resultCode == RESULT_OK) {
            devicePresenter.queryPhoto(MyApp.getInstance().getSpManager().getTerminalId());
        }
    }

    private QueryPhotoView mQueryPhotoView = new QueryPhotoView() {
        @Override
        public void onSuccess(QueryPhotoInfo info) {
            Logger.d(TAG, "照片查询成功=" + info.getResult());
            if (info.getResult().equals(BaseConstant.SUCCESS)) {
                albumInfos.clear();
                photosBeanList = info.getResponseDataObj().getPhotos();
                if (photosBeanList.size() != 0) {
                    for (int i = 0; i < photosBeanList.size(); i++) {
                        QueryPhotoInfo.ResponseDataObjBean.PhotosBean photosBean = photosBeanList.get(i);
                        albumInfos.add(new AlbumInfo(MyApp.getInstance().getOssManager().getOssConstrainedObjectURL(photosBean.getPhotourl()), photosBean.getPhotourl(),
                                photosBean.getPhotoid(), false, false));
                    }
                    refreshPhoto();
                }
            }

        }

        @Override
        public void onError(String result) {
            Logger.d(TAG, "照片查询失败：" + result);
        }
    };

    private DelPhotoView mDelPhotoView = new DelPhotoView() {
        @Override
        public void onSuccess(String album) {
            Logger.d(TAG, "删除成功");
            MyApp.getInstance().getOssManager().photographDel(album);

        }

        @Override
        public void onError(String result) {
            Logger.d(TAG, "删除失败：" + result);
        }
    };

    //刷新数据
    private void refreshPhoto() {
        albumAdapter.setData(albumInfos);
        albumAdapter.notifyDataSetChanged();
        mRecyclerView.smoothScrollToPosition(albumInfos.size()-1);
    }

    @Override
    public void onBackPressed() {
        Logger.d(TAG, "按下返回键");
        if (isEditState) {//编辑状态下
            //退出编辑状态
            setAlbumInfoNotifyData(false);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 更新数据
     *
     * @param isEdit
     */
    private void setAlbumInfoNotifyData(boolean isEdit) {
        btnPhotograph.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        if (albumInfos != null && albumInfos.size() != 0) {
            for (int i = 0; i < albumInfos.size(); i++) {
                albumInfos.get(i).setEditState(isEdit);
                albumInfos.get(i).setDel(false);
            }
        }
        albumAdapter.notifyDataSetChanged();
        isEditState = isEdit;
    }
}
