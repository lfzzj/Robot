package com.hamitao.framework.constant;

import android.os.Environment;

import com.hamitao.framework.R;

/**
 * @data on 2018/3/9
 * @describe:
 */

public class BaseConstant {

    public static final String VENDOR_HAMITAO = "hamitao";//厂家_哈蜜淘
    public static final String VENDOR_JINGUOWEI = "jinguowei";//厂家_金国威
    public static final String VENDOR_HAIER = "xiaoshuai";//厂家_海尔

    //sp
    public static final String SP_CHILD_DEVICE = "sp_child_device";
    public static final String SP_IS_FRIST_BOOT = "sp_is_frist_boot";//是否是第一次进入
    public static final String SP_IS_JM_REGISTER = "sp_is_jm_register";//jm是否注册过
    public static final String SP_TERMINAL_ID = "sp_terminal_id";//终端id
    public static final String SP_CHANNEL_ID = "sp_channel_id";//推送通道id
    public static final String SP_ALARM_CLOCK = "sp_alarm_clock";//保存设置的闹钟信息
    public static final String SP_POWER = "sp_power";//保存电量
    public static final String SP_IS_CHARGING = "sp_is_charging";//保存是否在充电
    public static final String SP_BIND_NAME = "sp_bind_name";//要给谁聊天的对象
    public static final String SP_COURSE_SCHEDULE = "sp_course_schedule";//保存课程表的id
    public static final String SP_IM_NICKNAME = "sp_im_nickname";//保存极光的昵称
    public static final String SP_OPEN_EYE_PROTECT = "sp_open_eye_protect";//护眼模式
    public static final String SP_PHONE_MANAGER = "sp_phone_manager";//电话接听管理
    public static final String SP_AUTO_DATA_TIME = "sp_auto_data_time";//获取自动日期和时间
    public static final String SP_PLAY_INFOS = "sp_play_infos";//播放信息
    public static final String SP_WIFI_SWITCH = "sp_wifi_switch";//wifi开关
    public static final String SP_MOBIE_SWITCH = "sp_mobie_switch";//移动网络开关
    public static final String SP_MOBIE_STATE= "sp_mobie_state";//移动网络状态

    //handler
    public static final int REPORT_DEVICE_INFO = 0x01;//上报设备的标识
    public static final int TIMING_SHOTDOWN = 0x02;//定时关机

    //极光推送
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";//自定义消息

    //返回数据类型
    public static final String SCENARIO_PLAY_CONTENT = "playcontent";//———内容
    public static final String SCENARIO_COMMAND = "command";//———指令

    public static final String SCENARIO_IM_CHAT = "chat";//———指令_IM聊天
    public static final String SCENARIO_VIDEO_CHAT = "video_chat";//———指令_视频聊天

    //具体内容格式
    public static final String MEDIA_CONTENT_YPE_KEYONOSS = "keyonoss";

    public static final String TYPE_AUDIO = "audio";//音频
    public static final String TYPE_MUSIC = "music";//音乐
    public static final String TYPE_VIDEO = "video";//视频
    public static final String TYPE_IMAGE = "image";//图片
    public static final String TYPE_ANIMATION = "animation";//动画（gif）
    public static final String TYPE_TEXT = "text";//文本
    public static final String TYPE_STREAMINGMEDIA = "streamingmedia";//媒体流(m3u8)

    public static final String TYPE_VIDEO_MP4 = "mp4";//视频(mp4)
    //前置静音
    public static final int ASR_FRONT = 5000;
    //后置静音
    public static final int ASR_BACK = 3000;

    //指令
    public static final String INSTRUCT_NOTICE_TAKEPHOTO = "TAKE_PHOTO";//拍照上传
    public static final String INSTRUCT_NOTICE_DEVICE_OPEN = "DEVICE_OPEN";//开机
    public static final String INSTRUCT_NOTICE_DEVICE_CLOSE = "DEVICE_CLOSE";//关机

    public static final String INSTRUCT_NOTICE_EYE_PROTECT = "EYE_PROTECT";//开启护眼
    public static final String INSTRUCT_NOTICE_EYE_UNPROTECT = "EYE_UNPROTECT";//关闭护眼

    public static final String INSTRUCT_ACTION_PLAY_CONTENT = "PLAY_CONTENT";//远程播放内容（投送）
    public static final String INSTRUCT_ACTION_UPDATA_CONTACT = "UPDATA_CONTACT";//增删改联系人

    public static final String INSTRUCT_ACTION_PHONE_VIDEO_DEVICE = "PHONE_VIDEO_DEVICE";// 手机找设备视频聊天
    public static final String INSTRUCT_ACTION_DEVICE_VIDEO_PHONE = "DEVICE_VIDEO_PHONE";//
    public static final String INSTRUCT_ACTION_PHONE_VOICE_DEVICE = "PHONE_VOICE_DEVICE";// 手机找设备语音聊天
    public static final String INSTRUCT_ACTION_DEVICE_VOICE_PHONE = "DEVICE_VOICE_PHONE";//
    public static final String INSTRUCT_ACTION_PHONE_MONITOR_DEVICE = "PHONE_MONITOR_DEVICE";

    public static final String INSTRUCT_ACTION_HANG_UP = "HANG_UP";//视频/音频 挂断
    public static final String INSTRUCT_ACTION_LINE_BUSY = "LINE_BUSY";//聊天占线

    public static final String INSTRUCT_ACTION_DEVICE_INFO = "DEVICE_INFO";//推送设备信息
    public static final String INSTRUCT_ACTION_DEVICE_RENAME = "DEVICE_RENAME";//修改nickname

    public static final String INSTRUCT_ACTION_START_PLAY = "START_PLAY";//开始播放
    public static final String INSTRUCT_ACTION_PAUSE_PLAY = "STOP_PLAY";//暂停播放
    public static final String INSTRUCT_ACTION_UPDATE_ALARM = "UPDATE_ALARM";//更新闹钟（增删改）
    public static final String INSTRUCT_ACTION_UPDATE_COURSE_SCHEDULE = "UPDATE_COURSE_SCHEDULE";//更新课程表（增删改）
    public static final String INSTRUCT_ACTION_PUSH_HABIT = "PUSH_HABIT";//习惯培养
    public static final String INSTRUCT_ACTION_PHONE_OPEN = "PHONE_OPEN";//电话接听管理  打开了之后40s自动接听
    public static final String INSTRUCT_ACTION_PHONE_CLOSE = "PHONE_CLOSE";//电话接听管理  关闭
    public static final String INSTRUCT_ACTION_TIMER = "TIMER";//定时关闭（从接收开始计时，到时间关闭正在播放的媒体）

    public static final String CAMERA_PHOTO_ADDRESS = "devicestorage/";//自动拍照照片上传地址
    public static final String CAMERA_PHOTO_ALBUM_ADDRESS = "devicealbum/";//相册地址
    public static final String CAMERA_DEVICE = "device";

    //设备注册时使用
    public static final String TERMINALTYPE = "kidsrobot";//终端型号,当前只支持："kidsrobot"
    public static final String DAGCLUSTER = "hamitao_kidsrobot";//dag(Data Access Group)标识符, 该字段为未来支持设备云而预设

    //接受服务数据返回 成功或失败
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    //闹钟的开关
    public static final String ENABLE = "enable";//开启
    public static final String DISABLE = "disable";//关闭

    public static final String OS = "android";//操作系统
    public static final String LANG = "zh";//语言
    public static final String HOST = "kidrobot";//宿主设备
    public static final String devicetype = "H2";//产品型号


    public static final int FLAG_RELATION_BINDNAME = 0;//bindname
    public static final int FLAG_RELATION_LOGINNAME = 1;//loginname
    public static final int FLAG_RELATION_CHANNELID = 2;//Channelid

    //场景的唯一标识

    //推送过来的内容表示
    public static final String PLAY_CONTENT_TYPE_MEDIAID = "mediaid";//单个
    public static final String PLAY_CONTENT_TYPE_CONTENTID = "contentid";//多个
    public static final String PLAY_CONTENT_TYPE_RECORDID = "recordid";//录音

    //保存到sd卡总路径
    public static final String PATH_BASE_SD = Environment.getExternalStorageDirectory() + "/childdevice/";
    public static final String path_jmheadimg = "jmheadimg.png";//头像

    //关机的广播
    public static final String ACTION_SHUTDOWN = "com.android.hamitao.kids.shutdown";

    public static final String FLAG_FACTORY_MODE = "*#*#83789#*#*";
    public static final String PKG_FACTORY_MODE = "com.sprd.validationtools";//工厂包名


    //机器人唤醒回应
    public static final String[] respondRobotAwaken = {"我在，你说吧！", "小朋友，我在呢", "嘻嘻，我们来聊天吧！"};
    public static final int[] respondRobotAwakenMedia = {R.raw.voice_wake_up_wozai, R.raw.voice_wake_up_xixi, R.raw.voice_wake_up_xiaopengyou};
    //没有识别到任何内容回应
    public static final String[] respondNoAsrContent = {"我没有听清楚，你再说一遍好吗？", "小朋友声音太小了，说大声点好吗?", "不小心发呆了，没有听清楚呢！嘻嘻。"};
    //长时间用户没有反应，退出唤醒界面
    public static final String[] respondExitWakeUp = {"有需要再叫我哦。", "我去休息啦。"};
    //没有检测到语音
    public static final String[] respondNoVoiceDetected = {"没有识别到任何内容"};
    //充电提示（插入电源）
    public static final String respondPowerConnected = "补给能量中…";
    //定时关闭的时间到了
    public static final String respondTimingClosure = "我们去休息吧，下次再一起玩咯～";
    //关机提示
    public static final String respondTurnOff = "小朋友我要休息啦，下次再见咯！";
    //没有添加联系人，提示去手机端添加
    public static final String respondNoAddContact = "咦，还没有添加联系人哦，请先打开手机APP添加联系人吧";
    //没有添加这个联系人，提示去手机端添加
    public static final String respondNoAddSingleContact = "咦，还没有添加这个联系人哦，请先打开手机APP添加联系人吧";
    //退出提示
    public static final String respondExit = "好的，那我先去休息了，有需要再叫我哦";

    /**
     * 一小时对应的毫秒值
     */
    public static final long HOUR_MILLIS = 60 * 60 * 1000;

    //硬指令
    public static final String INSTRUCTION_SHUTDOWN = "shutdown";//关机
    public static final String INSTRUCTION_PAUSE = "pause";//暂停播放
    public static final String INSTRUCTION_RESUME = "resume";//继续播放

    public static final String INSTRUCTION_OPEN_CHINESE_POETRY = "open_chinese_poetry";//打开国学诗词
    public static final String INSTRUCTION_OPEN_ENGLISH_STUDY = "open_english_study";//打开英语学习
    public static final String INSTRUCTION_OPEN_SAFETY_EDUCATION = "open_safety_education";//打开安全教育
    public static final String INSTRUCTION_OPEN_READ_BOOK = "open_read_book";//打开读绘本
    public static final String INSTRUCTION_OPEN_CHILDREN_SONG = "open_children_song";//打开儿歌
    public static final String INSTRUCTION_OPEN_STORY = "open_story";//打开故事
    public static final String INSTRUCTION_OPEN_ANIM = "open_anim";//打开动画


    public static final String[] boot = {"开机"};
    public static final String[] shutdown = {"关机"};
    public static final String[] study = {"学英语"};
    public static final String[] turnOnLight = {"开灯"};

    public static final String[] pause = {"暂停", "暂停播放", "我不想听了", "停止播放", "退出播放"};
    public static final String[] resume = {"继续播放"};

    public static final String[] openChinesePoetry = {"播放国学诗词", "我要听国学诗词", "我想听国学诗词"};
    public static final String[] openEnglishStudy = {"播放英语学习", "我要听英语学习", "我想听英语学习"};
    public static final String[] openSafetyEducation = {"播放安全教育", "我要听安全教育", "我想听安全教育"};
    public static final String[] openReadBook = {"打开绘本", "播放绘本", "我要听绘本", "我想听绘本"};
    public static final String[] openChildrenSong = {"播放儿歌", "我要听儿歌", "我想听儿歌", "唱首儿歌"};
    public static final String[] openStory = {"播放故事", "我要听故事", "我想听故事", "讲个故事", "给我讲个故事"};
    public static final String[] openAnim = {"播放动画", "我要看动画", "我要看动画片", "我想看动画片", "我想看动画", "播放一个动画"};

    public static final String[] open = {"打开", "进入"};

    //界面跳转传值
    public static final String BUNDLE_FOLLOW_ME = "bundle_follow_me";

    public static final String BUNDLE_FOLLOW_ME_POSITION = "bundle_follow_me_position";
    public static final String BUNDLE_FOLLOW_ME_DATAS = "bundle_follow_me_datas";

    //设备apk下载地址
    public static final String APK_DOWN_LOAD_PATH = "upgradestorage/device/xiaotaolauncher.apk";

    public static String pgk_fota_upgrade = "com.adups.fota";//无线升级的bug
//    public static String pgk_fota_upgrade = "com.aispeech.sample";//无线升级的bug

    public static String devicedoit = "devicedoit";

}