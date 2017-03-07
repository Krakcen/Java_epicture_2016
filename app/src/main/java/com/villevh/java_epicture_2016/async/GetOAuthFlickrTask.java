package com.villevh.java_epicture_2016.async;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthInterface;
import com.villevh.java_epicture_2016.Global;
import com.villevh.java_epicture_2016.MainActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class GetOAuthFlickrTask extends AsyncTask<Void, Integer, OAuth> {

    private static final Logger logger = LoggerFactory.getLogger(GetOAuthFlickrTask.class);

    private Flickr f;
    private FragmentActivity fActivity;
    private MainActivity mActivity;
    private OAuthInterface oauthApi;
    private String oauthToken;
    private String oauthVerifier;
    private String requestTokenSecret;
    private Global G;

    public GetOAuthFlickrTask(Global G, MainActivity mainActivity, String oT, String oV, String secret) {
        super();
        this.G = G;
        this.f = G.getF();
        this.mActivity = mainActivity;
        this.oauthToken = oT;
        this.oauthVerifier = oV;
        this.requestTokenSecret = secret;
    }

    @Override
    protected OAuth doInBackground(Void... params) {
        OAuthInterface oauthApi = this.f.getOAuthInterface();
        try {
            return (oauthApi.getAccessToken(oauthToken, requestTokenSecret, oauthVerifier));
        }
        catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return (null);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(OAuth result) {
        //this.G.setFlickrUser(result.getUser());
        this.G.setFlickrToken(result.getToken());
        mActivity.onAuthDone(result);
    }
}