package com.example.feedbackapp.ui.assignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.feedbackapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddAssignmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddAssignmentFragment extends Fragment {

    private AssignmentViewModel assignmentViewModel;

    // TODO: Control Varible
    Spinner spinner_module, spinner_class, spinner_trainer;
    Button btn_Save, btn_Back;

    public AddAssignmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddAssignmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddAssignmentFragment newInstance(String param1, String param2) {
        AddAssignmentFragment fragment = new AddAssignmentFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        assignmentViewModel =
                new ViewModelProvider(this).get(AssignmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_assignment, container, false);
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

                AddAssignmentFragment addAssignmentFragment = new AddAssignmentFragment();
                addAssignmentFragment.setArguments(bundle);

                Navigation.findNavController(root).navigate(R.id.add_assignment_to_assignment, bundle);
            }
        });

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","abc"); // Put anything what you want

                AddAssignmentFragment addAssignmentFragment = new AddAssignmentFragment();
                addAssignmentFragment.setArguments(bundle);

                Navigation.findNavController(root).navigate(R.id.add_assignment_to_assignment, bundle);
            }
        });
    }

    private void addControls(View root) {
        spinner_module = (Spinner) root.findViewById(R.id.spinner_Topic);
        spinner_class = (Spinner) root.findViewById(R.id.spinner_class);
        spinner_trainer = (Spinner) root.findViewById(R.id.spinner_trainer);
        btn_Save = (Button) root.findViewById(R.id.btn_Save);
        btn_Back = (Button) root.findViewById(R.id.btn_Back);
    }
}