package com.santos.herald.carmuditakehomeexam.ui.productlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.santos.herald.carmuditakehomeexam.R;
import com.santos.herald.carmuditakehomeexam.data.DataEntity;
import com.santos.herald.carmuditakehomeexam.data.ProductDataEntity;
import com.santos.herald.carmuditakehomeexam.data.constant.Constants;
import com.santos.herald.carmuditakehomeexam.ui.base.BaseActivity;
import com.santos.herald.carmuditakehomeexam.ui.base.PresenterFactory;
import com.santos.herald.carmuditakehomeexam.utils.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;

public class ProductListActivity extends BaseActivity<ProductListPresenter, ProductListView.View> implements ProductListView.View, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = ProductListActivity.class.getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.activityMain)
    CoordinatorLayout activityMain;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.tvSort)
    TextView tvSort;

    @Inject
    CompositeDisposable mCompositeDisposable;

    @Inject
    ProductListAdapter productListAdapter;

    private int mCurrentPage = 1;
    private int visibleThreshold = 1;
    private boolean mEndOfList = false;
    private boolean isFilter = false;
    private boolean isClickFirstFilter = false;
    private boolean isLoadMore = false;
    private String filterStr;
    private LinearLayoutManager linearLayoutManager;
    public static final String BUNDLE_CURRENT_PAGE = "current_page";

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInjectDaggerDependency() {
        DaggerProductListComponent.builder()
                .applicationComponent(getApplicationComponent())
                .productListModule(new ProductListModule(this))
                .build().inject(this);
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initToolbar();
        init();
        if (savedInstanceState != null) {
            if (isPresenterAvailable())
                getPresenter().setmCurrentPage(savedInstanceState.getInt(BUNDLE_CURRENT_PAGE));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_CURRENT_PAGE, mCurrentPage);
        super.onSaveInstanceState(outState);
    }

    private void init() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeRefreshLayout.setOnRefreshListener(this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.addOnScrollListener(mOnScrollListener);
        recyclerView.setAdapter(productListAdapter);
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int visibleItemsCount = layoutManager.getChildCount();
            int totalItemsCount = layoutManager.getItemCount();
            int firstVisibleItemPos = layoutManager.findFirstVisibleItemPosition();

            if (swipeRefreshLayout != null) {
                if (NetworkUtils.isNetworkConnected(ProductListActivity.this)) {
                    if (!isLoadMore && !productListAdapter.ismWithFooter() && !swipeRefreshLayout.isRefreshing() && !mEndOfList) {
                        if ((totalItemsCount - visibleItemsCount) <= (firstVisibleItemPos + visibleThreshold)) {
                            mCurrentPage = mCurrentPage + 1;
                            if (isPresenterAvailable()) {
                                if (isFilter) {
                                    getPresenter().fetchProductList(mCurrentPage, filterStr);
                                } else {
                                    getPresenter().fetchProductList(mCurrentPage);
                                }
                            }
                            isLoadMore = true;
                        }
                    }
                } else {
                    noInternetConnection();
                }

            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPresenterAvailable()) getPresenter().destroy();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable.dispose();
        }
    }

    @NonNull
    @Override
    protected PresenterFactory<ProductListPresenter> createPresenterFactory() {
        return this::createPresenter;
    }

    @Override
    protected void onPresenterCreated(@NonNull ProductListPresenter presenter) {
        presenter.findLaunchMode();
    }

    @Override
    protected void onPresenterStateRestore(@NonNull ProductListPresenter presenter, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void onPresenterStateSave(@NonNull ProductListPresenter presenter, @NonNull Bundle outState) {

    }

    @Override
    protected void onPresenterDestroyed() {

    }

    private ProductListPresenter createPresenter() {
        return ProductListPresenter.newInstance();
    }

    @Override
    public void showProductList(List<DataEntity> dataEntityList) {
        if (isClickFirstFilter) {
            productListAdapter.getData().clear();
            productListAdapter.notifyDataSetChanged();
        }
        Log.d(TAG, "mCurrentPage: " + mCurrentPage);
        if (mCurrentPage > 1) {
            productListAdapter.getData().addAll(dataEntityList);
        } else {
            productListAdapter.setData(dataEntityList);
        }

        productListAdapter.notifyItemRangeInserted(recyclerView.getAdapter().getItemCount() + Constants.PAGE_SIZE, Constants.PAGE_SIZE);
        isLoadMore = false;
        isClickFirstFilter = false;
    }

    @Override
    public void showProductListLocal(List<DataEntity> dataEntityList) {
        productListAdapter.setData(dataEntityList);
        productListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyResult() {
        Snackbar.make(activityMain, "Empty Result", Snackbar.LENGTH_SHORT);
    }

    @Override
    public void showLoading() {
        if (swipeRefreshLayout != null) swipeRefreshLayout.setRefreshing(true);

    }

    @Override
    public void dismissLoading() {
        if (swipeRefreshLayout != null) swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showEndlessLoading() {
        if (productListAdapter != null) productListAdapter.setmWithFooter(true);
        if (productListAdapter != null)
            productListAdapter.notifyItemRangeChanged(0, recyclerView.getAdapter().getItemCount());
    }

    @Override
    public void dismissEndlessLoading() {
        if (productListAdapter != null) productListAdapter.setmWithFooter(false);
    }

    @Override
    public void stopPagination() {

    }

    @Override
    public void noInternetConnection() {
        Snackbar.make(activityMain, "No", Snackbar.LENGTH_LONG);
    }

    @Override
    public void showError(String message, int mCurrentPage) {
        Log.e(TAG, "showError: " + message);
        Log.d(TAG, "mCurrentPage: " + mCurrentPage);
    }

    @Override
    public void onLaunchMode() {
        if (isPresenterAvailable()) {
            getPresenter().fetchProductList(mCurrentPage);
        }
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 1;
        if (isPresenterAvailable()) {
            getPresenter().fetchProductList(mCurrentPage);
        }
    }

    @OnClick(R.id.tvSort)
    public void ClickSort() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort By");
        builder.setItems(R.array.sort_list_array, (dialog, which) -> {
            isFilter = true;
            isClickFirstFilter = true;
            mCurrentPage = 1;
            String[] value = getResources().getStringArray(R.array.sort_list_array);
            filterStr = value[0].toLowerCase();
            tvSort.setText(String.format(getString(R.string.string_value_sort_by), value[which]));
            if (isPresenterAvailable()) {
                getPresenter().fetchProductList(mCurrentPage, filterStr);
            }
        });
        builder.show();
    }
}
