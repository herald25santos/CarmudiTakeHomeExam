package com.santos.herald.carmuditakehomeexam.data;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class DataEntity extends RealmObject {

    @SerializedName("data")
    @Expose
    public ProductDataEntity data;

    @PrimaryKey
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("images")
    @Expose
    public RealmList<ImagesEntity> images;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
