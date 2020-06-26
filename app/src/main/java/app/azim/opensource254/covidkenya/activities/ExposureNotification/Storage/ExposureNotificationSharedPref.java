package app.azim.opensource254.covidkenya.activities.ExposureNotification.Storage;

import android.content.Context;
import android.content.SharedPreferences;

public class ExposureNotificationSharedPref {
    SharedPreferences localStorage;
    SharedPreferences.Editor editor;

    public static final String ENABLE = "enable";

    public ExposureNotificationSharedPref(Context context) {
        localStorage = context.getSharedPreferences("exposurenotification", 0);
        editor = localStorage.edit();
    }

    public void setEnable(boolean enable){
        SharedPreferences.Editor editor = localStorage.edit();
        editor.putBoolean(ENABLE,enable);
        editor.commit();
    }

    public boolean isEnabled(){
        return localStorage.getBoolean(ENABLE,false);
    }

    public void clear(){
        SharedPreferences.Editor editor = localStorage.edit();
        editor.clear();
        editor.commit();
    }
}
