package com.jenbumapps.core.memory;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.jenbumapps.core.model.User;


public class SharedPrefManager {
    //The constants
    private static final String TAG = SharedPrefManager.class.getSimpleName();
    private static final String EPASSBORDUMSA_PREF = "EPASSBORDUMSA_PREF";
    private static final String KEY_USER_PHONE = "KEY_USER_PHONE";
    private static final String KEY_IS_LOGGED_IN = "KEY_IS_LOGGED_IN";
    private static final String KEY_APP_VERSION = "KEY_APP_VERSION";
    private static final String KEY_CITY_ID = "KEY_CITY_ID";

    private static SharedPrefManager mInstance;
    private Context context;

    private SharedPrefManager(Context context) {
        this.context = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will store the user data in shared preferences
    public void saveUserPref(User user, boolean loggedIn) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(EPASSBORDUMSA_PREF
                , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(KEY_USER_PHONE, user.getPhone());
        editor.putBoolean(KEY_IS_LOGGED_IN, loggedIn);
        editor.apply();
        Log.i(TAG + "saveUserPref", "User data saved in SharedPreference");
    }

    // If the supplier uncheck Keep me logged in checkbox
    //this method will remove the user data in shared preferences
    public void removeUserPref() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(EPASSBORDUMSA_PREF
                , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Log.i(TAG + "removeUserPref", "User data removed from SharedPreference");
    }
//
//    //this method will check whether user is already logged in or not
    public boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(EPASSBORDUMSA_PREF
                , Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }
//
//    //this method will give the logged in user's phone
    public long getUserPhone() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(EPASSBORDUMSA_PREF
                , Context.MODE_PRIVATE);
        return sharedPreferences.getLong(KEY_USER_PHONE, 0);
    }
//
//    public String getAppVersion() {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(EPASSBORDUMSA_PREF
//                , Context.MODE_PRIVATE);
//        return sharedPreferences.getString(KEY_APP_VERSION, "v1.0.0");
//    }
//
//    public void saveAppVersion(String version) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(EPASSBORDUMSA_PREF
//                , Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(KEY_APP_VERSION, version);
//        editor.apply();
//        Log.i(TAG + "saveAppVersion", "App version saved SharedPreference");
//    }
//
//    public int getCurrentCity() {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(EPASSBORDUMSA_PREF
//                , Context.MODE_PRIVATE);
//        return sharedPreferences.getInt(KEY_CITY_ID, 0);
//    }
//
//    public void saveCity(City city) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(EPASSBORDUMSA_PREF
//                , Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt(KEY_CITY_ID, city.getId());
//        editor.apply();
//        Log.i(TAG + "saveCity", "City Id "+city.getId()+" saved in SharedPreference");
//    }
}
