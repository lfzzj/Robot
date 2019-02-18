package com.hamitao.kids.manager.MediaManager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hamitao.framework.callback.OnCompleteCallBack;
import com.hamitao.framework.utils.GifDrawableUtil;
import com.hamitao.framework.utils.GifUtil;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.callback.OnEmojiCallBack;

import pl.droidsonroids.gif.GifImageView;

/**
 * 表情view
 */
public class EmojiView extends RelativeLayout {
    private static String TAG = "EmojiView";
    private Context mContext;

    private GifImageView mImageView;
    private GifImageView mIvSpeak;
    private RelativeLayout mRlFace;
    private ImageView mIvEyes1;
    private ImageView mIvEyes2;

    public EmojiView(Context context) {
        super(context);
    }

    public EmojiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public EmojiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_media_emoji, this);
        mImageView = view.findViewById(R.id.iv_emoji);
        mIvSpeak = view.findViewById(R.id.iv_emoji_speak);
        mRlFace = view.findViewById(R.id.rl_face);
        mIvEyes1 = view.findViewById(R.id.iv_eyes1);
        mIvEyes2 = view.findViewById(R.id.iv_eyes2);

        mRlFace.setOnClickListener(mOnclickListener);
        mIvEyes1.setOnClickListener(mOnclickListener);
        mIvEyes2.setOnClickListener(mOnclickListener);
    }

    private boolean isShowSpeak = false;//说话的表情是否显示


    /**
     * 是否展示设备听的 表情
     *
     * @param isShow
     */
    public void isShowDeviceListenView(boolean isShow) {
        Logger.d(TAG, "是否展示听的表情=" + isShow);
        if (isShow && !isShowSpeak) {
            isShowSpeak = true;
            handler.sendEmptyMessage(1000);
            showNormalEmojiView();
        } else {
            isShowSpeak = false;
            handler.sendEmptyMessage(1001);
        }
    }

    /**
     * 是否展示设备说的 表情
     *
     * @param isShow
     */
    public void isShowDeviceTalkView(boolean isShow) {
        if (isShow) {
            showEmoji(emojiDeviceTalkResId);
        } else {
            showNormalEmojiView();
        }
    }

    //创建Handler
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1000) {
                GifUtil.loadGif(mContext, emojiDeviceListenResId, mIvSpeak);
                mIvSpeak.setVisibility(View.VISIBLE);
            } else if (msg.what == 1001) {
                mIvSpeak.setVisibility(View.GONE);
            } else if (msg.what == 1002) {
                showEmoji(emojiDeviceTalkResId);
            } else if (msg.what == 1003) {
                showNormalEmojiView();
            }
        }
    };


    /**
     * 正常表情播放
     */
    public void showNormalEmojiView() {
        GifDrawableUtil.playGifAnim(mContext, emojiNormalResId, mImageView);
    }

    /**
     * 展示表情
     *
     * @param resId
     */
    private void showEmoji(int resId) {
        GifDrawableUtil.playGifAnim(mContext, resId, mImageView);
    }

    //戳眼睛动画
    public void showPokeEyesEmojiView() {
        showOnceEmojiView(emojiPokeEyesResId);
    }

    //戳脸动画
    public void showPokeFaceEmojiView() {
        showOnceEmojiView(emojiPokeFaceResId);
    }

    /*--------------------------------------------------------------------------*/

    //展示一次的动画
    private void showOnceEmojiView(int emojiRes) {
        GifDrawableUtil.playGifAnim(mContext, emojiRes, mImageView, new OnCompleteCallBack() {
            @Override
            public void onComplete() {//播放完成之后播放正常动画
                showNormalEmojiView();
            }
        });
    }


    private OnClickListener mOnclickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rl_face:
                    Logger.d(TAG, "点击了脸");
                    showPokeFaceEmojiView();
                    if (callBack != null) {
                        callBack.onPokeFace();
                    }
                    break;
                case R.id.iv_eyes1:
                    Logger.d(TAG, "点击了眼睛1");
                    showPokeEyesEmojiView();
                    if (callBack != null) {
                        callBack.onPokeEyes();
                    }
                    break;
                case R.id.iv_eyes2:
                    Logger.d(TAG, "点击了眼睛2");
                    showPokeEyesEmojiView();
                    if (callBack != null) {
                        callBack.onPokeEyes();
                    }
                    break;
            }
        }
    };

    private OnEmojiCallBack callBack;

    public void setOnEmojiCallBack(OnEmojiCallBack callBack) {
        this.callBack = callBack;
    }

    private int emojiPokeEyesResId = R.drawable.anim_emoji_poke_eyes;
    private int emojiNormalResId = R.drawable.anim_emoji_normal;
    private int emojiDeviceListenResId = R.drawable.anim_emoji_device_listen;
    private int emojiPokeFaceResId = R.drawable.anim_emoji_poke_face;
    private int emojiDeviceTalkResId = R.drawable.anim_emoji_device_talk;

    public void initEmoji() {
        showNormalEmojiView();
    }
}
