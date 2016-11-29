package com.xiaoziqianbao.viewdraghelperdemo.view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by liaopenghui on 2016/11/29.
 */

public class SwipDragViewLayout extends RelativeLayout {
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
            //传入3个参数：1 布局本事  2滑动系数   3ViewdragHelper的回调
         viewDragHelper =  ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

             @Override
             public int clampViewPositionHorizontal(View child, int left, int dx)
             {
                 return left;
             }

             @Override
             public int clampViewPositionVertical(View child, int top, int dy)
             {
                 return top;
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
