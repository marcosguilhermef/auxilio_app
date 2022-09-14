package com.origin.auxilio_emergencial.utils;

import android.util.Log;

public class Ads {
    public final String bannerID;
    public Ads(String firstName) {
        Log.i("seted: ", firstName);
        this.bannerID = firstName;
    }

    public String getBannerID(){
        return bannerID;
    }

}
