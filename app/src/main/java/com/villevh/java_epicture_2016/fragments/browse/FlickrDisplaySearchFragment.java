package com.villevh.java_epicture_2016.fragments.browse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.villevh.java_epicture_2016.Global;
import com.villevh.java_epicture_2016.R;

/**
 * Created by villev_h on 02/03/2017.
 */

/*Key c04354a5e9ebed8394a6b9fc583b77ab
Secret: b78106c5664ccea3
*/

public class FlickrDisplaySearchFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout rootView = (RelativeLayout) inflater.inflate(R.layout.browse_results, container, false);
        Global G = ((Global) getActivity().getApplicationContext());

        TextView t = (TextView) rootView.findViewById(R.id.browseText);
        t.setText(G.getInput());

        if (G.getCurrentWebsite() == "flickr") {

        }
        else {

        }

        return rootView;
    }
}