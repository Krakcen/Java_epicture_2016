package com.villevh.java_epicture_2016.async;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;

import com.googlecode.flickrjandroid.Flickr;
import com.villevh.java_epicture_2016.MainActivity;



public class FlickrAPIRequestTask extends AsyncTask<Void, Integer, String> {


    private Flickr f;
    private FragmentActivity fActivity;
    private MainActivity mActivity;
    private int     apiCallID;

    public FlickrAPIRequestTask(Flickr flicker, FragmentActivity fa, MainActivity mainActivity) {
        super();
        this.f = flicker;
        this.fActivity = fa;
        this.mActivity = mainActivity;
    }

    @Override
    protected String doInBackground(Void... params) {
        return ("Hello");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
    }
}