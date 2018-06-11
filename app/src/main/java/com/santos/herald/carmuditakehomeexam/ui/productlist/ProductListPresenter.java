package com.santos.herald.carmuditakehomeexam.ui.productlist;

import android.content.Context;
import android.support.annotation.NonNull;

import com.santos.herald.carmuditakehomeexam.App;
import com.santos.herald.carmuditakehomeexam.data.DataEntity;
import com.santos.herald.carmuditakehomeexam.data.ImagesEntity;
import com.santos.herald.carmuditakehomeexam.data.ProductDataEntity;
import com.santos.herald.carmuditakehomeexam.data.ProductResponse;
import com.santos.herald.carmuditakehomeexam.di.components.ApplicationComponent;
import com.santos.herald.carmuditakehomeexam.network.exception.ErrorBundle;
import com.santos.herald.carmuditakehomeexam.ui.base.BasePresenter;
import com.santos.herald.carmuditakehomeexam.ui.base.Presenter;
import com.santos.herald.carmuditakehomeexam.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.realm.RealmList;

public class ProductListPresenter extends BasePresenter<ProductListView.View> implements Presenter, ProductListView.Action {

    @Inject
    ProductListInteractor productListInteractor;

    @Inject
    CompositeDisposable mCompositeDisposable;

    private Context context;
    private int mCurrentPage;

    public static ProductListPresenter newInstance() {
        return new ProductListPresenter();
    }

    @Override
    protected void onViewAttached(Context context, @NonNull ProductListView.View view) {
        ApplicationComponent applicationComponent = ((App) App.getApplication()).getApplicationComponent();
        applicationComponent.inject(this);
        this.context = context;
    }

    public void fetchProductList(int page) {
        fetchProductList(page, null);
    }

    public void fetchProductList(int page, String filter) {
        if (isViewAttached()) {
            if (NetworkUtils.isNetworkConnected(context)) {
                mCurrentPage = page;
                if (mCurrentPage > 1) {
                    getView().showEndlessLoading();
                } else {
                    getView().showLoading();
                }
                if (filter == null) {
                    Disposable disposable = productListInteractor.fetchProductList(mCurrentPage, this);
                    mCompositeDisposable.add(disposable);
                } else {
                    Disposable disposable = productListInteractor.fetchProductList(mCurrentPage, filter, this);
                    mCompositeDisposable.add(disposable);
                }
            } else {
                List<DataEntity> dataEntityList = new ArrayList<>();
                for (ProductDataEntity productDataEntity : productListInteractor.getLocalProductList()) {
                    DataEntity dataEntity = new DataEntity();
                    dataEntity.data = productDataEntity;
                    dataEntity.id = productDataEntity.id;
                    RealmList<ImagesEntity> dataEntityRealmList = new RealmList<>();
                    dataEntityRealmList.addAll(productListInteractor.getLocalProductListImages(productDataEntity.id));
                    dataEntity.images = dataEntityRealmList;
                    dataEntityList.add(dataEntity);
                }
                getView().dismissLoading();
                getView().showProductListLocal(dataEntityList);
            }
        }
    }

    public void setmCurrentPage(int mCurrentPage) {
        this.mCurrentPage = mCurrentPage;
    }

    public void findLaunchMode() {
        if (isViewAttached()) {
            getView().onLaunchMode();
        }
    }

    @Override
    protected void onViewDetached() {
        productListInteractor = null;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void onFetchProductList(ProductResponse productResponse) {
        if (isViewAttached()) {
            if (productResponse.metadata.results.size() > 0) {
                getView().showProductList(productResponse.metadata.results);
                if (productResponse.metadata.results.size() == 0) {
                    getView().stopPagination();
                }
            } else {
                getView().dismissLoading();
                getView().dismissEndlessLoading();
                getView().showEmptyResult();
            }
            if (mCurrentPage > 1) {
                getView().dismissEndlessLoading();
            } else {
                getView().dismissLoading();
            }
        }
    }

    @Override
    public void onError(ErrorBundle errorBundle) {
        if (isViewAttached()) {
            if (mCurrentPage > 1) {
                getView().dismissEndlessLoading();
            } else {
                getView().dismissLoading();
            }
            if (mCurrentPage > 0) {
                mCurrentPage--;
            }
            getView().showError(errorBundle.getErrorMessage(), mCurrentPage);
        }
    }
}
