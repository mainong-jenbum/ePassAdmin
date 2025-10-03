package com.jenbumapps.core.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GlobalParamApi {

    @GET("/global_params/add/{meta_id}/{meta_value/{comment}")
    Call<Void> create(@Path("meta_id") String metaId, @Path("meta_value") String metaValue, @Path("comment") String comment);

    @GET("/global_params/all")
    Call<Map<String, String>> fetchAll();

    @PUT("/global_params/update/{meta_id}/{meta_value}/{comment}")
    Call<Void> update(@Path("meta_id") String metaId, @Path("meta_value") String metaValue, @Path("comment") String comment);

}