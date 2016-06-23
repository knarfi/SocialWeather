package com.framboos.socialweather.socialweather.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.framboos.socialweather.socialweather.fragments.CommentsFragment;
import com.framboos.socialweather.socialweather.fragments.PhotoFragment;

public class GalleryPhotoPagerAdapter extends FragmentStatePagerAdapter {
    private int PHOTO_FRAGMENT = 0;
    private int PHOTO_COMMENT_FRAGMENT = 1;
    private int postID;

    public GalleryPhotoPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public GalleryPhotoPagerAdapter(FragmentManager fm, int postID) {
        this(fm);
        this.postID = postID;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == PHOTO_FRAGMENT) {
            PhotoFragment photoFragment = new PhotoFragment();
            photoFragment.id = postID;
            return photoFragment;
        } else {
            CommentsFragment photoCommentsFragment = new CommentsFragment();
            photoCommentsFragment.id = postID;
            return photoCommentsFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}