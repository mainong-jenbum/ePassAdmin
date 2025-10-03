package com.jenbumapps.core.api;

import com.jenbumapps.core.model.EPass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EPassApi {
    // Create e-pass
    @POST("/e-pass/add")
    Call<Void> create(@Body EPass t);

    @GET("/e-pass/all")
    Call<List<EPass>> fetchAll();

    @GET("/e-pass/new-request/{city_id}")
    Call<List<EPass>> fetchNewRequests(@Path("city_id") int cityId);

    @GET("/e-pass/approved-request/{city_id}")
    Call<List<EPass>> fetchApprovedRequests(@Path("city_id") int cityId);

    @GET("/e-pass/id/{id}")
    Call<EPass> fetchById(@Path("id") int id);

    @GET("/e-pass/applicant_contact/{phone}")
    Call<List<EPass>> fetchByApplicantContact(@Path("phone") long phone);

    @PUT("/e-pass/update-qr/{id}")
    Call<String> updateQRCode(@Path("id") int id, @Body String qrUrl);

    @PUT("/e-pass/approve/{id}")
    Call<String> approve(@Path("id") int id);

    @PUT("/e-pass/reject/{id}")
    Call<String> reject(@Path("id") int id, @Body String reason);

    @DELETE("/e-pass/delete/{id}")
    Call<Void> delete(@Path("id") int id);

    @DELETE("/e-pass/delete-doc/{id}")
    Call<Void> deleteDoc(@Path("id") int docId);
}