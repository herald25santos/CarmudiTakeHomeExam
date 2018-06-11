package com.santos.herald.carmuditakehomeexam.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;

public class MetaDataEntity {

    @SerializedName("product_count")
    @Expose
    public int productCount;

    @SerializedName("results")
    @Expose
    public List<DataEntity> results;

}
