package com.santos.herald.carmuditakehomeexam.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ImagesEntity extends RealmObject {

    public String productId;

    @SerializedName("url")
    @Expose
    public String url;

}
