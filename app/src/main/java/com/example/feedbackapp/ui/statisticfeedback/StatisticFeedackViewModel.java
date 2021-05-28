package com.example.feedbackapp.ui.statisticfeedback;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
//import androidx.lifecycle.ViewModel;

public class StatisticFeedackViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Integer> integer = new MutableLiveData<>();
    public void setInt(Integer input){
        integer.setValue(input);
    }
    public LiveData<Integer> getInt(){
        return integer;
    }
}