package com.example.feedbackapp.ui.module;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.feedbackapp.Adapter.CustomAdapter;
import com.example.feedbackapp.R;
import com.example.feedbackapp.model.CustomItem;
import com.example.feedbackapp.model.CustomItemAdapter;
import com.example.feedbackapp.model.MyDate;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AddModuleFragment extends Fragment {
    // init
    LinearLayout moduleNameWrap, moduleStartDateWrap, moduleEndDateWrap,
                moduleFeedbackEndDateWrap, moduleFeedbackStartDateWrap;
    Button btnSave, btnBack;
    EditText txt_AddModuleName, txt_AddModuleStartDate, txt_AddModuleEndDate,
              txt_AddModuleFeedbackStartDate, txt_AddModuleFeedbackEndDate;

    Spinner adminSpiner, feedbackSpinner;
    ArrayList<CustomItem> customList;


    public static AddModuleFragment newInstance() {
        return new AddModuleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.add_module_layout, container, false);

        adminSpiner = root.findViewById(R.id.spn_listAdmin);
        feedbackSpinner = root.findViewById(R.id.spn_listFeedback);

        txt_AddModuleName = root.findViewById(R.id.txt_AddModuleName);
        txt_AddModuleStartDate = root.findViewById(R.id.txt_AddModuleStartDate);
        txt_AddModuleEndDate = root.findViewById(R.id.txt_AddModuleEndDate);
        txt_AddModuleFeedbackStartDate = root.findViewById(R.id.txt_AddModuleFeedbackStartDate);
        txt_AddModuleFeedbackEndDate = root.findViewById(R.id.txt_AddModuleFeedbackEndDate);

        moduleNameWrap = root.findViewById(R.id.moduleNameWrap);
        moduleStartDateWrap = root.findViewById(R.id.moduleStartDateWrap);
        moduleEndDateWrap = root.findViewById(R.id.moduleEndDateWrap);
        moduleFeedbackEndDateWrap = root.findViewById(R.id.moduleFeedbackEndDateWrap);
        moduleFeedbackStartDateWrap = root.findViewById(R.id.moduleFeedbackStartDateWrap);

        // get api
        customList = getCustomList();
        CustomItemAdapter adapter = new CustomItemAdapter(root.getContext(), customList);

        if(adminSpiner != null){
            adminSpiner.setAdapter(adapter);
            feedbackSpinner.setAdapter(adapter);
        }

        //save data
        btnSave = root.findViewById(R.id.btn_SaveAddModule);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean isAccept = OnSaveModuleValidate(root);
            }
        });


        return root;
    }


    private boolean OnSaveModuleValidate(View root){
        boolean flag = true;

        //tạo biến lưu giá trị Username và Password
        String moduleName = txt_AddModuleName.getText().toString();
        String moduleStartTime = txt_AddModuleStartDate.getText().toString();
        String moduleEndTime = txt_AddModuleEndDate.getText().toString();
        String feedbackStartTime = txt_AddModuleFeedbackStartDate.getText().toString();
        String feedbackEndTime = txt_AddModuleFeedbackEndDate.getText().toString();

        // check module name
        if(moduleName.isEmpty()){
            flag = false;
            try {
                TextView tvModuleNameNotify = root.findViewById(R.id.moduleNameNotify);
                ((ViewGroup) tvModuleNameNotify.getParent()).removeView(tvModuleNameNotify);
            }catch (Exception e){}


            TextView tvModuleNameNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
            tvModuleNameNotify.setId(R.id.moduleNameNotify);
            tvModuleNameNotify.setText("Please enter module name and less than 255");
            moduleNameWrap.addView(tvModuleNameNotify);
        }else{
            try {
                TextView tvModuleNameNotify = root.findViewById(R.id.moduleNameNotify);
                ((ViewGroup) tvModuleNameNotify.getParent()).removeView(tvModuleNameNotify);
            }catch (Exception e){}
        }

        // check module start time
        if(moduleStartTime.isEmpty()){
            flag = false;
            try {
                TextView tvModuleStartTimeNotify = root.findViewById(R.id.moduleStartTimeNotify);
                ((ViewGroup) tvModuleStartTimeNotify.getParent()).removeView(tvModuleStartTimeNotify);
            }catch (Exception e){}


            TextView tvModuleStartTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
            tvModuleStartTimeNotify.setId(R.id.moduleStartTimeNotify);
            tvModuleStartTimeNotify.setText("Please choose start date or fill mm/dd/yyyy");
            moduleStartDateWrap.addView(tvModuleStartTimeNotify);
        }else {
            MyDate startDate = convertStringToDate(moduleStartTime);

            if(startDate == null){
                flag = false;
                try {
                    TextView tvModuleStartTimeNotify = root.findViewById(R.id.moduleStartTimeNotify);
                    ((ViewGroup) tvModuleStartTimeNotify.getParent()).removeView(tvModuleStartTimeNotify);
                }catch (Exception e){}


                TextView tvModuleStartTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
                tvModuleStartTimeNotify.setId(R.id.moduleStartTimeNotify);
                tvModuleStartTimeNotify.setText("Date is not in the correct");
                moduleStartDateWrap.addView(tvModuleStartTimeNotify);
            }else{
                try {
                    TextView tvModuleStartTimeNotify = root.findViewById(R.id.moduleStartTimeNotify);
                    ((ViewGroup) tvModuleStartTimeNotify.getParent()).removeView(tvModuleStartTimeNotify);
                }catch (Exception e){}

                MyDate currentDate = getCurrentDate();

                if(!compareDate(currentDate, startDate)){
                    TextView tvModuleStartTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
                    tvModuleStartTimeNotify.setId(R.id.moduleStartTimeNotify);
                    tvModuleStartTimeNotify.setText("Please choose start date after now date");
                    moduleStartDateWrap.addView(tvModuleStartTimeNotify);
                }
            }
        }

        // check module end time
        if(moduleEndTime.isEmpty()){
            flag = false;
            try {
                TextView tvModuleEndTimeNotify = root.findViewById(R.id.moduleEndTimeNotify);
                ((ViewGroup) tvModuleEndTimeNotify.getParent()).removeView(tvModuleEndTimeNotify);
            }catch (Exception e){}


            TextView tvModuleEndTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
            tvModuleEndTimeNotify.setId(R.id.moduleEndTimeNotify);
            tvModuleEndTimeNotify.setText("Please choose start date or fill mm/dd/yyyy");
            moduleEndDateWrap.addView(tvModuleEndTimeNotify);
        }else {
            MyDate endDate = convertStringToDate(moduleEndTime);

            if(endDate == null){
                flag = false;
                try {
                    TextView tvModuleEndTimeNotify = root.findViewById(R.id.moduleEndTimeNotify);
                    ((ViewGroup) tvModuleEndTimeNotify.getParent()).removeView(tvModuleEndTimeNotify);
                }catch (Exception e){}


                TextView tvModuleEndTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
                tvModuleEndTimeNotify.setId(R.id.moduleStartTimeNotify);
                tvModuleEndTimeNotify.setText("Date is not in the correct");
                moduleStartDateWrap.addView(tvModuleEndTimeNotify);
            }else{
                try {
                    TextView tvModuleEndTimeNotify = root.findViewById(R.id.moduleEndTimeNotify);
                    ((ViewGroup) tvModuleEndTimeNotify.getParent()).removeView(tvModuleEndTimeNotify);
                }catch (Exception e){}
            }
        }


        // check module feedback start time
        if(feedbackStartTime.isEmpty()){
            flag = false;
            try {
                TextView tvModuleFeedbackStartTimeNotify = root.findViewById(R.id.moduleFeedbackStartTimeNotify);
                ((ViewGroup) tvModuleFeedbackStartTimeNotify.getParent()).removeView(tvModuleFeedbackStartTimeNotify);
            }catch (Exception e){}


            TextView tvModuleFeedbackStartTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
            tvModuleFeedbackStartTimeNotify.setId(R.id.moduleFeedbackStartTimeNotify);
            tvModuleFeedbackStartTimeNotify.setText("Please choose start date or fill mm/dd/yyyy");
            moduleFeedbackStartDateWrap.addView(tvModuleFeedbackStartTimeNotify);

        }else {
            MyDate startFeedbackDate = convertStringToDate(feedbackStartTime);

            if(startFeedbackDate == null){
                flag = false;
                try {
                    TextView tvModuleFeedbackStartTimeNotify = root.findViewById(R.id.moduleFeedbackStartTimeNotify);
                    ((ViewGroup) tvModuleFeedbackStartTimeNotify.getParent()).removeView(tvModuleFeedbackStartTimeNotify);
                }catch (Exception e){}

                TextView tvModuleFeedbackStartTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
                tvModuleFeedbackStartTimeNotify.setId(R.id.moduleFeedbackStartTimeNotify);
                tvModuleFeedbackStartTimeNotify.setText("Date is not in the correct");
                moduleFeedbackStartDateWrap.addView(tvModuleFeedbackStartTimeNotify);
            }else{
                try {
                    TextView tvModuleFeedbackStartTimeNotify = root.findViewById(R.id.moduleFeedbackStartTimeNotify);
                    ((ViewGroup) tvModuleFeedbackStartTimeNotify.getParent()).removeView(tvModuleFeedbackStartTimeNotify);
                }catch (Exception e){}

                MyDate currentDate = getCurrentDate();

                if(!compareDate(currentDate, startFeedbackDate)){
                    TextView tvModuleFeedbackStartTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
                    tvModuleFeedbackStartTimeNotify.setId(R.id.moduleStartTimeNotify);
                    tvModuleFeedbackStartTimeNotify.setText("Please choose feedback start date after now date");
                    moduleFeedbackStartDateWrap.addView(tvModuleFeedbackStartTimeNotify);
                }
            }
        }


        // check module feedback end time
        if(feedbackEndTime.isEmpty()){
            flag = false;
            try {
                TextView tvModuleFeedbackEndTimeNotify = root.findViewById(R.id.moduleFeedbackEndTimeNotify);
                ((ViewGroup) tvModuleFeedbackEndTimeNotify.getParent()).removeView(tvModuleFeedbackEndTimeNotify);
            }catch (Exception e){}


            TextView tvModuleFeedbackEndTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
            tvModuleFeedbackEndTimeNotify.setId(R.id.moduleFeedbackEndTimeNotify);
            tvModuleFeedbackEndTimeNotify.setText("Please choose start date or fill mm/dd/yyyy");
            moduleFeedbackEndDateWrap.addView(tvModuleFeedbackEndTimeNotify);

        }else {
            MyDate endFeedbackDate = convertStringToDate(feedbackEndTime);

            if(endFeedbackDate == null){
                flag = false;
                try {
                    TextView tvModuleFeedbackEndTimeNotify = root.findViewById(R.id.moduleFeedbackEndTimeNotify);
                    ((ViewGroup) tvModuleFeedbackEndTimeNotify.getParent()).removeView(tvModuleFeedbackEndTimeNotify);
                }catch (Exception e){}

                TextView tvModuleFeedbackEndTimeNotify = new TextView(root.getContext(), null, 0, R.style.notifyWarning);
                tvModuleFeedbackEndTimeNotify.setId(R.id.moduleFeedbackStartTimeNotify);
                tvModuleFeedbackEndTimeNotify.setText("Date is not in the correct");
                moduleFeedbackEndDateWrap.addView(tvModuleFeedbackEndTimeNotify);
            }else{
                try {
                    TextView tvModuleFeedbackEndTimeNotify = root.findViewById(R.id.moduleFeedbackEndTimeNotify);
                    ((ViewGroup) tvModuleFeedbackEndTimeNotify.getParent()).removeView(tvModuleFeedbackEndTimeNotify);
                }catch (Exception e){}
            }
        }

        return flag;
    }

    private MyDate getCurrentDate(){
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        Date date = calendar.getTime();
        int day = calendar.get(Calendar.DATE);
        //Note: +1 the month for current month
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        MyDate currentDate = new MyDate(day, month, year);
        return currentDate;
    }

    private boolean compareDate(MyDate firstDate, MyDate secondDate){
        if(firstDate.getYear() > secondDate.getYear()) return false;
        if(firstDate.getMonth() > secondDate.getMonth()) return false;
        if(firstDate.getDate() > secondDate.getDate()) return false;

        return true;
    }

    private MyDate convertStringToDate(String strDate){
        String[] arrOfStr = strDate.split("/");
        int lengthTerm = arrOfStr.length;
        if(lengthTerm != 3) return null;

        // check year
        int year = 2000;
        try{
            year = Integer.parseInt(arrOfStr[2]);
            if(year < 2000) return null;
        }catch (Exception ex){
            return null;
        }

        // check month
        int month = 1;
        try{
            month = Integer.parseInt(arrOfStr[0]);
            if(month > 12 || month < 1) return null;
        }catch (Exception ex){
            return null;
        }


        // check date
        int date = 1;
        try{
            date = Integer.parseInt(arrOfStr[1]);
            int[] arrMonth = {31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if(isLeapYear(year)){
                arrMonth[2] = 29;
            }

            if(date < 1 || date > arrMonth[month]) return null;

        }catch (Exception ex){
            return null;
        }

        return new MyDate(date, month, year);
    }

    private boolean isLeapYear (int year){
        boolean isLeap = false;
        if(year % 4 == 0)//chia hết cho 4 là năm nhuận
        {
            if( year % 100 == 0)
            //nếu vừa chia hết cho 4 mà vừa chia hết cho 100 thì không phải năm nhuận
            {
                if ( year % 400 == 0)//chia hết cho 400 là năm nhuận
                    isLeap = true;
                else
                    isLeap = false;//không chia hết cho 400 thì không phải năm nhuận
            }
            else//chia hết cho 4 nhưng không chia hết cho 100 là năm nhuận
                isLeap = true;
        }
        else {
            isLeap = false;
        }

        return isLeap;
    }

    private ArrayList<CustomItem> getCustomList() {
        customList = new ArrayList<CustomItem>();
        customList.add(new CustomItem("Android1", "android id 1"));
        customList.add(new CustomItem("Android2", "android id 2"));
        customList.add(new CustomItem("Android3", "android id 3"));

        return customList;
    }
}
