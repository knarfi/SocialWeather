package com.framboos.socialweather.socialweather.fragments;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.framboos.socialweather.socialweather.R;
import com.framboos.socialweather.socialweather.utils.Blurrable;
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // This is a workaround for the inline-class - view needs to be final
        final View finalView = view;

        // Set up a blurred image of the original image
        final ImageView originalImageView = (ImageView) view.findViewById(R.id.originalPhoto);
        final ImageView blurredImageView = (ImageView) view.findViewById(R.id.blurredPhoto);

        // Note: we'll do this in a thread as to not block the main drawing thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Get the original bitmap so we can pass it to the blur algorithm
                final Bitmap originalBitmap = ((BitmapDrawable) originalImageView.getDrawable()).getBitmap();

                // Blur the image
                final Bitmap blurredBitmap = Blurrable.fastblur(originalBitmap, 0.10f, 20);

                // We can only touch view objects through the main thread. As we're blurring this
                // stuff in another thread (it's not fast enough so we have to offload it),
                // we'll have to get back to the main thread. This handler will take care of that:
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    public void run() {
                        // Now that we're on the main thread, we can set the blurred bitmap.
                        blurredImageView.setImageBitmap(blurredBitmap);
                    }
                });
            }
        }).start();

        final ViewPager verticalViewPager = (ViewPager) view.findViewById(R.id.vertical_photo_view_pager);
        // Listen for changes in the scroll state of the verticalViewPager
        verticalViewPager.addOnPageChangeListener (new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                float alpha = 0;

                if (position == 0) {
                    Resources r = getResources();
                    float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, r.getDisplayMetrics());
                    alpha = 1.0f - Math.min(positionOffsetPixels / px, 1.0f);
                }

                originalImageView.setAlpha(alpha);
            }

            @Override
            public void onPageSelected(int position) { }
            @Override
            public void onPageScrollStateChanged(int state) { }
        });

    }
}
