package com.villevh.java_epicture_2016.fragments.gallery;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.GridView;

import com.google.gson.Gson;
import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.googlecode.flickrjandroid.people.User;
import com.googlecode.flickrjandroid.photos.PhotoList;
import com.villevh.java_epicture_2016.MainActivity;

import java.util.HashSet;
import java.util.Set;


public class FlickrUserGalleryTask extends AsyncTask<Void, Void, PhotoList> {


    private Flickr f;
    private MainActivity mActivity;
    private GridView gridview;

    private PhotoList photos;

    public FlickrUserGalleryTask(Flickr flicker, MainActivity mainActivity, GridView g) {
        super();
        this.f = flicker;
        this.mActivity = mainActivity;
        this.gridview = g;
    }

    @Override
    protected PhotoList doInBackground(Void... params) {
        //Get Token
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mActivity);
        Gson gson = new Gson();
        String json = preferences.getString("FlickrToken", "");
        OAuthToken token = gson.fromJson(json, OAuthToken.class);
        json = preferences.getString("UserFlickr", "");
        User user = gson.fromJson(json, User.class);

        Set<String> extras = new HashSet<String>();
        extras.add("url_sq"); //$NON-NLS-1$
        extras.add("url_l"); //$NON-NLS-1$
        extras.add("views"); //$NON-NLS-1$

        try {
            return (f.getPeopleInterface().getPhotos(user.getId(), extras, 20, 1));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (null);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(PhotoList result) {

    }
}