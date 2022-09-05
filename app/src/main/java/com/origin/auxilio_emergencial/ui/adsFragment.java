package com.origin.auxilio_emergencial.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.ads.mediationtestsuite.MediationTestSuite;
import com.origin.auxilio_emergencial.MainActivity;
import com.origin.auxilio_emergencial.R;
import com.origin.auxilio_emergencial.databinding.FragmentAdsBinding;
import com.origin.auxilio_emergencial.utils.AdMob;

public class adsFragment extends Fragment {
    public static AdMob ads;
    private static FragmentAdsBinding binding;
    private static String UNIT;


    public adsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UNIT =  getString(R.string.banner_id) ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAdsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        root.setBackgroundColor( getActivity().getResources().getColor( R.color.cinza ) );
        configureBanner();
        return root;
    }

    public void configureBanner(){
        ads = new AdMob(getActivity());
        ads.bannerAds(UNIT, binding.adViewF);
    }

    public static void destroy(){
        if(ads != null){
            ads.destroy( binding.adViewF );
            ViewGroup.LayoutParams params = binding.adViewF.getLayoutParams();
            params.height = 0;
            binding.adViewF.setLayoutParams(params);

        }
    }

    public static void call(){
        if(ads != null){
            ads.bannerAds(UNIT, binding.adViewF);
            ViewGroup.LayoutParams params = binding.adViewF.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            binding.adViewF.setLayoutParams(params);
        }
    }
}