package com.example.feedbackapp.ui.feedback.Service;

import com.example.feedbackapp.ui.feedback.Model.ListFeedbackModel;
import com.example.feedbackapp.ui.feedback.Model.ListTypeFeedbackModel;
import com.example.feedbackapp.ui.feedback.Model.TopicModel;
import com.example.feedbackapp.ui.feedback.Model.TypeOfFeedbackModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface DataService {
    @GET("api/feedback")
    Call<ListFeedbackModel>GetDataListFeedback(@Header("Authorization") String token);

    @GET("api/type-feedback")
    Call<TypeOfFeedbackModel>GetDataTypeFeedback(@Header("Authorization") String token);

    @GET("api/topic")
    Call<TopicModel>GetDataTopic(@Header("Authorization") String token);
}
