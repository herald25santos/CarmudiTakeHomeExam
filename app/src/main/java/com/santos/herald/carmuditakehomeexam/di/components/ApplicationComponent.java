package com.santos.herald.carmuditakehomeexam.di.components;

import com.santos.herald.carmuditakehomeexam.di.modules.ApplicationModule;
import com.santos.herald.carmuditakehomeexam.di.modules.NetModule;
import com.santos.herald.carmuditakehomeexam.di.modules.RealmModule;
import com.santos.herald.carmuditakehomeexam.ui.productlist.ProductListInteractor;
import com.santos.herald.carmuditakehomeexam.ui.productlist.ProductListPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class, RealmModule.class})
public interface ApplicationComponent {

    void inject(ProductListPresenter presenter);
    void inject(ProductListInteractor interactor);
//
//    void inject(WeatherDetailPresenter weatherDetailPresenter);
//    void inject(ProductListInteractor weatherDetailInteractor);

}