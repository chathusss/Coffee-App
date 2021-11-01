package com.cbl.cofees.NETWORKING;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("users/create")
    Call<JsonObject> userSignIn(@Body JsonObject auth_info);
    @POST("shops/create")
    Call<JsonObject> shopRegister(@Body JsonObject auth_info);
    @POST("users/login")
    Call<JsonObject> userloginIn(@Body JsonObject auth_info);

    @POST("Menu/create")
    Call<JsonObject> createMenue(@Body JsonObject auth_info);



}
