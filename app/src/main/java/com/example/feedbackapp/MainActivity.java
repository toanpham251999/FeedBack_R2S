package com.example.feedbackapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Menu;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;


import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.Assignment;
import com.example.feedbackapp.ui.assignment.AssignmentFragment;
import com.example.feedbackapp.ui.feedback.FeedBackFragment;
import com.example.feedbackapp.ui.feedback.FeedbackAdapter;
import com.example.feedbackapp.ui.feedback.FeedbackModel;

import com.example.feedbackapp.UserInfo.UserInfo;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Test and delete
    private ScaleGestureDetector mScaleGestureDetector;
    GestureDetector gestureDetector;
    float mScale = 0.1f;
    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ẩn title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        //
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ConfigNavigationView();
        //ẩn bớt phần tử trong slide menu
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_homepage, R.id.nav_assignment, R.id.nav_classs, R.id.nav_module,R.id.nav_enrrollment, R.id.nav_feedback,
                R.id.nav_result, R.id.nav_question, R.id.nav_contact, R.id.nav_logout,R.id.nav_add_feedback,R.id.nav_review_new_feedback,R.id.nav_feedbackright, R.id.nav_feedbackdetail,R.id.nav_statisticdofeedback,R.id.nav_dofeedback)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);// điều hướng đến  fragment nav_host_fragment trong layout content_main
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


//Code to zoom
        gestureDetector = new GestureDetector(this, new GestureListener());

        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener(){
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                float scale = 1 - detector.getScaleFactor();
                float prevScale = mScale;
                mScale += scale;

                if (mScale > 10f)
                    mScale = 10f;

                ScaleAnimation scaleAnimation = new ScaleAnimation(1f / prevScale, 1f / mScale, 1f / prevScale, 1f / mScale, detector.getFocusX(), detector.getFocusY());
                scaleAnimation.setDuration(0);
                scaleAnimation.setFillAfter(true);
                RecyclerView layout = (RecyclerView) findViewById(R.id.rcvDetail);
                layout.startAnimation(scaleAnimation);
                return true;
            }
        });


        //hiện thông tin người dùng sau khi đăng nhập, dùng để test
        ShowUserData();

    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    // Code for zoom in/out scroll view
    //Link ferer:  https://www.youtube.com/watch?v=TRqysuYnDlU
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        super.dispatchTouchEvent(event);
        mScaleGestureDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        return gestureDetector.onTouchEvent(event);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {

            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return true;
        }
    }

    void ConfigNavigationView(){
        //chưa dùng tới
        UserInfo userInfo = new UserInfo(getApplicationContext());
        String role = userInfo.role();

        Menu nav_Menu = navigationView.getMenu();
        if(role.equals("admin")){
            //không ẩn đi gì cả, sau này sẽ ẩn Join đi
//            nav_Menu.findItem(R.id.nav_join).setVisible(false);
        }
        else if(role.equals("trainer")){
            nav_Menu.findItem(R.id.nav_enrrollment).setVisible(false);
            nav_Menu.findItem(R.id.nav_feedback).setVisible(false);
            nav_Menu.findItem(R.id.nav_question).setVisible(false);
            //nav_Menu.findItem(R.id.nav_join).setVisible(false);
        }
        else{
            nav_Menu.findItem(R.id.nav_assignment).setVisible(false);
            nav_Menu.findItem(R.id.nav_enrrollment).setVisible(false);
            nav_Menu.findItem(R.id.nav_feedback).setVisible(false);
            nav_Menu.findItem(R.id.nav_result).setVisible(false);
            nav_Menu.findItem(R.id.nav_question).setVisible(false);
            //nav_Menu.findItem(R.id.nav_join).setVisible(false);
        }
    }

    //hàm này để thử xem dữ liệu như token, username, có lưu lại được không
    public void ShowUserData(){
        UserInfo userInfo = new UserInfo(getApplicationContext());
        String toastValue = "token: " + userInfo.token() +
                "\n username: " + userInfo.username() +
                "\n login time: " + userInfo.loginTime() +
                "\n remember: " + userInfo.isRemember() +
                "\n role: " + userInfo.role();
        Toast.makeText(getApplicationContext(),toastValue,Toast.LENGTH_LONG).show();
    }
}