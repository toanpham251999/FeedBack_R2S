package com.example.feedbackapp.ui.classs;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.feedbackapp.R;

public class ShowTraineeOfClassFragment extends Fragment {

    private ShowTraineeOfClassViewModel mViewModel;

    public static ShowTraineeOfClassFragment newInstance() {
        return new ShowTraineeOfClassFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_trainee_of_class_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShowTraineeOfClassViewModel.class);
        // TODO: Use the ViewModel
    }

}