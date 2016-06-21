package com.framboos.socialweather.socialweather.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.framboos.socialweather.socialweather.R;
import com.framboos.socialweather.socialweather.fragments.*;
import com.framboos.socialweather.socialweather.utils.MainContainerViewPager;

public class MainContainerActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.main_view);
        MainContainerViewPager mainContainer = (MainContainerViewPager) findViewById(R.id.main_container_view);

        PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mainContainer.setAdapter(pagerAdapter);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        IntroFragment introFragment = new IntroFragment();
        MenuFragment menuFragment = new MenuFragment();
        PhotoGalleryFragment photoGalleryFragment = new PhotoGalleryFragment();

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.w("i got here", "lel");
            switch(position) {
                case 0:
                    return introFragment;
                case 1:
                    return menuFragment;
                case 2:
                    return photoGalleryFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
