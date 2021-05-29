package com.example.feedbackapp.ui.classs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.Adapter.ClassAdapter;
import com.example.feedbackapp.Adapter.ModuleAdapter;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ListClass;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.ClassAPIService;
import com.example.feedbackapp.RetrofitAPISetvice.ModuleAPIService;
import com.example.feedbackapp.UserInfo.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClasssFragment extends Fragment {

    ClassAdapter classAdapter;
    private ClasssViewModel slideshowViewModel;
    ListClass listClassReceived;
    ArrayList<Classs> listClass;
    RecyclerView classListRecycler;
    ImageButton btnAddClass;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel = new ViewModelProvider(this).get(ClasssViewModel.class);
        View root = inflater.inflate(R.layout.fragment_classs, container, false);

        classListRecycler = root.findViewById(R.id.rcv_ClassList);
        LoadAllClass(root);
        classListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnAddClass = root.findViewById(R.id.btn_AddClass);
        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(root.getContext(),"Click add Class!",Toast.LENGTH_LONG).show();
            }
        });
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    void LoadAllClass(View root){
        ClassAPIService.classAPIService.getAllClass("Bearer "+ new UserInfo(root.getContext()).token()).enqueue(new Callback<ListClass>() {
            @Override
            public void onResponse(Call<ListClass> call, Response<ListClass> response) {
                if(response.isSuccessful()){
                    listClassReceived = response.body();
                    setClassAdapter(root);
                    Toast.makeText(root.getContext(),"lấy được",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(root.getContext(),"lấy không được",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ListClass> call, Throwable t) {
                Toast.makeText(root.getContext(),"có lỗi xảy ra!",Toast.LENGTH_LONG).show();
            }
        });
    }
    void setClassAdapter(View root){
        listClass = new ArrayList<Classs>(Arrays.asList(listClassReceived.getListClass()));
        classAdapter =new ClassAdapter(root.getContext(), listClass);
        Toast.makeText(root.getContext(),"thêm vào adapter thành công "+listClass.size(),Toast.LENGTH_LONG).show();
        classListRecycler.setAdapter(classAdapter);
    }
}