package com.hamitao.kids.utils;

import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.StringUtil;
import com.hamitao.kids.constant.Constants;

/**
 * @data on 2018/5/11 9:23
 * @describe: 音量控制管理器
 */

public class VoiceUtil {
    private static final String TAG = "VoiceManager";
    Context context;
    AudioManager mAudioManager;
    private int totalVoice = 15;

    public VoiceUtil(Context context) {
        this.context = context;
        if (mAudioManager == null) {
            mAudioManager = (AudioManager) context.getSystemService(Service.AUDIO_SERVICE);
        }
    }

    /**
     * 获取系统最大音量（以媒体音量为准）
     *
     * @return
     */
    public int getStreamMaxVolume() {
        return mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    /**
     * 获取当前音量（以媒体音量为准）
     *
     * @return
     */
    public int getStreamVolume() {
        return mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    public void setStreamVolume(int value, boolean isShow) {
        int flags = 0;
        if (isShow) {
            flags = 1;
        }
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, value, flags);//媒体音量
        mAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, value, 0);//提示音音量
        mAudioManager.setStreamVolume(AudioManager.STREAM_RING, value, 0);//铃声音量
        mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, value, 0);//系统音量
        mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, value, 0);//通话音量
        mAudioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, value, 0);//通话音量
    }

    /**
     * 设置音量
     * flag   ---1---->显示系统音量的浮动面板   0--->不显示
     *
     * @param value
     */
    public void setStreamVolume(int value) {
        setStreamVolume(value,false);
    }


    /**
     * 设置音量
     *
     * @param maximum 是否设置成最值 ture-->表示设置成最值（最大/最小）
     * @param isAdd   是否是加音量  ture-->表示增大音量
     */
    public void setVoice(boolean maximum, boolean isAdd) {
        if (maximum) {
            if (isAdd) {
                setStreamVolume(totalVoice);
            } else {
                setStreamVolume(0);
            }
        } else {//不是设置成最值，则一次增大/减少10
            int curVocie = getStreamVolume();
            if (isAdd) {
                curVocie = curVocie + 2;
                setStreamVolume(curVocie > totalVoice ? totalVoice : curVocie);
            } else {//减小音量
                curVocie = curVocie - 2;
                setStreamVolume(curVocie < 0 ? 0 : curVocie);
            }
        }
    }


    private boolean isMaximum = false;
    private boolean isAdd = false;

    /**
     * 设置控制信息
     *
     * @param value
     */
    public void setVoiceInfo(String value) {
        Logger.d(TAG, "value===" + value);
        if (Constants.COMMAND_VALUE_PLUS.equals(value)) {
            isMaximum = false;
            isAdd = true;
        } else if (Constants.COMMAND_VALUE_REDUCE.equals(value)) {
            isMaximum = false;
            isAdd = false;
        } else if (Constants.COMMAND_VALUE_MAX.equals(value)) {
            isMaximum = true;
            isAdd = true;
        } else if (Constants.COMMAND_VALUE_MIN.equals(value)) {
            isMaximum = true;
            isAdd = false;
        } else {
            if (StringUtil.isEndsWith(value, "%")) {//如果最后一个字符是%，则去除
                String newValue = StringUtil.substringLast(value);
                boolean isNum = StringUtil.isNumeric(newValue);//判断是否是数字
                if (isNum) {
                    int percentum = Integer.parseInt(newValue);
                    int curVoice = Math.round(totalVoice * percentum / 100);
                    if (curVoice > 15) {
                        curVoice = 15;
                    }
                    if (curVoice < 0) {
                        curVoice = 0;
                    }
                    Logger.d(TAG, "curVoice = " + curVoice);
                    setStreamVolume(curVoice);
                }
            }

            return;
        }
        setVoice(isMaximum, isAdd);
    }

    /***
     * 增大音量
     */
    public void addVoide() {
//        dealCallVoice(true);
//        dealSystemVoice(true);
//        dealRingVoice(true);
//        dealMediaVoice(true);
//        dealMessageVoice(true);
        setVoice(false, true);
    }


    /***
     * 减少声音
     */
    public void reduceVoide() {
//        dealCallVoice(false);
//        dealSystemVoice(false);
//        dealRingVoice(false);
//        dealMediaVoice(false);
//        dealMessageVoice(false);
        setVoice(false, false);
    }

    /**
     * 提示音设置
     *
     * @param b
     */
    private void dealMessageVoice(boolean b) {
        if (mAudioManager == null) {
            mAudioManager = (AudioManager) context.getSystemService(Service.AUDIO_SERVICE);
        }
        int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
        int current = mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM);
        Log.i(TAG, "===处理前提示音音量====" + current + "  /" + max);
        if (b) {
            if (current == max) {
                return;
            }
            mAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, current + 1, 0);
        } else {
            if (current < 1) {
                return;
            }
            mAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, current - 1, 0);
        }
        int maxdeal = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
        int currentdeal = mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM);
        Logger.e(TAG, "===处理前后提示音音量====" + currentdeal + "  /" + maxdeal);
    }

    /***
     * 处理媒体音量
     * @param b
     */
    private void dealMediaVoice(boolean b) {
        if (mAudioManager == null) {
            mAudioManager = (AudioManager) context.getSystemService(Service.AUDIO_SERVICE);
        }
        int mediamax = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int mediacurrent = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.i(TAG, "===处理前媒体音量====" + mediacurrent + "  /" + mediamax);
        if (b) {
            if (mediacurrent == mediamax) {
                return;
            }
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mediacurrent + 1, 0);
        } else {
            if (mediacurrent < 1) {
                return;
            }
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mediacurrent - 1, 0);
        }
    }

    /***
     * 铃声音量
     * @param b
     */
    private void dealRingVoice(boolean b) {
        if (mAudioManager == null) {
            mAudioManager = (AudioManager) context.getSystemService(Service.AUDIO_SERVICE);
        }
        int ringmax = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        int ringcurrent = mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
        Log.i(TAG, "===处理前铃声音量====" + ringcurrent + "  /" + ringmax);
        if (b) {
            if (ringcurrent == ringmax) {
                return;
            }
            mAudioManager.setStreamVolume(AudioManager.STREAM_RING, ringcurrent + 1, 0);
        } else {
            if (ringcurrent < 1) {
                return;
            }
            mAudioManager.setStreamVolume(AudioManager.STREAM_RING, ringcurrent - 1, 0);
        }
    }

    /***
     * 处理系统音量
     * @param b
     */
    private void dealSystemVoice(boolean b) {
        if (mAudioManager == null) {
            mAudioManager = (AudioManager) context.getSystemService(Service.AUDIO_SERVICE);
        }
        int sysmax = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
        int syscurrent = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
        if (b) {
            if (syscurrent == sysmax) {
                return;
            }
            mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, syscurrent + 1, 0);
        } else {
            if (syscurrent < 1) {
                return;
            }
            mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, syscurrent - 1, 0);
        }
    }

    /***
     *  处理通话音量
     * @param isAdd
     * true 是音量+
     * false 是音量-
     */
    private void dealCallVoice(boolean isAdd) {
        if (mAudioManager == null) {
            mAudioManager = (AudioManager) context.getSystemService(Service.AUDIO_SERVICE);
        }
        int callmax = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
        int callcurrent = mAudioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
        Logger.e(TAG, "=====设置前通话音量==" + callcurrent + "/" + callmax);
        if (isAdd) {
            if (callcurrent == callmax) {
                return;
            }
            mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, callcurrent + 1, 0);
        } else {
            if (callcurrent < 1) {
                return;
            }
            mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, callcurrent - 1, 0);
        }
    }


    /***
     * 静音
     */
    public void stopMediaVoice() {
        if (mAudioManager == null) {
            mAudioManager = (AudioManager) context.getSystemService(Service.AUDIO_SERVICE);
        }
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);  //设置媒体音量为 0
    }

}
