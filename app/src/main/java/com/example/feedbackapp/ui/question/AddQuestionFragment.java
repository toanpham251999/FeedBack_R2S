package com.example.feedbackapp.ui.question;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.feedbackapp.R;
import com.example.feedbackapp.ui.question.AddQuestionFragment;
import com.example.feedbackapp.ui.question.QuestionViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddQuestionFragment extends Fragment {

    private QuestionViewModel questionViewModel;

    // TODO: Control Varible
    Spinner spinner_Topic;
    EditText editText_questionContent;
    Button btn_Save, btn_Back;

    public AddQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddQuestionFragment.
     */
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
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        String strtext = "";
        if(bundle != null){
            // handle your code here.
            bundle.getBundle("key");
            strtext = bundle.getString("key");
        }
        return root;
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
    }

    private void addControls(View root) {
        editText_questionContent = root.findViewById(R.id.editText_questionContent);
        spinner_Topic = root.findViewById(R.id.spinner_Topic);
        btn_Save = (Button) root.findViewById(R.id.btn_Save);
        btn_Back = (Button) root.findViewById(R.id.btn_Back);
    }
}