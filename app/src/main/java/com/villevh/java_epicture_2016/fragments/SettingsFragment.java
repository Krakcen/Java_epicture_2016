package com.villevh.java_epicture_2016.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.googlecode.flickrjandroid.people.User;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.villevh.java_epicture_2016.Global;
import com.villevh.java_epicture_2016.MainActivity;
import com.villevh.java_epicture_2016.R;
import com.villevh.java_epicture_2016.async.OAuthFlickrTask;

import static android.content.ContentValues.TAG;


public class SettingsFragment extends Fragment implements Handler.Callback{

    private RelativeLayout rootViewF;
    private LayoutInflater inflaterF;
    private ViewGroup containerF;
    private Handler mFragmentHandler;


    @Override
    public boolean handleMessage(Message msg) {


         return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Standard creation code
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        // Create a handler for this fragment
        mFragmentHandler = new Handler(this);

        // Other stuff...
    }

    public void initialFlickrOauth() {
        Global G = ((Global) getActivity().getApplicationContext());
        new OAuthFlickrTask(G.getF(), getActivity(), (MainActivity)getActivity()).execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout rootView = (RelativeLayout) inflater.inflate(R.layout.settings_content, container, false);
        Global G = ((Global) getActivity().getApplicationContext());
        G.setIsSettingsFragmentInit(1);

        this.rootViewF = rootView;
        this.inflaterF = inflater;
        this.containerF = container;
        Button btnFlickr = (Button) rootView.findViewById(R.id.flickrConnectButton);

        ImageView flickrSettingsLogo = (ImageView) rootView.findViewById(R.id.flickr_settings_logo);
        ImageView imgurSettingsLogo = (ImageView) rootView.findViewById(R.id.imgur_settings_logo);

        /*Drawable flickrLogo = getResources().getDrawable(R.drawable.flickr_logo_menu);
        Drawable imgurLogo = getResources().getDrawable(R.drawable.imgur_logo_menu);*/


        flickrSettingsLogo.setImageResource(R.drawable.flickr_logo_menu);
        imgurSettingsLogo.setImageResource(R.drawable.imgur_logo_menu);

        View C = this.rootViewF.findViewById(R.id.flickrConnectBoxTMP);
        View D = this.rootViewF.findViewById(R.id.flickrConnectBoxUser);

        if (G.getIsFlickrConnected() == 1) {
            /*Gson gson = new Gson();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String json = preferences.getString("UserFlickr", "");
            User user = gson.fromJson(json, User.class);*/
            User user = G.getFlickrUser();

            TextView textUserName = (TextView) this.rootViewF.findViewById(R.id.userNameConnected);

            ImageView userConnected = (ImageView) rootView.findViewById(R.id.userFlickrImageConnected);
            Log.d("buddy", user.getBuddyIconUrl());
            UrlImageViewHelper.setUrlDrawable(userConnected, user.getBuddyIconUrl());

            String userInfo = "Connected as\n\n" + user.getRealName() + "\n" + user.getUsername() + "\nPhotos: " + user.getPhotosCount();
            textUserName.setText(userInfo);
            C.setVisibility(View.GONE);
            D.setVisibility(View.VISIBLE);

        }
        else {
            btnFlickr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //init OAuth
                    initialFlickrOauth();
                }
            });
            C.setVisibility(View.VISIBLE);
            D.setVisibility(View.GONE);
        }

        Button btnImgur = (Button) rootView.findViewById(R.id.imgurConnectButton);
        btnImgur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "I clicked Imgur Connect Button");
            }
        });
        return (rootView);
    }
}
