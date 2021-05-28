package com.example.feedbackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Login.LoginInfo;
import com.example.feedbackapp.ModelClassToSendAPI.Login.LoginValue;
import com.example.feedbackapp.RetrofitAPISetvice.LoginAPIServices;
import com.example.feedbackapp.UserInfo.UserInfo;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    //khai báo các thành phần có trong layout
    EditText txtUserName;
    TextView txtUserNameReport;
    EditText txtPassword;
    TextView txtPasswordReport;
    Spinner spnRolePicker;
    CheckBox chkRememberMe;
    Button btnLogin;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //lấy các thành phần có trong layout
        txtUserName = (EditText) findViewById(R.id.txt_UserName);
        txtUserNameReport = (TextView) findViewById(R.id.txt_UserNameReport);
        txtPassword = (EditText) findViewById(R.id.txt_Password);
        txtPasswordReport = (TextView) findViewById(R.id.txt_PasswordReport);
        spnRolePicker = (Spinner) findViewById(R.id.spn_RolePicker);
        chkRememberMe = (CheckBox) findViewById(R.id.chk_RememberMe);
        btnLogin = (Button) findViewById(R.id.btn_Login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                OnLoginValidate();
            }
        });
    }
    //hàm validate cho login
    void OnLoginValidate(){
        //tạo biến kiểm tra đã đúng validate chưa
        boolean isValidated = true;

        //tạo biến lưu giá trị Username và Password
        String username = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();
        int role = spnRolePicker.getSelectedItemPosition()+1;

        //kiểm tra để trống và chứa khoảng trắng username và password
        if(username.trim().equals("")){
            txtUserNameReport.setText("Username must have at least 1 character!");
            isValidated = false;
            //return;
        }
        else if(username.contains(" ")){
            txtUserNameReport.setText("Username must have at blank space!");
            isValidated = false;
            //return;
        }
        else {
            txtUserNameReport.setText("");
        }

        if(password.trim().equals("")) {
            txtPasswordReport.setText("Password must have at least 1 character!");
            isValidated = false;
            //return;
        }
        else if(password.contains(" ")){
            txtPasswordReport.setText("Password must have at blank space!");
            isValidated = false;
            //return;
        }
        else{
            txtPasswordReport.setText("");
        }
        //nếu validate không lỗi, kiểm tra tài khoản
        if(isValidated){
            OnAccountCheck();
        }
    }

    void OnAccountCheck(){
        //tạo biến lưu giá trị Username và Password
        String username = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();
        int role = spnRolePicker.getSelectedItemPosition()+1;
        String strRole = "";

        //kiểm tra tài khoản và role
        if(role==2){
            strRole = "trainer";
        }
        else if(role==3){
            strRole = "trainee";
        }
        else{
            strRole = "admin";
        }

        //tạo model lưu giá trị nhập khi login
        LoginValue lv = new LoginValue(username,password,strRole);
        //gọi API kiểm tra tài khoản đăng nhập, nếu đúng, vào trang chính, nếu sai hiển thị lỗi
        LoginAPIServices.loginAPIServices.onLoginClick(lv).enqueue(new Callback<LoginInfo>() {
            @Override
            public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                LoginInfo loginInfo = response.body();
                if(loginInfo!=null){
                    if(loginInfo.isSuccess()){
                        //lưu thông tin đăng nhập lại để còn dùng tiếp
                        UserInfo userInfo = new UserInfo(getApplicationContext());
                        userInfo.newInfo(loginInfo.getAccessToken(),loginInfo.getAccount().getUserName(), Calendar.getInstance().getTime().toString(),true);
                        //chuyển đến trang menu
                        Intent mainMenu = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainMenu);
                    }
                    else{
                        ShowLoginFailDialog();
                    }
                }
                else{
                    ShowLoginFailDialog();
                }
            }
            @Override
            public void onFailure(Call<LoginInfo> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Có lỗi xảy ra!",Toast.LENGTH_LONG).show();
            }
        });
    }

    //hàm kiểm tra xử lý Remember Me
    void OnRememberMeCheck(){
        //chưa xử lý
    }

    void ShowLoginFailDialog(){
        //hiện dialog login failed
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.login_failed_dialog, null);
        final Button btnYes = (Button) alertLayout.findViewById(R.id.btn_Yes);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setCancelable(false);
        AlertDialog dialog = alert.create();
        btnYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}