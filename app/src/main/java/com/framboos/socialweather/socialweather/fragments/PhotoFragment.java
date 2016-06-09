package com.framboos.socialweather.socialweather.fragments;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.framboos.socialweather.socialweather.R;
import com.framboos.socialweather.socialweather.utils.Blurrable;

public class PhotoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Return an instance of the photo view for it to be the root view for this fragment
        return inflater.inflate(R.layout.photo_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // This is a workaround for the inline-class - view needs to be final
        final View finalView = view;

        // Get the scroll view from the layout
        final NestedScrollView scrollView = (NestedScrollView) view.findViewById(R.id.scroll_view);

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
                final Bitmap blurredBitmap = Blurrable.blurRenderScript(getContext(), originalBitmap, 25);

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

        // Listen for changes in the scroll state of the scrollView
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // The user has scrolled. We'll need to get the scrollY position and calculate
                // the 'amount' of blur that we want to see.
                Resources r = getResources();
                float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, r.getDisplayMetrics());
                float alpha = 1.0f - Math.min(scrollY / px, 1.0f);

                // Apply the alpha to the blurry image
                originalImageView.setAlpha(alpha);
            }
        });

    }
}
