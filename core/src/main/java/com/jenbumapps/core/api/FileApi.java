package com.jenbumapps.core.api;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface FileApi {

    @POST("/uploadfile")
    @Multipart
    Call<String> saveImage(@Part MultipartBody.Part file);
}
