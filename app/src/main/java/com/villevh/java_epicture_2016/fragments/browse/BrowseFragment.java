package com.villevh.java_epicture_2016.fragments.browse;

/**
 * Created by villev_h on 28/02/2017.
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.villevh.java_epicture_2016.Global;
import com.villevh.java_epicture_2016.R;

public class BrowseFragment extends Fragment {
    private String input;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout rootView = (RelativeLayout) inflater.inflate(R.layout.browse_content, container, false);

        final Global G = ((Global) getActivity().getApplicationContext());
        G.setImgBrowseText((TextView) rootView.findViewById(R.id.imageBrowseText));
        G.setImageLogo((ImageView) rootView.findViewById(R.id.imageLogo));

        input = "";
        final SearchView searchView = (SearchView) rootView.findViewById(R.id.searchViewImage);
        Button searchButton = (Button) rootView.findViewById(R.id.browseimagesButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (input != "") {
                  G.setInput(input);

                  FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                  transaction.replace(R.id.fragment_container_content, new FlickrDisplaySearchFragment());
                  transaction.addToBackStack(null);
                  transaction.commit();
              }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                input = query;
                searchView.clearFocus();
                return (true);
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//              if (searchView.isExpanded() && TextUtils.isEmpty(newText)) {
//              }
                input = newText;
                return (true);
            }
        });

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