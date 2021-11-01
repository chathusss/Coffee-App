package com.cbl.cofees.NETWORKING;

import com.google.gson.JsonObject;

public interface RetrofitRequestImpl {

    void registerVendor(JsonObject vendorInfo);

    void registerVendorStepTwo(JsonObject vendorInfo);

    void loginUser(JsonObject vendorInfo);

    void createMenue(JsonObject vendorInfo);


    void loadSops();
}
