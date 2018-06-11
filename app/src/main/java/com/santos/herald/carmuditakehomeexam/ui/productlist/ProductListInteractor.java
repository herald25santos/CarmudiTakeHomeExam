package com.santos.herald.carmuditakehomeexam.ui.productlist;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.santos.herald.carmuditakehomeexam.App;
import com.santos.herald.carmuditakehomeexam.data.DataEntity;
import com.santos.herald.carmuditakehomeexam.data.ImagesEntity;
import com.santos.herald.carmuditakehomeexam.data.MetaDataEntity;
import com.santos.herald.carmuditakehomeexam.data.ProductDataEntity;
import com.santos.herald.carmuditakehomeexam.data.ProductResponse;
import com.santos.herald.carmuditakehomeexam.data.constant.Constants;
import com.santos.herald.carmuditakehomeexam.di.components.ApplicationComponent;
import com.santos.herald.carmuditakehomeexam.network.ApiServices;
import com.santos.herald.carmuditakehomeexam.network.exception.DefaultErrorBundle;
import com.santos.herald.carmuditakehomeexam.ui.base.SchedulerProviderImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ProductListInteractor extends SchedulerProviderImpl {

    public static final String TAG = ProductListInteractor.class.getSimpleName();

    @Inject
    ApiServices mApiServices;

    @Inject
    Realm mRealm;

    @Inject
    Context context;

    @Inject
    public ProductListInteractor(Application application) {
        ApplicationComponent applicationComponent = ((App) application).getApplicationComponent();
        applicationComponent.inject(this);
    }

    public Disposable fetchProductList(int page, ProductListView.Action action) {
        return fetchProductList(page, null, action);
    }

    public Disposable fetchProductList(int page, String filter, ProductListView.Action action) {
        return (filter == null ? mApiServices.fetchProductList(page, Constants.PAGE_SIZE) : mApiServices.fetchProductListSort(page, Constants.PAGE_SIZE, filter))
                .subscribeOn(io())
                .observeOn(ui())
                .subscribe(productResponse -> {
                            saveProduct(productResponse);
                            action.onFetchProductList(productResponse);
                        },
                        e -> {
                            e.printStackTrace();
                            Log.e(TAG, "onError -> Goes here " + e.getMessage());
                            action.onError(new DefaultErrorBundle((Exception) e));
                        });

    }

    public RealmResults<ProductDataEntity> getLocalProductList(){
            return mRealm.where(ProductDataEntity.class).findAllAsync();
    }

    public RealmResults<ImagesEntity> getLocalProductListImages(String id){
        return mRealm.where(ImagesEntity.class).equalTo("productId", id).findAllAsync();
    }

    private void saveProduct(ProductResponse productResponse){
        mRealm.executeTransactionAsync(realm -> {
            for (DataEntity dataEntity: productResponse.metadata.results) {
                realm.insertOrUpdate(dataEntity.data);
                for (ImagesEntity imagesEntity: dataEntity.images){
                    imagesEntity.productId = dataEntity.data.id;
                    realm.insertOrUpdate(imagesEntity);
                }
            }
        });
    }
}
