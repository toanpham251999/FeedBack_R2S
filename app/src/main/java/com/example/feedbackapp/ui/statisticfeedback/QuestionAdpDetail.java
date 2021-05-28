package com.example.feedbackapp.ui.statisticfeedback;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.model.Question;

import java.util.ArrayList;

public class QuestionAdpDetail extends RecyclerView.Adapter<QuestionAdpDetail.ViewHolder> {
    // Initialize Arraylist
    ArrayList<Question> arrayListQuestion;

    //Create constructor
   public QuestionAdpDetail(ArrayList<Question> arrayListQuestion){
        this.arrayListQuestion = arrayListQuestion;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Initalize view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_question_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Set question name on TextView
        // need a getPercnet in class ClassDataUtils.java by id question
        holder.questionName.setText(arrayListQuestion.get(position).getQuestionContent());
        holder.stronglyDisagree.setText("100%");
        holder.disagree.setText("100%");
        holder.neutral.setText("100%");
        holder.strongAgree.setText("100%");
        holder.agree.setText("100%");
    }

    @Override
    public int getItemCount() {
        return arrayListQuestion.size();
    }
    // Initialize radio
    private void setPercent(final ViewHolder holder, int selection) {



    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Inittial variable
        TextView questionName, stronglyDisagree, disagree, neutral, agree, strongAgree;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Asign variable
            questionName = itemView.findViewById(R.id.questionName);
            stronglyDisagree = itemView.findViewById(R.id.StronglyDisagree);
            disagree = itemView.findViewById(R.id.Disagree);
            neutral = itemView.findViewById(R.id.Neutral);
            strongAgree = itemView.findViewById(R.id.StrongAgree);
            agree = itemView.findViewById(R.id.Agree);
        }
    }
}



