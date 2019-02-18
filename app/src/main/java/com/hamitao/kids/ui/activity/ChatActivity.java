package com.hamitao.kids.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hamitao.aispeech.engine.TTSEngine;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.framework.utils.FileUtil;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.chat.DefaultUser;
import com.hamitao.kids.chat.MessageInfo;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.model.AnyEventType;
import com.hamitao.kids.model.RelationInfo;
import com.hamitao.kids.mvp.ipresenter.IDevicePresenter;
import com.hamitao.kids.mvp.presenter.DevicePresenterImpl;
import com.hamitao.kids.record.AudioRecordButton;
import com.hamitao.kids.utils.GlideUtil;
import com.hamitao.kids.view.TitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import cn.jiguang.imui.commons.ImageLoader;
import cn.jiguang.imui.commons.models.IMessage;
import cn.jiguang.imui.messages.MessageList;
import cn.jiguang.imui.messages.MsgListAdapter;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.EventNotificationContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.android.api.options.MessageSendingOptions;
import cn.jpush.im.api.BasicCallback;

/**
 * @data on 2018/6/6 15:12
 * @describe: 聊天界面
 */
public class ChatActivity extends BaseMsgActivity {

    private String myDefaultHeader = "R.drawable.image_head";

    @BindView(R.id.btn_voice)
    AudioRecordButton btnVoice;//语音
    @BindView(R.id.btn_video)
    Button btnVideo;//视频
    @BindView(R.id.msg_list)
    MessageList msgList;

    private TitleView mTitle;

    private ImageLoader imageLoader; //图片加载

    private String loginName;
    private String bindName;

    private String sourceChannelid;

    private MsgListAdapter<MessageInfo> mAdapter;

    private List<MessageInfo> mMsgDatas = new ArrayList<>();

    private List<Message> mMsgList = new ArrayList<>();

    private MessageInfo MessageInfo; //接受到的单条消息

    private boolean isSingle;//单聊/群聊

    private Conversation conversation;

    public static final int PAGE_MESSAGE_COUNT = 18;
    private int mOffset = PAGE_MESSAGE_COUNT;

    private UserInfo userInfo;
    private String position;

    private String defaultAvatar = "";//默认头像地址

    private IDevicePresenter devicePresenter;

    private List<RelationInfo> relationInfos = new ArrayList<>();

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_video:
                    Logger.d(TAG, "点击了视频聊天");
//                    showCameraDialog(false, bindName, sourceChannelid);
//                    getP2PByGuidInfo(sourceChannelid,false);
                    enterVideoChatActivity(sourceChannelid, false, BaseConstant.INSTRUCT_ACTION_DEVICE_VIDEO_PHONE, bindName);
                    break;
            }
        }
    };

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_chat);
    }

    @Override
    public void initData() {
        EventBus.getDefault().post(new AnyEventType(Constants.FLAG_CLOSE_AWAKEN, ""));
        mTitle = new TitleView(this);
        Intent intent = getIntent();
        relationInfos = (List<RelationInfo>) intent.getSerializableExtra(Constants.FLAG_CUSTOMERS_INFO_ALL);
        RelationInfo relationInfo = (RelationInfo) intent.getSerializableExtra(Constants.FLAG_CUSTOMERS_INFO);
        position = intent.getStringExtra(Constants.FLAG_CUSTOMERS_INFO_POSIOTION);

        isSingle = relationInfo.isSinger();
        loginName = relationInfo.getLoginName();
        bindName = relationInfo.getNickName();
        sourceChannelid = relationInfo.getSourceChannelid();

        mTitle.setTitle(bindName);
        if (!isSingle) {
            btnVideo.setVisibility(View.GONE);
            btnVoice.setCompoundDrawables(null, null, null, null);
            msgList.setShowReceiverDisplayName(true);
            msgList.setDisplayNameTextColor(getResources().getColor(R.color.white));
        }
        devicePresenter = new DevicePresenterImpl(mContext);

        userInfo = JMessageClient.getMyInfo();
        initConversation();
        initImageLoader();
        initAdaper();

        btnVoice.setAudioFinishRecorderListener(new AudioRecordButton.AudioFinishRecorderListener() {
            @Override
            public void onDown() {
                if (!getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)){
                    btnVoice.setBackgroundColor(getResources().getColor(R.color.btn_uncheck));
                }
            }

            @Override
            public void onUp() {
                if (!getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)){
                    btnVoice.setBackgroundColor(getResources().getColor(R.color.btn_check));
                }
            }

            @Override
            public void onFinished(float seconds, String filePath) {
                if (!getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)){
                    btnVoice.setBackgroundColor(getResources().getColor(R.color.btn_check));
                }

                File voiceFile = new File(filePath);
                Message message1 = null;//创建消息
                try {
                    message1 = conversation.createSendVoiceMessage(voiceFile, (int) seconds);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                final MessageInfo message = new MessageInfo(null, IMessage.MessageType.SEND_VOICE.ordinal(), message1);

                if (userInfo.getAvatarFile() == null) {
                    defaultAvatar = FileUtil.getUriFromDrawableRes(mContext, R.drawable.image_head).getPath();
                    //上传头像
                    devicePresenter.updateUserAvatar();
                }
//                message.setUserInfo(new DefaultUser(userInfo.getUserName(), userInfo.getNickname(),
//                        userInfo.getAvatarFile() == null ? defaultAvatar : userInfo.getAvatarFile().getPath()));
                message.setUserInfo(new DefaultUser(userInfo.getUserName(), userInfo.getNickname(),
                        myDefaultHeader));

                if (seconds == 0) {
                    seconds = 1;
                }
//                speakTTS("咻~", TTSEngine.TTS_FLAG_COMP_NO_RESULT);
                speak(FuncTitle.CONTENT_VOICE_SEND_MSG.toString());
                message.setMediaFilePath(filePath);
                message.setDuration((long) seconds);
                message.setMessageStatus(IMessage.MessageStatus.SEND_GOING); //发送中
                message.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
                mAdapter.addToStart(message, true);
                MessageSendingOptions options = new MessageSendingOptions();
                options.setNeedReadReceipt(true);
                JMessageClient.sendMessage(message1, options);
                message1.setOnSendCompleteCallback(new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i == 0) {
                            message.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                            mAdapter.updateMessage(message);
                        }
                    }
                });
                voiceFile = null;
                if (!"-1".equals(position)) {
                    EventBus.getDefault().post(new AnyEventType(Constants.FLAG_CHAT_LIST_REFRESH, position));
                }

            }
        });
        btnVideo.setOnClickListener(mOnClickListener);
    }


    /**
     * 获取群组中用户的头像
     *
     * @return
     */
    private String getUserHeaderInGroup(String username) {

        if (relationInfos != null && relationInfos.size() > 0) {
            for (int i = 0; i < relationInfos.size(); i++) {
                if (relationInfos.get(i).getLoginName().equals(username)) {
                    return getUserHeader(relationInfos.get(i).getNickName());
                }
            }
        }
        return "R.drawable.icon_head_other";
    }

    /**
     * 获取用户头像
     *
     * @param nickName 关系名称（爸爸、妈妈）
     * @return
     */
    private String getUserHeader(String nickName) {
        if (nickName.equals("爸爸")) {
            return "R.drawable.icon_head_father";
        } else if (nickName.equals("妈妈")) {
            return "R.drawable.icon_head_mother";
        } else if (nickName.equals("爷爷")) {
            return "R.drawable.icon_head_grandpa";
        } else if (nickName.equals("奶奶")) {
            return "R.drawable.icon_head_grandma";
        } else if (nickName.equals("外公")) {
            return "R.drawable.icon_head_grandfather";
        } else if (nickName.equals("外婆")) {
            return "R.drawable.icon_head_grandmother";
        } else if (nickName.equals("叔叔")) {
            return "R.drawable.icon_head_uncle";
        } else if (nickName.equals("阿姨")) {
            return "R.drawable.icon_head_aunt";
        } else if (nickName.equals("哥哥")) {
            return "R.drawable.icon_head_brother";
        } else if (nickName.equals("姐姐")) {
            return "R.drawable.icon_head_sister";
        } else {
            return "R.drawable.icon_head_other";
        }
    }

    /**
     * 发送文本消息
     *
     * @param msg
     */
    private void sendTextMessage(String msg) {
        TextContent content = new TextContent(msg); //文本
        final Message message1 = conversation.createSendMessage(content);//创建文本消息
        final MessageInfo myMessage = new MessageInfo(msg, IMessage.MessageType.SEND_TEXT.ordinal(), message1);
        myMessage.setMessageStatus(IMessage.MessageStatus.CREATED);
        myMessage.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
        myMessage.setUserInfo(new DefaultUser(userInfo.getUserName(), userInfo.getNickname(), myDefaultHeader));
        myMessage.setMessageStatus(IMessage.MessageStatus.SEND_GOING); //发送中
        message1.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                myMessage.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED); //发送完成
                mAdapter.updateMessage(myMessage);
                if (i == 0) {
                    Logger.d(TAG, "发送成功");
                } else {
                    Logger.d(TAG, "发送失败");
                }
            }
        });
        MessageSendingOptions options = new MessageSendingOptions();
        options.setNeedReadReceipt(true);
        JMessageClient.sendMessage(message1, options);
        mAdapter.addToStart(myMessage, true);
    }

    /**
     * 初始化会话
     */
    private void initConversation() {
        //消息界面关闭通知
        JMessageClient.enterSingleConversation(deviceManager.getDeviceId());

        if (isSingle) {
            conversation = JMessageClient.getSingleConversation(loginName);
            if (conversation == null) {
                conversation = Conversation.createSingleConversation(loginName);
            }
        } else {
            conversation = JMessageClient.getGroupConversation(Long.parseLong(loginName));
            if (conversation == null) {
                conversation = Conversation.createGroupConversation(Long.parseLong(loginName));
            }
        }
        if (conversation != null) {
            conversation.resetUnreadCount();
            if (!"-1".equals(position)) {
                EventBus.getDefault().post(new AnyEventType(Constants.FLAG_CHAT_LIST_REFRESH_MSG_NUM, position));
            }
            mMsgList = conversation.getMessagesFromNewest(0, mOffset);
        }

        initChatMsg(mMsgList);

        if (conversation != null) {
            conversation.resetUnreadCount();
        }

    }

    private void initImageLoader() {
        //加载头像图片的方法
        imageLoader = new ImageLoader() {
            @Override
            public void loadAvatarImage(ImageView avatarImageView, String string) {
                if (string.contains("R.drawable")) {
                    Integer resId = getResources().getIdentifier(string.replace("R.drawable.", ""), "drawable", getPackageName());
                    avatarImageView.setImageResource(resId);
                }
            }

            @Override
            public void loadImage(ImageView imageView, String string) {
                GlideUtil.loadImage(mContext, string, imageView);
            }

            @Override
            public void loadVideo(ImageView imageCover, String uri) {

            }
        };
    }

    private void initAdaper() {
        mAdapter = new MsgListAdapter<MessageInfo>(deviceManager.getDeviceId(), new MsgListAdapter.HoldersConfig(), imageLoader);

        //单击消息事件，可以选择查看大图或者播放视频
        mAdapter.setOnMsgClickListener(new MsgListAdapter.OnMsgClickListener<MessageInfo>() {
            @Override
            public void onMessageClick(MessageInfo message) {
                Logger.d(TAG, "点击了消息");
            }
        });

        //长按消息
        mAdapter.setMsgLongClickListener(new MsgListAdapter.OnMsgLongClickListener<MessageInfo>() {
            @Override
            public void onMessageLongClick(View view, final MessageInfo message) {
//                vibrate(50);
                Logger.d(TAG, "长按消息执行操作");
            }
        });
        AudioManager audioManager = (AudioManager) mContext
                .getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setSpeakerphoneOn(true);
        mAdapter.addToEnd(mMsgDatas);

        mAdapter.setOnLoadMoreListener(new MsgListAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int page, int totalCount) {
                if (totalCount <= mMsgDatas.size()) {
                    Log.i("MessageListActivity", "Loading next page");
//                    loadNextPage();
//                    mAdapter.addToEndChronologically();
                }
            }
        });

        msgList.setAdapter(mAdapter);

        mAdapter.getLayoutManager().scrollToPosition(0);
    }

    private void initChatMsg(List<Message> mMsgList) {
        //目前仅支持 文本和语音
        if (mMsgList.size() > 0) {
            //  获取消息，message 为单条消息
            for (Message message : mMsgList) {
                if (message.getDirect().equals(MessageDirect.send)) {   //用户自己为发送方
                    if (message.getContentType().equals(ContentType.text)) {  //消息为文本类型
                        // 构建消息体
                        MessageInfo = new MessageInfo(((TextContent) message.getContent()).getText(), IMessage.MessageType.SEND_TEXT.ordinal(), message); //文本消息
                    } else {
                        //语音消息
                        final VoiceContent content = (VoiceContent) message.getContent();
                        MessageInfo = new MessageInfo(null, IMessage.MessageType.SEND_VOICE.ordinal(), message);  //语音消息
                        MessageInfo.setDuration(content.getDuration());
                        MessageInfo.setMessage(message);
                        MessageInfo.setMediaFilePath(content.getLocalPath());
                    }

                    if (userInfo.getAvatarFile() == null) {
                        defaultAvatar = FileUtil.getUriFromDrawableRes(mContext, R.drawable.image_head).getPath();
                        //上传头像
                        devicePresenter.updateUserAvatar();
                    }


                    MessageInfo.setUserInfo(new DefaultUser(userInfo.getUserName(), userInfo.getNickname(), myDefaultHeader));

                } else {
                    //这里是接收方
                    if (message.getContentType().equals(ContentType.text)) {
                        MessageInfo = new MessageInfo(((TextContent) message.getContent()).getText(), IMessage.MessageType.RECEIVE_TEXT.ordinal(), message); //文本消息

                        MessageInfo.setUserInfo(new DefaultUser(message.getFromUser().getUserName(), message.getFromUser().getNickname(),
                                isSingle ? getUserHeader(bindName) : getUserHeaderInGroup(message.getFromUser().getUserName())));

//                        MessageInfo.setUserInfo(new DefaultUser(message.getFromUser().getUserName(), message.getFromUser().getNickname(), isSingle ? getUserHeader(bindName) :
//                                message.getFromUser().getAvatarFile() == null
//                                        ? FileUtil.getUriFromDrawableRes(mContext, R.drawable.icon_chat_default_avatar).getPath() : message.getFromUser().getAvatarFile().getPath()));

                    } else if (message.getContentType().equals(ContentType.voice)) {
                        final VoiceContent content = (VoiceContent) message.getContent();
                        MessageInfo = new MessageInfo(null, IMessage.MessageType.RECEIVE_VOICE.ordinal(), message);  //语音消息
                        MessageInfo.setDuration(content.getDuration());
                        MessageInfo.setMessage(message);
                        MessageInfo.setMediaFilePath(content.getLocalPath());
                        MessageInfo.setUserInfo(new DefaultUser(message.getFromUser().getUserName(), message.getFromUser().getNickname(),
                                isSingle ? getUserHeader(bindName) : getUserHeaderInGroup(message.getFromUser().getUserName())));

                    } else if (message.getContentType().equals(ContentType.eventNotification)) {
                        EventNotificationContent.EventNotificationType type = ((EventNotificationContent) message.getContent()).getEventNotificationType();
                        switch (type) {
                            case group_member_added:

                                List<String> userNames = ((EventNotificationContent) message.getContent()).getUserNames();
                                for (int i = 0; i < userNames.size(); i++) {
                                    String userName = userNames.get(i);
                                    if (userName.equals(deviceManager.getDeviceId())) {//是自己
                                        userName = JMessageClient.getMyInfo().getNickname();
                                        MessageInfo = new MessageInfo(userName + " 进入群聊", IMessage.MessageType.EVENT.ordinal(), message);
                                    } else {
                                        MessageInfo = new MessageInfo(userName + " 进入群聊", IMessage.MessageType.EVENT.ordinal(), message);
                                    }
                                }
                                break;
                            case group_info_updated:
                                String msg = ((EventNotificationContent) message.getContent()).getEventText();
                                MessageInfo = new MessageInfo(msg, IMessage.MessageType.EVENT.ordinal(), message);
                                break;
                            case group_dissolved:
                                String dissolved = ((EventNotificationContent) message.getContent()).getEventText();
                                MessageInfo = new MessageInfo(dissolved, IMessage.MessageType.EVENT.ordinal(), message);
                                break;
                            case group_member_exit:
                                //成员退出
                                String exit = ((EventNotificationContent) message.getContent()).getEventText();
                                MessageInfo = new MessageInfo(exit, IMessage.MessageType.EVENT.ordinal(), message);
                                break;
                            case group_member_removed:
                                String remove = ((EventNotificationContent) message.getContent()).getEventText();
                                MessageInfo = new MessageInfo(remove, IMessage.MessageType.EVENT.ordinal(), message);
                                break;
                            default:
                                break;
                        }
                    }
                }
                MessageInfo.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(message.getCreateTime()));
                MessageInfo.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                mMsgDatas.add(MessageInfo);
            }
        }
    }

    /**
     * 收到消息
     *
     * @param event
     */
    public void onEvent(MessageEvent event) {

        final Message message = event.getMessage();
        message.setHaveRead(new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {

            }
        });
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (message.getTargetType() == ConversationType.single) {
                    UserInfo userInfo = (UserInfo) message.getTargetInfo();
                    String targetId = userInfo.getUserName();
                    if (!targetId.equals(loginName)) {
                        return;
                    }
                } else {
                    long gid = ((GroupInfo) message.getTargetInfo()).getGroupID();
                    if (Long.parseLong(loginName) != gid) {
                        return;
                    }
                }

                //创建一个消息对象,目前只接收文本和语音
                if (message.getContentType().equals(ContentType.text)) {
                    MessageInfo = new MessageInfo(((TextContent) message.getContent()).getText(), IMessage.MessageType.RECEIVE_TEXT.ordinal(), message);   //接收到文本消息
                } else if (message.getContentType().equals(ContentType.voice)) {
                    final VoiceContent content = (VoiceContent) message.getContent();
                    MessageInfo = new MessageInfo(null, IMessage.MessageType.RECEIVE_VOICE.ordinal(), message);  //语音消息
                    MessageInfo.setDuration(content.getDuration());
                    MessageInfo.setMessage(message);
//                    MessageInfo.setMessageStatus(IMessage.MessageStatus.RECEIVE_GOING);
                    MessageInfo.setMediaFilePath(content.getLocalPath());
//                    MessageInfo.setUserInfo(new DefaultUser(message.getFromUser().getUserName(), message.getFromUser().getNickname(),
//                            message.getFromUser().getAvatarFile() == null
//                                    ? FileUtil.getUriFromDrawableRes(mContext, R.drawable.icon_chat_default_avatar).getPath()
//                                    : message.getFromUser().getAvatarFile().getPath()));

                } else if (message.getContentType().equals(ContentType.eventNotification)) {
                    EventNotificationContent.EventNotificationType type = ((EventNotificationContent) message
                            .getContent()).getEventNotificationType();
                    switch (type) {
                        case group_member_added:
                            String msg = ((EventNotificationContent) message.getContent()).getEventText();
                            MessageInfo = new MessageInfo(msg, IMessage.MessageType.EVENT.ordinal(), message);
                            break;
                        case group_info_updated:
                            String aaa = ((EventNotificationContent) message.getContent()).getEventText();
                            MessageInfo = new MessageInfo(aaa, IMessage.MessageType.EVENT.ordinal(), message);
                            break;
                        case group_dissolved:
                            String dissolved = ((EventNotificationContent) message.getContent()).getEventText();
                            MessageInfo = new MessageInfo(dissolved, IMessage.MessageType.EVENT.ordinal(), message);
                            break;
                        case group_member_exit:
                            //成员退出
                            String exit = ((EventNotificationContent) message.getContent()).getEventText();
                            MessageInfo = new MessageInfo(exit, IMessage.MessageType.EVENT.ordinal(), message);
                            break;
                        case group_member_removed:
                            String remove = ((EventNotificationContent) message.getContent()).getEventText();
                            MessageInfo = new MessageInfo(remove, IMessage.MessageType.EVENT.ordinal(), message);
                            break;
                        default:
                            break;
                    }
                }
                MessageInfo.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(message.getCreateTime()));
                MessageInfo.setMessageStatus(IMessage.MessageStatus.SEND_GOING);
                MessageInfo.setUserInfo(new DefaultUser(message.getFromUser().getUserName(), message.getFromUser().getNickname(),
                        isSingle ? getUserHeader(bindName) : getUserHeaderInGroup(message.getFromUser().getUserName())));
                mAdapter.addToStart(MessageInfo, true);
                mMsgDatas.add(MessageInfo); //收到消息时，添加到集合
                mAdapter.notifyDataSetChanged();
                conversation.resetUnreadCount();
            }
        });
    }

    @Subscribe
    public void getEventBus(AnyEventType anyEventType) {
        String flag = anyEventType.getFlag();
        String content = anyEventType.getContent();
        Logger.d(TAG, "来消息了   " + flag);
        if (flag.equals(Constants.FLAG_SEND_TEXT_MSG_CANCEL)) {
            sendTextMessage(getStrByRes(R.string.cancelled));
            EventBus.getDefault().post(new AnyEventType(Constants.FLAG_CLOSE_AWAKEN, ""));
        } else if (flag.equals(Constants.FLAG_SEND_TEXT_MSG_REFUSED)) {
            sendTextMessage(getStrByRes(R.string.refused));
            EventBus.getDefault().post(new AnyEventType(Constants.FLAG_CLOSE_AWAKEN, ""));
        } else if (flag.equals(Constants.FLAG_SEND_TEXT_MSG_DURATION)) {
            sendTextMessage(getStrByRes(R.string.duration) + " " + content);
            EventBus.getDefault().post(new AnyEventType(Constants.FLAG_CLOSE_AWAKEN, ""));
        } else if (flag.equals(Constants.FLAG_VIDEOCHAT_ACTIVITY_FINISH)) {
            EventBus.getDefault().post(new AnyEventType(Constants.FLAG_CLOSE_AWAKEN, ""));
        } else if (flag.equals(Constants.FLAG_SEND_TEXT_LINE_BUSY)) {
            toast(getStrByRes(R.string.hint_line_busy));
            sendTextMessage(getStrByRes(R.string.hint_line_busy));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onHomePressed() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new AnyEventType(Constants.FLAG_RE_OPEN_AWAKEN, ""));
    }
}
