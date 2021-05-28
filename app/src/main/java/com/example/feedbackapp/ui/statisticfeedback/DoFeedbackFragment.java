package com.example.feedbackapp.ui.statisticfeedback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.Adapter.ClassDataUtils;
import com.example.feedbackapp.R;
import com.example.feedbackapp.model.HeaderRecycleView;
import com.example.feedbackapp.model.Topic;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoFeedbackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoFeedbackFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DoFeedbackFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoFeedbackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoFeedbackFragment newInstance(String param1, String param2) {
        DoFeedbackFragment fragment = new DoFeedbackFragment();
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
// My declare parameter
    // Initialize variable
    RecyclerView rcvListTopic;
    ArrayList<Topic> arrayListTopic;
    HeaderRecycleView headerRecycleView;
    LinearLayoutManager layoutManagerTopic;
    TopicAdp adapterTopic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_do_feedback, container, false);

// Code for rycycle view
        //Assign variable
        rcvListTopic = v.findViewById(R.id.rcvListTopic);

        // Using for loop to add multiple group
        arrayListTopic = new ArrayList<>();
        arrayListTopic = ClassDataUtils.getTopic();
        headerRecycleView = ClassDataUtils.getHeaderRecycleView();
       /* for (int i = 1; i <=10;i++)
        {
            arrayListTopic.add("Topic " + (i - 1));
        }*/
        //Initialize topic adapter
        adapterTopic = new TopicAdp(getActivity(), arrayListTopic, headerRecycleView);
        // Initailize layout manager
        layoutManagerTopic = new LinearLayoutManager(getContext());
        // Set layout manager
        rcvListTopic.setLayoutManager(layoutManagerTopic);
        //set adapter
        rcvListTopic.setAdapter(adapterTopic);

        return v;
    }
}