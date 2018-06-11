package com.santos.herald.carmuditakehomeexam.di.modules;

import com.santos.herald.carmuditakehomeexam.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;


@Module
public class RealmModule {

    private App application;

    public RealmModule(App app) {
        this.application = app;
    }

    @Singleton
    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }
}
