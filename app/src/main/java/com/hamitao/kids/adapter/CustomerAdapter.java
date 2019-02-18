package com.hamitao.kids.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hamitao.kids.R;
import com.hamitao.framework.enums.CustomerHeader;
import com.hamitao.kids.model.RelationInfo;
import com.hamitao.kids.utils.GlideUtil;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * @data on 2018/6/6 13:57
 * @describe:
 */

public class CustomerAdapter extends BGARecyclerViewAdapter<RelationInfo> {
    public CustomerAdapter(RecyclerView recyclerView,int layout) {
        super(recyclerView, layout);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, RelationInfo model) {
        setCustomerHeader(model.getNickName(), model.isSinger(), helper.getImageView(R.id.iv_head), helper.getTextView(R.id.tv_contact_name));
        int msgNum = model.getMsgNum();
        if (msgNum == 0) {
            helper.getTextView(R.id.tv_msg_num).setVisibility(View.GONE);
        } else {
            helper.getTextView(R.id.tv_msg_num).setVisibility(View.VISIBLE);
            helper.getTextView(R.id.tv_msg_num).setText(msgNum + "");
        }

        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnContactsListener != null) {
                    mOnContactsListener.onClickListener(position);
                }
            }
        });

    }

    private void setCustomerHeader(String bindname, boolean isSinger, ImageView imageView, TextView tv) {
        int resId;
        if (isSinger) {
            tv.setVisibility(View.GONE);
            if (CustomerHeader.CUSTOMER_AUNT.toString().equals(bindname)) {
                resId = R.drawable.icon_customer_aunt;
            } else if (CustomerHeader.CUSTOMER_BROTHER.toString().equals(bindname)) {
                resId = R.drawable.icon_customer_brother;
            } else if (CustomerHeader.CUSTOMER_FATHER.toString().equals(bindname)) {
                resId = R.drawable.icon_customer_father;
            } else if (CustomerHeader.CUSTOMER_GRANDMA.toString().equals(bindname)) {
                resId = R.drawable.icon_customer_grandma;
            } else if (CustomerHeader.CUSTOMER_GRANDMOTHER.toString().equals(bindname)) {
                resId = R.drawable.icon_customer_grandmother;
            } else if (CustomerHeader.CUSTOMER_GRANDPA2.toString().equals(bindname)) {
                resId = R.drawable.icon_customer_grandpa2;
            } else if (CustomerHeader.CUSTOMER_MOTHER.toString().equals(bindname)) {
                resId = R.drawable.icon_customer_mother;
            } else if (CustomerHeader.CUSTOMER_SISTER.toString().equals(bindname)) {
                resId = R.drawable.icon_customer_sister;
            } else if (CustomerHeader.CUSTOMER_UNCLE.toString().equals(bindname)) {
                resId = R.drawable.icon_customer_uncle;
            } else if (CustomerHeader.CUSTOMER_GRANDPA.toString().equals(bindname)) {
                resId = R.drawable.icon_customer_grandpa;
            } else {
                resId = R.drawable.icon_customer_other;
                tv.setVisibility(View.VISIBLE);
                tv.setText(bindname);
            }
        } else {//群聊
            resId = R.drawable.icon_customer_group;
            tv.setVisibility(View.VISIBLE);
            tv.setText(bindname);
        }

        GlideUtil.loadRes(mContext, imageView, resId);
    }

    private OnContactsListener mOnContactsListener;

    public void setOnContactsListener(OnContactsListener onContactsListener) {
        this.mOnContactsListener = onContactsListener;
    }

    public interface OnContactsListener {
        void onClickListener(int position);

    }

}
