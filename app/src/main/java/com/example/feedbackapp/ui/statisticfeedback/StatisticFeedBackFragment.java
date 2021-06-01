package com.example.feedbackapp.ui.statisticfeedback;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.example.feedbackapp.Adapter.CustomAdapter;
import com.example.feedbackapp.Adapter.CustomApdapterModule;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.ClassList;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ListModule;
import com.example.feedbackapp.R;
import com.example.feedbackapp.model.Class;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;
//chart

public class StatisticFeedBackFragment extends Fragment {
    // Viewmodel
    private StatisticFeedBackViewModel mviewModel;
    private CustomAdapter adapter;
    private CustomApdapterModule adapterModule;
    private String accessToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI2MGE3MjRiYTk1N2FhNjBjN2M3YzNlYTEiLCJ0eXBlVXNlciI6ImFkbWluIiwiaWF0IjoxNjIxODU5NDMwfQ.-GljSrlUF4b3nl8ojzpk1xK1O-_MX5B6a31g8u5eTp8";
    //swipe left
    float x1, x2, y1 , y2;
    //chart
    PieChart pieChart;
    //declare for spinner
    private Spinner spinner;// for clss
    private Spinner spinnerModule;
    private List<Class> classess;
    private List<com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module> modules;
   // private StatisticFeedackViewModel mViewModel;
    //textview
    private TextView textViewClass;
    private TextView textViewModule;
    //Button
    private Button showDetail;

    //to send data

    public static StatisticFeedBackFragment newInstance() {
        return new StatisticFeedBackFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mviewModel = ViewModelProviders.of(this).get(StatisticFeedBackViewModel.class);
        mviewModel.init();
        mviewModel.getClassListLiveData().observe(this, new Observer<ClassList>() {
            @Override
            public void onChanged(ClassList classList) {
                //link: https://viblo.asia/p/android-architecture-components-viewmodel-xu-ly-configuration-changes-chua-bao-gio-don-gian-den-the-ByEZk3A4ZQ0
               //if (classList != null) {

                    adapter = new CustomAdapter(getActivity(),
                            R.layout.spinner_item_layout,
                            R.id.textView_item_name,
                            classList.getListClass());
                    spinner.setAdapter(adapter);
                    try {
                            spinner.setSelection(getArguments().getInt("class", 0));
                            }
                  catch (Exception e){}
               // }

            }
        });
        //Module
        mviewModel.getModuleListLiveData().observe(this, new Observer<ListModule>() {
            @Override
            public void onChanged(ListModule listModule) {
                //link: https://viblo.asia/p/android-architecture-components-viewmodel-xu-ly-configuration-changes-chua-bao-gio-don-gian-den-the-ByEZk3A4ZQ0
                //if (classList != null) {

                adapterModule = new CustomApdapterModule(getActivity(),
                        R.layout.spinner_item_layout,
                        R.id.textView_item_name,
                        listModule.getListModule());
                //spinner = (Spinner) v.findViewById(R.id.spinner_class);
                spinnerModule.setAdapter(adapterModule);
                try {
                    //spinner.setSelection(getArguments().getInt("class", 0));

                    spinnerModule.setSelection(getArguments().getInt("module", 0));
                }
                catch (Exception e){}
                // }

            }
        });
        mviewModel.getClassPosition().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {

                spinner.setSelection(integer);
            }
        });
        mviewModel.getModulePosition().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                spinnerModule.setSelection(integer);
            }
        });
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.statistic_feedback_fragment, container, false);
// Code for spinner class

        this.spinner = (Spinner) v.findViewById(R.id.txt_className);
         mviewModel.getClas(accessToken);
        this.spinnerModule = (Spinner) v.findViewById(R.id.spinner_module);
        mviewModel.getModule(accessToken);


// set content for textview class and module
        this.textViewClass = (TextView)v.findViewById(R.id.textViewClass);
        this.textViewModule = (TextView)v.findViewById(R.id.textViewModule);
// Click event for button View Detail
        showDetail = (Button) v.findViewById(R.id.show_detail);
        this.showDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("class", spinner.getSelectedItemPosition());
                bundle.putInt("module",spinnerModule.getSelectedItemPosition());
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_feedbackdetail, bundle);
            }
        });
// When user select a List-Item on spinner Class
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

// When user select a List-Item on spinner Module
        this.spinnerModule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandlerModule(parent, view, position, id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//Swipe right
        //onTouch1(v,MotionEvent );
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    x1 = event.getX();
                    y1 = event.getY();
                }
                //return true;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    x2 = event.getX();
                    y2 = event.getY();
                    if(x1 > x2)
                    {
                        int lassSelected =spinner.getSelectedItemPosition();
                        int moduleSelected = spinnerModule.getSelectedItemPosition();
                       // code
                        Bundle bundle = new Bundle();
                        bundle.putInt("class", lassSelected);
                        bundle.putInt("module",moduleSelected);
                        NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_feedbackright, bundle);
                    }
                    else{
                        NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_dofeedback);
                    }

                }
//Chart using MPAndroidChart
                pieChart = (PieChart)v.findViewById(R.id.PieChart);
                pieChart.setDescription("");
                pieChart.setRotationEnabled(false);  //cho phép xoay
                pieChart.setHoleRadius(0f);         //tên của chart, được viết trong 1 vòng tròn ở giữa chart với bán kính này
                pieChart.setTransparentCircleAlpha(0);  // vòng tròng trong suốt, chắc để tạo thêm hiệu ứng cho đẹp?
                pieChart.setDrawEntryLabels(false);// hide lable


                // Legend
                Legend l = pieChart.getLegend();
                l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
                l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
                l.setOrientation(Legend.LegendOrientation.VERTICAL);
                l.setDrawInside(false);
                l.setTextSize(15);
                l.setEnabled(true);
                addDataSet();

                // onDestroyView();
                return true;
            }
        });

//Chart using MPAndroidChart
        pieChart = (PieChart)v.findViewById(R.id.PieChart);
        pieChart.setDescription("");
        pieChart.setRotationEnabled(false);  //cho phép xoay
        pieChart.setHoleRadius(0f);         //tên của chart, được viết trong 1 vòng tròn ở giữa chart với bán kính này
        pieChart.setTransparentCircleAlpha(0);  // vòng tròng trong suốt, chắc để tạo thêm hiệu ứng cho đẹp?
        pieChart.setDrawEntryLabels(false);// hide lable
        // Legend
        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setTextSize(15);
        l.setEnabled(true);
        addDataSet();

        // return view
        return v;
    }
    //Handler to send data

// handler click spinner
        // Class
    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Class clas = (Class) adapter.getItem(position);
        String itemName = clas.getClassName();
        String a = "<font color=#000>Feedback statistics of Class </font>" + "<font color=#e8e23e>" + itemName +"</font>";
        textViewClass.setText(Html.fromHtml(a));
    }

// module
        private void onItemSelectedHandlerModule(AdapterView<?> adapterView, View view, int position, long id) {
            Adapter adapter = adapterView.getAdapter();
            Module module = (Module) adapter.getItem(position);
            String itemModule =module.getModuleName();
            String a = "<font color=#000>Feedback statistics of Module </font>" + "<font color=#e8e23e>" + itemModule + "</font>";
            textViewModule.setText(Html.fromHtml(a));

        }

//Set data for chart
    private void addDataSet() {
        //database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        ArrayList<String> status = new ArrayList<>();
        status.add("Strongly Disagree");
        status.add("Disgree");
        status.add("Neutral");
        status.add("Agree");
        status.add("Strong Agree");
        ArrayList<Integer> count = new ArrayList<>();
        count.add(5);
        count.add(4);
        count.add(1);
        count.add(0);
        count.add(0);
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        Integer sum = 10;

        /*Cursor cursor = database.rawQuery("Select Count(NOTE.Id),STATUS.Status from NOTE INNER JOIN STATUS ON NOTE.StatusId = STATUS.Id where NOTE.UserId = '"+ Id +"' group by StatusId",null);
        while(cursor.moveToNext())
        {
            String sStatus = cursor.getString(1);
            status.add(sStatus);
            Integer sCount = cursor.getInt(0);
            count.add(sCount);
            sum+=sCount;
        }
        cursor.close();*/

        for(int i=0;i<status.size();i++){
            yEntrys.add(new PieEntry( (float)count.get(i)/sum*100,status.get(i)));
        }
        PieDataSet pieDataSet = new PieDataSet(yEntrys,"");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(5);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#f2afa9"));
        colors.add(Color.parseColor("#f27c71"));
        colors.add(Color.parseColor("#FF6600"));
        colors.add(Color.parseColor("#F75536"));
        colors.add(Color.parseColor("#FC2E05"));
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextColor(Color.WHITE);
        pieData.setValueTextSize(13);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
/*
    private void LoadAllClass(View root){
        //"Bearer "+ new UserInfo(root.getContext()).token() - đáng ra là phải dùng token này để tương tác, nhưng retrofit đã hỗ trợ lưu
        //load list module lên fragment
//        listModuleReceived = response.body();
//        Toast.makeText(root.getContext(),listModuleReceived.getListModule().length,Toast.LENGTH_LONG).show();
//        setModuleAdapter(root);
        StatisticClassSAPIService.statisticClassSAPIService.getClas(accessToken).enqueue(new Callback<ClassList>() {
            @Override
            public void onResponse(Call<ClassList> call, Response<ClassList> response) {
                listClassReceived = response.body();
                setClassAdapter(root);
            }

            @Override
            public void onFailure(Call<ClassList> call, Throwable t) {

            }
        });
    }
    private void setClassAdapter(View root){
        adapter = new CustomAdapter(getActivity(),
                R.layout.spinner_item_layout,
                R.id.textView_item_name,
                listClassReceived.getListClass());
        spinner.setAdapter(adapter);
    }
*/
  }