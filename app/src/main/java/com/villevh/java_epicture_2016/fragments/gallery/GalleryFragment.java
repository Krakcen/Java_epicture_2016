package com.villevh.java_epicture_2016.fragments.gallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.villevh.java_epicture_2016.Global;
import com.villevh.java_epicture_2016.MainActivity;
import com.villevh.java_epicture_2016.R;

public class GalleryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RelativeLayout rootView = (RelativeLayout) inflater.inflate(R.layout.gallery_content, container, false);

        Global G = ((Global) getActivity().getApplicationContext());


        LinearLayout errorFlickrGallery = (LinearLayout) rootView.findViewById(R.id.errorFlickrGallery);
        GridView flickrGallery = (GridView) rootView.findViewById(R.id.flickrGallery);
        LinearLayout errorImgurGallery = (LinearLayout) rootView.findViewById(R.id.errorImgurGallery);
        GridView imgurGallery = (GridView) rootView.findViewById(R.id.imgurGallery);

        if (G.getCurrentWebsite() == "flickr") {
            if (G.getIsFlickrConnected() == 1) {
                errorImgurGallery.setVisibility(View.GONE);
                imgurGallery.setVisibility(View.GONE);
                flickrGallery.setVisibility(View.VISIBLE);
                errorFlickrGallery.setVisibility(View.GONE);
                new FlickrUserGalleryTask(getContext(), G, (MainActivity) this.getActivity(), flickrGallery, rootView).execute();
            }
            else {
                errorImgurGallery.setVisibility(View.GONE);
                imgurGallery.setVisibility(View.GONE);
                flickrGallery.setVisibility(View.GONE);
                errorFlickrGallery.setVisibility(View.VISIBLE);
            }
        }
        else {
            if (G.getIsImgurConnected() == 1) {
                errorImgurGallery.setVisibility(View.GONE);
                imgurGallery.setVisibility(View.VISIBLE);
                flickrGallery.setVisibility(View.GONE);
                errorFlickrGallery.setVisibility(View.GONE);
            }
            else {
                errorImgurGallery.setVisibility(View.VISIBLE);
                imgurGallery.setVisibility(View.GONE);
                flickrGallery.setVisibility(View.GONE);
                errorFlickrGallery.setVisibility(View.GONE);
            }
        }
        return (rootView);
    }
}