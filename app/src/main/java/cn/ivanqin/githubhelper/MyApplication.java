package cn.ivanqin.githubhelper;

import android.app.Application;

import com.facebook.soloader.SoLoader;

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
        instance = this;
    }

    public static MyApplication getInstance(){
        return instance;
    }
}
