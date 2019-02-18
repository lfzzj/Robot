package com.hamitao.kids.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.hamitao.framework.R;
import com.hamitao.framework.enums.FuncTitle;

public class PlayHint {
    private static MediaPlayer player;

    private Context mContext;

    public PlayHint(Context context) {
        this.mContext = context;
    }

    /**
     * 播放提示声音-->播放完成之后回调
     *
     * @param rawID
     */
    private void playVoice(int rawID) {
        try {
            if (player != null) {
                player.release();
            }
            player = MediaPlayer.create(mContext, rawID);
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止声音
     */
    public static void stopVoice() {
        try {
            if (player != null) {
                player.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playFuncTitle(String title) {
//        TTSEngine.stop();
//        TuringEngine.getSingleton().stop();

        if (FuncTitle.FUNC_STUDY.toString().equals(title)) {//学习
            playVoice(R.raw.voice_func_study);
        } else if (FuncTitle.CONTENT_WISDOM_READ.toString().equals(title)) {//智慧阅读
            playVoice(R.raw.voice_func_wisdom_read);
        } else if (FuncTitle.CONTENT_PLAY.toString().equals(title)) {//娱乐
            playVoice(R.raw.voice_func_play);
        } else if (FuncTitle.CONTENT_CHAT.toString().equals(title)) {//聊天
            playVoice(R.raw.voice_func_chat);
        } else if (FuncTitle.CONTENT_PHONE.toString().equals(title)) {//打电话
            playVoice(R.raw.voice_func_phone);
        } else if (FuncTitle.CONTENT_ALBUM.toString().equals(title)) {//相册
            playVoice(R.raw.voice_func_album);
        } else if (FuncTitle.CONTENT_SETTING.toString().equals(title)) {//设置
            playVoice(R.raw.voice_func_setting);
        } else if (FuncTitle.CONTENT_CHINESE_POETRY.toString().equals(title)) {//国学诗词
            playVoice(R.raw.voice_func_chinese_poetry);
        } else if (FuncTitle.CONTENT_ENGLIST_STUDY.toString().equals(title)) {//英语学习
            playVoice(R.raw.voice_func_english_study);
        } else if (FuncTitle.CONTENT_SAFETY_EDUCATION.toString().equals(title)) {//安全教育
            playVoice(R.raw.voice_func_safety_enucation);
        } else if (FuncTitle.CONTENT_READ_BOOK.toString().equals(title)) {//读绘本
            playVoice(R.raw.voice_func_read_book);
        } else if (FuncTitle.CONTENT_SCAN_BOOK.toString().equals(title)) {//扫绘本
            playVoice(R.raw.voice_func_scan_book);
        } else if (FuncTitle.CONTENT_CHILDREN_SONG.toString().equals(title)) {//儿歌
            playVoice(R.raw.voice_func_children_song);
        } else if (FuncTitle.CONTENT_STORY.toString().equals(title)) {//故事
            playVoice(R.raw.voice_func_story);
        } else if (FuncTitle.CONTENT_ANIM.toString().equals(title)) {//动画
            playVoice(R.raw.voice_func_anim);
        } else if (FuncTitle.CONTENT_FOLLOW_ME.toString().equals(title)) {//跟我学
            playVoice(R.raw.voice_func_follow_me);
        } else if (FuncTitle.CONTENT_TRANSLATION.toString().equals(title)) {//中译英
            playVoice(R.raw.voice_func_translation);
        } else if (FuncTitle.CONTENT_BOOK.toString().equals(title)) {//绘本
            playVoice(R.raw.voice_func_book);
        }
        else if (FuncTitle.CONTENT_NO_CONTENT.toString().equals(title)) {//已经没有内容了，无法继续播放哦
            playVoice(R.raw.voice_hint_no_content);
        }
        else if (FuncTitle.CONTENT_POKE_FACE.toString().equals(title)) {//别戳我的小脸蛋呀
            playVoice(R.raw.voice_hint_poke_face);
        } else if (FuncTitle.CONTENT_POKE_EYES.toString().equals(title)) {//不要戳我的眼睛，我快晕了
            playVoice(R.raw.voice_hint_eyes);
        } else if (FuncTitle.CONTENT_RECEIVE_NEW_INFO.toString().equals(title)) {//收到新消息哟
            playVoice(R.raw.voice_hint_receive_new_info);
        } else if (FuncTitle.CONTENT_TOO_LONG_REST.toString().equals(title)) {//看太久了，请休息一下哦
            playVoice(R.raw.voice_hint_too_long_rest);
        }
        else if (FuncTitle.CONTENT_VOICE_HINT_INSTALL_SIM.toString().equals(title)) {//请先安装sim卡再使用哦～
            playVoice(R.raw.voice_hint_install_sim);
        } else if (FuncTitle.CONTENT_VOICE_HINT_NO_SEACH_CONTENT.toString().equals(title)) {//哎呀~网络信号差，我搜索不到内容，请求加强信号…
            playVoice(R.raw.voice_hint_no_seach_content);
        } else if (FuncTitle.CONTENT_VOICE_HINT_NET_CONNTCT.toString().equals(title)) {//网络连接成功，快来看看我的本领吧～
            playVoice(R.raw.voice_hint_net_connect);
        } else if (FuncTitle.CONTENT_VOICE_HINT_NET_CONNTCT_FAIL.toString().equals(title)) {//呜呜，网络连接失败了
            playVoice(R.raw.voice_hint_net_connect_fail);
        } else if (FuncTitle.CONTENT_VOICE_HINT_NET_DISCONNTCT.toString().equals(title)) {//我已经失去网络连接，无法发挥自己的本领，请先连接网络吧
            playVoice(R.raw.voice_hint_net_disconnect);
        } else if (FuncTitle.CONTENT_VOICE_HINT_NO_NET_UPDATA_CONTACT.toString().equals(title)) {//我已经失去网络连接，无法更新联系人号码，请先连接网络
            playVoice(R.raw.voice_hint_no_net_updata_contact);
        }else if (FuncTitle.CONTENT_VOICE_HINT_TURN_OFF.toString().equals(title)){//小朋友，我要休息啦，下次再见咯
            playVoice(R.raw.voice_hint_turn_off);
        }else if (FuncTitle.CONTENT_VOICE_SEND_MSG.toString().equals(title)){
            playVoice(R.raw.voice_send_msg);
        }else if (FuncTitle.CONTENT_VOICE_HINT_LOW_POWER.toString().equals(title)){
            playVoice(R.raw.voice_hint_low_power_reminder);
        }else if (FuncTitle.CONTENT_VOICE_HINT_FULL_POWER.toString().equals(title)){
            playVoice(R.raw.voice_hint_full_power_reminder);
        }else if (FuncTitle.CONTENT_VOICE_HINT_CHARGING.toString().equals(title)){
            playVoice(R.raw.voice_hint_charging);
        }
    }
}
