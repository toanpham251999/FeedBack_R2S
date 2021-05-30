package com.example.feedbackapp.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.Assignment;
import com.example.feedbackapp.R;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Question.Question;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{
    Context context;
    ArrayList<Question> questionArrayList;

    public QuestionAdapter(Context context, ArrayList<Question> questionArrayList) {
        this.context = context;
        this.questionArrayList = questionArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.question_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Gán dữ liêuk
        Question question = questionArrayList.get(position);
        holder.textView_idTopic.setText(Html.fromHtml("<b>Topic Id: </b>"+ question.getTopicId()));
        holder.textView_topicName.setText(Html.fromHtml("<b>Topic Name: </b>"+question.getTopicName()));
        holder.textView_questionId.setText(Html.fromHtml("<b>Question Id: </b>"+question.getId()));
        holder.textView_questionContent.setText(Html.fromHtml("<b>Question Content: </b>"+question.getQuestionContent()));

    }

    @Override
    public int getItemCount() {
        return questionArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_idTopic, textView_topicName,
                textView_questionId, textView_questionContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ view
            textView_idTopic = itemView.findViewById(R.id.textView_idTopic);
            textView_topicName = itemView.findViewById(R.id.textView_topicName);
            textView_questionId = itemView.findViewById(R.id.textView_questionId);
            textView_questionContent = itemView.findViewById(R.id.textView_questionContent);
        }
    }
}
