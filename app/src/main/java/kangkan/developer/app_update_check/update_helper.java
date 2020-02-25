package kangkan.developer.app_update_check;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class update_helper {

    public  static String KEY_UPDATE_ENABLE="isUpdate";
    public  static String KEY_UPDATE_VERSION="version";
    public  static String KEY_UPDATE_URL="update_url";

    public interface onUpdatteCheckListener{
        void onUpdatteCheckListener(String urlApp);
    }


    public static Builder with(Context context)
    {
        return new Builder(context);
    }
    private onUpdatteCheckListener onUpdatteCheckListener;
    private  Context context;

    public update_helper(update_helper.onUpdatteCheckListener onUpdatteCheckListener, Context context) {
        this.onUpdatteCheckListener = onUpdatteCheckListener;
        this.context = context;
    }

    public void check()
    {
        FirebaseRemoteConfig remoteConfig=FirebaseRemoteConfig.getInstance();
        if (remoteConfig.getBoolean(KEY_UPDATE_ENABLE))
        {
            String currentVersion=remoteConfig.getString(KEY_UPDATE_VERSION);
            String appVersion=getAppVersion(context);
            String updateUrl=remoteConfig.getString(KEY_UPDATE_URL);

            if (!TextUtils.equals(currentVersion,appVersion)&& onUpdatteCheckListener!=null)
            {onUpdatteCheckListener.onUpdatteCheckListener(updateUrl);}
        }
    }

    private String getAppVersion(Context context) {

        String result="";
        try {
            result =context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionName;
            result=result.replaceAll("a-zA-Z|-","");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static class Builder{
        private Context context;
        private onUpdatteCheckListener onUpdatteCheckListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder onUpdateCheck(onUpdatteCheckListener onUpdatteCheckListener)
        {
            this.onUpdatteCheckListener=onUpdatteCheckListener;
            return this;
        }

        public update_helper build()
        {
            return new update_helper(onUpdatteCheckListener, context);
        }
        public update_helper check()
        {
            update_helper update_helper=build();
            update_helper.check();
            return update_helper;

        }

    }
}
