package com.hamitao.kids.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.adapter.CustomerAdapter;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.model.AnyEventType;
import com.hamitao.kids.model.RelationInfo;
import com.hamitao.kids.mvp.ipresenter.IDevicePresenter;
import com.hamitao.kids.mvp.presenter.DevicePresenterImpl;
import com.hamitao.kids.utils.DataUtil;
import com.hamitao.kids.utils.WindowUtils;
import com.hamitao.requestframe.entity.QueryRelationInfo;
import com.hamitao.requestframe.view.QueryRelationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Conversation;

/**
 * 会话列表界面
 *
 * @data on 2018/6/6 10:37
 * @describe:
 */

public class SessionListActivity extends BaseMsgActivity {
    @BindView(R.id.rv_contacts)
    RecyclerView mRecyclerView;

    @BindView(R.id.iv_shake)
    ImageView ivShake;

    private IDevicePresenter devicePresenter;

    private CustomerAdapter mCustomerAdapter;//关系

    /**
     * 所有绑定的关系
     */
    private List<RelationInfo> mRelationInfos = new ArrayList<>();

    /**
     * 所有的单聊
     */
    private List<RelationInfo> singerInfos;

    /**
     * 会话列表（包括群组）
     */
    private List<RelationInfo> relationInfoList = new ArrayList<>();

    private int layout = R.layout.item_contacts;

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_customer);
        EventBus.getDefault().post(new AnyEventType(Constants.FLAG_UN_READCOUNT, ""));
    }

    @Override
    public void initData() {
        ivShake.setVisibility(getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)?View.VISIBLE:View.GONE);
        devicePresenter = new DevicePresenterImpl(mContext, mQueryRelationView);
        initCustomer();

        //隐藏弹窗
        WindowUtils.hidePopupWindow();

        if (!isNetworkAvailable()){
            stopTTS();
            speak(FuncTitle.CONTENT_VOICE_HINT_NET_DISCONNTCT.toString());
        }
    }


    //初始化关系列表
    private void initCustomer() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mCustomerAdapter = new CustomerAdapter(mRecyclerView, R.layout.item_contacts);
        mRecyclerView.setAdapter(mCustomerAdapter);
        mCustomerAdapter.setOnContactsListener(mOnContactsListener);
        devicePresenter.queryRelation(MyApp.getInstance().getSpManager().getTerminalId());
    }


    private QueryRelationView mQueryRelationView = new QueryRelationView() {

        @Override
        public void onSuccess(QueryRelationInfo info) {
            List<QueryRelationInfo.ResponseDataObjBean.RelationBean.CustomerInfosBean> customerInfos = info.getResponseDataObj().getRelation().getCustomerInfos();
            List<RelationInfo> relationInfos = new ArrayList<>();
            if (customerInfos != null && customerInfos.size() != 0) {
//                List<RelationInfo> singerInfos = KeyboardUtil.getSingerChatList(customerInfos);
                singerInfos = DataUtil.getSingerChatList(customerInfos);
                relationInfos.addAll(singerInfos);
            }
            mRelationInfos.clear();
            mRelationInfos.addAll(relationInfos);
            getSessionList();
        }

        @Override
        public void onError(String result) {
            Logger.d(TAG, "聊天联系人 获取失败=" + result);
        }
    };

    /**
     * 获取会话列表
     */
    private void getSessionList() {
        List<Conversation> conversations = JMessageClient.getConversationList();//会话列表
        if (conversations != null && conversations.size() != 0) {
            relationInfoList = DataUtil.CheckExit(conversations, mRelationInfos);

        } else {
            relationInfoList.addAll(mRelationInfos);
        }
        if (relationInfoList != null && relationInfoList.size() > 0) {
            mCustomerAdapter.setData(relationInfoList);
        }
    }


    private CustomerAdapter.OnContactsListener mOnContactsListener = new CustomerAdapter.OnContactsListener() {
        @Override
        public void onClickListener(int position) {
            RelationInfo relationInfo = relationInfoList.get(position);
            if (relationInfoList.size() > position) {
                if (JMessageClient.getMyInfo() == null) return;
                enterImChatActivity(position, relationInfo, singerInfos);
            }
        }
    };

    /**
     * 更新单条数据
     *
     * @param position
     */
    private void notifyItemChanged(int position) {
        if (relationInfoList.get(position).getMsgNum() != 0) {
            RelationInfo info = relationInfoList.get(position);
            info.setMsgNum(0);
            relationInfoList.remove(position);
            relationInfoList.add(position, info);
            mCustomerAdapter.notifyItemChanged(position);
        }
    }

    /**
     * 聊天之后把数据更新到第一条
     *
     * @param position
     */
    private void notifyItemChangedFrist(int position) {
        RelationInfo info = relationInfoList.get(position);
        relationInfoList.remove(position);
        relationInfoList.add(0, info);
        mCustomerAdapter.notifyDataSetChanged();
    }

    //任意写一个方法，给这个方法一个@Subscribe注解，参数类型可以自定义，但是一定要与你发出的类型相同
    @Subscribe
    public void getEventBus(AnyEventType anyEventType) {
        String flag = anyEventType.getFlag();
        if (!TextUtils.isEmpty(anyEventType.getContent())) {
            String content = anyEventType.getContent();
            if (Constants.FLAG_CHAT_LIST_REFRESH.equals(flag)) {
                notifyItemChangedFrist(Integer.parseInt(content)) ;
            } else if (Constants.FLAG_CHAT_LIST_REFRESH_MSG_NUM.equals(flag)) {
                notifyItemChanged(Integer.parseInt(content));
            }
        }
    }

    @OnClick({R.id.iv_shake})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_shake:
                openActivity(ShakeActivity.class);
                break;
        }
    }

    @Override
    public void receiveMessage() {//接收到消息回调
        devicePresenter.queryRelation(MyApp.getInstance().getSpManager().getTerminalId());
    }

}
