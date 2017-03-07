package com.villevh.java_epicture_2016;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.googlecode.flickrjandroid.people.User;
import com.villevh.java_epicture_2016.async.GetFlickrUserIdTask;
import com.villevh.java_epicture_2016.async.GetOAuthFlickrTask;
import com.villevh.java_epicture_2016.fragments.AboutFragment;
import com.villevh.java_epicture_2016.fragments.SettingsFragment;
import com.villevh.java_epicture_2016.fragments.WelcomeFragment;
import com.villevh.java_epicture_2016.fragments.browse.BrowseFragment;
import com.villevh.java_epicture_2016.fragments.gallery.GalleryFragment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.villevh.java_epicture_2016.async.OAuthFlickrTask.REST_CONSUMER_KEY;
import static com.villevh.java_epicture_2016.async.OAuthFlickrTask.REST_CONSUMER_SECRET;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private static final Logger logger = LoggerFactory.getLogger(MainActivity.class);

    public static final String CALLBACK_SCHEME = "flickrj-android-oauth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Global G = ((Global) getApplicationContext());

        G.setF(new Flickr(REST_CONSUMER_KEY, REST_CONSUMER_SECRET));

        G.setImgurUser(null);
        G.setFlickrUser(null);
        G.setIsSettingsFragmentInit(0);
        G.setCurrentWebsite("imgur");
        G.setLastW("imgur");
        G.setIsFlickrConnected(0);
        G.setIsImgurConnected(0);

        G.setWelcomeFragment(new WelcomeFragment());
        G.setAboutFragment(new AboutFragment());
        G.setSettingsFragment(new SettingsFragment());
        G.setGalleryFragment(new GalleryFragment());

        //fragment welcome
        if (findViewById(R.id.fragment_container_content) != null) {
            if (savedInstanceState != null) {
                return;
            }
            G.getWelcomeFragment().setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_content, G.getWelcomeFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    protected void doMySearch(String query) {
        Global G = ((Global) getApplicationContext());
        G.getImgBrowseText().setText(query);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);

        //set menulogobutton
        final Global G = ((Global) getApplicationContext());

        final ImageButton imageB = (ImageButton)findViewById(R.id.menuLogoButton);
        if (G.getCurrentWebsite() == "imgur") {
            imageB.setImageResource(R.drawable.imgur_logo_menu);
        }
        else {
            imageB.setImageResource(R.drawable.flickr_logo_menu);
        }

        imageB.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v) {
                if (G.getCurrentWebsite() == "imgur") {
                    imageB.setImageResource(R.drawable.flickr_logo);
                    G.setCurrentWebsite("flickr");
                }
                else {
                    imageB.setImageResource(R.drawable.imgur_logo);
                    G.setCurrentWebsite("imgur");
                }
            }
            });

                return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Global G = ((Global) getApplicationContext());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_browse) {
            G.setBrowseFragment(null);
            G.setBrowseFragment(new BrowseFragment());
            transaction.replace(R.id.fragment_container_content, G.getBrowseFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_gallery) {
            if (G.getCurrentWebsite() != G.getLastW()) {
                G.setGalleryFragment(new GalleryFragment());
                G.setLastW(G.getCurrentWebsite());
            }
            transaction.replace(R.id.fragment_container_content, G.getGalleryFragment(), "GalleryF");
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_settings) {
            transaction.replace(R.id.fragment_container_content, G.getSettingsFragment(), "SettingsF");
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_about) {
            transaction.replace(R.id.fragment_container_content, G.getAboutFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client.connect();
        //AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void onUserDone(User user) {
        Global G = ((Global) getApplicationContext());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        G.setFlickrUser(user);
        if (G.getFlickrUser() == null || G.getFlickrToken() == null) {
            Toast.makeText(this, "Couldn't Log into Flickr",
                    Toast.LENGTH_LONG).show();
        }
        else {
            //G.setFlickrUser(user);
            //G.setFlickrToken(token);
            G.setIsFlickrConnected(1);
        }

        transaction.replace(R.id.fragment_container_content, G.getSettingsFragment(), "SettingsF");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onAuthDone(OAuth oauth) {

        Global G = ((Global) getApplicationContext());

        User user = oauth.getUser();
        new GetFlickrUserIdTask(G, user, this).execute();
        OAuthToken token = oauth.getToken();
        G.setFlickrToken(token);
    }

    @Override
    public void onResume() {
        super.onResume();

        Global G = ((Global) getApplicationContext());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Log.d("MainActivity", "RESUMED ACTIVITY");
        Intent intent = getIntent();
        String scheme = intent.getScheme();

        if (CALLBACK_SCHEME.equals(scheme)) {
            Uri uri = intent.getData();
            String query = uri.getQuery();
            String[] data = query.split("&");
            if (data != null && data.length == 2) {
                String oauthToken = data[0].substring(data[0].indexOf("=") + 1);
                String oauthVerifier = data[1].substring(data[1].indexOf("=") + 1);

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                String requestTokenSecret = preferences.getString("SecretFlickr", "");
                if(!requestTokenSecret.equalsIgnoreCase(""))
                {
                    Log.d("Secret Retrieved", requestTokenSecret);
                    new GetOAuthFlickrTask(G, this, oauthToken, oauthVerifier, requestTokenSecret).execute();
                }
                else {
                    Log.d("ERROR in Retrieval", "couldnt get secret token from SharedPreferences");
                }
            }
        }
    }
}
