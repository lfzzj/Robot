package com.hamitao.kids.utils;

import android.widget.ImageView;

import com.hamitao.kids.R;
import com.hamitao.kids.constant.Constants;
import com.hamitao.framework.enums.ContentTree;
import com.hamitao.framework.enums.CustomerHeader;

/**
 * 资源工具类
 */
public class ResUtil {

    /**
     * 设置来电头像
     *
     * @param callName
     */
    public static void setCallheaderByName(String callName, ImageView imageView) {
        int resId;
        if (CustomerHeader.CUSTOMER_AUNT.toString().equals(callName)) {
            resId = R.drawable.icon_head_aunt;
        } else if (CustomerHeader.CUSTOMER_BROTHER.toString().equals(callName)) {
            resId = R.drawable.icon_head_brother;
        } else if (CustomerHeader.CUSTOMER_FATHER.toString().equals(callName)) {
            resId = R.drawable.icon_head_father;
        } else if (CustomerHeader.CUSTOMER_GRANDMA.toString().equals(callName)) {
            resId = R.drawable.icon_head_grandma;
        } else if (CustomerHeader.CUSTOMER_GRANDMOTHER.toString().equals(callName)) {
            resId = R.drawable.icon_head_grandmother;
        } else if (CustomerHeader.CUSTOMER_GRANDPA2.toString().equals(callName)) {
            resId = R.drawable.icon_head_grandfather;
        } else if (CustomerHeader.CUSTOMER_MOTHER.toString().equals(callName)) {
            resId = R.drawable.icon_head_mother;
        } else if (CustomerHeader.CUSTOMER_SISTER.toString().equals(callName)) {
            resId = R.drawable.icon_head_sister;
        } else if (CustomerHeader.CUSTOMER_UNCLE.toString().equals(callName)) {
            resId = R.drawable.icon_head_uncle;
        } else {
            resId = R.drawable.icon_head_other;
        }
        imageView.setBackgroundResource(resId);
    }
    /**
     * 设置WiFi信号--哈蜜淘
     *
     * @param level
     * @param imageView
     */
    public static void setWifiLevel(int level, ImageView imageView) {
        switch (level) {
            case 1:
                imageView.setBackgroundResource(R.drawable.img_wifi_3);
                break;
            case 2:
                imageView.setBackgroundResource(R.drawable.img_wifi_2);
                break;
            case 3:
                imageView.setBackgroundResource(R.drawable.img_wifi_1);
                break;
            case 4:
                imageView.setBackgroundResource(R.drawable.img_wifi_0);
                break;

        }
    }

    /**
     * 设置信号
     * @param gsmIntensity
     * @param stateSignal
     */
    public static void setGsmIntensity(int gsmIntensity, ImageView stateSignal) {
        switch (gsmIntensity) {
            case 1:
                stateSignal.setBackgroundResource(R.drawable.img_gsmintensity_4);
                break;
            case 2:
                stateSignal.setBackgroundResource(R.drawable.img_gsmintensity_3);
                break;
            case 3:
                stateSignal.setBackgroundResource(R.drawable.img_gsmintensity_2);
                break;
            case 4:
                stateSignal.setBackgroundResource(R.drawable.img_gsmintensity_1);
                break;
            case 5:
                stateSignal.setBackgroundResource(R.drawable.img_gsmintensity_0);
                break;
            default :
                stateSignal.setBackgroundResource(R.drawable.img_gsmintensity_0);
                break;

        }
    }

    /***
     * 查询分类内容
     * @param funcFlag
     * @return
     */
    public static String queryCategoryContent(String funcFlag) {
        String keyWork = ContentTree.CONTENT_COUNTRY_STUDY.toString();//默认是国学诗词
        if (Constants.FLAG_THREE_POETRY_RECITE.equals(funcFlag)) {//国学诗词
            keyWork = ContentTree.CONTENT_COUNTRY_STUDY.toString();
        } else if (Constants.FLAG_THREE_ENGLISH_STUDY.equals(funcFlag)) {//英语学习
            keyWork = ContentTree.CONTENT_ENGLISH_STUDY.toString();
        } else if (Constants.FLAG_THREE_SAFETY_EDUCATION.equals(funcFlag)) {//安全教育
            keyWork = ContentTree.CONTENT_SAFETY.toString();
        } else if (Constants.FLAG_THREE_READ_PICTURE_BOOK.equals(funcFlag)) {//读绘本
            keyWork = ContentTree.CONTENT_PICTURE_BOOK.toString();
        } else if (Constants.FLAG_THREE_NURSERY_RHYMES.equals(funcFlag)) {//儿歌
            keyWork = ContentTree.CONTENT_CHILDREN_SONG.toString();
        } else if (Constants.FLAG_THREE_STORY.equals(funcFlag)) {//故事
            keyWork = ContentTree.CONTENT_STORY.toString();
        } else if (Constants.FLAG_THREE_ANIM.equals(funcFlag)) {//动画
            keyWork = ContentTree.CONTENT_ANIMATION.toString();
        }
        return keyWork;
    }
}
