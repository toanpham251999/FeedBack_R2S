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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.feedbackapp.Adapter.ClassDataUtils;
import com.example.feedbackapp.Adapter.CustomAdapter;
import com.example.feedbackapp.Adapter.CustomApdapterModule;
import com.example.feedbackapp.R;
import com.example.feedbackapp.model.Class;
import com.example.feedbackapp.model.Module;
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
    //swipe left
    float x1, x2, y1 , y2;
    //chart
    PieChart pieChart;
    //declare for spinner
    private Spinner spinner;// for clss
    private Spinner spinnerModule;
    private List<Class> classess;
    private List<Module> modules;
    private StatisticFeedackViewModel mViewModel;
    private  View v;
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.statistic_feedback_fragment, container, false);

// Code for spinner class
        // Data:
        this.classess = ClassDataUtils.getClasss();

        this.spinner = (Spinner) v.findViewById(R.id.spinner_class);

        // Adapter"
        CustomAdapter adapter = new CustomAdapter(this.getActivity(),
                R.layout.spinner_item_layout,
                R.id.textView_item_name,
                this.classess);


        this.spinner.setAdapter(adapter);
        // End for spinner class
        //code for spinner module
        //data
        this.modules = ClassDataUtils.getModule();

        this.spinnerModule = (Spinner) v.findViewById(R.id.spinner_module);

        // Adapter"
        CustomApdapterModule adapterModule = new CustomApdapterModule(this.getActivity(),
                R.layout.spinner_item_layout,
                R.id.textView_item_name,
                this.modules);

        this.spinnerModule.setAdapter(adapterModule);
// get data from feedbackrightfragment
        try{
        this.spinner.setSelection(getArguments().getInt("class"));
        this.spinnerModule.setSelection(getArguments().getInt("module"));
        }
        catch (Exception exception ){

        }

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
                //Navigation.findNavController(v).navigate(R.id.action_feedback_to_feedbackdetail, bundle);
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
                        //Navigation.findNavController(v).navigate(R.id.action_fragment1_to_fragment2, bundle);
                      //Navigation.findNavController(v).navigate(R.id.action_feedback_to_feedbackdetail);
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
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StatisticFeedackViewModel.class);
        // TODO: Use the ViewModel
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

  }