package com.dhananjay.democreateaccount.fragments.budget;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BudgetViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BudgetViewModel() {
        mText = new MutableLiveData<>();
        String text = "This is budget fragment";
        mText.setValue(text);
    }

    public LiveData<String> getText() {
        return mText;
    }
}