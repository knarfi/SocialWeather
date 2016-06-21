package com.framboos.socialweather.socialweather.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.framboos.socialweather.socialweather.R;
import com.framboos.socialweather.socialweather.activities.PostActivity;
import com.framboos.socialweather.socialweather.utils.IntroContainerViewPager;
import com.framboos.socialweather.socialweather.utils.MainContainerViewPager;

public class MenuFragment extends Fragment {
    private final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_view, container, false);

        final LayoutInflater givenInflater = inflater;
        final MainContainerViewPager mainContainer = (MainContainerViewPager)
                getActivity().findViewById(R.id.main_container_view);
        final LinearLayout introLayout = (LinearLayout) inflater.inflate(R.layout.intro_view, null);
        final ViewPager introContainer =
                (ViewPager) introLayout.findViewById(R.id.intro_container_view);

        Button newestPostsButton = (Button) view.findViewById(R.id.action_newest_posts);
        newestPostsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainContainer.setCurrentItem(2, true);
            }
        });

        Button uploadButton = (Button) view.findViewById(R.id.action_upload);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                makePhoto();
            }
        });

        Button loginButton = (Button) view.findViewById(R.id.action_login);
        final FragmentManager fm = getChildFragmentManager();
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainContainer.setCurrentItem(0, false);
                introContainer.setCurrentItem(0, true);
            }
        });

        // Return an instance of the intro view so it'll be the root view for this fragment
        return view;
    }

    public void makePhoto() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
            Log.v("Camera", "Working");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            Intent intent = new Intent(getActivity(), PostActivity.class);
            intent.putExtra("data", imageBitmap);
            startActivity(intent);
        }
    }
}
