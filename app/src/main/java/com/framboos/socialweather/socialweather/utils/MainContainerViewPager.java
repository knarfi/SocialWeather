package com.framboos.socialweather.socialweather.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MainContainerViewPager extends ViewPager {

    public MainContainerViewPager(Context context) {
        super(context);
    }

    public MainContainerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if(this.getCurrentItem() == 1) {
            // Never allow swiping to switch between pages
            return false;
        } else {
            return super.onInterceptTouchEvent(event);
        }
    }
}
