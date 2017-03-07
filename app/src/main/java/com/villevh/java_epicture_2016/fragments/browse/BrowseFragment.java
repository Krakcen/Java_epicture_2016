package com.villevh.java_epicture_2016.fragments.browse;

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
import android.widget.TextView;

import com.villevh.java_epicture_2016.Global;
import com.villevh.java_epicture_2016.R;

public class BrowseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout rootView = (RelativeLayout) inflater.inflate(R.layout.browse_content, container, false);

        Global G = ((Global) getActivity().getApplicationContext());
        G.setImgBrowseText((TextView) rootView.findViewById(R.id.imageBrowseText));
        G.setImageLogo((ImageView) rootView.findViewById(R.id.imageLogo));

        if (G.getCurrentWebsite() == "flickr") {
            G.getImgBrowseText().setText(R.string.flickr_browse);
            G.getImageLogo().setImageResource(R.drawable.flickr_logo_medium);
        }
        else {
            G.getImgBrowseText().setText(R.string.imgur_browse);
            G.getImageLogo().setImageResource(R.drawable.imgur_logo_medium);
        }
        return rootView;
    }
}