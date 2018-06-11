package com.santos.herald.carmuditakehomeexam.network;


import com.santos.herald.carmuditakehomeexam.data.MetaDataEntity;
import com.santos.herald.carmuditakehomeexam.data.ProductResponse;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {


//    //User Authentication
//    @FormUrlEncoded
//    @POST("oauth/token")
//    Observable<OAuthModel> requestAuth(
//            @Field("client_id") Integer client_id,
//            @Field("client_secret") String client_secret,
//            @Field("grant_type") String grant_type,
//            @Field("username") String username,
//            @Field("password") String password
//    );

    @GET("api/cars/page:{page}/maxitems:{num_items_per_page}/")
    Flowable<ProductResponse> fetchProductList(
            @Path("page") int page,
            @Path("num_items_per_page") int maxitems
    );

    @GET("api/cars/page:{page}/maxitems:{num_items_per_page}/sort:{sort}/")
    Flowable<ProductResponse> fetchProductListSort(
            @Path("page") int page,
            @Path("num_items_per_page") int maxitems,
            @Path("sort") String sort
    );
}