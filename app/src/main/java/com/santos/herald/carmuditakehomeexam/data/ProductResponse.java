package com.santos.herald.carmuditakehomeexam.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductResponse {

    @SerializedName("success")
    @Expose
    public Boolean success;

    @SerializedName("metadata")
    @Expose
    public MetaDataEntity metadata;
}
