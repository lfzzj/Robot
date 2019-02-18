package com.hamitao.kids.ui.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamitao.aispeech.engine.TTSEngine;
import com.hamitao.aispeech.util.InstructUtils;
import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.framework.utils.MobileUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.adapter.ContactAdapter;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.manager.SystemContacts;
import com.hamitao.kids.mvp.ipresenter.IDevicePresenter;
import com.hamitao.kids.mvp.presenter.DevicePresenterImpl;
import com.hamitao.kids.ui.activity.ContactActivity;
import com.hamitao.kids.utils.PlayHint;
import com.hamitao.requestframe.entity.QueryContactInfo;
import com.hamitao.requestframe.view.QueryContactView;

import java.util.ArrayList;
import java.util.List;

/**
 * @data on 2018/6/7 17:36
 * @describe: 联系人
 */

@SuppressLint("ValidFragment")
public class ContactsFragment extends Fragment {
    private RecyclerView mRecyclerView;

    private List<QueryContactInfo.ResponseDataObjBean.ContactsBean> contactsInfos = new ArrayList<>();//联系人列表
    private ContactActivity contactActivity = (ContactActivity) getActivity();
    private ContactAdapter mContactAdapter;//联系人
    private ContactActivity activity;

    private IDevicePresenter devicePresenter;
    private int layout = R.layout.item_phone_book;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        activity = (ContactActivity) getActivity();
        mRecyclerView = view.findViewById(R.id.rv_contacts);

        initPresenter();
        initContact();
        return view;
    }

    private void initPresenter() {
        devicePresenter = new DevicePresenterImpl(activity, mQueryContactView);
        devicePresenter.queryContact(MyApp.getInstance().getSpManager().getTerminalId());
    }


    //初始化联系人列表
    private void initContact() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(contactActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mContactAdapter = new ContactAdapter(mRecyclerView,R.layout.item_phone_book );
        mRecyclerView.setAdapter(mContactAdapter);
        mContactAdapter.setOnPhoneBookListener(mOnPhoneBookListener);
    }

    private ContactAdapter.OnPhoneBookListener mOnPhoneBookListener = new ContactAdapter.OnPhoneBookListener() {
        @Override
        public void onClickListener(int position) {
            String loginName = contactsInfos.get(position).getPhonenum();
            ((ContactActivity) getActivity()).publicPresenter.callPhone(loginName);
            if( !MobileUtil.ishasSimCard( getActivity())){
                ((ContactActivity) getActivity()).speak(FuncTitle.CONTENT_VOICE_HINT_INSTALL_SIM.toString());
            }
        }
    };

    private QueryContactView mQueryContactView = new QueryContactView() {
        @Override
        public void onSuccess(QueryContactInfo info) {
            contactsInfos = info.getResponseDataObj().getContacts();

            SystemContacts.refreshContacts(getActivity(), info.getResponseDataObj().getContacts());

            if (contactsInfos != null && contactsInfos.size() > 0) {
                mContactAdapter.setData(contactsInfos);
                mContactAdapter.notifyDataSetChanged();
            }else if (contactsInfos != null && contactsInfos.size() <= 0){
                //数据搜索到 但未添加联系人
                PlayHint.stopVoice();
                TTSEngine.getSingleton().speak(InstructUtils.respondNoAddContact(), TTSEngine.TTS_FLAG_COMP_NO_RESULT);
//                TuringEngine.getSingleton().speak(InstructUtils.respondNoAddContact(), TTSEngine.TTS_FLAG_COMP_NO_RESULT);
            }
        }

        @Override
        public void onError(String result) {

        }
    };

}
