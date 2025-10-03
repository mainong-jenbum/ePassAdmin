package com.jenbumapps.core.api;

import com.jenbumapps.core.model.EPassTerm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EPassTermApi {

    @GET("/e-pass-term/all")
    Call<List<EPassTerm>> fetchAll();
    
    @GET("/e-pass-term/id/{id}")
    Call<EPassTerm> fetchById(@Path("id") int id);

    @DELETE("/e-pass-term/delete/{id}")
    Call<Void> delete(@Path("id") int couponId);
}