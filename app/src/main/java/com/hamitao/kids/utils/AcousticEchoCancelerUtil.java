package com.hamitao.kids.utils;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.media.audiofx.AcousticEchoCanceler;
import android.util.Log;

import com.hamitao.framework.utils.Logger;

public class AcousticEchoCancelerUtil {
    private AudioRecord mRecorder;
    private byte[] pcm;
    private int mRecorderBufferSize;

    /**
     * 初始化录音
     */
    public void initRecorder() {
        mRecorderBufferSize = AudioRecord.getMinBufferSize(8000,
                AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        pcm = new byte[320];
        mRecorder = new AudioRecord(MediaRecorder.AudioSource.VOICE_COMMUNICATION, 8000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT,
                mRecorderBufferSize);
    }

    private AudioTrack mAudioTrack;
    private int audioSessionId = -1;

    public void initAudioTrack() {
        if (mAudioTrack == null) {
            if (audioSessionId == -1) {
                mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 8000, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, mRecorderBufferSize * 2
                        , AudioTrack.MODE_STREAM);
            } else {
                mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 8000, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, mRecorderBufferSize * 2
                        , AudioTrack.MODE_STREAM, audioSessionId);
            }
        }
    }


    public static boolean isDeviceSupport() {
        return AcousticEchoCanceler.isAvailable();
    }

    private AcousticEchoCanceler acousticEchoCanceler;

    public void initAEC() {
        if (AcousticEchoCanceler.isAvailable()) {
            if (acousticEchoCanceler == null) {
                acousticEchoCanceler = AcousticEchoCanceler.create(audioSessionId);
                Logger.d("initAEC", "initAEC: ---->" + acousticEchoCanceler + "\t" + audioSessionId);
                if (acousticEchoCanceler == null) {
                    Logger.e("initAEC", "initAEC: ----->AcousticEchoCanceler create fail.");
                } else {
                    acousticEchoCanceler.setEnabled(true);
                }
            }
        }
    }

    public void write(byte[] data) {
        if (mAudioTrack != null && mAudioTrack.getPlayState() == AudioTrack.PLAYSTATE_PLAYING) {
            mAudioTrack.write(data, 0, data.length);
        }
    }
}
