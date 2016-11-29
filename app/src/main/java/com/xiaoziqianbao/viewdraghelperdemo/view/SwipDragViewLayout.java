package com.xiaoziqianbao.viewdraghelperdemo.view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by liaopenghui on 2016/11/29.
 */

public class SwipDragViewLayout extends RelativeLayout {
    private static final String TAG = "SwipDragViewLayout";
    private ViewDragHelper viewDragHelper;

    public SwipDragViewLayout(Context context) {
        super(context);
    }

    public SwipDragViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }



    public SwipDragViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 创建ViewDragHelper
     */
    private void init() {
            //传入3个参数：1 布局本身  2滑动系数主，要用于设置touchSlop:   3ViewdragHelper的回调
         viewDragHelper =  ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

             /**
              * 用来控制View水平的位置
              * @param child   被拖动的View本身
              * @param left   view view的X坐标，随拖动而改变，
              * @param dx   美妙的相对位移 相当于加速度  坐标
              * @return   用来限定View的水平移动范围，如果写死 就只能上下拖动，如果传入参数left  则水平可以任意拖动
              */
             @Override
             public int clampViewPositionHorizontal(View child, int left, int dx)
             {
                 Log.d(TAG,"left"+left+",dx"+dx);
               //  return 0;      左右移动被定死，  只能贴着屏幕左边 上下移动
                // return left;  水平移动没有限制
                 /**防止view拖动超过屏幕边缘*/
                 final int leftBound = getPaddingLeft();
                 final int rightBound = getWidth() - child.getWidth() - leftBound;
                 final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
                 return newLeft;

             }

             /**
              * 同clampViewPositionHorizontal，用来控制拖动的View 竖直方向的范围
              * @param child
              * @param top
              * @param dy
              * @return
              */
             @Override
             public int clampViewPositionVertical(View child, int top, int dy)
             {
                 Log.d(TAG,"top"+top+",dy"+dy);
                // return top;
                 /**防止view拖动超过屏幕边缘*/
                 final int topBound = getPaddingTop();
                 final int bottomBound = getHeight() - child.getHeight() - topBound;
                 final int newBottom = Math.min(Math.max(top, topBound), bottomBound);
                 return newBottom;
             }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return viewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        viewDragHelper.processTouchEvent(event);
        return true;
    }


}
