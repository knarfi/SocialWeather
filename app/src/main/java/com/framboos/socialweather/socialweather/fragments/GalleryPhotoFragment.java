package com.framboos.socialweather.socialweather.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framboos.socialweather.socialweather.R;
import com.framboos.socialweather.socialweather.utils.GalleryPhotoPagerAdapter;
import com.framboos.socialweather.socialweather.utils.VerticalViewPager;

public class GalleryPhotoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Return an instance of the photo view for it to be the root view for this fragment
        View result = inflater.inflate(R.layout.gallery_photo_view, container, false);

        VerticalViewPager pagerView = (VerticalViewPager) result.findViewById(R.id.vertical_photo_view_pager);
        pagerView.setAdapter(new GalleryPhotoPagerAdapter(getChildFragmentManager()));

        return result;
    }
}
