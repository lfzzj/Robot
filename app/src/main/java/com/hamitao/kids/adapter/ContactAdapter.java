package com.hamitao.kids.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.requestframe.entity.QueryContactInfo;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * @data on 2018/6/6 18:01
 * @describe: 电话本
 */

public class ContactAdapter extends BGARecyclerViewAdapter<QueryContactInfo.ResponseDataObjBean.ContactsBean> {
    public ContactAdapter(RecyclerView recyclerView,int layout) {
        super(recyclerView, layout);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, QueryContactInfo.ResponseDataObjBean.ContactsBean model) {

        helper.getTextView(R.id.tv_phone_name).setText(model.getContactname());
        helper.getTextView(R.id.tv_phone_num).setText(model.getPhonenum());
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnPhoneBookListener != null) {
                    mOnPhoneBookListener.onClickListener(position);
                }
            }
        });

    }

    private OnPhoneBookListener mOnPhoneBookListener;

    public void setOnPhoneBookListener(OnPhoneBookListener onPhoneBookListener) {
        this.mOnPhoneBookListener = onPhoneBookListener;
    }

    public interface OnPhoneBookListener {
        void onClickListener(int position);

    }

}
