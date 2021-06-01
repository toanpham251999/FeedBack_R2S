package com.example.feedbackapp.ui.feedback;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feedbackapp.R;
import com.example.feedbackapp.ui.feedback.Adapter.TopicAdapter;
import com.example.feedbackapp.ui.feedback.Adapter.TopicReviewAdapter;
import com.example.feedbackapp.ui.feedback.Interface.ICheckBoxListener;
import com.example.feedbackapp.ui.feedback.Model.ListQuestion;
import com.example.feedbackapp.ui.feedback.Model.TopicModel;
import com.example.feedbackapp.ui.feedback.Service.APIService;
import com.example.feedbackapp.ui.feedback.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Review_NewFeedbackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Review_NewFeedbackFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button btnReview;
    ArrayList<ListQuestion>listQuestions;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Review_NewFeedbackFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Review_NewFeedbackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Review_NewFeedbackFragment newInstance(String param1, String param2) {
        Review_NewFeedbackFragment fragment = new Review_NewFeedbackFragment();
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
        View view = inflater.inflate(R.layout.fragment_review__new_feedback, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.rcv_review);
        DataService dataServiceTopic = APIService.getService();
        Call<TopicModel> callbackListTopic = dataServiceTopic.GetDataTopic("Bearer eyJhbGciOiJIUzI1NiIsInR5c" +
                "CI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI2MGE3MjRiYTk1N2FhNjBjN2M3YzNlYTEiLCJ0eXBlVXNlciI6ImFkbWluIiwiaWF0Ij" +
                "oxNjIxOTU0NDg5fQ.i4JExKXlcmHIi-m3E6O46YEKoj1pV6R0Wi9ezN77GG0");
        callbackListTopic.enqueue(new Callback<TopicModel>() {
            @Override
            public void onResponse(Call<TopicModel> call, Response<TopicModel> response) {
                TopicModel topicModel = (TopicModel)response.body();
                TopicReviewAdapter topicAdapter = new TopicReviewAdapter(topicModel.getListTopic());

                recyclerView.setAdapter(topicAdapter);
                RecyclerView.LayoutManager layoutManagerTopic = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManagerTopic);
                Log.d("BTopic","Done");
            }

            @Override
            public void onFailure(Call<TopicModel> call, Throwable t) {
                Log.d("DTopic",t.toString());
            }
        });
        Bundle bundle = getArguments();
        TextView txt_review_feedbacktitle;
        txt_review_feedbacktitle = (TextView) view.findViewById(R.id.txt_review_feedbacktitle);
        if(bundle !=null) {
            txt_review_feedbacktitle.setText(bundle.getString("feedbackName"));
        }
        else
            Toast.makeText(this.getContext(),"Lỗi",Toast.LENGTH_LONG).show();
        return view;
    }

}