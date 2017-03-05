package com.villevh.java_epicture_2016;

import android.app.Application;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.googlecode.flickrjandroid.Flickr;
import com.villevh.java_epicture_2016.fragments.AboutFragment;
import com.villevh.java_epicture_2016.fragments.SettingsFragment;
import com.villevh.java_epicture_2016.fragments.WelcomeFragment;
import com.villevh.java_epicture_2016.fragments.browse.BrowseFragment;
import com.villevh.java_epicture_2016.fragments.gallery.GalleryFragment;

public class Global extends Application {

    private Flickr f;

    private String lastW;

    private int isFlickrConnected;
    private int isImgurConnected;

    private ImageButton menuLogoButton;
    private String currentWebsite;
    private TextView imgBrowseText;
    private ImageView imageLogo;

    private BrowseFragment browseFragment;
    private AboutFragment aboutFragment;
    private WelcomeFragment welcomeFragment;

    private GalleryFragment galleryFragment;

    private SettingsFragment settingsFragment;
    private int isSettingsFragmentInit;

    private ImageView welcomeEpitechLogo;

    ////////////////////////////////////////////////////////////////////////////

    public String getLastW() {
        return (this.lastW);
    }
    public void setLastW(String s) {
        this.lastW = s;
    }

    ////////////////////////////////////////////////////////////////////////////

    public void setF(Flickr flickr) {
        this.f = flickr;
    }
    public Flickr getF() {
        return (this.f);
    }

    ////////////////////////////////////////////////////////////////////////////

    public int getIsFlickrConnected() {
        return (this.isFlickrConnected);
    }
    public void setIsFlickrConnected(int isConnected) {
        this.isFlickrConnected = isConnected;
    }

    public int getIsImgurConnected() {
        return (this.isImgurConnected);
    }
    public void setIsImgurConnected(int isConnected) {
        this.isImgurConnected = isConnected;
    }

    ////////////////////////////////////////////////////////////////////////////

    public ImageButton getMenuLogoButton() {
        return (this.menuLogoButton);
    }
    public void setMenuLogoButton(ImageButton i) {
        this.menuLogoButton = i;
    }

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

    ////////////////////////////////////////////////////////////////////////////

    public SettingsFragment getSettingsFragment() {
        return (this.settingsFragment);
    }
    public void setSettingsFragment(SettingsFragment s) {
        this.settingsFragment = s;
    }

    public int getIsSettingsFragmentInit() {
        return (this.isSettingsFragmentInit);
    }
    public void setIsSettingsFragmentInit(int nb) {
        this.isSettingsFragmentInit = nb;
    }

    ////////////////////////////////////////////////////////////////////////////

    public GalleryFragment getGalleryFragment() {
        return (this.galleryFragment);
    }
    public void setGalleryFragment(GalleryFragment g) {
        this.galleryFragment = g;
    }

    ////////////////////////////////////////////////////////////////////////////

    public ImageView getWelcomeEpitechLogo() {
        return (this.welcomeEpitechLogo);
    }
    public void setWelcomeEpitechLogo(ImageView i) {
        this.welcomeEpitechLogo = i;
    }
}