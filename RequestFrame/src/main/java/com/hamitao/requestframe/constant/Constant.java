package com.hamitao.requestframe.constant;

/**
 * @data on 2018/3/21 10:08
 * @describe:
 */

public class Constant {
    //    public static final String BASE_URL = "http://192.168.1.200:8080/";
    public static final String BASE_URL = "http://cloud.kidknow.net:8080/";//哈蜜淘服务器

    public static final String BASE_URL_LONGYU = "https://worldhttps.hubaoxing.cn/";//龙域服务器


    //上报设备信息
    public static final String REPORT_INFO = "proxy/deviceHome/reportinfo";

    //注册设备
    public static final String CREATE_DEVICE = "proxy/socialgraph/createdevice";

    //查询内容
    public static final String QUERY_CONTENT = "proxy/contentIndex/querycontent";

    //语义解析
    public static final String PARSE_CHINESE = "proxy/NLP/parsechinese";

    //获取OSS Key
    public static final String QUERY_KEY_ON_OSS = "proxy/security/querykeyonoss";

    //检测升级
    public static final String CHECK_UPDATA = "proxy/sysMaintain/uptodateversion";

    //推送消息
    public static final String PUSH_MSG = "proxy/channelMsgPush/pushmsg";

    //关系查询
    public static final String QUERY_RELATION = "proxy/socialgraph/queryrelation";

    //根据key生成httpUrl
    public static final String GEN_HTTPURL_FROM_OSSKEY = "proxy/security/genHttpURLFromOSSKey";

    //查询家长控制信息
    public static final String QUERY_SETTING = "proxy/deviceHome/querySetting";

    //添加闹钟
    public static final String ADD_TIMER_ALARM = "proxy/deviceHome/addTimerAlarm";

    //删除闹钟
    public static final String DELETE_TIMER_ALARM = "proxy/deviceHome/deleteTimerAlarm";

    //修改闹钟
    public static final String UPDATE_TIMER_ALARM = "proxy/deviceHome/updateTimerAlarm";

    //更新闹钟状态
    public static final String UPDATE_TIMER_ALARM_STATUS = "proxy/deviceHome/updateTimerAlarmStatus";

    //查询闹钟
    public static final String QUERY_TIMER_ALARM = "proxy/deviceHome/queryTimerAlarm";


    //根据mediaid查询内容
    public static final String QUERY_CONTENT_BY_MEDIAID = "proxy/contentIndex/querycontentByMediaid";

    //根据内容id查询内容
    public static final String QUERY_CONTENT_BY_CONTENTID = "proxy/contentIndex/querycontentByContentid";

    //根据录音ID查询录音信息
    public static final String QUERY_VOICE_RECORDING_BY_ID = "proxy/voiceRecording/queryVoiceRecordingByID";

    //查询联系人
    public static final String QUERY_CONTACT = "proxy/contactBook/querycontact";

    // 增加照片
    public static final String ADD_PHOTO = "proxy/deviceHome/addPhoto";

    //删除照片
    public static final String DELETE_PHOTO = "proxy/deviceHome/deletePhoto";

    //查询照片
    public static final String QUERY_PHOTO = "proxy/deviceHome/queryPhoto";

    // 查询发布的课程表
    public static final String QUERY_PUBLISHED_COURSE_SCHEDULE = "proxy/courseSchedule/queryPublishedCourseSchedule";

    //获取内容树(层级结构)
    public static final String GET_CONTENT_TREE = "proxy/contentIndex/getContentTree";

    //批量查询课程表内容（录音+内容）
    public static final String BATCH_QUERY_COURSE_SCHEDULE_DETAIL = "proxy/compositeagent/batchQueryCourseScheduleDetail";

    //根据条码查询卡的内容
    public static final String QUERY_NFC_BY_ID = "proxy/NFC/querynfcbyid";

    //视频聊天获取guid
    public static final String GET_P2P_BY_GUID = "user/getP2P_by_Guid.gz";

    //释放视频聊天资源
    public static final String RELEASE_P2P_BY_GUID = "user/releaseP2P_by_Guid.gz";


}
