package com.example.feedbackapp.ui.assignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.feedbackapp.Adapter.ModuleAdapter;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.ModuleAPIService;
import com.example.feedbackapp.UserInfo.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    // TODO: Khai báo các list và Adapter
    ModuleAdapter moduleAdapter;
    ArrayList<Module> moduleList;

    //TODO: AccessToken Varible
    String accessToken = "";

    public AddAssignmentFragment() {
        // Required empty public constructor
    }

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
        accessToken = "Bearer "+ new UserInfo(root.getContext()).token();
        // Lấy danh sách Module, Class, Trainer và đổ lên Spinner
        LoadAllModule(root);

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

        //Events khi chọn item trên Spinner
        // When user select a List-Item.
        this.spinner_module.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    private void LoadAllModule(View root){
        //"Bearer "+ new UserInfo(root.getContext()).token() - đáng ra là phải dùng token này để tương tác, nhưng retrofit đã hỗ trợ lưu
        //load list module lên fragment
        ModuleAPIService.moduleAPIServices.getAllModule(accessToken).enqueue(new Callback<ListModule>() {
            @Override
            public void onResponse(Call<ListModule> call, Response<ListModule> response) {
                moduleList = new ArrayList<Module>(Arrays.asList(response.body().getListModule()));
                setModuleSpinner(root);
            }

            @Override
            public void onFailure(Call<ListModule> call, Throwable t) {
            }
        });
    }

    private void setModuleSpinner(View root){
        moduleAdapter =new ModuleAdapter(root.getContext(), moduleList);

        ArrayAdapter<Module> adapter =
                new ArrayAdapter<Module>(root.getContext(),  android.R.layout.simple_spinner_dropdown_item, moduleList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner_module.setAdapter(adapter);
    }

    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Module module = (Module) adapter.getItem(position);
        Toast.makeText(view.getContext(), "Selected Module: " + module.getModuleName() ,Toast.LENGTH_SHORT).show();
    }
}