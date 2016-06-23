package com.framboos.socialweather.socialweather.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framboos.socialweather.socialweather.R;

public class CommentsFragment extends Fragment {
    public int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.information_view, container, false);



        return result;
    }
}
