package com.hamitao.framework.enums;

public enum FuncTitle {
    FUNC_STUDY("学习"),
    CONTENT_WISDOM_READ("智慧阅读"),
    CONTENT_PLAY("娱乐"),
    CONTENT_CHAT("聊天"),
    CONTENT_PHONE("打电话"),
    CONTENT_ALBUM("相册"),
    CONTENT_SETTING("设置"),

    CONTENT_CHINESE_POETRY("国学诗词"),
    CONTENT_ENGLIST_STUDY("英语学习"),
    CONTENT_SAFETY_EDUCATION("安全教育"),
    CONTENT_READ_BOOK("读绘本"),
    CONTENT_SCAN_BOOK("扫绘本"),
    CONTENT_CHILDREN_SONG("儿歌"),
    CONTENT_STORY("故事"),
    CONTENT_ANIM("动画"),

    CONTENT_FOLLOW_ME("跟我学"),
    CONTENT_TRANSLATION("中译英"),
    CONTENT_BOOK("绘本"),

    CONTENT_IGNORE_ME_REST("Ignore_me_rest"),//不理我，我要去休息了
    CONTENT_NO_CONTENT("no_content"),//已经没有内容了，无法继续播放哦
    CONTENT_NO_MOVIE_FUNC("no_movie_func"),//小淘现在还没有播放电影的功能哦
    CONTENT_NOT_YET("not_yet"),//这个问题小淘还不会哦，小淘还在学习中
    CONTENT_POKE_FACE("poke_face"),//别戳我的小脸蛋呀
    CONTENT_RECEIVE_NEW_INFO("receive_new_info"),//收到新消息哟
    CONTENT_TOO_LONG_REST("too_long_rest"),//看太久了，请休息一下哦
    CONTENT_POKE_EYES("eyes"),//不要戳我的眼睛，我快晕了
    CONTENT_UNDERSTAND_QUESTION("understand_question"),//小淘没有听懂你的问题，请再说一次
    CONTENT_NO_CONTACT("no_contact"),//暂时没获取到对应的联系人哦，请添加后再试
    CONTENT_VOICE_HINT_NO_DATA("voice_hint_no_data"),//暂时没获取到你想要的数据哦,请刷新试试

    CONTENT_VOICE_HINT_LOW_POWER("voice_hint_low_power"),// 能量不足，请求补给能量…
    CONTENT_VOICE_HINT_FULL_POWER("voice_hint_full_power"),// 能量补给完成，元气满满哦！
    CONTENT_VOICE_HINT_CHARGING("content_voice_hint_charging"),// 补充能量中

    CONTENT_VOICE_HINT_INSTALL_SIM("hint_install_sim"),// 请先安装sim卡再使用哦～
    CONTENT_VOICE_HINT_NO_SEACH_CONTENT("hint_no_seach_content"),// 哎呀~网络信号差，小乐搜索不到内容，请求加强信号…
    CONTENT_VOICE_HINT_NET_CONNTCT("hint_net_connect"),// 网络连接成功，快来看看我的本领吧～
    CONTENT_VOICE_HINT_NET_CONNTCT_FAIL("hint_net_connect_fail"),// 呜呜，网络连接失败了
    CONTENT_VOICE_HINT_NET_DISCONNTCT("hint_net_disconnect"),// 我已经失去网络连接，无法发挥自己的本领，请先连接网络吧
    CONTENT_VOICE_HINT_NO_NET_UPDATA_CONTACT("hint_no_net_updata_contact"),// 小乐已经失去网络连接，无法更新联系人号码，请先连接网络
    CONTENT_VOICE_SEND_MSG("content_voice_send_msg"),// 我已经失去网络连接，无法更新联系人号码，请先连接网络
    CONTENT_VOICE_HINT_TURN_OFF("hint_turn_off");// 关机


    private final String text;

    FuncTitle(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
