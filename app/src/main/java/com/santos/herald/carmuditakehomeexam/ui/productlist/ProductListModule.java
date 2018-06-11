package com.santos.herald.carmuditakehomeexam.ui.productlist;

import com.santos.herald.carmuditakehomeexam.App;
import com.santos.herald.carmuditakehomeexam.di.PerActivity;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ProductListModule {

    private ProductListActivity mView;

    public ProductListModule(ProductListActivity view) {
        mView = view;
    }

    @PerActivity
    @Provides
    ProductListActivity provideView() {
        return mView;
    }

    @PerActivity
    @Provides
    ProductListInteractor provideHomeInteractor() {
        return new ProductListInteractor(App.getApplication());
    }

    @PerActivity
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @PerActivity
    @Provides
    ProductListAdapter provideProductListAdapter(){
        return new ProductListAdapter(mView, new ArrayList<>(), false, false);
    }
}