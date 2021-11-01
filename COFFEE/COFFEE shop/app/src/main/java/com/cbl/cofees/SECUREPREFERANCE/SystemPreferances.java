package com.cbl.cofees.SECUREPREFERANCE;

import android.content.Context;

import java.util.HashMap;


public class SystemPreferances {

    private SecurePreferences mSecurePrefs;
    private static Context mcontext;

    private static final String KEY_IS_LOGIN = "ISLOGGEDIN";
    private static final String SRVER_URL = "SERVERURL";

    public SystemPreferances(Context context) {
        mcontext = context;
        mSecurePrefs = new SecurePreferences(mcontext);
    }
    public String getServerURL(){
       return mSecurePrefs.getString(SRVER_URL, null);
    }

    public void setServerURL(String serverURL) {

        mSecurePrefs.edit().putString(SRVER_URL, serverURL).commit();

    }


    /*User related PREF*/

    private static String USER_PROFILE_ID = "profilr_id";
    private static String SHOP_NAME = "profilr_id";
    private static String SHOP_ADRESS = "shop_address";
    private static String SHOP_ID="shop_id";

    public void SetUserInformation(String user_id) {

        mSecurePrefs.edit().putBoolean(KEY_IS_LOGIN, true).commit();
        mSecurePrefs.edit().putString(USER_PROFILE_ID, user_id).commit();

    }

    public void setShopInformation(String shopname, String addresss01, String address2,String id) {

        mSecurePrefs.edit().putString(SHOP_NAME, shopname).commit();
        mSecurePrefs.edit().putString(SHOP_ADRESS, address2 + "," + addresss01).commit();
        mSecurePrefs.edit().putString(SHOP_ID, id).commit();
    }


    public HashMap<String, String> getAppInformations() {
        HashMap<String, String> shopInfo = new HashMap<String, String>();
        shopInfo.put(SHOP_NAME, mSecurePrefs.getString(SHOP_NAME, null));
        shopInfo.put(SHOP_ADRESS, mSecurePrefs.getString(SHOP_ADRESS, null));
        shopInfo.put(USER_PROFILE_ID, mSecurePrefs.getString(USER_PROFILE_ID, null));
        shopInfo.put(SHOP_ID, mSecurePrefs.getString(SHOP_ID, null));

        return shopInfo;
    }


    public String ShopName(){
        return getAppInformations().get(SHOP_NAME);
    }
    public String ShopID(){
        return getAppInformations().get(SHOP_ID);
    }
    public String ShopAddress(){
        return getAppInformations().get(SHOP_ADRESS);
    }


}
