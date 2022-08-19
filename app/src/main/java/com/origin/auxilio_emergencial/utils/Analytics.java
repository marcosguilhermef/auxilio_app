package com.origin.auxilio_emergencial.utils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Analytics {
    public static FirebaseAnalytics mFirebaseAnalytics;
    public static final String REPORT = "Report";
    public static final String ADD_GROUP = "add_group";

    public Analytics(Activity activity){
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity);
    }
    public static void ScreenNameSend(String title,String classe){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, title);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, classe);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }
    public static void share(String _id, String title){
        Bundle bundle = new Bundle();
        bundle.putString("id",_id);
        bundle.putString("title",title);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle);
    }

    public static void report(String _id, String title){
        Bundle bundle = new Bundle();
        bundle.putString("id",_id);
        bundle.putString("title",title);
        Log.i( "REPORTED", _id );
        mFirebaseAnalytics.logEvent(REPORT, bundle);
    }

    public static void addGroup(Bundle bundle){
        mFirebaseAnalytics.logEvent(ADD_GROUP, bundle);
    }

    public static void purchasedItem(Bundle bundle){
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.PURCHASE, bundle);
    }

    public static void join(String _id, String title){
        Bundle bundle = new Bundle();
        bundle.putString("id",_id);
        bundle.putString("title",title);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.JOIN_GROUP, bundle);
    }
    public static void success(String _id, String title){
        Bundle bundle = new Bundle();
        bundle.putString("id",_id);
        bundle.putString("title",title);
        mFirebaseAnalytics.logEvent("SUCCESS_GROUP_ADD", bundle);
    }
    public static void error(String erro){
        Bundle bundle = new Bundle();
        bundle.putString("erro",erro);
        mFirebaseAnalytics.logEvent("ERROR_GROUP_ADD", bundle);
    }
}
