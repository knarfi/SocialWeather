package com.framboos.socialweather.socialweather.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.framboos.socialweather.socialweather.R;
import com.framboos.socialweather.socialweather.fragments.PhotoFragment;
import com.framboos.socialweather.socialweather.fragments.IntroFragment;

public class PhotoContainerActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // The photo_container_view is a placeholder view, which will get filled by a ViewPager
        // which will enable us to show multiple views by swiping left/right.
        this.setContentView(R.layout.photo_container_view);

        // We need to set up a ViewPager that will handle the user's touch events, such as
        // swiping left and right, to switch pages.
        ViewPager pagerView = (ViewPager) findViewById(R.id.photo_container_view);

        // The ViewPager needs an adapter that returns the pages that are available.
        // This is something like a delegate in iOS. Let's set it up:
        PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pagerView.setAdapter(pagerAdapter);

        // We won't have to initialize anything else here. The PhotoContainer is literally
        // a container; all logic shall be in the containing controllers, such as the
        // IntroController and PhotoController.

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        // We want to set this up as a class var so we don't have to re-create it every time
        // the user goes to position==0. Saves some CPU time at the cost of a little memory.
        IntroFragment introFragment = new IntroFragment();

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // TODO: fetch this from a shared data model instance
            return (position == 0) ? this.introFragment : new PhotoFragment();
        }

        @Override
        public int getCount() {
            // TODO: fetch this from a shared data model instance
            return 5;
        }
    }
}
