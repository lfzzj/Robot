package com.hamitao.kids.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @data on 2018/6/2 16:13
 * @describe:
 */

public class SildingViewPager extends ViewPager {
    // 当前的页面
    private int currentItemNum = 0;
    // 记录开始手指点击的位置,和滑动的X距离
    private int StartX, SlipX;

    public SildingViewPager(Context context) {
        super(context, null);
    }

    public SildingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.addOnPageChangeListener(mOnPageChangeListener);
        this.setOnTouchListener(mOnTouchListener);
    }

    private OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            // 获取当前页码
            currentItemNum = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // 一定要spuer，否则事件打住,不会在向下调用了
        super.dispatchTouchEvent(ev);
        switch (ev.getAction()) {
            // 记录用户手指点击的位置
            case MotionEvent.ACTION_DOWN:
                StartX = (int) ev.getX();
                Log.i("info", "StartX = " + StartX);
                break;
        }
        return true;// return false,继续向下传递，return true;拦截,不向下传递
    }

    private OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    StartX = (int) event.getX();
                    Log.i("info", "StartX11111 = " + StartX);
                    break;
                case MotionEvent.ACTION_MOVE:
                    // 获取滑动时候的X距离
                    SlipX = (int) event.getX();
                    Log.d("info", "StartX11111=" + StartX+"   SlipX = " + SlipX);
                    if (currentItemNum == 0 && StartX < 100 && SlipX - StartX > 100) {
                    }
                    break;

                default:
                    break;
            }
            return false;
        }
    };

    private SlidingViewPagerListener listener;

    public interface SlidingViewPagerListener {
        void onSlidRight();
    }

    public void setSlidingViewPagerListener(SlidingViewPagerListener listener) {
        this.listener = listener;
    }

}
