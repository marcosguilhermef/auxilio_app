package com.origin.auxilio_emergencial.utils;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.origin.auxilio_emergencial.R;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.Executor;

public class RemoteConfig {
    public static FirebaseRemoteConfig mFirebaseRemoteConfig;
    public final int TIME = 60;
    
    @RequiresApi(api = Build.VERSION_CODES.P)
    public RemoteConfig(@NotNull Context context){
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(TIME)
                .build();

        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults );

        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener( context.getMainExecutor(), new OnCompleteListener<Boolean>() {

                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()) {
                            boolean updated = task.getResult();
                            Log.d("REMOTE_CONGIF", "Config params updated: " + RemoteConfig.getString("banner_admob"));


                        }
                    }
                });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public RemoteConfig(@NotNull Context context, Executor thead){
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(TIME)
                .build();

        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener( thead, new OnCompleteListener<Boolean>() {

                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()) {
                            boolean updated = task.getResult();
                            Log.d("REMOTE_CONGIF", "Config params updated: " + RemoteConfig.getString("banner_admob"));
                        }
                    }
                });

    }


    public static FirebaseRemoteConfig getFirebaseRemoteConfig(){
        return mFirebaseRemoteConfig;
    }

    public static String getString(@NotNull String key){

        String object = mFirebaseRemoteConfig.getString( key );


        if(object.isEmpty()){
            Log.d("getString", "Config params updated: " + object);

            return "Nothing value returned!";
        }
        Log.d("getString", "Config params updated: " + object);

        return object;

    }


}
