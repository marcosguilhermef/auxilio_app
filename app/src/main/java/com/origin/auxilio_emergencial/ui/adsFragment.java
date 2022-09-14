package com.origin.auxilio_emergencial.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.util.Log;
import com.origin.auxilio_emergencial.R;
import com.origin.auxilio_emergencial.databinding.FragmentAdsBinding;
import com.origin.auxilio_emergencial.utils.AdMob;
import com.origin.auxilio_emergencial.utils.Ads;
import com.origin.auxilio_emergencial.utils.RemoteConfig;

public class adsFragment extends Fragment {
    public static AdMob ads;
    private static FragmentAdsBinding binding;
    private static String UNIT;
    Ads adsmodel;

    public adsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UNIT =  RemoteConfig.getString("banner_admob") ;
        adsmodel = new Ads( UNIT );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAdsBinding.inflate(inflater, container, false);
        binding.setAds( adsmodel );
        View root = binding.getRoot();
        root.setBackgroundColor( getActivity().getResources().getColor( R.color.cinza ) );
        configureBanner();
        return root;
    }

    public void configureBanner(){
        ads = new AdMob(getActivity());
        ads.bannerAds(adsmodel.getBannerID(), binding.frameLayout);
    }
}