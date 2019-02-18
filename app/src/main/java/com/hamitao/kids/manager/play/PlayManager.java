package com.hamitao.kids.manager.play;

import com.hamitao.framework.utils.NumUtil;
import com.hamitao.framework.enums.ContentTree;
import com.hamitao.kids.utils.PlayGifAnim;

public class PlayManager {
    private static String[] animCollect = {
            PlayGifAnim.FLAG_PLAY_GIF_ANIM_CHINESE_POETRY,
            PlayGifAnim.FLAG_PLAY_GIF_ANIM_ENGLISH_STUDY,
            PlayGifAnim.FLAG_PLAY_GIF_ANIM_SAFETY_EDUCATION,
            PlayGifAnim.FLAG_PLAY_GIF_ANIM_READ_BOOK,
            PlayGifAnim.FLAG_PLAY_GIF_ANIM_CHILDREN_SONG,
            PlayGifAnim.FLAG_PLAY_GIF_ANIM_STORY};
    /**
     * 设置动画类型
     *
     * @param keyWork
     */
    public static String getGifAnimType(String keyWork) {
        String animRes;

        if (ContentTree.CONTENT_COUNTRY_STUDY.toString().equals(keyWork)) {//国学诗词
            animRes = PlayGifAnim.FLAG_PLAY_GIF_ANIM_CHINESE_POETRY;
        } else if (ContentTree.CONTENT_ENGLISH_STUDY.toString().equals(keyWork)) {//英语学习
            animRes = PlayGifAnim.FLAG_PLAY_GIF_ANIM_ENGLISH_STUDY;
        } else if (ContentTree.CONTENT_SAFETY.toString().equals(keyWork)) {//安全教育
            animRes = PlayGifAnim.FLAG_PLAY_GIF_ANIM_SAFETY_EDUCATION;
        } else if (ContentTree.CONTENT_PICTURE_BOOK.toString().equals(keyWork)) {//读绘本
            animRes = PlayGifAnim.FLAG_PLAY_GIF_ANIM_READ_BOOK;
        } else if (ContentTree.CONTENT_CHILDREN_SONG.toString().equals(keyWork)) {//儿歌
            animRes = PlayGifAnim.FLAG_PLAY_GIF_ANIM_CHILDREN_SONG;
        } else if (ContentTree.CONTENT_STORY.toString().equals(keyWork)) {//故事
            animRes = PlayGifAnim.FLAG_PLAY_GIF_ANIM_STORY;
        } else {
            animRes = animCollect[NumUtil.getRandomNum(animCollect.length)];
        }
        return animRes;
    }

    private static String[] animCollectJgw = {
            PlayGifAnim.FLAG_PLAY_GIF_ANIM_CHINESE_POETRY_JGW,
            PlayGifAnim.FLAG_PLAY_GIF_ANIM_SAFETY_EDUCATION_JGW,
            PlayGifAnim.FLAG_PLAY_GIF_ANIM_READ_BOOK_JGW,
            PlayGifAnim.FLAG_PLAY_GIF_ANIM_CHILDREN_SONG_JGW,
            PlayGifAnim.FLAG_PLAY_GIF_ANIM_STORY_JGW};
    /**
     * 设置动画类型
     *
     * @param keyWork
     */
    public static String getGifAnimTypeJgw(String keyWork) {
        String animRes;

        if (ContentTree.CONTENT_COUNTRY_STUDY.toString().equals(keyWork)) {//国学诗词
            animRes = PlayGifAnim.FLAG_PLAY_GIF_ANIM_CHINESE_POETRY_JGW;
        }  else if (ContentTree.CONTENT_SAFETY.toString().equals(keyWork)) {//安全教育
            animRes = PlayGifAnim.FLAG_PLAY_GIF_ANIM_SAFETY_EDUCATION_JGW;
        } else if (ContentTree.CONTENT_PICTURE_BOOK.toString().equals(keyWork)) {//读绘本
            animRes = PlayGifAnim.FLAG_PLAY_GIF_ANIM_READ_BOOK_JGW;
        } else if (ContentTree.CONTENT_CHILDREN_SONG.toString().equals(keyWork)) {//儿歌
            animRes = PlayGifAnim.FLAG_PLAY_GIF_ANIM_CHILDREN_SONG_JGW;
        } else if (ContentTree.CONTENT_STORY.toString().equals(keyWork)) {//故事
            animRes = PlayGifAnim.FLAG_PLAY_GIF_ANIM_STORY_JGW;
        } else {
            animRes = animCollectJgw[NumUtil.getRandomNum(animCollectJgw.length)];
        }
        return animRes;
    }


}
