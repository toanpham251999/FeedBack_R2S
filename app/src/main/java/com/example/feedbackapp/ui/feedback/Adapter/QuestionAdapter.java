package com.example.feedbackapp.ui.feedback.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.ui.feedback.Interface.ICheckBoxListener;
import com.example.feedbackapp.ui.feedback.Model.ListQuestion;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<ListQuestion>listQuestions;
    private ICheckBoxListener checkBoxListener;
    ArrayList<ListQuestion>arrayList_id;
   private ArrayList<String>arrayList = new ArrayList<String >();

    QuestionAdapter(List<ListQuestion>listQuestions, ICheckBoxListener checkBoxListener)
    {
        this.listQuestions =listQuestions;
        this.checkBoxListener = checkBoxListener;
    }
    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_create_feedback_subitem, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {
        ListQuestion listQuestion = listQuestions.get(position);
        holder.ck_question.setText(listQuestion.getQuestionContent());
        holder.ck_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.ck_question.isChecked())
                {
                    //arrayList_id.add(listQuestions.get(position));
                    Log.d("Check",listQuestion.getId());
                    arrayList.add(listQuestion.getId());
                }
                else
                {
                    arrayList.remove(listQuestion.getId());
                }
                //checkBoxListener.onCheckBoxChecking(arrayList);
            }
        });

    }
    public ArrayList<String> onclicked()
    {
        return this.arrayList;
    }

    @Override
    public int getItemCount() {
        return listQuestions.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {
        CheckBox ck_question;
        ViewHolder(View itemView)
        {
            super(itemView);
            ck_question = itemView.findViewById(R.id.ck_question);
        }

    }
}
