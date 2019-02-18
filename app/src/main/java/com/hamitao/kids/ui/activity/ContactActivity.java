package com.hamitao.kids.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.kids.R;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.ui.fragment.CallPhoneFragment;
import com.hamitao.kids.ui.fragment.ContactsFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @data on 2018/6/6 10:37
 * @describe: 联系人
 */

public class ContactActivity extends BaseMsgActivity {
    @BindView(R.id.rb_customer)
    RadioButton rbCustomer;
    @BindView(R.id.rb_call_phone)
    RadioButton rbCallPhone;
    @BindView(R.id.rl_bg)
    RelativeLayout rlBg;


    private Fragment[] mFragments;
    private int mIndex;


    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_contact);
    }

    @Override
    public void initData() {
        initFragment();
        checkNet();
        rbCustomer.setChecked(true);
    }

    /**
     * 检测网络
     */
    private void checkNet() {
        if (!isNetworkAvailable()) {
            speak(FuncTitle.CONTENT_VOICE_HINT_NO_NET_UPDATA_CONTACT.toString());
        }
    }

    private void initFragment() {
        ContactsFragment contactsFragment = new ContactsFragment();
        CallPhoneFragment callPhoneFragment = new CallPhoneFragment();
        mFragments = new Fragment[]{contactsFragment, callPhoneFragment};
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        //添加首页
        ft.add(R.id.fl_content, contactsFragment).commit();

        //默认设置为第0个
        setIndexSelected(0);
    }


    private void setIndexSelected(int index) {
        if (mIndex == index) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.fl_content, mFragments[index]).show(mFragments[index]);
        } else {
            ft.show(mFragments[index]);
        }
        ft.commit();
        //再次赋值
        mIndex = index;
    }

    @OnClick({R.id.rb_customer, R.id.rb_call_phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_customer:
                setIndexSelected(0);
                break;
            case R.id.rb_call_phone:
                setIndexSelected(1);
                break;
        }

    }


}
