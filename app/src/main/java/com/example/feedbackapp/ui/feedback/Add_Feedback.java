package com.example.feedbackapp.ui.feedback;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.ui.feedback.Adapter.QuestionAdapter;
import com.example.feedbackapp.ui.feedback.Adapter.TopicAdapter;
import com.example.feedbackapp.ui.feedback.Interface.ICheckBoxListener;
import com.example.feedbackapp.ui.feedback.Model.ClassDataUtilsFeedback;
import com.example.feedbackapp.ui.feedback.Adapter.CustomAdapter;
import com.example.feedbackapp.ui.feedback.Model.ListFeedbackModel;
import com.example.feedbackapp.ui.feedback.Model.ListQuestion;
import com.example.feedbackapp.ui.feedback.Model.ListTopic;
import com.example.feedbackapp.ui.feedback.Model.ListTypeFeedbackModel;
import com.example.feedbackapp.ui.feedback.Model.TopicModel;
import com.example.feedbackapp.ui.feedback.Model.TypeFeedbackModel;
import com.example.feedbackapp.ui.feedback.Model.TypeOfFeedbackModel;
import com.example.feedbackapp.ui.feedback.Service.APIService;
import com.example.feedbackapp.ui.feedback.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Add_Feedback#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Add_Feedback extends Fragment implements ICheckBoxListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnReviewFeedback;
    private String typeFeedback;
    private EditText feedbackName;

    //
    ArrayList<String> arrayList_save;


    //private Spinner spinnerTypeFeedback;
    //private List<TypeOfFeedbackModel>typeOfFeedbackModels;
    private Spinner spinner;
    private List<TypeFeedbackModel> classes;

    // adt
    private TopicAdapter topicAdapter;
    private QuestionAdapter questionAdapter;
    private ICheckBoxListener iCheckBoxListener;
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
        DataService dataServiceFeedback = APIService.getService();
        Call<TypeOfFeedbackModel> callbackFeedback = dataServiceFeedback.GetDataTypeFeedback("Bearer eyJhbGciOiJIUzI1NiIsInR5c" +
                "CI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI2MGE3MjRiYTk1N2FhNjBjN2M3YzNlYTEiLCJ0eXBlVXNlciI6ImFkbWluIiwiaWF0Ij" +
                "oxNjIxOTU0NDg5fQ.i4JExKXlcmHIi-m3E6O46YEKoj1pV6R0Wi9ezN77GG0");
        callbackFeedback.enqueue(new Callback<TypeOfFeedbackModel>()
        {
            @Override
            public void onResponse(Call<TypeOfFeedbackModel> call, Response<TypeOfFeedbackModel> response) {

                //classes = ClassDataUtilsFeedback.getClasss();
                spinner = (Spinner) view.findViewById(R.id.spn_Type_Feedback);
                TypeOfFeedbackModel listTypeFeedbackModel = (TypeOfFeedbackModel) response.body();
                // Adapter"
                CustomAdapter adapter = new CustomAdapter(getActivity(),
                        R.layout.spinner_item_layout_type_feedback,
                        R.id.textView_item_name,
                        listTypeFeedbackModel.getListTypeFeedback());
                spinner.setAdapter(adapter);
                spinner.setSelected(true);
                Log.d("BBBB",listTypeFeedbackModel.getMessage());
            }

            @Override
            public void onFailure(Call<TypeOfFeedbackModel> call, Throwable t) {
                Log.d("DDD","Get Data Fail");
            }
        });
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.rcv_Topic);
        DataService dataServiceTopic = APIService.getService();
        UserInfo userInfo = new UserInfo(getContext());
        Call<TopicModel> callbackListTopic = dataServiceTopic.GetDataTopic("Bearer "+userInfo.token());
        callbackListTopic.enqueue(new Callback<TopicModel>() {
            @Override
            public void onResponse(Call<TopicModel> call, Response<TopicModel> response) {
                TopicModel topicModel = (TopicModel)response.body();
                topicAdapter = new TopicAdapter(topicModel.getListTopic(),iCheckBoxListener );

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
        //Xử lý Review
        btnReviewFeedback =(Button)view.findViewById(R.id.btn_ReviewFeedback);
        feedbackName =(EditText)view.findViewById(R.id.edt_FeedbackTitleCreate);
        btnReviewFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //arr id question choose
                ArrayList<String> a = questionAdapter.onclicked();
                Bundle bundle = new Bundle();
                bundle.putString("feedbackName", feedbackName.getText().toString().trim());
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_review_new_feedback,bundle);
            }
        });
        return view;
    }
    // handler click spinner
    // Class
    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        TypeFeedbackModel clas = (TypeFeedbackModel) adapter.getItem(position);
        String itemName = clas.getClassName();
    }

    @Override
    public void onCheckBoxChecking(ArrayList<String> arrayList) {
        arrayList_save = arrayList;
       // Log.d("AAA","OK");
    }


}