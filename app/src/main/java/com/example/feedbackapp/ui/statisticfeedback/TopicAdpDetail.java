package com.example.feedbackapp.ui.statisticfeedback;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.Adapter.ClassDataUtils;
import com.example.feedbackapp.R;
import com.example.feedbackapp.model.Question;
import com.example.feedbackapp.model.Topic;

import java.util.ArrayList;

public class TopicAdpDetail extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // Initial activity and array list
    private Activity activity;
    ArrayList<Topic> arrayListTopic;
    //Create contructor
    TopicAdpDetail(Activity activity, ArrayList<Topic> arrayListTopic){
        this.activity =activity;
        this.arrayListTopic = arrayListTopic;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_row_topic, parent, false);
            return new TopicViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            // Set topic name on TextView
        TopicViewHolder topicViewHolder = (TopicViewHolder) holder;
            //holder.topicName.setText(arrayListTopic.get(position));
            topicViewHolder.topicName.setText(arrayListTopic.get(position).getTopicName());

            //Initialize memer ArrayList
            ArrayList<Question> arrayListQuestion = new ArrayList<>();
            arrayListQuestion = ClassDataUtils.getQuestionDetail(position);

            //Initialize member adapter
            QuestionAdpDetail adapterQuestion = new QuestionAdpDetail(arrayListQuestion);

            //Initialize layout manager
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
            //Set layout manager
            topicViewHolder.rcvListQuestion.setLayoutManager(linearLayoutManager);
            // Set adapter
            topicViewHolder.rcvListQuestion.setAdapter(adapterQuestion);
    }

    @Override
    public int getItemCount() {
        return arrayListTopic.size();
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

}
//Link refer:https://eitguide.net/multiple-view-type-trong-recyclerview/
//link refer header footer: http://www.devexchanges.info/2016/10/adding-header-and-footer-layouts-for.html