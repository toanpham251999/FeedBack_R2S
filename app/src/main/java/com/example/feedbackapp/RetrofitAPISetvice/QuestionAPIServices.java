package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.AssignmentInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Question.QuestionInfo;
import com.example.feedbackapp.UserInfo.BaseUrl;
import com.example.feedbackapp.model.Question;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface QuestionAPIServices {
    //tạo 1 biến gson dùng cho service bên dưới
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .create();

    //khởi tạo service
    QuestionAPIServices QUESTION_API_SERVICES = new Retrofit.Builder()
            .baseUrl(BaseUrl.value())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(QuestionAPIServices.class);

    //service lấy danh sách Question
    @GET("/api/question")
    Call<QuestionInfo> getQuestionList(@Header("Authorization") String authHeader);

    //Lấy danh sach question theo topic
    @POST("/api/question/filter")
    @FormUrlEncoded
    Call<QuestionInfo> getQuestionListByTopicId(@Header("Authorization") String authHeader,@Field("TopicId") String topicId);

    //Add new question
    //@POST("/api/question")
    //Call<QuestionInfo> addNewQuestion(@Header("Authorization") String authHeader);

}
