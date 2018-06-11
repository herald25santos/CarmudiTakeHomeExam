package com.santos.herald.carmuditakehomeexam.ui.productlist;

import com.santos.herald.carmuditakehomeexam.data.DataEntity;
import com.santos.herald.carmuditakehomeexam.data.MetaDataEntity;
import com.santos.herald.carmuditakehomeexam.data.ProductDataEntity;
import com.santos.herald.carmuditakehomeexam.data.ProductResponse;
import com.santos.herald.carmuditakehomeexam.network.exception.ErrorBundle;
import com.santos.herald.carmuditakehomeexam.ui.base.BaseView;

import java.util.List;

public interface ProductListView {

    public interface View extends BaseView {

        void showProductList(List<DataEntity> dataEntityList);

        void showProductListLocal(List<DataEntity> dataEntityList);

        void showEmptyResult();

        void showLoading();

        void dismissLoading();

        void showEndlessLoading();

        void dismissEndlessLoading();

        void stopPagination();

        void noInternetConnection();

        void showError(String message, int mCurrentPage);

    }

    public interface Action {

        void onFetchProductList(ProductResponse productResponse);

        void onError(ErrorBundle errorBundle);

    }

}
