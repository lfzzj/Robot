package com.hamitao.kids.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;

import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.manager.HomeListener;

/**
 * @data on 2018/5/29 15:32
 * @describe: 处理 按键 事件
 */

public abstract class BaseKeyActivity extends BaseCommonActivity {
    private boolean shortPress = false;//是否是短按事件

    private HomeListener mHomeListen = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeListen = new HomeListener(this);
        mHomeListen.setInterface(new HomeListener.KeyFun() {
            @Override
            public void home() {
                onHomePressed();
            }

            @Override
            public void recent() {
                Logger.d(TAG, "recent");
            }

            @Override
            public void longHome() {
                Logger.d(TAG, "longHome");
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_0://按键0

                break;
            case KeyEvent.KEYCODE_VOLUME_UP:   // 音量向上键 (home)
                Log.d(TAG, "KEYCODE_VOLUME_UP--->");
//                dealDeviceAwaken();
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN://音量向下键（长按说话，短按暂停）
//                if (event.getAction() == KeyEvent.ACTION_DOWN) {
//                    event.startTracking();
//                    if (event.getRepeatCount() == 0) {
//                        shortPress = true;
//                    }
//                    return true;
//                }
                break;

        }

        return super.onKeyDown(keyCode, event);
    }

    public void onHomePressed() {

    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            if (shortPress) {
                //短按事件
                Logger.d(TAG, "短按事件");
//                dealShortPress();
            } else {
                Logger.d(TAG, "抬起");

                //结束按住说话表情
//                displayerManager.playEmojiHoldTalkComp();
                //Don't handle longpress here, because the user will have to get his finger back up first
            }
            shortPress = false;
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            shortPress = false;
            //长按事件
            Logger.d(TAG, "长按事件 按下");

//            displayerManager.playEmojiHoldTalk();
            return true;
        }
        //Just return false because the super call does always the same (returning false)
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHomeListen.startListen();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHomeListen.stopListen();
    }


}
