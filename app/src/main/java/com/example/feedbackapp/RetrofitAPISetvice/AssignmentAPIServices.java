package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.AssignmentInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.NewAssignment;
import com.example.feedbackapp.ModelClassToSendAPI.Assignment.AddAssignmentInfo;
import com.example.feedbackapp.UserInfo.BaseUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AssignmentAPIServices {
    //tạo 1 biến gson dùng cho service bên dưới
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .create();

    //khởi tạo service
    AssignmentAPIServices ASSIGNMENT_API_SERVICES = new Retrofit.Builder()
            .baseUrl(BaseUrl.value())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AssignmentAPIServices.class);

    //service lấy danh sách asignment
    @GET("/api/assignment")
    Call<AssignmentInfo> getAssignmentList(@Header("Authorization") String authHeader);

    //Thêm assignment mới
    @POST("api/assignment")
    Call<ResponseBody> addNewAssignment(@Header("Authorization") String authHeader,
                                        @Body AddAssignmentInfo addAssignmentInfo);

//    @FormUrlEncoded
//    Call<ResponseBody> addNewAssignment(@Header("Authorization") String authHeader,
//                                        @Field("ModuleId") String moduleId,
//                                        @Field("ClassId") String classId,
//                                        @Field("TrainerId") String trainerId);

}
