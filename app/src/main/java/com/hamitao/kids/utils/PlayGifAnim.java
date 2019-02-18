package com.hamitao.kids.utils;

import android.animation.Animator;
import android.text.TextUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class PlayGifAnim {
    private static String images = "/images/";
    private static String data_json = "/data.json";
    /**
     * --------------------------------哈蜜淘--------------------------------
     **/
    //播放动画的标识
    public static String FLAG_PLAY_GIF_WELCOME = "flag_play_gif_welcome";//欢迎界面
    public static String FLAG_PLAY_GIF_LOADING = "flag_play_gif_loading";//加载框
    public static String FLAG_PLAY_GIF_ALARM = "flag_play_gif_alarm";//闹钟


    public static String FLAG_PLAY_GIF_EMOJI_NORMAL = "flag_play_gif_emoji_normal";//表情——正常表情
    public static String FLAG_PLAY_GIF_EMOJI_POKE_EYES = "flag_play_gif_emoji_poke_eyes";//表情——戳眼睛
    public static String FLAG_PLAY_GIF_EMOJI_POKE_FACE = "flag_play_gif_emoji_poke_face";//表情——戳脸蛋
    public static String FLAG_PLAY_GIF_EMOJI_DEVICE_LISTEN = "flag_play_gif_emoji_user_talk";//表情——用户说话
    public static String FLAG_PLAY_GIF_EMOJI_DEVICE_TALK = "flag_play_gif_emoji_device_talk";//表情——设备说话

    public static String FLAG_PLAY_GIF_ANIM_CHINESE_POETRY = "flag_play_gif_anim_chinese_poetry";//动画——国学诗词
    public static String FLAG_PLAY_GIF_ANIM_ENGLISH_STUDY = "flag_play_gif_anim_english_study";//动画——英语学习
    public static String FLAG_PLAY_GIF_ANIM_SAFETY_EDUCATION = "flag_play_gif_anim_safety_education";//动画——安全教育
    public static String FLAG_PLAY_GIF_ANIM_READ_BOOK = "flag_play_gif_anim_read_book";//动画——读绘本
    public static String FLAG_PLAY_GIF_ANIM_CHILDREN_SONG = "flag_play_gif_anim_children_song";//动画——儿歌
    public static String FLAG_PLAY_GIF_ANIM_STORY = "flag_play_gif_anim_children_story";//动画——故事

    /**
     * --------------------------------金国威--------------------------------
     **/
    //播放动画的标识
    public static String FLAG_PLAY_GIF_WELCOME_JGW = "flag_play_gif_welcome_jgw";//欢迎界面
    public static String FLAG_PLAY_GIF_ALARM_JGW = "flag_play_gif_alarm_jgw";//闹钟

    public static String FLAG_PLAY_GIF_EMOJI_NORMAL_JGW = "flag_play_gif_emoji_normal";//表情——正常表情
    public static String FLAG_PLAY_GIF_EMOJI_POKE_EYES_JGW = "flag_play_gif_emoji_poke_eyes";//表情——戳眼睛
    public static String FLAG_PLAY_GIF_EMOJI_POKE_FACE_JGW = "flag_play_gif_emoji_poke_face";//表情——戳脸蛋
    public static String FLAG_PLAY_GIF_EMOJI_LISTEN_TALK_JGW = "flag_play_gif_emoji_listen_talk";//表情——监听说话

    public static String FLAG_PLAY_GIF_ANIM_CHINESE_POETRY_JGW = "flag_play_gif_anim_chinese_poetry_jgw";//动画——国学诗词
    public static String FLAG_PLAY_GIF_ANIM_SAFETY_EDUCATION_JGW = "flag_play_gif_anim_safety_education_jgw";//动画——安全教育
    public static String FLAG_PLAY_GIF_ANIM_READ_BOOK_JGW = "flag_play_gif_anim_read_book_jgw";//动画——读绘本
    public static String FLAG_PLAY_GIF_ANIM_CHILDREN_SONG_JGW = "flag_play_gif_anim_children_song_jgw";//动画——儿歌
    public static String FLAG_PLAY_GIF_ANIM_STORY_JGW = "flag_play_gif_anim_story_jgw";//动画——故事
    public static String FLAG_PLAY_GIF_ANIM_ANIM_JGW = "flag_play_gif_anim_anim_jgw";//动画——动画

    private static String jsonName;
    private static String folder;

    private static void setFlag(String flag) {
        String folderStr = null;
        if (TextUtils.isEmpty(flag)) {
            return;
        }
        //哈蜜淘
        if (flag.equals(FLAG_PLAY_GIF_WELCOME)) {//欢迎动画
            folderStr = "welcome";
        } else if (flag.equals(FLAG_PLAY_GIF_LOADING)) {//加载动画
            folderStr = "loading";
        } else if (flag.equals(FLAG_PLAY_GIF_ANIM_CHINESE_POETRY)) {//国学诗词
            folderStr = "anim_chinese_poetry";
        } else if (flag.equals(FLAG_PLAY_GIF_ANIM_ENGLISH_STUDY)) {//英语学习
            folderStr = "anim_english_study";
        } else if (flag.equals(FLAG_PLAY_GIF_ANIM_SAFETY_EDUCATION)) {//安全教育
            folderStr = "anim_safety_education";
        } else if (flag.equals(FLAG_PLAY_GIF_ANIM_READ_BOOK)) {//读绘本
            folderStr = "anim_read_book";
        } else if (flag.equals(FLAG_PLAY_GIF_ANIM_CHILDREN_SONG)) {//儿歌
            folderStr = "anim_children_song";
        } else if (flag.equals(FLAG_PLAY_GIF_ANIM_STORY)) {//故事
            folderStr = "anim_story";
        } else if (flag.equals(FLAG_PLAY_GIF_EMOJI_NORMAL)) {//表情——正常表情
            folderStr = "emoji_normal";
        } else if (flag.equals(FLAG_PLAY_GIF_EMOJI_POKE_EYES)) {//表情——戳眼睛
            folderStr = "emoji_poke_eyes";
        } else if (flag.equals(FLAG_PLAY_GIF_EMOJI_POKE_FACE)) {//表情——戳脸蛋
            folderStr = "emoji_poke_face";
        } else if (flag.equals(FLAG_PLAY_GIF_EMOJI_DEVICE_LISTEN)) {//表情——监听说话(设备听)
            folderStr = "emoji_device_listen";
        } else if (flag.equals(FLAG_PLAY_GIF_EMOJI_DEVICE_TALK)) {//表情——设备说话
            folderStr = "emoji_device_talk";
        }else if (FLAG_PLAY_GIF_ALARM.equals(flag)){//闹钟
            folderStr = "alarm";
        }

        //金国威
        else if (flag.equals(FLAG_PLAY_GIF_WELCOME_JGW)) {
            folderStr = "anim_chinese_poetry_jgw";
        } else if (flag.equals(FLAG_PLAY_GIF_ANIM_CHINESE_POETRY_JGW)) {//国学诗词
            folderStr = "anim_chinese_poetry_jgw";
        } else if (flag.equals(FLAG_PLAY_GIF_ANIM_CHILDREN_SONG_JGW)) {//儿歌
            folderStr = "anim_chinese_poetry_jgw";
        } else if (flag.equals(FLAG_PLAY_GIF_ANIM_SAFETY_EDUCATION_JGW)) {//安全教育
            folderStr = "anim_safety_education_jgw";
        } else if (flag.equals(FLAG_PLAY_GIF_ANIM_READ_BOOK_JGW)) {//读绘本
            folderStr = "anim_read_book_jgw";
        } else if (flag.equals(FLAG_PLAY_GIF_ANIM_STORY_JGW)) {//故事
            folderStr = "anim_story_jgw";
        } else if (flag.equals(FLAG_PLAY_GIF_ANIM_ANIM_JGW)) {//动画
            folderStr = "anim_anim_jgw";
        }else if (FLAG_PLAY_GIF_ALARM_JGW.equals(flag)){//闹钟
            folderStr = "alarm_jgw";
        }
        //
        else {
            folderStr = "anim_chinese_poetry";
        }

        jsonName = folderStr + data_json;
        folder = folderStr + images;

    }

    /**
     * 开始播放动画
     *
     * @param animationView
     * @param flag
     */
    public static void play(LottieAnimationView animationView, String flag) {
        play(animationView, flag, null);
    }

    public static void play(LottieAnimationView animationView, String flag, GifAnimListener listener) {
        setFlag(flag);
        if (TextUtils.isEmpty(jsonName) || TextUtils.isEmpty(folder)) {
            return;
        }
        animationView.setAnimation(jsonName);//在assets目录下的动画json文件名。
        animationView.setImageAssetsFolder(folder);//assets目录下的子目录，存放动画所需的图片
        if (listener != null) {
            animationView.loop(false);//设置动画循环播放
            animationView.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    listener.onComplete();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
        } else {
            animationView.loop(true);//设置动画循环播放
        }
        animationView.playAnimation();//播放动画
    }

    /**
     * 暂停播放动画
     */
    public static void pause(LottieAnimationView mAnimationView) {
        if (mAnimationView != null) {
            mAnimationView.pauseAnimation();
        }
    }

    /**
     * 继续播放动画
     */
    public static void resume(LottieAnimationView mAnimationView) {
        if (mAnimationView != null) {
            mAnimationView.resumeAnimation();
        }
    }

    /**
     * 销毁动画
     *
     * @param mAnimationView
     */
    public static void cancel(LottieAnimationView mAnimationView) {
        if (mAnimationView != null) {
            mAnimationView.cancelAnimation();
        }
    }


    public interface GifAnimListener {
        /**
         * 动画播放完成
         */
        void onComplete();
    }

}
