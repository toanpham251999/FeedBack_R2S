package com.example.feedbackapp.ui.statisticfeedback;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.Adapter.ClassDataUtils;
import com.example.feedbackapp.ModelClassToSendAPI.Answer.Answer;
import com.example.feedbackapp.R;
import com.example.feedbackapp.model.HeaderRecycleView;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.Question;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Toppic.Topic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopicAdp extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //Init vavirables to package answer
    private String ClassId;
    private String ModuleId;
    private String QuestionId;
    //private ArrayList<Integer> ListValue;
    private ArrayList<com.example.feedbackapp.model.Question> newArrayListQuestion = new ArrayList<com.example.feedbackapp.model.Question>();
   // Initial activity and array list
    private Activity activity;
    ArrayList<Topic> arrayListTopic;
    HeaderRecycleView headerRecycleView;
    private ArrayList<Question> arrayListQuestion;
    private QuestionAdp adapterQuestion;
    //Create contructor
TopicAdp(Activity activity, ArrayList<Topic> arrayListTopic, HeaderRecycleView headerRecycleView){
    this.activity =activity;
    this.arrayListTopic = arrayListTopic;
    this.headerRecycleView = headerRecycleView;
    //Arrays.fill(this.ListValue, );
}
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == 1){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_row_topic, parent, false);
            return new TopicViewHolder(view);
        }
        else if(viewType == -1){
            View view2 = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.footer_recycleview, parent, false);
            return new FooterViewHolder(view2);
        }
        {
            View view1 = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_recycleview, parent, false);
            return new HeaderViewHolder(view1);
        }

     //return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Code text header
        String stt;
        switch (position){
            case 1: stt = "I. ";break;
            case 2: stt = "II. ";break;
            case 3: stt = "III. ";break;
            default: stt = "IV. ";break;
        }

        if(position == 0)
        {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.clas.setText(headerRecycleView.getClas());
            headerViewHolder.module.setText(headerRecycleView.getModule());
            headerViewHolder.name.setText(headerRecycleView.getName());
        }
        else if(position == (arrayListTopic.size() - 1))
        {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            ((FooterViewHolder) holder).submitFeedback.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                 /*   Thêm 1 answer: String classId, String moduleId, String questionId, int value
                    + bên QuestionAdp: + OnbindViewHolder: có được questionId, value :
                            + ben ngoài lấy được: ClassId, ModuleId đã truyền qua bundle tới. THÊM BIẾN VÀO HÀM KHỞI TẠO
                            + nut submit ở AdpTopic.:   Ở ĐÂY*/
                            // Handle event submit feed back
                    ArrayList<Answer> arrrListAnswer = new ArrayList<Answer>();
                    int vt = 0;
                    for(int i =1; i < (arrayListTopic.size() - 1); i++)
                    {

                        for(int j =0; j < arrayListTopic.get(i).getListQuestion().size() ; j++ ){
                            Answer answer = new Answer(headerRecycleView.getClassId(), headerRecycleView.getModuleId(), arrayListTopic.get(i).getListQuestion().get(j).getId(),newArrayListQuestion.get(vt).getAnswer());
                            arrrListAnswer.add(answer);
                            vt++;
                        }
                        if(newArrayListQuestion.get(vt-1).getAnswer() == -1)
                        {
                            ShowLoginFailDialog();
                            break;
                        }

                    }
                    int a;
                    // Hàm xử lý gửi dữ liệu lên
                   ShowConFirmDialog(arrrListAnswer);
                }
            });
        }
        else {
            // Set topic name on TextView
            TopicViewHolder topicViewHolder = (TopicViewHolder) holder;
            //holder.topicName.setText(arrayListTopic.get(position));
            topicViewHolder.topicName.setText(stt + arrayListTopic.get(position).getTopicName());

        //Initialize memer ArrayList
            arrayListQuestion = arrayListTopic.get(position).getListQuestion();
            //newArrayListQuestion.clear();
            // add answer
            ArrayList<com.example.feedbackapp.model.Question> newArrayListQuestionCall = new ArrayList<com.example.feedbackapp.model.Question>();
            for(int i = 0; i < arrayListQuestion.size() ; i++)
            {
                Question question = arrayListQuestion.get(i);
                com.example.feedbackapp.model.Question newQuestion = new com.example.feedbackapp.model.Question(question.getId(), question.getQuestionContent(), -1);
                newArrayListQuestion.add(newQuestion);
                newArrayListQuestionCall.add(newQuestion);
            }
            //Initialize member adapter
            //adapterQuestion = new QuestionAdp((ArrayList<com.example.feedbackapp.model.Question>) newArrayListQuestion.subList(newArrayListQuestion.size() - arrayListQuestion.size(), newArrayListQuestion.size() - 1), position);
            adapterQuestion = new QuestionAdp(newArrayListQuestionCall, position);
        //Initialize layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        //Set layout manager
            topicViewHolder.rcvListQuestion.setLayoutManager(linearLayoutManager);
        // Set adapter
            topicViewHolder.rcvListQuestion.setAdapter(adapterQuestion);
        }
    }

    @Override
    public int getItemCount() {
        return arrayListTopic.size();
    }
// Get type item if position == 0 : is header, else: is topic
    @Override
    public int getItemViewType(int position) {
        if (position ==  0)
            return 0;//header
        else if (position == arrayListTopic.size() - 1)
            return -1;//footer
        else
            return 1;

    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
       //Initialize variable
        TextView topicName;
        RecyclerView rcvListQuestion;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            topicName = itemView.findViewById(R.id.topicName);
            rcvListQuestion = itemView.findViewById(R.id.rcvListQuestion);

        }
    }
   //Header for recycleview
    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable
        TextView module;
        TextView clas;
        TextView name;
        RecyclerView rcvListQuestion;
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            module = itemView.findViewById(R.id.textView_module);
            clas = itemView.findViewById(R.id.textView_class);
            name = itemView.findViewById(R.id.textViewNameUser);
            rcvListQuestion = itemView.findViewById(R.id.rcvListQuestion);

        }
    }
    //Footer holder
    public class FooterViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable
        Button submitFeedback;
        RecyclerView rcvListQuestion;

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            submitFeedback = itemView.findViewById(R.id.SubmitFeedback);
            rcvListQuestion = itemView.findViewById(R.id.rcvListQuestion);
        }
    }
    void ShowLoginFailDialog(){
        //hiện dialog complete do feedback failed
        LayoutInflater inflater = activity.getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.login_failed_dialog, null);
        TextView txt_LogoutTitle = (TextView) alertLayout.findViewById(R.id.txt_LogoutTitle);
        txt_LogoutTitle.setText("Please complete your feedback!");
        final Button btnYes = (Button) alertLayout.findViewById(R.id.btn_Yes);
        //final AlertDialog alertDialog=new AlertDialog.Builder(activity.getApplicationContext()).create();
        AlertDialog.Builder alert = new AlertDialog.Builder(alertLayout.getContext());
        alert.setView(alertLayout);
        alert.setCancelable(false);
        AlertDialog dialog = alert.create();
        btnYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    void ShowConFirmDialog(ArrayList<Answer> listAnswer){
        //hiện dialog complete do feedback failed
        LayoutInflater inflater = activity.getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.logout_confirm_dialog, null);
        TextView txt_LogoutMessage = (TextView) alertLayout.findViewById(R.id.txt_LogoutMessage);
        txt_LogoutMessage.setText("Do you want to submit feedback?");
        final Button btnYes = (Button) alertLayout.findViewById(R.id.btn_Yes);
        final Button btnCancel= (Button) alertLayout.findViewById(R.id.btn_Cancel);
        AlertDialog.Builder alert = new AlertDialog.Builder(alertLayout.getContext());
        alert.setView(alertLayout);
        alert.setCancelable(false);
        AlertDialog dialog = alert.create();
        btnYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
                // hiện dialog thông báo thành công

                //thực hiện API: đưa answer lên, update iscompleted

                //(Xóa nút sửa) navigation đến traineeDashboardFragment.
                // Đẩy comment lên nha
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
//Link refer:https://eitguide.net/multiple-view-type-trong-recyclerview/
//link refer header footer: http://www.devexchanges.info/2016/10/adding-header-and-footer-layouts-for.html