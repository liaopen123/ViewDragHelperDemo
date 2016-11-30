package com.xiaoziqianbao.viewdraghelperdemo.view;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.xiaoziqianbao.viewdraghelperdemo.R;

/**
 * Created by liaopenghui on 2016/11/30.
 */

public class DragPhoneViewLayout extends FrameLayout {
    private static final String TAG = "DragPhoneViewLayout";
    private RelativeLayout rl_contact;
    public Point point = new Point();
    private ViewDragHelper viewDragHelper;
    private int red_width;//红色条目的宽度
    private int green_width;//蓝色
    private int mDistance;//移动距离
    private RelativeLayout rl_back;

    public DragPhoneViewLayout(Context context) {
        super(context);
        iniViewDragHelper();
    }

    public DragPhoneViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        iniViewDragHelper();
    }






    public void iniViewDragHelper(){
         viewDragHelper = ViewDragHelper.create(this, 0.1f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return rl_contact == child;
            }

            /**
             * 用来控制View水平的位置
             * @param child   被拖动的View本身
             * @param left  left表示view被拖动的相对位移  如果left<0表示右移，如果left>0 表示左移了
             * @param dx   美妙的相对位移 相当于加速度  坐标
             * @return 用来限定View的水平移动范围，如果写死 就只能上下拖动，如果传入参数left  则水平可以任意拖动
             */
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
             //保证只能右移不能左移
                Log.d(TAG,"left"+left);
                mDistance=left;
                mDistance = left>=0?0:mDistance;
                return mDistance;

            }


            /**
             * 同clampViewPositionHorizontal，用来控制拖动的View 竖直方向的范围
             * @param child
             * @param top
             * @param dy
             * @return
             */
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return point.y;
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth() - child.getMeasuredWidth();
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return getMeasuredHeight() - child.getMeasuredHeight();
            }
            //松手时候的
             @Override
             public void onViewReleased(View releasedChild, float xvel, float yvel) {
                 super.onViewReleased(releasedChild, xvel, yvel);
                 Log.d(TAG,"松手"+mDistance+"red_width:"+red_width);
                 if(mDistance<0){
                     //向右移动
                     if(Math.abs(mDistance)<red_width/2){
                         //收拢
                         Log.d(TAG,"收拢");
                         viewDragHelper.settleCapturedViewAt(0,0);
                     }else if(Math.abs(mDistance)<red_width){
                    //打开红色框
                         Log.d(TAG,"打开红色框");
                         viewDragHelper.settleCapturedViewAt(-red_width,0);
                     }else if(Math.abs(mDistance)<red_width+green_width/2){
                         //打开红色框
                         Log.d(TAG,"打开红色框2");
                         viewDragHelper.settleCapturedViewAt(-red_width,0);
                     }else {
                         //打开绿色框
                         Log.d(TAG,"打开绿色框");
                         viewDragHelper.settleCapturedViewAt(-(red_width+green_width),0);
                     }

                 }else{
                     //向左移动
                     viewDragHelper.settleCapturedViewAt(0,0);
                 }
                 invalidate();
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

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
         rl_contact = (RelativeLayout) getChildAt(1);
         rl_back = (RelativeLayout) getChildAt(0);


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        point.x =  rl_contact.getHeight();
        red_width = rl_back.findViewById(R.id.view_red).getWidth();
        green_width = rl_back.findViewById(R.id.view_green).getWidth();
    }



    @Override
    public void computeScroll()
    {
        if(viewDragHelper.continueSettling(true))
        {
            invalidate();
        }
    }
}
