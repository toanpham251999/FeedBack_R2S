package com.example.feedbackapp.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Feedback.Feedback;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import com.example.feedbackapp.R;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> {
    Context context;
    ArrayList<Feedback> listFeedback;

    public FeedbackAdapter(Context context, ArrayList<Feedback> listFeedback){
        this.context = context;
        this.listFeedback = listFeedback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.feedback_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listFeedback.size();
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feedback feedback = listFeedback.get(position);

        //gán dữ liệu cho từng item
        holder.txtFeedbackTitle.setText(Html.fromHtml("<b>Feedback Title: </b>" + feedback.getTitle()) );
        holder.txtClassId.setText(Html.fromHtml("<b>Class Id: </b>" + feedback.getClassId()));
        holder.txtClassName.setText(Html.fromHtml("<b>Class Name: </b>" + feedback.getClassName()));
        holder.txtModuleId.setText(Html.fromHtml("<b>Module Id: </b>" + feedback.getModuleId()));
        holder.txtModuleName.setText(Html.fromHtml("<b>Module Name: </b>" + feedback.getModuleName()));
        holder.txtEndTime.setText(Html.fromHtml("<b>End Time: </b>" + feedback.getEndTime()));

        // check is completed
        if(feedback.getIsCompleted()){
            holder.txtStatus.setText(Html.fromHtml("<b>Status: </b>" + "InComplete"));
        }else {
            holder.txtStatus.setText(Html.fromHtml("<b>Status: </b>" + "Complete"));
        }

    }


    class ViewHolder extends RecyclerView.ViewHolder {
        //khởi tạo các view con trong 1 item
        TextView txtFeedbackTitle, txtClassId, txtClassName, txtModuleId, txtModuleName, txtEndTime, txtStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtFeedbackTitle = itemView.findViewById(R.id.txtFeedbackTitle);
            txtClassId = itemView.findViewById(R.id.txtClassId);
            txtClassName = itemView.findViewById(R.id.txtClassName);
            txtModuleId = itemView.findViewById(R.id.txtModuleId);
            txtModuleName = itemView.findViewById(R.id.txtModuleName);
            txtEndTime = itemView.findViewById(R.id.txtEndTime);
            txtStatus = itemView.findViewById(R.id.txtStatus);

        }
    }

}
