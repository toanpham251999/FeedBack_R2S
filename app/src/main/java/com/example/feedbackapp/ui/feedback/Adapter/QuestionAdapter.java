package com.example.feedbackapp.ui.feedback.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.constant.SystemConstant;
import com.example.feedbackapp.ui.feedback.Model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<Question> listQuestions;
    public ArrayList<Question>arrayList_id =  new ArrayList<>();

    public QuestionAdapter() {
         this.listQuestions = new ArrayList<>();
    }

    public QuestionAdapter(List<Question>listQuestions)
    {
        this.listQuestions =listQuestions;
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
        //Question question = listQuestions.get(position);
        holder.ck_question.setText(listQuestions.get(position).getQuestionContent());
        holder.ck_question.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(holder.ck_question.isChecked())
                {
                    //arrayList_id.add(listQuestions.get(position));
                    Log.i(" LIST QUESTION VALUES", "123"+listQuestions.get(position).toString());
                    SystemConstant.arrayList_id.add(listQuestions.get(position));
                    Log.i("ARRAY LIST VALUES", "123"+arrayList_id.toString());

                }
                else
                {
                    SystemConstant.arrayList_id.remove(listQuestions.get(position));

                }

            }
        });

    }
    public ArrayList<Question> onclicked()
    {
        return SystemConstant.arrayList_id;
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
