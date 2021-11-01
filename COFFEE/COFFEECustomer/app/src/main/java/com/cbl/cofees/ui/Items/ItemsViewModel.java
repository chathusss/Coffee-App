package com.cbl.cofees.ui.Items;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;

import java.util.ArrayList;

public class ItemsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<JsonObject>> mText;

    public ItemsViewModel() {
        mText = new MutableLiveData<>();
        ArrayList<JsonObject> items=  new ArrayList<>();
        mText.setValue(items);
    }

    public LiveData<ArrayList<JsonObject>> getText() {
        return mText;
    }
}