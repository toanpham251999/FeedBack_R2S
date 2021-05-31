package com.example.feedbackapp.ui.feedback.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.ui.feedback.Interface.ICheckBoxListener;
import com.example.feedbackapp.ui.feedback.Model.ListQuestion;

import java.util.ArrayList;
import java.util.List;

public class QuestionReviewAdapter extends RecyclerView.Adapter<QuestionReviewAdapter.ViewHolder> {
    private List<ListQuestion> listQuestions;
    ArrayList<ListQuestion>arrListQuestion;
    QuestionReviewAdapter(List<ListQuestion>listQuestions)
    {
        this.listQuestions =listQuestions;
    }
    @NonNull
    @Override
    public QuestionReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_review_new_feedback_item_sub, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionReviewAdapter.ViewHolder holder, int position) {
        ListQuestion listQuestion = listQuestions.get(position);
        holder.txt_question_review.setText(listQuestion.getQuestionContent());
    }

    @Override
    public int getItemCount() {
        return listQuestions.size();
    }

//    @Override
//    public void onCheckBoxChecking(ArrayList<ListQuestion> arrayList) {
//        arrListQuestion=arrayList;
//        Log.d("checkbox","Check box OK");
//    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_question_review;
        ViewHolder(View itemView)
        {
            super(itemView);
            txt_question_review = itemView.findViewById(R.id.txt_question_review);
        }

    }
}
