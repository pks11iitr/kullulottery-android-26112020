package com.kuil.KuilLottery.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessonManager {

    private static SessonManager pref;
    private SharedPreferences sharedPreference;
    private SharedPreferences.Editor editor;
    public static final String NAME = "MY_PREFERENCES";
    public static final String Token = "token";


    public SessonManager(Context ctx) {
        sharedPreference = ctx.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        editor = sharedPreference.edit();
    }


    public static SessonManager getInstance(Context ctx) {
        if (pref == null) {
            pref = new SessonManager(ctx);
        }
        return pref;
    }


    public void setToken(String token) {

     //   Log.d("sssss", token);
        editor.putString(Token, token);
        editor.commit();
    }

    public String getToken() {
        return sharedPreference.getString(Token, "");
    }
}
