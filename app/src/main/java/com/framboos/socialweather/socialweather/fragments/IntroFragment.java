package com.framboos.socialweather.socialweather.fragments;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.framboos.socialweather.socialweather.R;

public class IntroFragment extends Fragment {
    private ViewPager introContainer;
    public static int toFragment = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.intro_view, container, false);

        introContainer = (ViewPager) result.findViewById(R.id.intro_container_view);
        introContainer.setAdapter(new ScreenSlidePagerAdapter(getChildFragmentManager()));

        final ViewPager photoGalleryContainer = (ViewPager) result.findViewById(R.id.android_photo_gallery_container_view);
        photoGalleryContainer.setAdapter(new AndroidScreenSlidePagerAdapter(getChildFragmentManager()));
        photoGalleryContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        final ImageView androidFrame = (ImageView) result.findViewById(R.id.android_frame);
        final LinearLayout androidFrameLayout = (LinearLayout) result.findViewById(R.id.android_frame_layout);

        introContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //all in PX, hardcoded values
                double margin = 1830;

                switch(position) {
                    case 0:
                    case 3:
                        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                        Display display = wm.getDefaultDisplay();
                        Point size = new Point();
                        display.getSize(size);
                        int width = size.x;

                        double factor = (margin - 400) / width;

                        if(position == 0) {
                            margin -= (factor * positionOffsetPixels);
                        } else {
                            margin = 400;
                            margin += (factor * positionOffsetPixels);
                        }
                        break;
                    case 1:
                    case 2:
                        margin = 400;
                        break;
                }

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, (int) margin, 0, (int) margin * -1);
                androidFrame.setLayoutParams(lp);

                RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                lp2.setMargins(50, (int) (margin + 220), 50, 0);
                androidFrameLayout.setLayoutParams(lp2);
            }

            @Override
            public void onPageSelected(int position) { }
            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        return result;
    }

    public void setCurrentIntroFragment(int position) {
        introContainer.setCurrentItem(position, false);
    }

    public void setCurrentIntroFragment() {
        this.setCurrentIntroFragment(toFragment);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        IntroPart1Fragment introPart1Fragment = new IntroPart1Fragment();
        IntroPart2Fragment introPart2Fragment = new IntroPart2Fragment();
        IntroPart3Fragment introPart3Fragment = new IntroPart3Fragment();
        IntroPart4Fragment introPart4Fragment = new IntroPart4Fragment();
        LoginFragment loginFragment = new LoginFragment();

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return introPart1Fragment;
                case 1:
                    return introPart2Fragment;
                case 2:
                    return introPart3Fragment;
                case 3:
                    return introPart4Fragment;
                case 4:
                    return loginFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

    public static class IntroPart1Fragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.intro_view_part1, container, false);
        }
    }

    public static class IntroPart2Fragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.intro_view_part2, container, false);
        }
    }

    public static class IntroPart3Fragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.intro_view_part3, container, false);
        }
    }

    public static class IntroPart4Fragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.intro_view_part4, container, false);
        }
    }

    private class AndroidScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public AndroidScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // TODO: fetch this from a shared data model instance
            return new GalleryPhotoFragment();
        }

        @Override
        public int getCount() {
            // TODO: fetch this from a shared data model instance
            return 5;
        }
    }
}
