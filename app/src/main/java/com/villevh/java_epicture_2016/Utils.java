package com.villevh.java_epicture_2016;

/**
 * Created by villev_h on 03/03/2017.
 */

//Object in SharedPrefs In
/*
Gson gson = new Gson();
String json = gson.toJson(MyObject);
prefsEditor.putString("MyObject", json);
 */

//Object out Shared Prefs Out
/*
Gson gson = new Gson();
String json = mPrefs.getString("MyObject", "");
MyObject obj = gson.fromJson(json, MyObject.class);
*/

////////////////////////////////////////////////////////////////////////////////////

//Put In Shared Prefs
/*
SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
SharedPreferences.Editor editor = preferences.edit();
editor.putString("Name","Harneet");
editor.apply();
 */

//Put Out Shared Prefs
/*
SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
String name = preferences.getString("Name", "");
*/
public class Utils {
}
