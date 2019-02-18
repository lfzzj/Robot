package com.hamitao.kids.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @data on 2018/6/6 10:08
 * @describe:
 */

public class MyToggle extends View implements View.OnTouchListener {
    //开关开启的背景图片
    private Bitmap bkgSwitchOn;
    //开关关闭的背景图片
    private Bitmap bkgSwitchOff;
    //开关的滚动图片
    private Bitmap btnSlip;
    //当前开关是否为开启状态
    private boolean toggleStateOn;
    //开关状态的监听事件
    private OnToggleStateListener toggleStateListener;
    //记录开关·当前的状态
    private boolean isToggleStateListenerOn;
    //手指按下屏幕时的x坐标
    private float proX;
    //手指滑动过程中当前x坐标
    private float currentX;
    //是否处于滑动状态
    private boolean isSlipping;
    //记录上一次开关的状态
    private boolean proToggleState = true;
    //开关开启时的矩形
    private Rect rect_on;
    //开关关闭时的矩形
    private Rect rect_off;

    public MyToggle(Context context) {
        super(context);
        init(context);
    }

    public MyToggle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    //初始化方法
    private void init(Context context) {
        setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录手指按下时的x坐标
                proX = event.getX();
                currentX = proX;
                //将滑动标识设置为true
                isSlipping = true;
                break;

            case MotionEvent.ACTION_MOVE:
                //记录手指滑动过程中当前x坐标
                currentX = event.getX();
                break;

            case MotionEvent.ACTION_UP:
                //手指抬起时将是否滑动的标识设置为false
                isSlipping = false;
                //处于关闭状态
                if(currentX < bkgSwitchOn.getWidth() / 2 ){
                    toggleStateOn = false;
                } else { // 处于开启状态
                    toggleStateOn = true;
                }

                // 如果使用了开关监听器，同时开关的状态发生了改变，这时使用该代码
                if(isToggleStateListenerOn && toggleStateOn != proToggleState){
                    proToggleState = toggleStateOn;
                    toggleStateListener.onToggleState(toggleStateOn);
                }
                break;
        }

        invalidate();//重绘
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //用来记录我们滑动块的位置
        int left_slip = 0;
        Matrix matrix = new Matrix();
        Paint paint = new Paint();
        if(currentX < bkgSwitchOn.getWidth() / 2){
            //在画布上绘制出开关状态为关闭时的  背景图片
            canvas.drawBitmap(bkgSwitchOff, matrix, paint);
        }else{
            //在画布上绘制出开关状态为开启时的  背景图片
            canvas.drawBitmap(bkgSwitchOn, matrix, paint);
        }
        if(isSlipping){//开关是否处于滑动状态
            // 滑动块 是否超过了整个滑动按钮的宽度
            if(currentX > bkgSwitchOn.getWidth()){
                //指定滑动块的位置
                left_slip = bkgSwitchOn.getWidth() - btnSlip.getWidth();
            } else {
                //设置当前滑动块的位置
                left_slip = (int) (currentX - btnSlip.getWidth() /2);
            }
        } else {//开关是否处于   不滑动状态
            if(toggleStateOn){
                left_slip = rect_on.left;
            } else {
                left_slip = rect_off.left;
            }
        }

        if(left_slip < 0){
            left_slip = 0;
        } else if( left_slip > bkgSwitchOn.getWidth() - btnSlip.getWidth()){
            left_slip = bkgSwitchOn.getWidth() - btnSlip.getWidth();
        }
        //绘制图像
        canvas.drawBitmap(btnSlip, left_slip, 0, paint);
    }
    //计算开关的宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(bkgSwitchOn.getWidth(), bkgSwitchOn.getHeight());
    }

    /**
     * 设置图片资源信息
     * @param bkgSwitch_on
     * @param bkgSwitch_off
     * @param btn_Slip
     */
    public void setImageRes(int bkgSwitch_on, int bkgSwitch_off, int btn_Slip) {
        bkgSwitchOn = BitmapFactory.decodeResource(getResources(), bkgSwitch_on);
        bkgSwitchOff = BitmapFactory.decodeResource(getResources(),bkgSwitch_off);
        btnSlip = BitmapFactory.decodeResource(getResources(), btn_Slip);
        rect_on = new Rect(bkgSwitchOn.getWidth() - btnSlip.getWidth(), 0,bkgSwitchOn.getWidth(), btnSlip.getHeight());
        rect_off = new Rect(0, 0, btnSlip.getWidth(), btnSlip.getHeight());
    }

    /**
     * 设置开关按钮的状态
     * @param state
     */
    public void setToggleState(boolean state) {
        toggleStateOn = state;
    }

    /**
     * 自定义开关状态监听器
     * @author liuyazhuang
     *
     */
   public interface OnToggleStateListener {
         void onToggleState(boolean state);
    }
    //设置开关监听器并将是否设置了开关监听器设置为true
    public void setOnToggleStateListener(OnToggleStateListener listener) {
        toggleStateListener = listener;
        isToggleStateListenerOn = true;
    }

}