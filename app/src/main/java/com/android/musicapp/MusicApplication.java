package com.android.musicapp;

import android.app.Application;
import android.os.StrictMode;

public class MusicApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectActivityLeaks()
                .detectLeakedClosableObjects()
                .detectLeakedSqlLiteObjects()
                .detectLeakedRegistrationObjects()
                .build());
    }
}
