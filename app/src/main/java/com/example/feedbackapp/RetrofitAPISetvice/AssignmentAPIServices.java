package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.AssignmentInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AssignmentAPIServices {
    //tạo 1 biến gson dùng cho service bên dưới
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .create();

    //khởi tạo service
    AssignmentAPIServices ASSIGNMENT_API_SERVICES = new Retrofit.Builder()
            .baseUrl("https://androidserverr2s.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AssignmentAPIServices.class);

    //service lấy danh sách asignment
    @GET("/api/assignment")
    Call<AssignmentInfo> getAssignmentList(@Header("Authorization") String authHeader);
}
