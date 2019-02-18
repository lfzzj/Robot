package com.hamitao.aispeech.util;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.NumUtil;

/**
 * @data on 2018/3/19 9:09
 * @describe: 指令
 */

public class InstructUtils {


    /**
     * 机器人唤醒回应
     */
    public static String respondAwakenRobotRespond() {
        return BaseConstant.respondRobotAwaken[NumUtil.getRandomNum(BaseConstant.respondRobotAwaken.length)];
    }

    public static int respondAwakenRobotRespondMedia(){
        return BaseConstant.respondRobotAwakenMedia[NumUtil.getRandomNum(BaseConstant.respondRobotAwakenMedia.length)];
    }

    /**
     * 没有识别到任何内容回应
     *
     * @return
     */
    public static String respondNoAsrContent() {
        return BaseConstant.respondNoAsrContent[NumUtil.getRandomNum(BaseConstant.respondNoAsrContent.length)];
    }

    /**
     * 没有检测到语音
     *
     * @return
     */
    public static String respondNoVoiceDetected() {
        return BaseConstant.respondNoVoiceDetected[NumUtil.getRandomNum(BaseConstant.respondNoVoiceDetected.length)];
    }

    /**
     * 提示退出唤醒界面
     *
     * @return
     */
    public static String respondExitWakeUp() {
        return BaseConstant.respondExitWakeUp[NumUtil.getRandomNum(BaseConstant.respondExitWakeUp.length)];
    }

    /**
     * 接听电源提示
     *
     * @return
     */
    public static String respondPowerConnected() {
        return BaseConstant.respondPowerConnected;
    }

    /**
     * 提示定时关闭的时间到了
     *
     * @return
     */
    public static String respondTimingClosure() {
        return BaseConstant.respondTimingClosure;
    }

    /**
     * 关机提示
     *
     * @return
     */
    public static String respondTurnOff() {
        return BaseConstant.respondTurnOff;
    }

    /**
     * 没有添加联系人
     *
     * @return
     */
    public static String respondNoAddContact() {
        return BaseConstant.respondNoAddContact;
    }

    /**
     * 没有添加这个联系人
     *
     * @return
     */
    public static String respondNoAddSingleContact() {
        return BaseConstant.respondNoAddSingleContact;
    }

    public static String respondExit() {
        return BaseConstant.respondExit;
    }

    /**
     * 检测是否是硬指令
     *
     * @param instruct
     * @return
     */
    public static String checkHardInstruction(String instruct) {
        for (int i = 0; i < BaseConstant.shutdown.length; i++) {//关机
            if (instruct.equals(BaseConstant.shutdown[i])) {
                return BaseConstant.INSTRUCTION_SHUTDOWN;
            }
        }
        for (int i = 0; i < BaseConstant.pause.length; i++) {//暂停播放
            if (instruct.equals(BaseConstant.pause[i])) {
                return BaseConstant.INSTRUCTION_PAUSE;
            }
        }
        for (int i = 0; i < BaseConstant.resume.length; i++) {//继续播放
            if (instruct.equals(BaseConstant.resume[i])) {
                return BaseConstant.INSTRUCTION_RESUME;
            }
        }
        for (int i = 0; i < BaseConstant.openChinesePoetry.length; i++) {//打开国学诗词
            if (instruct.equals(BaseConstant.openChinesePoetry[i])) {
                return BaseConstant.INSTRUCTION_OPEN_CHINESE_POETRY;
            }
        }
        for (int i = 0; i < BaseConstant.openEnglishStudy.length; i++) {//打开英语学习
            if (instruct.equals(BaseConstant.openEnglishStudy[i])) {
                return BaseConstant.INSTRUCTION_OPEN_ENGLISH_STUDY;
            }
        }
        for (int i = 0; i < BaseConstant.openSafetyEducation.length; i++) {//打开安全教育
            if (instruct.equals(BaseConstant.openSafetyEducation[i])) {
                return BaseConstant.INSTRUCTION_OPEN_SAFETY_EDUCATION;
            }
        }
        for (int i = 0; i < BaseConstant.openReadBook.length; i++) {//打开读绘本
            if (instruct.equals(BaseConstant.openReadBook[i])) {
                return BaseConstant.INSTRUCTION_OPEN_READ_BOOK;
            }
        }
        for (int i = 0; i < BaseConstant.openChildrenSong.length; i++) {//打开儿歌
            if (instruct.equals(BaseConstant.openChildrenSong[i])) {
                return BaseConstant.INSTRUCTION_OPEN_CHILDREN_SONG;
            }
        }
        for (int i = 0; i < BaseConstant.openStory.length; i++) {//打开故事
            if (instruct.equals(BaseConstant.openStory[i])) {
                return BaseConstant.INSTRUCTION_OPEN_STORY;
            }
        }
        for (int i = 0; i < BaseConstant.openAnim.length; i++) {//打开动画
            if (instruct.equals(BaseConstant.openAnim[i])) {
                return BaseConstant.INSTRUCTION_OPEN_ANIM;
            }
        }

//        String instructs = instruct.substring(0, 1);
//        for (int i = 0; i < Constants.open.length; i++) {
//            if (instructs.equals(Constants.open[i])) {
//                String instructLast = instruct.substring(1);
//            }
//        }
        return null;
    }

    public static boolean isContain(String s1, String[] s2) {
        for (int i = 0; i < s2.length - 1; i++) {
            if (isContain(s1, s2[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * s1是否包s2
     */
    public static boolean isContain(String s1, String s2) {
        return s1.contains(s2);
    }


}
