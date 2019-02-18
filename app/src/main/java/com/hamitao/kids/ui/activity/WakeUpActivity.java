package com.hamitao.kids.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.WindowManager;

import com.aispeech.common.JSONResultParser;
import com.hamitao.aispeech.engine.TTSEngine;
import com.hamitao.aispeech.util.InstructUtils;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.framework.model.MediaInfo;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.callback.OnEmojiCallBack;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.manager.MediaManager.EmojiView;
import com.hamitao.kids.manager.wakeup.WakeUpFactory;
import com.hamitao.kids.manager.wakeup.WakeUpUtil;
import com.hamitao.kids.manager.wakeup.WakeUpView;
import com.hamitao.kids.manager.wakeup.callback.DeviceCallBack;
import com.hamitao.kids.manager.wakeup.callback.InstructionCallBack;
import com.hamitao.kids.manager.wakeup.callback.ServerCallBack;
import com.hamitao.kids.model.AnyEventType;
import com.hamitao.kids.model.RelationInfo;
import com.hamitao.kids.turing.callback.TuringParserCallBack;
import com.hamitao.kids.turing.constant.TuringConstant;
import com.hamitao.kids.turing.model.AlarmBean;
import com.hamitao.kids.turing.util.TuringUtil;
import com.hamitao.kids.utils.BrightUtil;
import com.hamitao.kids.utils.DataUtil;
import com.hamitao.kids.utils.PlayHint;
import com.hamitao.kids.utils.ScreenUtil;
import com.hamitao.kids.utils.VoiceUtil;
import com.hamitao.kids.utils.WindowUtils;
import com.hamitao.requestframe.entity.CommonInfo;
import com.hamitao.requestframe.entity.ParseChineseInfo;
import com.hamitao.requestframe.view.CommonInfoView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

public class WakeUpActivity extends BaseMsgActivity {

    boolean isBack = false;

    @BindView(R.id.view_emoji)
    EmojiView emojiView;

    private WakeUpFactory wakeUpFactory;
    //    private AISpeechParserManager mAISpeechParserManager; //思必驰解析管理类
    private PlayHint playHint;

    private String mDomain;
    private String mContextId;

    //未听到消息
    private Handler exitHandler;
    private Runnable exitRunnable;

    private List<MediaInfo> curPlayMediaInfo;

    @Override
    public void setActivityView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_wake_up);
    }


    @Override
    public void initData() {
        initManager();
        emojiView.initEmoji();
        emojiView.setOnEmojiCallBack(mOnEmojiCallBack);

        Intent intent = getIntent();
        boolean isWakeUp = intent.getBooleanExtra(Constants.FLAG_START_WAKE_UP_IS_WAKE_UP, false);
        boolean isClickHome = intent.getBooleanExtra(Constants.FLAG_START_WAKE_UP_IS_CLICK_HOME, false);
        boolean isOpen = intent.getBooleanExtra(Constants.FLAG_START_WAKE_UP_IS_OPEN, false);
        Logger.d(TAG, "是否是直接唤醒=" + isWakeUp);
        if (isOpen) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    speakDoronResult(InstructUtils.respondAwakenRobotRespond());
                }
            }, 500);
        } else if (isClickHome) {
            speakDoronResult(InstructUtils.respondAwakenRobotRespond());
        } else if (isWakeUp) {
            onDeviceWakeUp();
        }
        //隐藏弹窗
        WindowUtils.hidePopupWindow();
    }

    @Subscribe
    public void getEventBus(AnyEventType anyEventType) {
        String flag = anyEventType.getFlag();
        if (Constants.FLAG_START_WAKE_UP_RESULT.equals(flag)) {
            Logger.d(TAG, "接收到唤醒请求的数据了");
            if (isBack) {
                EventBusPost(Constants.FLAG_START_WAKE_UP, "");//打开唤醒
                return;
            }
            emojiView.isShowDeviceListenView(false);
            String content = anyEventType.getContent();
            handlerAsrResult(content);
        } else if (Constants.FLAG_START_WAKE_UP_ASR_ERROR.equals(flag)) {
            speechAsrError();
        } else if (Constants.FLAG_START_WAKE_UP_NOT_ONE_SHOT.equals(flag)) {
            onDeviceWakeUp();
        } else if (TTSEngine.TTS_FLAG_COMP_OPEN_DORON_DIALOG.equals(flag)) {
            EventBusPost(Constants.FLAG_OPEN_DORON_DIALOGUE, mDomain + "," + mContextId);
            emojiView.isShowDeviceListenView(true);
        } else if (TTSEngine.TTS_FLAG_COMP_FINISH.equals(flag)) {
            finish();
        } else if (TTSEngine.TTS_FLAG_COMP_PLAY_CONTENT.equals(flag)) {
            enterPlay();
        } else if (TTSEngine.TTS_FLAG_COMP_OPEN_APP.equals(flag)) {
            openActivity(pClass);
            finish();
        } else if (TTSEngine.TTS_FLAG_COMP_NO_RESULT.equals(flag)) {
            emojiView.isShowDeviceTalkView(false);
        } else if (TTSEngine.TTS_FLAG_COMP_START_ASR.equals(flag)) {
            emojiView.isShowDeviceListenView(true);
        }
    }

    /**
     * 进入播放界面
     */
    public void enterPlay() {
        if (curPlayMediaInfo != null && curPlayMediaInfo.size() != 0) {
            //进入播放界面
            enterPlayActivity(curPlayMediaInfo);
        } else {
            speakDoronResult(getStrByRes(R.string.hint_aispeech_no_seach_data));
        }
    }

    /**
     * 思必驰识别出错（未识别到信息）
     */
    private void speechAsrError() {
        Logger.d(TAG, "没有检测到语音");
        emojiView.isShowDeviceListenView(false);
        speakNoResult(InstructUtils.respondNoVoiceDetected(), TTSEngine.TTS_FLAG_COMP_NO_RESULT);
        exitHandler = new Handler();
        exitRunnable = new Runnable() {
            @Override
            public void run() {
                speakNoResult(InstructUtils.respondExitWakeUp(), TTSEngine.TTS_FLAG_COMP_FINISH);
            }
        };
        exitHandler.postDelayed(exitRunnable, 15000);
    }


    /**
     * 设备被唤醒
     */
    private void onDeviceWakeUp() {
        Logger.d(TAG, "设备被唤醒了");
        removeExitHandle();
        mContextId = "";
        mDomain = "";
        ScreenUtil.screenOn(this);
        emojiView.isShowDeviceTalkView(true);

    }

    /**
     * 初始化管理类
     */
    private void initManager() {
//        mAISpeechParserManager = new AISpeechParserManager(mAISpeechParserCallBack);
        wakeUpFactory = new WakeUpFactory(mContext, mWakeUpView, mInstructionCallBack, mServerCallBack, mDeviceCallBack);
        voiceUtil = new VoiceUtil(mContext);
        brightUtil = new BrightUtil(mActivity);
        playHint = new PlayHint(mContext);
    }

    private void handlerAsrResult(String result) {
        wakeUpFactory.handlerAsrResult(result);
    }

    //    private String speechAsrResult = "";//思必驰Asr识别结果
    private String turingResult = "";//Turing数据
    private boolean isDealWithData = false;//数据是否处理过

    private WakeUpView mWakeUpView = new WakeUpView() {

        @Override
        public void handlerAsrResultListener(String result) {
//            speechAsrResult = result;
            isDealWithData = false;
            JSONResultParser parser = new JSONResultParser(result);
            String input = parser.getInput();
            Logger.d(TAG, "识别结果   input=" + input);
            if (!TextUtils.isEmpty(input)) {
                if (!wakeUpFactory.handlerInstruction(input)) {
                    wakeUpFactory.handlerParsingByServer(input);
                    wakeUpFactory.handlerParsingByTuring(input);
                }
//                wakeUpFactory.handlerParsingByAISpeech();
            } else {
                speakDoronResult(InstructUtils.respondNoAsrContent());
            }
        }

        @Override
        public void handlerParsingByServerSuccess(ParseChineseInfo info) {
            Logger.d(TAG, "服务器+数据请求成功");
            if (isDealWithData) {
                Logger.d(TAG, "Turing数据已经处理，无需再处理服务器的数据");
                return;
            }
            if (WakeUpUtil.isNullParseChinese(info)) {
                Logger.d(TAG, "==走自己服务器解析 ");
                wakeUpFactory.handlerDataByServer(info);
                turingResult = "";
                isDealWithData = true;
            } else {
                Logger.d(TAG, "==自己服务器无数据 ");
                if (!TextUtils.isEmpty(turingResult)) {
                    handlerTuringData();
                }
            }
            removeHandle();
        }

        @Override
        public void handlerParsingByServerError(String result) {
            Logger.d(TAG, "服务器数据请求失败 ==   result=" + result);
            if (isDealWithData) {
                Logger.d(TAG, "已处理过数据，无需再处理数据");
                return;
            }
            if (!TextUtils.isEmpty(turingResult)) {
                handlerTuringData();
                removeHandle();
            }
        }

        @Override
        public void handlerParsingByTuringSuccess(String result) {
            turingResult = result;
            if (isBack || isDealWithData) {
                removeHandle();
                return;
            }

            Logger.d(TAG, "开启handler");
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    Logger.d(TAG, "==自己服务器2S之后没有任何返回结果，直接执行思必驰解析结果");
                    handlerTuringData();
                    turingResult = "";
                    isDealWithData = true;
                }
            };
            handler.postDelayed(runnable, 2000);
        }
    };

    private void handlerTuringData() {
        Logger.d(TAG, "处理turing的内容==");
        String result = turingResult;
        turingResult = "";
        wakeUpFactory.handlerDataByTuring(result, new TuringParserCallBack() {
            @Override
            public void readValue(String value) {
                Logger.d(TAG, "需要TTS播放的内容：" + value);
                speakDoronResult(value);
            }

            @Override
            public void playUrl(String value, String url, String title) {
                Logger.d(TAG, "需要TTS播放的内容：" + value + " 播放完tts需要播放的url=" + url + "  title=" + title);
                if (isBack) {
                    return;
                }
                if (TextUtils.isEmpty(url)) {
                    value = getStrByRes(R.string.hint_aispeech_no_seach_data);
                    speakNoResult(value, TTSEngine.TTS_FLAG_COMP_OPEN_DORON_DIALOG);
                    return;
                }
                curPlayMediaInfo = TuringUtil.getMeidaDataByUrl(url, title);
                speakNoResult(value, TTSEngine.TTS_FLAG_COMP_PLAY_CONTENT);
            }

            @Override
            public void callPhone(String value, String name) {
                if (isBack) {
                    return;
                }
                Logger.d(TAG, "提示：" + value + "    要打电话给：" + name);
                //关系列表  contactsInfos
                wakeUpFactory.queryTelephoneContact(MyApp.getInstance().getSpManager().getTerminalId(), name);
            }

            @Override
            public void exit() {
                Logger.d(TAG, "用户退出");
                speakNoResult(InstructUtils.respondExit(), TTSEngine.TTS_FLAG_COMP_FINISH);
            }

            @Override
            public void setState(String values, int operateState) {
                Logger.d(TAG, "设置  " + operateState);
                switch (operateState) {
                    case TuringConstant.CODE_OS_SYS_SETTING_ADD_VOICE://增大音量
                        voiceUtil.addVoide();
                        break;
                    case TuringConstant.CODE_OS_SYS_SETTING_MINUS_VOICE://减小音量
                        voiceUtil.reduceVoide();
                        break;
                    case TuringConstant.CODE_OS_SYS_SETTING_ADD_BRIGHTNESS://增大屏幕亮度
                        brightUtil.addBright();
                        break;
                    case TuringConstant.CODE_OS_SYS_SETTING_MINUS_BRIGHTNESS://减小屏幕亮度
                        brightUtil.reduceBright();
                        break;
                    case TuringConstant.CODE_OS_SYS_SETTING_ELECTRICITY_QUERY://电量查询
                        speakDoronResult("当前设备电量为，百分之" + deviceManager.getDeviceBattery());
                        return;
                }
                speakDoronResult(values);

            }

            @Override
            public void unknownContent() {
                Logger.d(TAG, "未知领域");
                speakDoronResult(getStrByRes(R.string.hint_aispeech_no_data));
            }

            @Override
            public void startApp(String values, String appName) {
                Logger.d(TAG, "打开：" + appName);
                if (appName.equals("拍照") || appName.equals("相机") || appName.equals("相册")) {//进入拍照界面
                    pClass = AlbumActivity.class;
                } else if (appName.equals("打电话") || appName.equals("联系人") || appName.equals("通讯录") || appName.equals("电话本")) {
                    pClass = ContactActivity.class;
                } else if (appName.equals("聊天")) {
                    pClass = SessionListActivity.class;
                } else if (appName.equals("设置")) {
                    pClass = SettingActivity.class;
                } else if (appName.equals("机器绑定码") || appName.equals("机器绑定") || appName.equals("设备绑定")) {
                    pClass = MachineBindingCodeActivity.class;
                } else if (appName.equals("本机信息")) {
                    pClass = LocalInfoActivity.class;
                } else if (appName.equals("使用向导")) {
                    pClass = UsingWizardActivity.class;
                } else if (appName.equals("扫绘本")) {
                    pClass = ScanBookActivity.class;
                } else {
                    speakDoronResult(getStrByRes(R.string.hint_aispeech_no_data));
                    return;
                }
                speakNoResult(values, TTSEngine.TTS_FLAG_COMP_OPEN_APP);


            }

            @Override
            public void setAlarm(String values, AlarmBean alarmBean) {
                speakDoronResult(values);
                //将闹钟添加到服务器
                publicPresenter.addTimerAlarm(DataUtil.getTimerAlarmData(
                        deviceManager.getDeviceId(), MyApp.getInstance().getSpManager().getTerminalId(), alarmBean), new CommonInfoView() {
                    @Override
                    public void onSuccess(CommonInfo info) {
                        //添加成功之后，查询闹钟，查询完了就设置闹钟
                        Logger.d(TAG, "将设置的闹钟添加到服务器成功");
                        publicPresenter.queryTimerAlarm(MyApp.getInstance().getSpManager().getTerminalId());
                    }

                    @Override
                    public void onError(String result) {
                        Logger.d(TAG, "将设置的闹钟添加到服务器失败");
                        speakDoronResult("添加闹钟出错了");
                    }
                });

            }
        });
    }

    private Class<?> pClass;
    //服务器请求等待2s
    private Handler handler;
    private Runnable runnable;

    /**
     * 移除服务器等待2s返回数据的定时器
     */
    private void removeHandle() {
//        speechAsrResult = "";
        if (handler != null && runnable != null) {
            Logger.d(TAG, "数据得到回应，移除计时器");
            handler.removeCallbacks(runnable);
        }
    }

    /**
     * 移除退出唤醒的定时器
     */
    private void removeExitHandle() {
        if (exitHandler != null && exitRunnable != null) {
            Logger.d(TAG, "移除退出唤醒的定时器");
            exitHandler.removeCallbacks(exitRunnable);
        }
    }


    /**
     * 硬指令回调
     */
    private InstructionCallBack mInstructionCallBack = new InstructionCallBack() {
        @Override
        public void onShutDown() {
            Logger.d(TAG, "关机");
            turnOffBroadcast();
        }

        @Override
        public void onPausePlay() {
            Logger.d(TAG, "暂停播放");
            speakTTS(getStrByRes(R.string.hint_pause_play), TTSEngine.TTS_FLAG_COMP_FINISH);
            dealPausePlayEvent();

        }

        @Override
        public void onResumePlay() {
            Logger.d(TAG, "继续播放");
            finish();
            dealResumePlayEvent();
        }

        @Override
        public void onTurnOnLight() {
            Logger.d(TAG, "关灯");
        }

        @Override
        public void onOpenFunc(String flag) {
            finish();
            enterPlayActivity(flag);
        }
    };

    /**
     * 服务器数据解析结果回调
     */
    private ServerCallBack mServerCallBack = new ServerCallBack() {
        @Override
        public void onPlayContent(List<MediaInfo> mediaInfos) {
            Logger.d(TAG, "服务器返回的数据回调 请求结果：" + mediaInfos.size());
            //进入播放
            if (isBack) return;
            enterPlayActivity(mediaInfos);
        }

        @Override
        public void onImChat(String whoName) {
            Logger.d(TAG, "IM聊天：" + whoName);
            //进入IM聊天
            enterChat(whoName, false);
        }

        @Override
        public void onVideoChat(String whoName) {
            Logger.d(TAG, "视频聊天：" + whoName);
            //进入视频聊天
            enterChat(whoName, true);
        }
    };

    private DeviceCallBack mDeviceCallBack = new DeviceCallBack() {

        @Override
        public void onQueryContactSuccess(String phoneNum) {
            publicPresenter.callPhone(phoneNum);

        }

        @Override
        public void onQueryContactError(String result) {
            Logger.d(TAG, "电话本查询失败  " + result);
            speakDoronResult(InstructUtils.respondNoAddSingleContact());

        }

        @Override
        public void enterVideoChat(String sourceChannelid, String whoName) {
            removeHandle();
            enterVideoChatActivity(sourceChannelid, false, BaseConstant.INSTRUCT_ACTION_DEVICE_VIDEO_PHONE, whoName);
        }

        @Override
        public void enterImChat(RelationInfo relationInfo) {
            enterImChatActivity(-1, relationInfo, null);
        }

        @Override
        public void onQueryRelationError(String result) {
            Logger.d(TAG, "联系人查询失败  " + result);
            speakDoronResult(InstructUtils.respondNoAddSingleContact());
        }
    };


//    private AISpeechParserCallBack mAISpeechParserCallBack = new AISpeechParserCallBack() {
//        @Override
//        public void onChatListener(String output) {
//            if (TextUtils.isEmpty(output)) {
//                output = getStrByRes(R.string.hint_aispeech_no_data);
//            } else if ("...".equals(output)) {
//                output = getStrByRes(R.string.hint_no_answer);
//            }
//            speakDoronResult(output);
//        }
//
//        @Override
//        public void onWeatherListener(String output) {
//            speakDoronResult(output);
//        }
//
//
//        @Override
//        public void onCalendarListener(String output) {
//            Logger.d(TAG, "onCalendarListener   output=" + output);
//            speakDoronResult(output);
//        }
//
//        @Override
//        public void onStockListener(String output) {
//            speakDoronResult(output);
//        }
//
//        @Override
//        public void onPoetryListener(String output) {
//            speakDoronResult(output);
//        }
//
//        @Override
//        public void onMusicListener(String output, String data) {
//            if (isBack) {
//                return;
//            }
//            curPlayMediaInfo = AISpeechParserUtil.getParserDbdata(data);
//            speakNoResult(output, TTSEngine.TTS_FLAG_COMP_PLAY_CONTENT);
//        }
//
//        @Override
//        public void onCommandListener(String nlu) {
//            if (isBack) {
//                return;
//            }
//            Logger.d(TAG, "   nlu=" + nlu);
//            try {
//                String objectValue = "";
//                String operationValue = "";
//                String brightnessValue = "";
//
//                JSONObject dataJson = new JSONObject(nlu);
//                if (dataJson.has(FLAG_PARSER_NLU_OBJECT)) {
//                    objectValue = dataJson.getString(FLAG_PARSER_NLU_OBJECT);
//                    Logger.d(TAG, "objectValue=" + objectValue);
//                }
//                if (dataJson.has(FLAG_PARSER_NLU_OPERATION)) {
//                    operationValue = dataJson.getString(FLAG_PARSER_NLU_OPERATION);
//                    Logger.d(TAG, "operationValue=" + operationValue);
//                }
//                if (FLAG_PARSER_EXIT.equals(operationValue)) {//退出
//                    speakNoResult(InstructUtils.respondExit(), TTSEngine.TTS_FLAG_COMP_FINISH);
//                    return;
//                }
//                if (FLAG_PARSER_TURN_OFF.equals(operationValue)) {//关机
//                    turnOffBroadcast();
//                    return;
//                }
//                if (dataJson.has(FLAG_PARSER_NLU_BRIGHTNESS)) {
//                    brightnessValue = dataJson.getString(FLAG_PARSER_NLU_BRIGHTNESS);
//                    Logger.d(TAG, "brightnessValue=" + brightnessValue);
//                }
//                if (!"".equals(objectValue) && Constants.VOLUME_VALUE.equals(objectValue)) {
//                    voiceUtil.setVoiceInfo(operationValue);
//                    speakDoronResult(getStrByRes(R.string.ok_setting_voice));
//                    return;
//                } else if (!"".equals(brightnessValue)) {
//                    brightUtil.setBrightInfo(brightnessValue);
//                } else if (!"".equals(objectValue) && Constants.VOLUME_MUSIC_COMMAND.equals(objectValue)) {//媒体控制
//                    if (dataJson.has(FLAG_PARSER_NLU_OPERATION)) {
//                        operationValue = dataJson.getString(FLAG_PARSER_NLU_OPERATION);
//                        if (operationValue != null) {
//                            if (operationValue.equals(Constants.VOLUME_MUSIC_COMMAND_ONPAUSE)) {//暂停
//                                finish();
//                                dealPausePlayEvent();
//                            } else if (operationValue.equals(Constants.VOLUME_MUSIC_COMMAND_ONRESUME)) {//继续
//                                finish();
//                                dealResumePlayEvent();
//                            }
//                        }
//                    }
//                } else {
//                    speakDoronResult(getStrByRes(R.string.hint_aispeech_no_data));
//                    return;
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                speakDoronResult(getStrByRes(R.string.hint_aispeech_no_data));
//
//            }
//            speakDoronResult(getStrByRes(R.string.ok));
//        }
//
//        @Override
//        public void onTranslationListener(String output) {
//            speakDoronResult(output);
//        }
//
//        @Override
//        public void onCalculatorListener(String output) {
//            speakDoronResult(getStrByRes(R.string.is_equal) + "," + output);
//        }
//
//        @Override
//        public void onDoMainIsNullListener() {
//            speakDoronResult(getStrByRes(R.string.hint_aispeech_no_data));
//        }
//
//        @Override
//        public void onMakeCallListener(String contact) {
//            if (isBack) {
//                return;
//            }
//            //关系列表  contactsInfos
//            Logger.d(TAG, "打电话给：" + contact);
//            wakeUpFactory.queryTelephoneContact(MyApp.getInstance().getSpManager().getTerminalId(), contact);
//        }
//
//        @Override
//        public void onFilmTelListener() {
//            speakDoronResult(getStrByRes(R.string.hint_no_film_tel));
//        }
//
//        @Override
//        public void onNetfmListener(String output, String data) {//电台
//            if (isBack) {
//                return;
//            }
//            curPlayMediaInfo = AISpeechParserUtil.getParserNetfmData(data);
//            speakNoResult(output, TTSEngine.TTS_FLAG_COMP_PLAY_CONTENT);
//        }
//
//        @Override
//        public void onReminderListener(String output, String data) {
//            if (isBack) {
//                return;
//            }
//            Logger.d(TAG, "output=" + output + "     data=" + data);
////            ttsManagerOther.speak(output);
//            speakDoronResult(output);
//            if (!TextUtils.isEmpty(data)) {
//                AlarmInfo alarmInfo = AISpeechParserUtil.getParserReminderDbdata(data);
//                publicPresenter.addTimerAlarm(DataUtil.getTimerAlarmData(deviceManager, MyApp.getInstance().getSpManager(), alarmInfo), null);
//            }
//        }
//
//        @Override
//        public void onPassDorenDialog(String domain, String contextId) {
//            Logger.d(TAG, "domain=" + domain + "    contextId=" + contextId);
//            mDomain = domain;
//            mContextId = contextId;
//        }
//
//        @Override
//        public void onNewsListener(String output, String data) {
//            if (isBack) {
//                return;
//            }
//            curPlayMediaInfo = AISpeechParserUtil.getParserNewsData(data);
//            speakNoResult(output, TTSEngine.TTS_FLAG_COMP_PLAY_CONTENT);
//        }
//    };

    //戳表情回调
    private OnEmojiCallBack mOnEmojiCallBack = new OnEmojiCallBack() {
        @Override
        public void onPokeFace() {
            speak(FuncTitle.CONTENT_POKE_FACE.toString());
        }

        @Override
        public void onPokeEyes() {
            speak(FuncTitle.CONTENT_POKE_EYES.toString());
        }

    };

    /**
     * 播放 提示音
     *
     * @param res
     */
    @Override
    public void speak(String res) {
        playHint.playFuncTitle(res);
    }

    /**
     * 进入播放界面
     */
    private void enterPlayActivity(List<MediaInfo> mediaInfos) {
        if (isBack) {
            return;
        }
        enterPlayActivity(Constants.FLAG_ENTER_PLAY_ACTIVITY_WAKE_UP_RESULT, mediaInfos);
        finish();
    }

    /**
     * 进入聊天
     *
     * @param whoName
     * @param isVideoChat 是否是视频聊天
     */
    private void enterChat(String whoName, boolean isVideoChat) {
        wakeUpFactory.queryImChatRelation(MyApp.getInstance().getSpManager().getTerminalId(), whoName, isVideoChat);
    }


    @Override
    public void onHomePressed() {
    }



    @Override
    public void onBackPressed() {
        isBack = true;
        super.onBackPressed();
    }

    private VoiceUtil voiceUtil;//音量设置
    private BrightUtil brightUtil;//亮度设置

    /**
     * 多伦对话
     *
     * @param key
     */
    private void speakDoronResult(String key) {
        speakNoResult(key, TTSEngine.TTS_FLAG_COMP_OPEN_DORON_DIALOG);
    }

    /**
     * tts播放完成之后 不继续监听说话
     */
    private void speakNoResult(String content, String flag) {
        Logger.d(TAG, "开启设备说话");
        speakTTS(content, flag);
        /*****开启设备说表情***/

        emojiView.isShowDeviceTalkView(true);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        EventBusPost(Constants.FLAG_START_WAKE_UP, "");//打开唤醒

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        Logger.d(TAG, "==onPause==");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Logger.d(TAG, "==onStop==");
        super.onStop();
        stopTTS();
    }

    @Override
    protected void onDestroy() {
        Logger.d(TAG, "==onDestroy==");
        EventBusPost(Constants.FLAG_START_WAKE_UP, "");//打开唤醒

        super.onDestroy();
//        TuringEngine.getSingleton().cancelRequest();

        removeHandle();//移除handler
        removeExitHandle();//移除退出的handler
        mDomain = "";
        mContextId = "";
    }
}
