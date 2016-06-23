package com.framboos.socialweather.socialweather.fragments;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framboos.socialweather.socialweather.R;
import com.framboos.socialweather.socialweather.activities.MainContainerActivity;
import com.framboos.socialweather.socialweather.utils.WeatherPost;

import org.w3c.dom.Text;

public class PhotoFragment extends Fragment {
    public int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Return an instance of the photo view for it to be the root view for this fragment
        View result = inflater.inflate(R.layout.information_view, container, false);

        setFonts(result);
        TextView temperature = (TextView) result.findViewById(R.id.temperature);
        ImageView weatherTypeImage = (ImageView) result.findViewById(R.id.weather_type_image);
        TextView weatherType = (TextView) result.findViewById(R.id.weather_type);
        TextView profileName = (TextView) result.findViewById(R.id.profileName);
        TextView location = (TextView) result.findViewById(R.id.location);
        TextView likes = (TextView) result.findViewById(R.id.likes);
        ImageView profile = (ImageView) result.findViewById(R.id.profile);

        try {
            WeatherPost post = MainContainerActivity.postsList.get(id);

            temperature.setText(post.temperature + "º");
            profileName.setText(post.profileName);
            location.setText(post.location.toUpperCase());
            likes.setText(post.likes);

            switch(post.weatherType) {
                case 0:
                default:
                    // sunny
                    weatherType.setText("SUNNY");
                    weatherTypeImage.setBackgroundResource(R.drawable.sunny);
                    break;
                case 1:
                    //cloudy
                    weatherType.setText("CLOUDY");
                    weatherTypeImage.setBackgroundResource(R.drawable.cloudy);
                    break;
                case 2:
                    //rainy
                    weatherType.setText("RAINY");
                    weatherTypeImage.setBackgroundResource(R.drawable.rainy);
                    break;
                case 3:
                    //foggy
                    weatherType.setText("FOGGY");
                    weatherTypeImage.setBackgroundResource(R.drawable.foggy);
                    break;
                case 4:
                    //thunder
                    weatherType.setText("THUNDERING");
                    weatherTypeImage.setBackgroundResource(R.drawable.thunder);
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("whoa easiest there!");
            e.printStackTrace();
        }

        return result;
    }

    private void setFonts(View result) {
        TextView myTextView=(TextView) result.findViewById(R.id.temperature);
        Typeface typeFace=Typeface.createFromAsset(result.getContext().getAssets(), "fonts/System_San_Francisco_Display_Ultralight.ttf");
        myTextView.setTypeface(typeFace);
    }
}
