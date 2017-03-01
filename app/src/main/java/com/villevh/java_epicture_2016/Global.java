package com.villevh.java_epicture_2016;

import android.app.Application;
import android.widget.ImageView;
import android.widget.TextView;

import com.villevh.java_epicture_2016.fragments.AboutFragment;
import com.villevh.java_epicture_2016.fragments.BrowseFragment;
import com.villevh.java_epicture_2016.fragments.WelcomeFragment;

public class Global extends Application {

    private String currentWebsite;
    private TextView imgBrowseText;
    private ImageView imageLogo;

    private BrowseFragment browseFragment;

    private AboutFragment aboutFragment;

    private WelcomeFragment welcomeFragment;
    private ImageView welcomeEpitechLogo;

    public String getCurrentWebsite() {
        return (this.currentWebsite);
    }
    public void setCurrentWebsite(String str) {
        this.currentWebsite = str;
    }

    public TextView getImgBrowseText() {
        return (this.imgBrowseText);
    }
    public void setImgBrowseText(TextView t) {
        this.imgBrowseText = t;
    }

    public ImageView getImageLogo() {
        return (this.imageLogo);
    }
    public void setImageLogo(ImageView i) {
        this.imageLogo = i;
    }

    public BrowseFragment getBrowseFragment() {
        return (this.browseFragment);
    }
    public void setBrowseFragment(BrowseFragment b) {
        this.browseFragment = b;
    }

    public AboutFragment getAboutFragment() {
        return (this.aboutFragment);
    }
    public void setAboutFragment(AboutFragment a) {
        this.aboutFragment = a;
    }

    public WelcomeFragment getWelcomeFragment() {
        return (this.welcomeFragment);
    }
    public void setWelcomeFragment(WelcomeFragment w) {
        this.welcomeFragment = w;
    }

    public ImageView getWelcomeEpitechLogo() {
        return (this.welcomeEpitechLogo);
    }
    public void setWelcomeEpitechLogo(ImageView i) {
        this.welcomeEpitechLogo = i;
    }
}