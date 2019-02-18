package com.hamitao.kids.utils;

import android.content.Context;
import android.media.MediaPlayer;

public class PlayVoice {

    private static MediaPlayer mediaPlayer;


    /**
     * 播放声音-->重复播放
     *
     * @param context
     * @param rawID
     */
    public static void playVoice(Context context, int rawID) {
        try {
            mediaPlayer = MediaPlayer.create(context, rawID);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //停止播放声音
    public static void stopVoice() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


}
