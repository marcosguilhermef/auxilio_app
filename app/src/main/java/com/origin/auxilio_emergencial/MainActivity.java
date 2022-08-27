package com.origin.auxilio_emergencial;

import static com.facebook.ads.AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.origin.auxilio_emergencial.databinding.ActivityMainBinding;
import com.origin.auxilio_emergencial.ui.adsFragment;
import com.origin.auxilio_emergencial.utils.Analytics;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavHostFragment navHostFragment;
    private AppBarConfiguration appBarConfiguration;
    public Analytics ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        ans = new Analytics(this);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        appBarConfiguration = new AppBarConfiguration.Builder(getNavController().getGraph()).build();

        NavigationUI.setupActionBarWithNavController(this,getNavController(),appBarConfiguration);

        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.ads_fragment_container, adsFragment.class, null)
                .commit();

        AudienceNetworkInitializeHelper.initialize(this);

        AdSettings.setIntegrationErrorMode(INTEGRATION_ERROR_CRASH_DEBUG_MODE);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    private NavController getNavController() {
        return navHostFragment.getNavController();
    }

}