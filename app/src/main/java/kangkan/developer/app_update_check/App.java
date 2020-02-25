package kangkan.developer.app_update_check;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        final FirebaseRemoteConfig remoteConfig=FirebaseRemoteConfig.getInstance();

        Map<String,Object> defaultValue=new HashMap<>();
        defaultValue.put(update_helper.KEY_UPDATE_ENABLE,false);
        defaultValue.put(update_helper.KEY_UPDATE_VERSION,"1.0");
        defaultValue.put(update_helper.KEY_UPDATE_URL,"TT");

        remoteConfig.setDefaults(defaultValue);
        remoteConfig.fetch(5)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            remoteConfig.activateFetched();
                        }
                    }
                });

    }
}
