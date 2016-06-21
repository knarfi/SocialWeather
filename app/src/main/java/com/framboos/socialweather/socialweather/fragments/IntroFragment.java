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

public class IntroFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.intro_view, container, false);

        ViewPager introContainer = (ViewPager) result.findViewById(R.id.intro_container_view);
        introContainer.setAdapter(new ScreenSlidePagerAdapter(getChildFragmentManager()));

        return result;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        IntroPart1Fragment introPart1Fragment = new IntroPart1Fragment();
        IntroPart2Fragment introPart2Fragment = new IntroPart2Fragment();
        IntroPart3Fragment introPart3Fragment = new IntroPart3Fragment();

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
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
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
}
