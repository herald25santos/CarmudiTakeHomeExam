package com.santos.herald.carmuditakehomeexam;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.bumptech.glide.Glide;
import com.facebook.stetho.Stetho;
import com.santos.herald.carmuditakehomeexam.di.components.ApplicationComponent;
import com.santos.herald.carmuditakehomeexam.di.components.DaggerApplicationComponent;
import com.santos.herald.carmuditakehomeexam.di.modules.ApplicationModule;
import com.santos.herald.carmuditakehomeexam.di.modules.NetModule;
import com.santos.herald.carmuditakehomeexam.di.modules.RealmModule;
import com.squareup.leakcanary.LeakCanary;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Application app;
    private ApplicationComponent mApplicationComponent;

    public static Application getApplication() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initializeApplicationComponent();
        initRealm();
        if (BuildConfig.DEBUG) {
            initLeakDetection();
            initStetho();
        }
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build()).build());
    }


    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .netModule(new NetModule(BuildConfig.HOST))
                .realmModule(new RealmModule(this))
                .build();
    }

    private void initRealm() {
        Realm.init(this);
        //byte[] key = new byte[64];
        //new SecureRandom().nextBytes(key);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                //.encryptionKey(key)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }


    private void initLeakDetection() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Glide.get(this).clearMemory();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}