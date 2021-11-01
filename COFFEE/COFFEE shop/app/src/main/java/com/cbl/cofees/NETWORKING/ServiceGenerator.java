package com.cbl.cofees.NETWORKING;

import android.content.Context;

import com.cbl.cofees.SECUREPREFERANCE.SystemPreferances;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .followRedirects(false)
            .followSslRedirects(false)
            .retryOnConnectionFailure(false)
            .pingInterval(1, TimeUnit.MINUTES)
            .cache(null);


    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

//    private static Retrofit.Builder builder =
//            new Retrofit.Builder()
//                    .baseUrl(new SystemPreferances().getServerURL())
//                    .addConverterFactory(GsonConverterFactory.create());


    public static <S> S createService(Class<S> serviceClass, Context contextA) {

        String tempUrl = "http://54.169.76.6/Cofeesex/index.php/api/";
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(tempUrl)
//                        .baseUrl(new SystemPreferances(contextA).getServerURL())
                        .addConverterFactory(GsonConverterFactory.create());
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        Retrofit retrofit = null;
        if (retrofit == null) {
            retrofit = builder.client(httpClient.build())
                    .build();
//            retrofit = builder
//                    .build();

        }
        return retrofit.create(serviceClass);

    }
}
