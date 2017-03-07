package com.villevh.java_epicture_2016.fragments;

/**
 * Created by villev_h on 28/02/2017.
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.villevh.java_epicture_2016.Global;
import com.villevh.java_epicture_2016.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class WelcomeFragment extends Fragment {
    private PopupWindow popup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final RelativeLayout rootView = (RelativeLayout) inflater.inflate(R.layout.welcome_content, container, false);

        Global G = ((Global) getActivity().getApplicationContext());
        /*G.setImageLogo((ImageView) rootView.findViewById(R.id.welcomeEpitechLogo));
        G.getImageLogo().setImageResource(R.drawable.epitech_black_logo);*/
        Button btn = (Button) rootView.findViewById(R.id.tipButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.tip_popup,null);

                PopupWindow mPopupWindow = new PopupWindow(
                        customView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                popup = mPopupWindow;

                Button btn = (Button) customView.findViewById(R.id.closeTipPopup);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        popup.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(rootView, Gravity.CENTER,0,0);
            }
        });


        return rootView;
    }
}
