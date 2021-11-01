package com.cbl.cofees.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Integer> mTotal;

    public HomeViewModel() {
        mTotal = new MutableLiveData<>();
        mTotal.setValue(45);
    }

    public LiveData<Integer> getTotal() {
        return mTotal;
    }
}