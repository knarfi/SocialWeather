package com.framboos.socialweather.socialweather.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.framboos.socialweather.socialweather.R;
import com.framboos.socialweather.socialweather.fragments.*;
import com.framboos.socialweather.socialweather.utils.MainContainerViewPager;
import com.framboos.socialweather.socialweather.utils.WeatherPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainContainerActivity extends FragmentActivity {
    public static final String PREFS_NAME = "SocialWeatherPrefsFile";

    public static ArrayList<WeatherPost> postsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.main_view);


        String url = "";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                final MainContainerViewPager mainContainer = (MainContainerViewPager) findViewById(R.id.main_container_view);

                final PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
                mainContainer.setAdapter(pagerAdapter);

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); // Get preferences file (0 = no option flags set)
                boolean firstRun = settings.getBoolean("firstRun", true); // Is it first run? If not specified, use "true"

                if (firstRun) {
                    mainContainer.setCurrentItem(0);

                    SharedPreferences.Editor editor = settings.edit(); // Open the editor for our settings
                    editor.putBoolean("firstRun", false); // It is no longer the first run
                    editor.commit(); // Save all changed settings
                } else {
                    mainContainer.setCurrentItem(1);
                }

                mainContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(final int position, final float v, final int i2) {
                    }

                    @Override
                    public void onPageSelected(final int position) {
                        Fragment fragment = (Fragment) pagerAdapter.instantiateItem(mainContainer, position);
                        if (fragment != null && mainContainer.getCurrentItem() == 0) {
                            ((IntroFragment)fragment).setCurrentIntroFragment();
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(final int position) {
                    }
                });

                try {
                    JSONArray posts = response.getJSONObject("data").getJSONArray("posts");
                    PhotoGalleryFragment.numberOfPosts = response.getJSONObject("data").getJSONArray("posts").length();

                    for(int i = 0; i < posts.length(); i++) {
                        JSONObject post = posts.getJSONObject(i);
                        String postURL = response.getJSONObject("data").getJSONObject("photos").getJSONObject(post.getString("photo_id")).getString("image");

                        WeatherPost weatherPost = new WeatherPost(postURL);
                        postsList.add(weatherPost);

                        weatherPost.temperature = post.getJSONObject("weather").getString("temperature");
                        weatherPost.weatherType = post.getJSONObject("weather").getInt("weather_type");
                        weatherPost.likes = post.getString("number_of_likes");
                        weatherPost.profileName = response.getJSONObject("user").getJSONObject(post.getString("user_id")).getString("name");
                        weatherPost.profileURL = response.getJSONObject("user").getJSONObject(post.getString("user_id")).getString("picture");
                        weatherPost.location = post.getJSONObject("location").getString("city");
                    }
                } catch (JSONException error) {
                    error.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();
            }
        });
        // Add the request to the queue
        Volley.newRequestQueue(this).add(jsObjRequest);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        IntroFragment introFragment = new IntroFragment();
        MenuFragment menuFragment = new MenuFragment();
        PhotoGalleryFragment photoGalleryFragment = new PhotoGalleryFragment();

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return introFragment;
                case 1:
                    return menuFragment;
                case 2:
                    return photoGalleryFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
