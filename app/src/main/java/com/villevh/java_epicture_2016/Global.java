package com.villevh.java_epicture_2016;

import android.app.Application;
import android.content.Context;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.googlecode.flickrjandroid.people.User;
import com.villevh.java_epicture_2016.fragments.AboutFragment;
import com.villevh.java_epicture_2016.fragments.WelcomeFragment;
import com.villevh.java_epicture_2016.fragments.browse.BrowseFragment;
import com.villevh.java_epicture_2016.fragments.gallery.GalleryFragment;
import com.villevh.java_epicture_2016.fragments.settings.SettingsFragment;

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
    private String input;

    private AboutFragment aboutFragment;
    private WelcomeFragment welcomeFragment;

    private GalleryFragment galleryFragment;

    private SettingsFragment settingsFragment;
    private int isSettingsFragmentInit;
    private User flickrUser;
    private User imgurUser;
    private OAuthToken flickrToken;

    private ImageView welcomeEpitechLogo;

    private static Context context;

    public void onCreate() {
        super.onCreate();
        Global.context = getApplicationContext();
    }
    public static Context getAppContext() {
        return Global.context;
    }


    ////////////////////////////////////////////////////////////////////////////

    public OAuthToken getFlickrToken() {
        return (this.flickrToken);
    }
    public void setFlickrToken(OAuthToken o) {
        this.flickrToken = o;
    }

    ////////////////////////////////////////////////////////////////////////////

    public User getFlickrUser() {
        return (this.flickrUser);
    }
    public void setFlickrUser(User f) {
        this.flickrUser = f;
    }
    public User getImgurUser() {
        return (this.imgurUser);
    }
    public void setImgurUser(User i) {
        this.imgurUser = i;
    }

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

    ////////////////////////////////////////////////////////////////////////////

    public BrowseFragment getBrowseFragment() {
        return (this.browseFragment);
    }
    public void setBrowseFragment(BrowseFragment b) {
        this.browseFragment = b;
    }

    public String getInput() {
        return (this.input);
    }
    public void setInput(String s) {
        this.input = s;
    }

    ////////////////////////////////////////////////////////////////////////////


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