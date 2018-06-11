package com.santos.herald.carmuditakehomeexam.ui.productlist;

import com.santos.herald.carmuditakehomeexam.di.PerActivity;
import com.santos.herald.carmuditakehomeexam.di.components.ApplicationComponent;

import dagger.Component;

@PerActivity
@Component(modules = ProductListModule.class, dependencies = ApplicationComponent.class)
public interface ProductListComponent {

    void inject(ProductListActivity activity);

}
