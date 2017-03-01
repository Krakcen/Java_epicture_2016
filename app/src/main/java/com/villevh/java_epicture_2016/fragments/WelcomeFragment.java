package com.villevh.java_epicture_2016.fragments;

/**
 * Created by villev_h on 28/02/2017.
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.villevh.java_epicture_2016.Global;
import com.villevh.java_epicture_2016.R;

public class WelcomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout rootView = (RelativeLayout) inflater.inflate(R.layout.welcome_content, container, false);

        Global G = ((Global) getActivity().getApplicationContext());
        G.setImageLogo((ImageView) rootView.findViewById(R.id.welcomeEpitechLogo));
        G.getImageLogo().setImageResource(R.drawable.epitech_black_logo);

        return rootView;
    }
}
