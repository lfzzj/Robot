package com.hamitao.kids.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;


/**
 * 首页电量显示
 */
public class BatteryViewSelfMain extends View {

    private Paint mPaint;
    private float width;
    private float height;

    private float powerQuantity = 0.5f;//电量

    private Bitmap batteryBitmap;

    private float curPower;//当前电量
    /**
     * 电池更新的时间间隔
     */
    private int mInterval = 1000;


    public BatteryViewSelfMain(Context context) {
        super(context, null);
        init();
    }

    public BatteryViewSelfMain(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    public BatteryViewSelfMain(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
//        canvas.drawBitmap(batteryBitmap, 0, 0, mPaint);

        int width = batteryBitmap.getWidth();
        int height = batteryBitmap.getHeight();
        int num = (int) (width * powerQuantity);
//        int power = height - num;
        if (powerQuantity < 0.2) {
            mPaint.setColor(getResources().getColor(R.color.red));
        } else if (powerQuantity >= 0.2 && powerQuantity < 0.3) {
            mPaint.setColor(getResources().getColor(R.color.blue));
        } else {
            mPaint.setColor(getResources().getColor(R.color.common_main_power_value_color));
        }
//        canvas.drawRect(width, height, 0, power, mPaint);
        canvas.drawRect(0, 0, num, height, mPaint);// 长方形
    }

    public void setCurPower(float curPower) {
        this.powerQuantity = curPower;
        invalidate();
    }


    //刷新
    public void refreshPower(float power, boolean isCharging) {

        this.powerQuantity = power;
        if (isCharging) {
            //增加状态控制
            chargingPower();
        } else {
            invalidate();
        }

    }


    /**
     * 充电
     */
    void chargingPower() {
        powerQuantity = (float) (powerQuantity + 0.1);
        if (powerQuantity >= 1) {
            powerQuantity = 1;
        }
        postDelayed(new Runnable() {
            @Override
            public void run() {
                postInvalidate();
                if (powerQuantity == 1) {
                    powerQuantity = curPower;
                }
                chargingPower();
            }
        }, mInterval);
    }

    public void setBatteryBackgroudRes(int bg_battery_power) {
        batteryBitmap = BitmapFactory.decodeResource(getResources(), bg_battery_power);
    }
}