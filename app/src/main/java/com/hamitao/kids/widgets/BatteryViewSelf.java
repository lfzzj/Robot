package com.hamitao.kids.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.camera.Constant;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.manager.DeviceManager;

import java.util.Timer;
import java.util.TimerTask;


/**
 * 充电
 */
public class BatteryViewSelf extends View {

    private Paint mPaint;
    private float width;
    private float height;

    private float powerQuantity = 0.5f;//电量

    private Bitmap batteryBitmap;
    /**
     * 正在充电中
     */
    private boolean isCharging = true;

    private float curPower;//当前电量
    /**
     * 电池更新的时间间隔
     */
    private int mInterval = 1000;
    private Context context;

    private Timer timer;


    public BatteryViewSelf(Context context) {
        super(context, null);
        this.context = context;
        init();
    }

    public BatteryViewSelf(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        this.context = context;
        init();
    }

    public BatteryViewSelf(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();

    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //计算控件尺寸
        setMeasuredDimension(batteryBitmap.getWidth(), batteryBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制界面
        int width = batteryBitmap.getWidth();
        int height = batteryBitmap.getHeight();
        int num;

        if (context.getResources().getString(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)){
            num = (int) (width * powerQuantity);
            mPaint.setColor(getResources().getColor(R.color.jinguowei_power_color));
            canvas.drawRect(0, 0, num, height, mPaint);// 长方形
        }else{
            num = (int) (height * powerQuantity);
            int power = height - num;
            mPaint.setColor(getResources().getColor(R.color.blue_app));
            canvas.drawRect(0, power, width, height, mPaint);// 长方形
        }


    }

    //设置电量的背景（设置电量变化的大小）
    public void setBatteryBackgroudRes(int bg_battery_power) {
        batteryBitmap = BitmapFactory.decodeResource(getResources(), bg_battery_power);
    }

    /**
     * 设置初始化电量
     */
    public void setInitialPower() {
        this.powerQuantity = curPower;
    }

    /**
     * 设置当前电量
     *
     * @param power
     */
    public void setCurPower(float power) {
        this.curPower = power;
    }

    /**
     * 开始计时器，隔1s中刷新一下
     */
    public void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // (1) 使用handler发送消息
                Message message = new Message();
                message.what = 0;
                mHandler.sendMessage(message);
            }
        }, 0, mInterval);//每隔一秒使用handler发送一下消息,也就是每隔一秒执行一次,一直重复执行
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                setPowerRefresh();
            }
        }
    };

    /**
     * 刷新电量
     */
    private void setPowerRefresh() {
        if (powerQuantity >= 1) {
            powerQuantity = 1;
        }
        postInvalidate();
        if (powerQuantity >= 1) {
            powerQuantity = curPower;
        } else {
            powerQuantity = (float) (powerQuantity + 0.1);
        }
    }

    public void stopTimer() {
        mHandler.removeMessages(0);
        timer.cancel();
    }
}