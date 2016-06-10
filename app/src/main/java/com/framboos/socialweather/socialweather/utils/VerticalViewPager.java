package com.framboos.socialweather.socialweather.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.framboos.socialweather.socialweather.fragments.PhotoFragment;

public class VerticalViewPager extends ViewPager {

    public VerticalViewPager(Context context) {
        super(context);
        init();
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // The majority of the magic happens here
        setPageTransformer(true, new VerticalPageTransformer());
        // The easiest way to get rid of the overscroll drawing that happens on the left and right
        //setOverScrollMode(OVER_SCROLL_NEVER);
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View view, float position) {

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                view.setAlpha(1);

                // Counteract the default slide transition
                view.setTranslationX(view.getWidth() * -position);

                //set Y position to swipe in from top
                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    /**
     * Swaps the X and Y coordinates of your touch event.
     */
    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);

        return ev;
    }

    //@Override
    //public boolean onInterceptTouchEvent(MotionEvent ev){
    //    boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
    //    swapXY(ev); // return touch coordinates to original reference frame for any child views
    //    return intercepted;
    //}

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // determine if a gesture up or down is being made or sideways.
        // if the gesture is up or down than handle it and return true
        // if the gesture is sideways than return false so the parents can handle the touchevent
        // https://github.com/juliome10/DoubleViewPager
        // http://vision-apps.blogspot.nl/2013/05/4-directions-swipe-navigation.html

        return false;
        //return super.onTouchEvent(swapXY(ev));
    }
}