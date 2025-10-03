package com.jenbumapps.core.utility.connect;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    // LIVE SERVER
    private static final String HOST = "43.225.53.40";

    // TEST SERVER
//    private static final String HOST = "192.168.225.50";

    private static final String PORT = "8086";
    private static final String BASE_URL = "http://" + HOST + ":" + PORT;
    private volatile static Retrofit retrofit;

    private NetworkService() {
    }

    public static Retrofit get() {
        Retrofit retrofitInstance = NetworkService.retrofit;
        if (retrofitInstance == null) {
            synchronized (NetworkService.class) {
                retrofitInstance = NetworkService.retrofit;
                if (retrofitInstance == null) {

                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                    OkHttpClient client = new OkHttpClient.Builder()
//                            .addInterceptor(new Interceptor() {
//                                @NotNull
//                                @Override
//                                public Response intercept(@NotNull Chain chain) throws IOException {
//                                    Request newRequest  = chain.request().newBuilder()
//                                            .addHeader("Authorization", "Bearer " + Constant.API_TOKEN)
//                                            .build();
//                                    return chain.proceed(newRequest);
//                                }
//                            })
                            .addInterceptor(interceptor)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .build();

                    retrofitInstance = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build();
                }
            }
        }
        retrofit = retrofitInstance;
        return retrofit;
    }

    public static String getHost() {
        return HOST;
    }

    public static String getServerPort() {
        return PORT;
    }
}
