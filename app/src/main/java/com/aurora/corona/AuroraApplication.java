

package com.aurora.corona;

import android.app.Application;

import com.aurora.corona.util.Log;

import io.reactivex.plugins.RxJavaPlugins;

public class AuroraApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Global RX-Error handler, just simply logs, I make sure all errors are handled at origin.
        RxJavaPlugins.setErrorHandler(throwable -> {
            Log.e(throwable.getMessage());
            if (BuildConfig.DEBUG) {
                throwable.printStackTrace();
            }
        });
    }
}
