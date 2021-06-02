package com.example.feedbackapp.ui.classs;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.feedbackapp.R;

public class ShowTraineeOfClassFragment extends Fragment{
    private ShowTraineeOfClassViewModel mViewModel;
    Button btnBack;

    public static ShowTraineeOfClassFragment getInstance() {
        return new ShowTraineeOfClassFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.show_trainee_of_class_fragment, container, false);
        btnBack = root.findViewById(R.id.btn_TraineeListBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                if(fragmentManager.getBackStackEntryCount()>0){
                    fragmentManager.popBackStack();
                }
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShowTraineeOfClassViewModel.class);
        // TODO: Use the ViewModel
    }
}