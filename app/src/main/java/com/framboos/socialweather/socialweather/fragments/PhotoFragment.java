package com.framboos.socialweather.socialweather.fragments;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framboos.socialweather.socialweather.R;

public class PhotoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Return an instance of the photo view for it to be the root view for this fragment
        View result = inflater.inflate(R.layout.information_view, container, false);

        setFonts(result);

        return result;
    }

    private void setFonts(View result) {
        TextView myTextView=(TextView) result.findViewById(R.id.textView6);
        Typeface typeFace=Typeface.createFromAsset(result.getContext().getAssets(), "fonts/System_San_Francisco_Display_Ultralight.ttf");
        myTextView.setTypeface(typeFace);
    }
}
