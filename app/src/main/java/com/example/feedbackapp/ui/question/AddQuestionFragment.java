package com.example.feedbackapp.ui.question;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Question.Question;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.Topic;
import com.example.feedbackapp.R;
import com.example.feedbackapp.ui.question.AddQuestionFragment;
import com.example.feedbackapp.ui.question.QuestionViewModel;

import java.util.ArrayList;

public class AddQuestionFragment extends Fragment {

    private QuestionViewModel questionViewModel;

    // TODO: Control Varible
    Spinner spinner_Topic;
    EditText editText_questionContent;
    Button btn_Save, btn_Back;

    //TODO: topicList
    ArrayList<Topic> topicList;

    public AddQuestionFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddQuestionFragment newInstance(String param1, String param2) {
        AddQuestionFragment fragment = new AddQuestionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        questionViewModel =
                new ViewModelProvider(this).get(QuestionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_question, container, false);
        addEvents(root);
        // Lấy listTopic từ QuestionFragment
        Bundle bundle = getArguments();
        if(bundle != null){
            // handle your code here.
            topicList = (ArrayList<Topic>) bundle.getSerializable("topicList");
        }

        // Lấy danh sách topic đổ lên spinner
        setTopicSpinner(root);
        return root;
    }

    //Đổ topic list vào spinner
    private void setTopicSpinner(View root){
        ArrayAdapter<Topic> adapter =
                new ArrayAdapter<Topic>(root.getContext(),  android.R.layout.simple_spinner_dropdown_item, topicList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner_Topic.setAdapter(adapter);
    }

    private void addEvents(View root) {
        addControls(root);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","abc"); // Put anything what you want

                AddQuestionFragment addQuestionFragment = new AddQuestionFragment();
                addQuestionFragment.setArguments(bundle);

                Navigation.findNavController(root).navigate(R.id.add_question_to_question, bundle);
            }
        });

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","abc"); // Put anything what you want

                AddQuestionFragment addQuestionFragment = new AddQuestionFragment();
                addQuestionFragment.setArguments(bundle);

                Navigation.findNavController(root).navigate(R.id.add_question_to_question, bundle);
            }
        });

        //Events khi chọn item trên Spinner
        // When user select a List-Item.
        this.spinner_Topic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Topic topic = (Topic) adapter.getItem(position);
        //LoadQuestionListByTopicId(view, topic.getId());
        Toast.makeText(view.getContext(), "Selected Topic: " + topic.getTopicName() ,Toast.LENGTH_SHORT).show();
    }

    private void addControls(View root) {
        editText_questionContent = root.findViewById(R.id.editText_questionContent);
        spinner_Topic = root.findViewById(R.id.spinner_Topic);
        btn_Save = (Button) root.findViewById(R.id.btn_Save);
        btn_Back = (Button) root.findViewById(R.id.btn_Back);
    }
}