package com.dhananjay.democreateaccount.fragments.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        String text = "This is dashboard fragment";
        mText.setValue(text);
    }

    public LiveData<String> getText() {
        return mText;
    }
}