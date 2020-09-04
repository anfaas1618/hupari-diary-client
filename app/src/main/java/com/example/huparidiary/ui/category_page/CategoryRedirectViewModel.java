package com.example.huparidiary.ui.category_page;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CategoryRedirectViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CategoryRedirectViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is category you will see category page soon");
    }


    public LiveData<String> getText() {
        return mText;
    }
}