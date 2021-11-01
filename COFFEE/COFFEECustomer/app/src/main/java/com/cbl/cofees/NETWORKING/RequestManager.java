package com.cbl.cofees.NETWORKING;

import android.content.Context;

import com.cbl.cofees.EVENTMANAGMENT.passed.EventLoadVendos;
import com.cbl.cofees.EVENTMANAGMENT.passed.EventLoginSuccess;
import com.cbl.cofees.EVENTMANAGMENT.passed.EventMenuRegisterd;
import com.cbl.cofees.EVENTMANAGMENT.passed.EventRegisterUser;
import com.cbl.cofees.EVENTMANAGMENT.passed.EventVendorSaved;
import com.cbl.cofees.SECUREPREFERANCE.SystemPreferances;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestManager implements RetrofitRequestImpl {
    RetrofitService service;
    String TAG = "SmartScore";
    Context mContext;


    public RequestManager(Context context) {
        service = ServiceGenerator.createService(RetrofitService.class, context);
        this.mContext = context;

    }

    public static RequestManager getInstantiate(Context context) {

        return new RequestManager(context);
    }

    @Override
    public void registerVendor(JsonObject vendorInfo) {
        Call<JsonObject> serviceCall01 = service.userSignIn(vendorInfo);
        serviceCall01.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.code() == 200) {
                    EventRegisterUser registerUserEvent = new EventRegisterUser();
                    registerUserEvent.setUser_id(response.body().get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString());
                    new SystemPreferances(mContext).SetUserInformation(response.body().get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString());
                    EventBus.getDefault().post(registerUserEvent);
                } else {
//                    EventBus.getDefault().post(new EventRegisterUser());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });


    }

    @Override
    public void registerVendorStepTwo(JsonObject vendorInfo) {
        Call<JsonObject> serviceCall02 = service.shopRegister(vendorInfo);
        serviceCall02.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.code() == 200) {

                    EventBus.getDefault().post(new EventVendorSaved());
                } else {
                    EventBus.getDefault().post(new EventVendorSaved());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });


    }


    @Override
    public void loginUser(JsonObject vendorInfo) {
        Call<JsonObject> serviceCall03 = service.userloginIn(vendorInfo);
        serviceCall03.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.code() == 200) {
                    JsonObject comingObject = response.body().getAsJsonArray("data").get(0).getAsJsonObject();
                    new SystemPreferances(mContext).SetUserInformation(comingObject.get("userid").getAsString());
                    new SystemPreferances(mContext).setShopInformation(comingObject.get("shop_name").getAsString(), comingObject.get("address_one").getAsString(), comingObject.get("address_two").getAsString(), comingObject.get("shopid").getAsString());

                    EventBus.getDefault().post(new EventLoginSuccess());
                } else {
                    EventBus.getDefault().post(new EventLoginSuccess());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });


    }


    @Override
    public void createMenue(JsonObject vendorInfo) {
        Call<JsonObject> serviceCall03 = service.createMenue(vendorInfo);
        serviceCall03.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.code() == 200) {
                    EventBus.getDefault().post(new EventMenuRegisterd());
                } else {
//                    EventBus.getDefault().post(new EventLoginSuccess());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });


    }



    @Override
    public void loadSops() {
        Call<JsonObject> serviceCall03 = service.loadShops();
        serviceCall03.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.code() == 200) {
                    EventLoadVendos loashops=  new EventLoadVendos();
                    ArrayList<JsonObject> sendinItems= new ArrayList<>();

                    for (JsonElement eliment:response.body().get("data").getAsJsonArray()
                         ) {
                        sendinItems.add(eliment.getAsJsonObject());

                    }
                    loashops.setSendinglist(sendinItems);
                    EventBus.getDefault().post(loashops);

                } else {
//                    EventBus.getDefault().post(new EventLoginSuccess());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });


    }

}
