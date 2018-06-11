package com.santos.herald.carmuditakehomeexam.ui.productlist;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.santos.herald.carmuditakehomeexam.R;
import com.santos.herald.carmuditakehomeexam.data.DataEntity;
import com.santos.herald.carmuditakehomeexam.data.ImagesEntity;
import com.santos.herald.carmuditakehomeexam.data.ProductDataEntity;
import com.santos.herald.carmuditakehomeexam.ui.base.BaseAdapter;
import com.santos.herald.carmuditakehomeexam.utils.listener.onRecyclerViewListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends BaseAdapter<DataEntity> {

    public final String TAG = ProductListAdapter.this.getClass().getSimpleName();
    private Context mContext;
    private onRecyclerViewListener mOnClickListener;
    private boolean isLoadMore;

    public ProductListAdapter(Context context, List<DataEntity> data, boolean withHeader, boolean withFooter) {
        super(data, withHeader, withFooter);
        //setHasStableIds(true);
        this.mContext = context;
    }

    @Override
    protected RecyclerView.ViewHolder getItemView(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    protected RecyclerView.ViewHolder getHeaderView(LayoutInflater inflater, ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder getFooterView(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.loader_item_layout, parent, false);
        return new FooterViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            //Glide.clear(itemViewHolder.imgProduct);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            prepareItemViewHolder(itemViewHolder, position);
        }
//        else if (holder instanceof FooterViewHolder) {
//            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
//            prepareFooterViewHolder(footerViewHolder, position);
//        }
    }

    private void prepareFooterViewHolder(FooterViewHolder footerViewHolder, int position) {

    }

    private void prepareItemViewHolder(ItemViewHolder itemViewHolder, int position) {
        DataEntity dataEntity = getData().get(position);
        ProductDataEntity productDataEntity = dataEntity.data;
        itemViewHolder.tvProductName.setText(productDataEntity.name);
        itemViewHolder.tvPrice.setText(String.format("Price: %s", productDataEntity.price));
        itemViewHolder.tvBrand.setText(String.format("Brand: %s", productDataEntity.brand));
        Glide.with(mContext)
                .load(dataEntity.images.get(0).url)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemViewHolder.imgProduct);
    }

    public void setOnClickListener(onRecyclerViewListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }


    public void showLoadMore(boolean isLoadMore) {
        this.isLoadMore = isLoadMore;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.textview)
        public TextView textView;

        @BindView(R.id.progressbar)
        public ProgressBar progressbar;

        @BindView(R.id.frameLoadMore)
        public FrameLayout frameLoadMore;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvProductName)
        public TextView tvProductName;

        @BindView(R.id.tvPrice)
        public TextView tvPrice;

        @BindView(R.id.tvBrand)
        public TextView tvBrand;

        @BindView(R.id.imgProduct)
        public AppCompatImageView imgProduct;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onItemClick(v, getAdapterPosition());
        }
    }

}