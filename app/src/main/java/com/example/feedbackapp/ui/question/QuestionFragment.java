package com.example.feedbackapp.ui.question;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.feedbackapp.R;
import com.example.feedbackapp.ui.question.QuestionFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QuestionFragment extends Fragment {

    private QuestionViewModel questionViewModel;

    // TODO: Control Varible
    FloatingActionButton btn_Add_Question;
    Spinner spinner_Topic;

    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        questionViewModel =
                new ViewModelProvider(this).get(QuestionViewModel.class);
        View root = inflater.inflate(R.layout.question_fragment, container, false);
        addEvents(root);
        return root;
    }

    private void addEvents(View root) {
        addControls(root);
        btn_Add_Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","abc"); // Put anything what you want

                AddQuestionFragment addQuestionFragment = new AddQuestionFragment();
                addQuestionFragment.setArguments(bundle);

                Navigation.findNavController(root).navigate(R.id.question_to_add_question, bundle);
            }
        });
    }

    private void addControls(View root) {
        btn_Add_Question = root.findViewById(R.id.btn_add_question);
        spinner_Topic = root.findViewById(R.id.spinner_Topic);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        // TODO: Use the ViewModel
    }

}