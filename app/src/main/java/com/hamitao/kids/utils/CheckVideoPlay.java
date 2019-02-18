package com.hamitao.kids.utils;

import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.manager.SPManager;
import com.hamitao.kids.manager.play.callback.LimitVideoPlayback;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CheckVideoPlay {
    private static List<String> mTimeStamp = new ArrayList<>();//保存时间戳
    private static int isPlayingNum;//正在播放视频的次数累计
    private static Timer timer;

    /**
     * 检测视频播放时间
     */
    public static void CheckVideoPlaybackTime(LimitVideoPlayback callBack, SPManager spManager) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!MyApp.getInstance().getSpManager().isOpenEyeProtect()) {
                    return;
                }
                if (MyApp.getInstance().isVideoPlaying()) {
                    mTimeStamp.add(Constants.VIDEO_PLAYING);
                } else {
                    mTimeStamp.add(Constants.VIDEO_NOT_PLAYING);
                }
                isPlayingNum = 0;
                //检测之前60分钟之内正在播放的时间戳
                for (int i = mTimeStamp.size() - 1; i > 0; i--) {
                    if (mTimeStamp.size() >= Constants.allowPlayTime) {
                        if (i > mTimeStamp.size() - Constants.allowPlayTime) {
                            if (Constants.VIDEO_PLAYING.equals(mTimeStamp.get(i))) {
                                isPlayingNum++;
                            }
                        }
                    } else {
                        if (Constants.VIDEO_PLAYING.equals(mTimeStamp.get(i))) {
                            isPlayingNum++;
                        }
                    }
                }
                if (MyApp.getInstance().getSpManager().isOpenEyeProtect()) {//如果护眼提醒开启了，检测在规定时间内有没有超过护眼时间
                    if (isPlayingNum >= Constants.totalTime) {
                        MyApp.getInstance().setAllowPlayVideo(false);
                    } else {
                        MyApp.getInstance().setAllowPlayVideo(true);
                    }
                } else {//护眼提醒关闭了，可以一直播放视频
                    MyApp.getInstance().setAllowPlayVideo(true);
                    isPlayingNum = 0;
                }
                if (!MyApp.getInstance().isAllowPlayVideo()) {
                    //时间到了不允许播放视频了，如果视频正在播放，则直接停止播放
                    if (MyApp.getInstance().isVideoPlaying()) {
                        //执行关闭视频播放
                        if (callBack != null) {
                            callBack.OnStopPlayingVideo();
                        }
                    }
                }
            }
        }, 0, Constants.oneMinuteTime);//每隔1分钟执行操作
    }

    public static void clearPlayingNum() {
        if (mTimeStamp != null) {
            mTimeStamp.clear();
        }
    }

    public static void stopPlaybackTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
