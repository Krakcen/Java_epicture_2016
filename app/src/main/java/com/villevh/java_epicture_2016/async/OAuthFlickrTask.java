package com.villevh.java_epicture_2016.async;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.auth.Permission;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.villevh.java_epicture_2016.MainActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static android.content.ContentValues.TAG;
import static com.villevh.java_epicture_2016.MainActivity.CALLBACK_SCHEME;



public class OAuthFlickrTask extends AsyncTask<Void, Integer, String> {

    private static final Logger logger = LoggerFactory
            .getLogger(OAuthFlickrTask.class);

    public static final String REST_CONSUMER_KEY = "c04354a5e9ebed8394a6b9fc583b77ab";
    public static final String REST_CONSUMER_SECRET = "b78106c5664ccea3";
    private static final Uri OAUTH_CALLBACK_URI = Uri.parse(CALLBACK_SCHEME + "://oauth");

    private Flickr f;
    private FragmentActivity fActivity;
    private MainActivity mActivity;

    public OAuthFlickrTask(Flickr flicker, FragmentActivity fa, MainActivity mainActivity) {
        super();
        this.f = flicker;
        this.fActivity = fa;
        this.mActivity = mainActivity;
    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            OAuthToken oauthToken = f.getOAuthInterface().getRequestToken(OAUTH_CALLBACK_URI.toString());
            String secretToken = oauthToken.getOauthTokenSecret();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mActivity);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("SecretFlickr", secretToken);
            editor.apply();

            URL oauthUrl = f.getOAuthInterface().buildAuthenticationUrl(Permission.READ, oauthToken);
            return (oauthUrl.toString());
            //OAuthToken oauthToken = f.getOAuthInterface().getRequestToken(null);
        }
        catch (Exception e) {
            return ("kek");
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d(TAG, result);
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse(result));
        fActivity.startActivity(viewIntent);
    }
}