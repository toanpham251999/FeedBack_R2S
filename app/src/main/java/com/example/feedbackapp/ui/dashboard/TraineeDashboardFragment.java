package com.example.feedbackapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.Adapter.FeedbackAdapter;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback.Feedback;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback.ListFeedbackInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback.Question;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.FeedbackAPIServices;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraineeDashboardFragment extends Fragment {
    ListFeedbackInfo listFeedbackInfo;
    FeedbackAdapter feedbackAdapter;
    ArrayList<Feedback> listFeedback;
    RecyclerView feedbackListRecycler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_trainee_dashboard, container, false);
        feedbackListRecycler = root.findViewById(R.id.rcv_feedbackList);
        listFeedback = new ArrayList<Feedback>();
        feedbackListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        actGetListFeedback(root);

        return root;
    }

    private void actGetListFeedback(View root){
        FeedbackAPIServices.feedbackAPIServices.getListFeedback(
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI2MGE3MjRjMTk1N2FhNjBjN2M3YzNlYTIiLCJ0eXBlVXNlciI6InRyYWluZWUiLCJpYXQiOjE2MjE4NjAwNjB9.2fpi3Fs7bYl233OHKppcDVZwmcVz3aG1TubOh_ZWj9E"
        ).enqueue(new Callback<ListFeedbackInfo>() {
            @Override
            public void onResponse(Call<ListFeedbackInfo> call, Response<ListFeedbackInfo> response) {
                listFeedbackInfo = response.body();
                listFeedback = listFeedbackInfo.getListFeedback();
                showListFeedback(root);
            }

            @Override
            public void onFailure(Call<ListFeedbackInfo> call, Throwable t) {

            }
        });
    }


    private void showListFeedback(View root){
        feedbackAdapter = new FeedbackAdapter(root.getContext(), listFeedback);
        feedbackListRecycler.setAdapter(feedbackAdapter);
    }
}
