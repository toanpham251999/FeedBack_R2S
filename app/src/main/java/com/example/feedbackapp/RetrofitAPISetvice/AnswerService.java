package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassList;
import com.example.feedbackapp.ModelClassToSendAPI.Answer.Answer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AnswerService {
    //service get lisst class
    @POST("api/class")// Sửa lại
    Call<ArrayList<Answer>> postAnswer(ArrayList<Answer> listAnswer);
}
