package com.villevh.java_epicture_2016.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.googlecode.flickrjandroid.people.User;
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

        if (G.getIsFlickrConnected() == 1) {
            Gson gson = new Gson();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String json = preferences.getString("UserFlickr", "");
            User user = gson.fromJson(json, User.class);

            //new OAuthFlickrTask(G.getF(), getActivity(), (MainActivity)getActivity()).execute();
            //User hugo = G.getF().getPeopleInterface().findByUsername("MiniKraken");

            TextView textUserName = (TextView) this.rootViewF.findViewById(R.id.userNameConnected);
            View C = this.rootViewF.findViewById(R.id.flickrConnectBoxTMP);
            View D = this.rootViewF.findViewById(R.id.flickrConnectBoxUser);

            String userInfo = "Real Name: " + user.getRealName() + "\nUser Name " + user.getUsername() + "\nPhoto Count: " + user.getPhotosCount();
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
