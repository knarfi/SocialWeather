package com.framboos.socialweather.socialweather.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.framboos.socialweather.socialweather.fragments.CommentsFragment;
import com.framboos.socialweather.socialweather.fragments.PhotoFragment;

public class GalleryPhotoPagerAdapter extends FragmentStatePagerAdapter {
    private int PHOTO_FRAGMENT = 0;
    private int PHOTO_COMMENT_FRAGMENT = 1;

    PhotoFragment photoFragment = new PhotoFragment();
    CommentsFragment photoCommentsFragment = new CommentsFragment();

    public GalleryPhotoPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return (position == PHOTO_FRAGMENT) ? photoFragment : photoCommentsFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}