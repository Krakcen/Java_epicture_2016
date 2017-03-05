package com.villevh.java_epicture_2016.async;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;

import com.google.gson.Gson;
import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthInterface;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.googlecode.flickrjandroid.people.User;
import com.villevh.java_epicture_2016.MainActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.villevh.java_epicture_2016.MainActivity.CALLBACK_SCHEME;



public class GetOAuthFlickrTask extends AsyncTask<Void, Integer, String> {

    private static final Logger logger = LoggerFactory
            .getLogger(OAuthFlickrTask.class);

    public static final String REST_CONSUMER_KEY = "c04354a5e9ebed8394a6b9fc583b77ab";
    public static final String REST_CONSUMER_SECRET = "b78106c5664ccea3";
    private static final Uri OAUTH_CALLBACK_URI = Uri.parse(CALLBACK_SCHEME + "://oauth");

    private Flickr f;
    private FragmentActivity fActivity;
    private MainActivity mActivity;
    private OAuthInterface oauthApi;
    private String oauthToken;
    private String oauthVerifier;
    private String requestTokenSecret;

    public GetOAuthFlickrTask(Flickr flicker, MainActivity mainActivity, OAuthInterface o, String oT, String oV, String secret) {
        super();
        this.f = flicker;
        this.mActivity = mainActivity;
        this.oauthApi = o;
        this.oauthToken = oT;
        this.oauthVerifier = oV;
        this.requestTokenSecret = secret;
    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            OAuth oauth = oauthApi.getAccessToken(oauthToken, requestTokenSecret, oauthVerifier);
            User user = oauth.getUser();
            OAuthToken token = oauth.getToken();

            Gson gson = new Gson();
            String userJson = gson.toJson(user);
            String tokenJson = gson.toJson(token);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mActivity);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("UserFlickr", userJson);
            editor.putString("FlickrToken", tokenJson);
            editor.apply();
            return ("KEK");
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

    }
}