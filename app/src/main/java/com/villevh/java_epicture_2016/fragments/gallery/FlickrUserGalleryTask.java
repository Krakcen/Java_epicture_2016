package com.villevh.java_epicture_2016.fragments.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.RequestContext;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.googlecode.flickrjandroid.people.User;
import com.googlecode.flickrjandroid.photos.Photo;
import com.googlecode.flickrjandroid.photos.PhotoList;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.villevh.java_epicture_2016.MainActivity;
import com.villevh.java_epicture_2016.R;
import com.villevh.java_epicture_2016.adapter.FlickrGalleryAdapter;

import java.util.HashSet;
import java.util.Set;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class FlickrUserGalleryTask extends AsyncTask<Void, Void, PhotoList> {


    private Flickr f;
    private MainActivity mActivity;
    private GridView gridview;
    private RelativeLayout rView;
    private Context context;

    private PopupWindow popup;

    private PhotoList photos;

    public FlickrUserGalleryTask(Context c, Flickr flicker, MainActivity mainActivity, GridView g, RelativeLayout rootView) {
        super();
        this.f = flicker;
        this.mActivity = mainActivity;
        this.gridview = g;
        this.rView = rootView;
        this.context = c;
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

        RequestContext requestContext = RequestContext.getRequestContext();
        OAuth auth = new OAuth();
        auth.setToken(new OAuthToken(token.getOauthToken(), token.getOauthTokenSecret()));
        requestContext.setOAuth(auth);

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
        if (result == null) {
            Log.d("kek", "not working");
        }
        else {
            photos = result;
            Log.d("size", String.valueOf(result.size()));
            FlickrGalleryAdapter adapter = new FlickrGalleryAdapter(this.context, this.mActivity, result, rView);
            this.gridview.setAdapter(adapter);

            this.gridview.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {

                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View customView = inflater.inflate(R.layout.flickr_gallery_popup,null);

                    Photo photo = photos.get(position);
                    ImageView i = (ImageView) customView.findViewById(R.id.flickrPopupImage);
                    UrlImageViewHelper.setUrlDrawable(i, photo.getLargeUrl());

                    PopupWindow mPopupWindow = new PopupWindow(
                            customView,
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT
                    );

                    popup = mPopupWindow;

                    Button btn = (Button) customView.findViewById(R.id.closeFlickrPopup);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Dismiss the popup window
                            popup.dismiss();
                        }
                    });
                    mPopupWindow.showAtLocation(rView, Gravity.CENTER,0,0);
                }
            });

        }
    }
}