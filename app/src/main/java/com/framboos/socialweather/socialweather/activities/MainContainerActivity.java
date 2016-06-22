package com.framboos.socialweather.socialweather.activities;

import android.content.SharedPreferences;
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
    public static final String PREFS_NAME = "SocialWeatherPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.main_view);
        final MainContainerViewPager mainContainer = (MainContainerViewPager) findViewById(R.id.main_container_view);

        final PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mainContainer.setAdapter(pagerAdapter);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); // Get preferences file (0 = no option flags set)
        boolean firstRun = settings.getBoolean("firstRun", true); // Is it first run? If not specified, use "true"

        if (firstRun) {
            mainContainer.setCurrentItem(0);

            SharedPreferences.Editor editor = settings.edit(); // Open the editor for our settings
            editor.putBoolean("firstRun", false); // It is no longer the first run
            editor.commit(); // Save all changed settings
        } else {
            mainContainer.setCurrentItem(1);
        }

        mainContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float v, final int i2) {
            }

            @Override
            public void onPageSelected(final int position) {
                Fragment fragment = (Fragment) pagerAdapter.instantiateItem(mainContainer, position);
                if (fragment != null && mainContainer.getCurrentItem() == 0) {
                    ((IntroFragment)fragment).setCurrentIntroFragment();
                }
            }

            @Override
            public void onPageScrollStateChanged(final int position) {
            }
        });
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
