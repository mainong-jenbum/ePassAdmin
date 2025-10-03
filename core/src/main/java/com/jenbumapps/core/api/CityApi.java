package com.jenbumapps.core.api;

import com.jenbumapps.core.model.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CityApi {

    // Create city
    @POST("/city/add")
    Call<Void> create(@Body City t);

    @GET("/city/all")
    Call<List<City>> fetchAll();

    @GET("/city/active")
    Call<List<City>> fetchActive();

    @GET("/city/in-active")
    Call<List<City>> fetchInActive();

    @GET("/city/id/{id}")
    Call<City> fetchById(@Path("id") int id);

    @GET("/city/applicant_contact/{phone}")
    Call<List<City>> fetchByApplicantContact(@Path("phone") long phone);

}
