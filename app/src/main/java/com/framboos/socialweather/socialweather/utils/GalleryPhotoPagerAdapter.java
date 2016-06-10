package com.framboos.socialweather.socialweather.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.framboos.socialweather.socialweather.fragments.CommentsFragment;
import com.framboos.socialweather.socialweather.fragments.PhotoFragment;

public class GalleryPhotoPagerAdapter extends FragmentStatePagerAdapter {
    // We want to set this up as a class var so we don't have to re-create it every time
    // the user goes to position==0. Saves some CPU time at the cost of a little memory.
    PhotoFragment photoFragment = new PhotoFragment();
    CommentsFragment photoCommentsFragment = new CommentsFragment();

    public GalleryPhotoPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // TODO: fetch this from a shared data model instance
        return (position == 0) ? photoFragment : photoCommentsFragment;
    }

    @Override
    public int getCount() {
        // TODO: fetch this from a shared data model instance
        return 2;
    }
}