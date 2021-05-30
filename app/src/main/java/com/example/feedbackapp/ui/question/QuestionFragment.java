package com.example.feedbackapp.ui.question;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.feedbackapp.Adapter.QuestionAdapter;
import com.example.feedbackapp.Adapter.QuestionAdapter;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Question.QuestionInfo;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Question.Question;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.QuestionAPIServices;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.ui.question.QuestionFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionFragment extends Fragment {

    private QuestionViewModel questionViewModel;

    // TODO: Control Varible
    FloatingActionButton btn_Add_Question;
    Spinner spinner_Topic;

    //TODO: AccessToken Varible
    String accessToken = "";

    //TODO: questionsList
    ArrayList<Question> questionsList;

    //TODO: Define RecyclerView and Adapter variable
    RecyclerView questionListRecycler;
    QuestionAdapter questionAdapter;

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

        accessToken = "Bearer "+ new UserInfo(root.getContext()).token();

        QuestionAPIServices.QUESTION_API_SERVICES.getQuestionList(accessToken).enqueue(new Callback<QuestionInfo>() {
            @Override
            public void onResponse(Call<QuestionInfo> call, Response<QuestionInfo> response) {
                questionsList = response.body().getListQuestion();
                LoadQuestionList(root);
                Log.d("AAAAAAAAAAAAAAAA", "onResponse()");
                Toast.makeText(getActivity(),"Load thành công!",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<QuestionInfo> call, Throwable t) {
                Toast.makeText(getActivity(),"Có lỗi xảy ra!",Toast.LENGTH_LONG).show();
                Log.d("TAG", "onFailure()");
            }
        });
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
        questionListRecycler = root.findViewById(R.id.questionList);
    }

    //Get question List for Adapter
    public void LoadQuestionList(View root){
        questionAdapter = new QuestionAdapter(root.getContext(),questionsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        questionListRecycler.setLayoutManager(layoutManager);
        //questionListRecycler.setHasFixedSize(true);
        questionListRecycler.setAdapter(questionAdapter);
    }

}