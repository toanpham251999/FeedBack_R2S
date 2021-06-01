package com.example.feedbackapp.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Question.Question;

import java.lang.ref.WeakReference;
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

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView btn_edit_question, btn_delete_question;
        TextView textView_idTopic, textView_topicName,
                textView_questionId, textView_questionContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ view
            btn_edit_question = itemView.findViewById(R.id.btn_edit_question);
            btn_delete_question = itemView.findViewById(R.id.btn_delete_question);

            textView_idTopic = itemView.findViewById(R.id.textView_idTopic);
            textView_topicName = itemView.findViewById(R.id.textView_topicName);
            textView_questionId = itemView.findViewById(R.id.textView_questionId);
            textView_questionContent = itemView.findViewById(R.id.textView_questionContent);

            btn_edit_question.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"nhấn Edit Question" + getAdapterPosition(),Toast.LENGTH_LONG).show();
                }
            });
            btn_delete_question.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"nhấn Delete Question" + getAdapterPosition(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
