package com.framboos.socialweather.socialweather.fragments;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.framboos.socialweather.socialweather.R;
import com.framboos.socialweather.socialweather.activities.MainContainerActivity;
import com.framboos.socialweather.socialweather.utils.Blurrable;
import com.framboos.socialweather.socialweather.utils.GalleryPhotoPagerAdapter;
import com.framboos.socialweather.socialweather.utils.VerticalViewPager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class GalleryPhotoFragment extends Fragment {
    public int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Return an instance of the photo view for it to be the root view for this fragment
        View result = inflater.inflate(R.layout.gallery_photo_view, container, false);

        // 'creates' the vertical view pager.
        VerticalViewPager pagerView = (VerticalViewPager) result.findViewById(R.id.vertical_photo_view_pager);
        pagerView.setAdapter(new GalleryPhotoPagerAdapter(getChildFragmentManager()));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            ImageView originalImageView = (ImageView) result.findViewById(R.id.originalPhoto);
            URL newurl = new URL(MainContainerActivity.postsList.get(id).imageURL);
            Bitmap bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
            originalImageView.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            System.out.println("whoa easy there!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("whoa easier there!");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("whoa easiest there!");
            e.printStackTrace();
        }

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
                    // if we are not fully in the comment fragment yet, apply the appropriate blur
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
