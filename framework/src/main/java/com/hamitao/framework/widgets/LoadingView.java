package com.hamitao.framework.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;


import com.airbnb.lottie.LottieAnimationView;
import com.hamitao.framework.R;
import com.hamitao.framework.utils.GifDrawableUtil;
import com.hamitao.framework.utils.GifUtil;

import pl.droidsonroids.gif.GifImageView;

/**
 * 加载框
 */
public class LoadingView extends RelativeLayout {
    private static String TAG = "LoadingView";
    private Context mContext;

    private LottieAnimationView ivLoading;

    public LoadingView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_loading, this);
        ivLoading = view.findViewById(R.id.iv_loading);
    }

    //暂时弹出框
    public void showLoading() {
        showLoading(View.VISIBLE);
    }

    //隐藏弹出框
    public void hintLoading() {
        ivLoading.setVisibility(View.GONE);
    }


    public void showLoading(int loadingPro){
        ivLoading.setVisibility(loadingPro);
    }
}
