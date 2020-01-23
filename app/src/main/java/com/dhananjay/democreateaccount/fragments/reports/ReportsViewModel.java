package com.dhananjay.democreateaccount.fragments.reports;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReportsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReportsViewModel() {
        mText = new MutableLiveData<>();
        String text = "This is reports fragment";
        mText.setValue(text);
    }

    public LiveData<String> getText() {
        return mText;
    }
}
