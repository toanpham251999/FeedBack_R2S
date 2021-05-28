package com.example.feedbackapp.ui.statisticfeedback;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.Adapter.ClassDataUtils;
import com.example.feedbackapp.R;
import com.example.feedbackapp.model.HeaderRecycleView;
import com.example.feedbackapp.model.Question;
import com.example.feedbackapp.model.Topic;

import java.util.ArrayList;

public class TopicAdp extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
   // Initial activity and array list
    private Activity activity;
    ArrayList<Topic> arrayListTopic;
    HeaderRecycleView headerRecycleView;
    //Create contructor
TopicAdp(Activity activity, ArrayList<Topic> arrayListTopic, HeaderRecycleView headerRecycleView){
    this.activity =activity;
    this.arrayListTopic = arrayListTopic;
    this.headerRecycleView = headerRecycleView;
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
                    // Handle event submit feed back

                }
            });
        }
        else {
            // Set topic name on TextView
            TopicViewHolder topicViewHolder = (TopicViewHolder) holder;
            //holder.topicName.setText(arrayListTopic.get(position));
            topicViewHolder.topicName.setText(arrayListTopic.get(position).getTopicName());

        //Initialize memer ArrayList
            ArrayList<Question> arrayListQuestion = new ArrayList<>();
            arrayListQuestion = ClassDataUtils.getQuestion(position);
       /* //Using loop to add multiple member
        for(int i = 1; i <= 4; i++)
        {
            arrayListQuestion.add("Member " + i);
        }*/

            //Initialize member adapter
        QuestionAdp adapterQuestion = new QuestionAdp(arrayListQuestion);

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
        if (position == 0)
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
}
//Link refer:https://eitguide.net/multiple-view-type-trong-recyclerview/
//link refer header footer: http://www.devexchanges.info/2016/10/adding-header-and-footer-layouts-for.html