package com.hamitao.kids.manager.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hamitao.framework.callback.CompleteCallBack;
import com.hamitao.framework.callback.OnCompleteCallBack;
import com.hamitao.framework.callback.PlayCallBack;
import com.hamitao.framework.utils.GifUtil;
import com.hamitao.kids.R;

import pl.droidsonroids.gif.GifImageView;

public class ImgPlayer extends RelativeLayout {
    private Context mContext;

    private TextView tvTitle;
    private GifImageView img;

    public ImgPlayer(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public ImgPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }


    public ImgPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_img_play, this);
        tvTitle = view.findViewById(R.id.title);
        img = view.findViewById(R.id.iv_img);
    }

    public void setImage(String title, String objectURL) {
        tvTitle.setText(title);
        GifUtil.loadGif(objectURL, img);
        img.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onPlayComplete();
                }
            }
        }, 3000);

    }

    public void setAnim(String title, String objectURL) {
        tvTitle.setText(title);
        GifUtil.loadGif(objectURL, img, callBack);
    }

    private CompleteCallBack callBack;

    public void setPlayCallBack(CompleteCallBack callBack) {
        this.callBack = callBack;
    }


}
