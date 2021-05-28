package com.example.feedbackapp.ui.feedback;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.example.feedbackapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Add_Feedback#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Add_Feedback extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnReviewFeedback;

    private Spinner spinner;
    private List<TypeFeedbackModel>typeFeedbackModels;

    public Add_Feedback() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Add_Feedback.
     */
    // TODO: Rename and change types and number of parameters
    public static Add_Feedback newInstance(String param1, String param2) {
        Add_Feedback fragment = new Add_Feedback();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add__feedback, container, false);
        ArrayList<TypeFeedbackModel>arrTypeFeedback=new ArrayList<>();
        arrTypeFeedback.add(new TypeFeedbackModel(1,"Online1"));
        arrTypeFeedback.add(new TypeFeedbackModel(2,"Online2"));
        arrTypeFeedback.add(new TypeFeedbackModel(3,"Online3"));
        spinner = (Spinner) view.findViewById(R.id.spn_Type_Feedback);
        btnReviewFeedback = (Button)view.findViewById(R.id.btn_ReviewFeedback);
        btnReviewFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_review_new_feedback);
            }
        });

        return view;
    }
}