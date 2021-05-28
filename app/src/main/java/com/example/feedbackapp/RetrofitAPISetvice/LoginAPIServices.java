package com.example.feedbackapp.RetrofitAPISetvice;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Login.LoginInfo;
import com.example.feedbackapp.ModelClassToSendAPI.Login.LoginValue;
import com.example.feedbackapp.UserInfo.BaseUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginAPIServices {

    //tạo 1 biến gson dùng cho service bên dưới
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy")
            .create();

    //khởi tạo service
    LoginAPIServices loginAPIServices = new Retrofit.Builder()
            .baseUrl(BaseUrl.value())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(LoginAPIServices.class);

    //để mẫu này lại sau dùng
//    @POST("api/auth/login")
//    @FormUrlEncoded
//    Call<LoginInfo> onLoginClick(@Field("username") String username,
//                                 @Field("password") String password,
//                                 @Field("typeUser") String typeUser);
    //service đăng nhập
    @POST("api/auth/login")
    Call<LoginInfo> onLoginClick(@Body LoginValue loginValue);

    //service lấy thông tin tất cả tài khoản
    @GET("/api/auth")
    Call<LoginInfo> getInfo();
}
