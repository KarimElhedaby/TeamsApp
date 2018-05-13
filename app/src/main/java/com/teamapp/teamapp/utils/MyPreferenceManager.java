package com.teamapp.teamapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.teamapp.teamapp.user.model.User;

/**
 * Created by karim on 4/15/18.
 */

public class MyPreferenceManager {

    private String TAG = MyPreferenceManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "TeamSpace_Pref";

    // All Shared Preferences Keys
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_PASSWORD = "user_password";
    private static final String KEY_USER_GENDER = "user_gender";


    // Constructor
    public MyPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
    }


    public void storeUser(User user) {
        editor.putInt(KEY_USER_ID, user.getUser_id());
        editor.putString(KEY_USER_NAME, user.getName());
        editor.putString(KEY_USER_EMAIL, user.getEmail());
        editor.putString(KEY_USER_PASSWORD, user.getPassword());
        editor.putString(KEY_USER_GENDER, user.getGender());
        editor.commit();

        Log.e(TAG, "User is stored in shared preferences. " + user.getName() + ", " + user.getEmail());
    }

    public User getUser() {
        if (pref.getString(KEY_USER_EMAIL, null) != null) {
            String name, email, password, gender;
            int id ;
            id = pref.getInt(KEY_USER_ID,0);
            name = pref.getString(KEY_USER_NAME, null);
            email = pref.getString(KEY_USER_EMAIL, null);
            password = pref.getString(KEY_USER_PASSWORD, null);
            gender = pref.getString(KEY_USER_GENDER, null);

            User user = new User(id,email, name, password, gender);
            return user;
        }
        return null;
    }


    public void clear() {
        editor.clear();
        editor.commit();
    }
}