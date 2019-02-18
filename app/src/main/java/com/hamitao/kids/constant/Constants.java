package com.hamitao.kids.constant;

import com.hamitao.kids.utils.ManifestUtil;

/**
 * @data on 2018/3/19 9:34
 * @describe:
 */

public class Constants {

    /**
     * 极光app key
     */
    public static final String JPUSH_APPID = ManifestUtil.getJPushAppKey();

    //闹钟相关
    public static final String INTERVALMILLIS = "intervalMillis";
    public static final String ALARM_MSG = "alarm_msg";
    public static final String ALARM_ID = "alarm_id";
    public static final String ALARM_SERVICE_ID = "alarm_service_id";//服务器上的闹钟id
    public static final String SOUNDORVIBRATOR = "soundOrVibrator";
    public static final String ALARM_TIME = "alarm_time";

    //课程表相关
    public static final String COURSES_CHEDULE_INFO_DATA = "courses_chedule_info_data";

    public static final String VIDEO_PLAYING = "1";//视频正在播放的标识
    public static final String VIDEO_NOT_PLAYING = "0";//视频未在播放的标识

    public static final int totalTime = 20;//观看视频总共只能看20分钟，
    public static final int allowPlayTime = 60;//在60分钟内检测视频播放时长

    public static final int oneDataTime = 24 * 60 * 60 * 1000;//一天的时间
    public static final int oneMinuteTime = 60 * 1000;//一分钟的时间
    public static final int oneSecondTime = 1000;//一秒钟的时间
    public static final int fiveSecondTime = 5 * oneSecondTime;//5秒钟的时间
    public static final int threeSecondTime = 3 * oneSecondTime;//3秒钟的时间
    public static final int fortySecondTime = 40 * oneSecondTime;//40秒钟的时间
    public static final int twoSecondTime = 20 * oneSecondTime;//20秒钟的时间
    public static final int threeMinuteTime = 3 * oneMinuteTime;//三分钟的时间


    public static final String isVideoChating_sourceChannelid = "isVideoChating_sourceChannelid";// 视频聊天的标识
    public static final String isCallVideoChat = "isCallVideoChat";//是否是来电的标识

    public static final String CallFlag = "CallFlag";//区分来电或者去电的标识
    public static final String CallName = "CallName";//需要联系的人的标识


    public static final int FLAG_PLAY_VIDEO = 0;//视频
    public static final int FLAG_PLAY_AUDIO = 1;//音频
    public static final int FLAG_PLAY_IMAGE = 2;//图片
    public static final int FLAG_PLAY_ANIMATION = 3;//动画
    public static final int FLAG_PLAY_TEXT = 4;//文本
    public static final int FLAG_PLAY_STREAMINGMEDIA = 5;//流媒体
    public static final int FLAG_PLAY_OTHER = 6;//其他


    public static final String PLAY_ACTIVITY = "PlayActivity";//播放Activity
    public static final String WELCOME_ACTIVITY = "WelcomeActivity";//WelcomeActivity
    public static final String SESSION_LIST_ACTIVITY = "SessionListActivity";//会话列表Activity
    public static final String CHAT_ACTIVITY = "ChatActivity";//聊天Activity
    public static final String VIDEO_CHAT_P2P_ACTIVITY = "VideoChatP2PActivity";//视频聊天Activity
    public static final String WAKE_UP_ACTIVITY = "WakeUpActivity";//唤醒Activity
    public static final String CAMERA_ACTIVITY = "CameraActivity";//拍照界面


    public static final String FLAG_ENTER_FUNC_THREE_ACTIVITY = "flag_enter_func_three_activity";

    public static final String FLAG_ENTER_ROBOT_ACTIVITY_AWAKEN = "flag_awaken";//唤醒
    public static final String FLAG_ENTER_ROBOT_ACTIVITY_PAUSEPLAY = "flag_pause_play";//暂停
    public static final String FLAG_ENTER_ROBOT_ACTIVITY_RESUMEPLAY = "flag_resume_play";//继续
    public static final String FLAG_ENTER_ROBOT_ACTIVITY_SENDCONTENT = "flag_send_content";//推送内容
    public static final String FLAG_ENTER_ROBOT_ACTIVITY_TO_CONTENT = "flag_to_content";//推送内容

    public static final String FLAG_ENTER_ROBOT_ACTIVITY_MAIN_FUNC_CONTENT_PLAY = "flag_main_func_content_play";//主功能播放
    public static final String FLAG_ENTER_ROBOT_ACTIVITY_CONTENT_QUERY = "flag_enter_robot_activity_content_query";//内容查询
    public static final String FLAG_ENTER_ROBOT_ACTIVITY_NFC_QUERY = "flag_enter_robot_activity_nfc_query";//条码查询 内容
    public static final String FLAG_ENTER_ROBOT_ACTIVITY_PUSH = "flag_enter_robot_activity_push";//推送


    /*-------------------------------------------------*/
    //进入播放界面
    public static final String FLAG_ENTER_PLAY_ACTIVITY = "flag_enter_play_activity";
    public static final String FLAG_ENTER_PLAY_ACTIVITY_DATA = "flag_enter_play_activity_data";//传输数据

    public static final String FLAG_ENTER_PLAY_ACTIVITY_RANDOM_PLAY = "flag_enter_play_activity_random_play";//随机播放（点播：如 动画/国学诗词/英语学习/）
    public static final String FLAG_ENTER_PLAY_ACTIVITY_NFC_QUERY = "flag_enter_play_activity_nfc_query";//条码扫一扫查询
    public static final String FLAG_ENTER_PLAY_ACTIVITY_PUSH = "flag_enter_play_activity_push";//推送
    public static final String FLAG_ENTER_PLAY_ACTIVITY_PLAY_CONTENT = "flag_enter_play_activity_play_content";//播放内容
    public static final String FLAG_ENTER_PLAY_ACTIVITY_RESUME = "flag_enter_play_activity_resume";//继续播放
    public static final String FLAG_ENTER_PLAY_ACTIVITY_WAKE_UP_RESULT = "flag_enter_play_activity_wake_up_result";//通过唤醒请求到的数据

    /*-------------------------------------------------*/

    public static final String FLAG_TWO_FUNC_DATA = "flag_two_func_data";//传递二级功能数据
    public static final String FLAG_THREE_COMMON_FUNC_DATA = "flag_three_common_func_data";//传递三级公共功能数据

    public static final String FLAG_THREE_POETRY_RECITE = "flag_three_poetry_recite";//国学诗词
    public static final String FLAG_THREE_ENGLISH_STUDY = "flag_three_english_study";//英语学习
    public static final String FLAG_THREE_SAFETY_EDUCATION = "flag_three_safety_education";//安全教育
    public static final String FLAG_THREE_READ_PICTURE_BOOK = "flag_three_read_picture_book";//读绘本
    public static final String FLAG_THREE_NURSERY_RHYMES = "flag_three_nursery_rhymes";//儿歌
    public static final String FLAG_THREE_STORY = "flag_three_story";//故事
    public static final String FLAG_THREE_ANIM = "flag_three_anim";//动画
    public static final String FLAG_THREE_ENGLISH_STUDY_FOLLOW_ME = "flag_three_english_study_follow_me";//跟我学
    public static final String FLAG_THREE_ENGLISH_STUDY_TRANSLATION = "flag_three_english_study_translation";//中译音


    public static final String FLAG_CONTENT_TREE_CHILDREN = "flag_content_tree_children";
    public static final String FLAG_CONTENT_TREE_TITLE = "flag_content_tree_title";

    public static final String FLAG_WIFI_NAME = "flag_wifi_name";//WIFI名称

    public static final String FLAG_CUSTOMERS_INFO_ALL = "flag_customer_info_all";//所有绑定设备的用户
    public static final String FLAG_CUSTOMERS_INFO = "flag_customers_info";
    public static final String FLAG_CUSTOMERS_INFO_POSIOTION = "flag_customers_info_posiotion";

    public static final String FLAG_ALBUM_URL = "flag_album_url";//照片地址
    public static final String FLAG_ALBUM_POSITION = "flag_album_position";//照片下标
    public static final String FLAG_ALBUM_DATA = "flag_album_data";//照片数据


    public static final String VOLUME_VALUE = "音量";
    public static final String COMMAND_VALUE_PLUS = "+";// +
    public static final String COMMAND_VALUE_REDUCE = "-";// -
    public static final String COMMAND_VALUE_MAX = "max";// 最大
    public static final String COMMAND_VALUE_MIN = "min";// 最小

    public static final String VOLUME_MUSIC_COMMAND = "音乐";
    public static final String VOLUME_MUSIC_COMMAND_PREVIOUS = "上一首";
    public static final String VOLUME_MUSIC_COMMAND_NEXT = "下一首";
    public static final String VOLUME_MUSIC_COMMAND_ONRESUME = "继续";
    public static final String VOLUME_MUSIC_COMMAND_ONPAUSE = "暂停";
    /**
     * EventBus传递的标识
     **/
    //启动唤醒标识
    public final static String FLAG_START_ASR_NOT_USING_ONE_SHOT = "flag_start_asr_not_using_one_shot";
    //再次唤醒 （主界面通知robot界面）
    public final static String FLAG_WAKE_UP_AGAIN = "flag_wake_up_again";
    //关闭唤醒
    public final static String FLAG_CLOSE_AWAKEN = "flag_close_awaken";
    //开启唤醒
    public final static String FLAG_START_WAKE_UP = "flag_start_wake_up";
    //定时关闭结束后通知播放提示
    public final static String FLAG_COMP_TIMING_CLOSURE = "flag_comp_timing_closure";

    //重新打开唤醒
    public final static String FLAG_RE_OPEN_AWAKEN = "flag_re_open_awaken";

    //启动唤醒 传送数据的标识
    public final static String FLAG_START_WAKE_UP_RESULT = "flag_start_wake_up_result";
    //设备被唤醒了
    public final static String FLAG_START_WAKE_UP_NOT_ONE_SHOT = "flag_start_wake_up_not_one_shot";
    //唤醒时ASR出错
    public final static String FLAG_START_WAKE_UP_ASR_ERROR = "flag_start_wake_up_asr_error";
    //开启多伦对话
    public final static String FLAG_OPEN_DORON_DIALOGUE = "flag_open_doron_dialogue";

    //设备是否唤醒（ture-->喊小陶同学进入  否-->其他（按Home键进入））
    public final static String FLAG_START_WAKE_UP_IS_WAKE_UP = "flag_start_wake_up_is_wake_up";
    //是否点击了home键进入
    public final static String FLAG_START_WAKE_UP_IS_CLICK_HOME = "flag_start_wake_up_is_click_home";
    //是否是刚开机
    public final static String FLAG_START_WAKE_UP_IS_OPEN = "flag_start_wake_up_is_open";

    //监听用户说话完成
    public final static String FLAG_SPEAK_COMPLETE = "flag_speak_complete";

    //p2p视频聊天界面退出的标识（通知聊天界面）
    public final static String FLAG_VIDEOCHAT_ACTIVITY_FINISH = "flag_speak_complete";

    //去电--发送文本消息  已取消
    public final static String FLAG_SEND_TEXT_MSG_CANCEL = "flag_send_text_msg_cancel";
    //去电--发送文本消息  已拒绝
    public final static String FLAG_SEND_TEXT_MSG_REFUSED = "flag_send_text_msg_refused";
    //去电--发送文本消息  通话时长
    public final static String FLAG_SEND_TEXT_MSG_DURATION = "flag_send_text_msg_duration";
    //占线忙
    public final static String FLAG_SEND_TEXT_LINE_BUSY = "flag_send_text_line_busy";

    public final static String FLAG_SEND_TEXT_MSG_REFUSED_NOTICE_VIDEO_CHAT = "flag_send_text_msg_refused_notice_video_chat";

    public final static String FLAG_CHAT_LIST_REFRESH = "flag_chat_list_refresh";//聊天列表刷新
    public final static String FLAG_CHAT_LIST_REFRESH_MSG_NUM = "flag_chat_list_refresh_msg_num";//聊天数刷新

    public final static String FLAG_UN_READCOUNT = "flag_un_readcount";//未读消息标识

    public final static String FLAG_SCAN_BOOK_BACK = "flag_scan_book_back";//在扫一扫界面按返回键

    public final static String FLAG_BAN_VIDEO_PLAY = "flag_ban_video_play";

    public final static String FLAG_TIMING_CLOSURE = "flag_timing_closure";//定时关闭


    //系统设置的标识
    public final static String FLAG_SYSTEM_SETTING = "flag_system_setting";
    public final static String FLAG_SYSTEM_SETTING_VOICE = "flag_system_setting_voice";//音量
    public final static String FLAG_SYSTEM_SETTING_BRIGHT = "flag_system_setting_bright";//亮度

    public final static String GROUP = "group";


    public final static String FLAG_IS_FIRST_ENTER_WIFI_LIST = "flag_is_first_enter_wifi_list";//是否第一次进入WiFi列表的标识


}
