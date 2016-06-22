package com.framboos.socialweather.socialweather.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framboos.socialweather.socialweather.R;

public class PhotoGalleryFragment extends Fragment {
    public static int numberOfPosts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.photo_gallery_view, container, false);

        ViewPager photoGalleryContainer = (ViewPager) result.findViewById(R.id.photo_gallery_container_view);
        ScreenSlidePagerAdapter adapter = new ScreenSlidePagerAdapter(getChildFragmentManager());
        photoGalleryContainer.setAdapter(adapter);

        return result;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            GalleryPhotoFragment galleryPhotoFragment = new GalleryPhotoFragment();
            if(position > numberOfPosts)
                position = numberOfPosts;
            galleryPhotoFragment.id = position;
            return galleryPhotoFragment;
        }

        @Override
        public int getCount() {
            return 50;
        }
    }
}
