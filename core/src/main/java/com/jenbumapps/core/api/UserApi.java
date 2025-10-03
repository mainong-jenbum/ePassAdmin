package com.jenbumapps.core.api;

import com.jenbumapps.core.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApi {

    @GET("/user/id/{id}")
    Call<User> fetchById(@Path("id") int id);

    @PUT("/user/login/{phone}")
    Call<User> login(@Path("phone") long phone, @Body String password);

    @GET("/user/phone/{phone}")
    Call<User> fetchByPhone(@Path("phone") long phone);

    @PUT("/user/update-password/{user_id}")
    Call<User> updatePassword(@Path("user_id") int id, @Body String password);

}
