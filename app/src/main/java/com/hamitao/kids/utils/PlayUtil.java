package com.hamitao.kids.utils;

import android.content.Context;
import android.media.MediaPlayer;

public class PlayUtil {
    private static MediaPlayer player;

    /**
     * 播放提示声音-->播放完成之后回调
     *
     * @param rawID
     */
    public static void playVoice(Context context, int rawID, MediaPlayer.OnCompletionListener mOnCompletionListener) {
        try {
            if (player != null) {
                player.release();
            }
            player = MediaPlayer.create(context, rawID);
            player.start();
            player.setOnCompletionListener(mOnCompletionListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopVoice() {
        if (player != null) {
            player.stop();
        }
    }
}
