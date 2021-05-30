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

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ListClass;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.RetrofitAPISetvice.ClassAPIService;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.ModuleAPIService;
import com.example.feedbackapp.UserInfo.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAssignmentFragment extends Fragment {

    private AssignmentViewModel assignmentViewModel;

    // TODO: Control Varible
    Spinner spinner_module, spinner_class, spinner_trainer;
    Button btn_Save, btn_Back;

    // TODO: Khai báo các list và Adapter
    ArrayList<Module> moduleList;
    ArrayList<Classs> classList;

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
        LoadAllClass(root);
        return root;
    }

    private void LoadAllClass(View root) {
        ClassAPIService.classAPIService.getAllClass(accessToken).enqueue(new Callback<ListClass>() {
            @Override
            public void onResponse(Call<ListClass> call, Response<ListClass> response) {
                classList = new ArrayList<Classs>(Arrays.asList(response.body().getListClass()));
                setClassSpinner(root);
            }

            @Override
            public void onFailure(Call<ListClass> call, Throwable t) {
            }
        });
    }

    private void setClassSpinner(View root) {
        ArrayAdapter<Classs> adapter =
                new ArrayAdapter<Classs>(root.getContext(),  android.R.layout.simple_spinner_dropdown_item, classList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner_class.setAdapter(adapter);
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

        //Events khi chọn item trên Spinner Module
        // When user select a List-Item.
        this.spinner_module.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandlerModule(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Events khi chọn item trên Spinner Class
        // When user select a List-Item.
        this.spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandlerClass(parent, view, position, id);
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
        ModuleAPIService.moduleAPIServices.getAllModule(accessToken).enqueue(new Callback<ListModule>() {
            @Override
            public void onResponse(Call<ListModule> call, Response<ListModule> response) {
                //moduleList = new ArrayList<Module>(Arrays.asList(response.body().getListModule()));
                moduleList = response.body().getListModule();
                setModuleSpinner(root);
            }

            @Override
            public void onFailure(Call<ListModule> call, Throwable t) {
            }
        });
    }

    private void setModuleSpinner(View root){
        ArrayAdapter<Module> adapter =
                new ArrayAdapter<Module>(root.getContext(),  android.R.layout.simple_spinner_dropdown_item, moduleList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner_module.setAdapter(adapter);
    }

    private void onItemSelectedHandlerModule(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Module module = (Module) adapter.getItem(position);
        Toast.makeText(view.getContext(), "Selected Module: " + module.getModuleName() ,Toast.LENGTH_SHORT).show();
    }

    private void onItemSelectedHandlerClass(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Classs aClasss = (Classs) adapter.getItem(position);
        Toast.makeText(view.getContext(), "Selected Class: " + aClasss.getClassName() ,Toast.LENGTH_SHORT).show();
    }
}