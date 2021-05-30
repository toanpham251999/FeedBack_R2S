package com.example.feedbackapp.ui.feedback.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.ui.feedback.Model.ListFeedback;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> {
    List<ListFeedback> listFeedback;
    public  FeedbackAdapter(List<ListFeedback>listFeedback)
    {
        this.listFeedback=listFeedback;
    }

    @NonNull
    @Override
    public FeedbackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //gán view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_feedback_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListFeedback list = listFeedback.get(position);
        holder.txtFeedbackId.setText(list.getTypeFeedbackId());
        holder.txtFeedbackTitle.setText(list.getTitle());
        holder.txtAdminId.setText(list.getAdminId());

    }
    public int getItemCount()
    {
        return listFeedback.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtFeedbackId;
        TextView txtFeedbackTitle;
        TextView txtAdminId;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //Ánh xạ view
            txtFeedbackId =itemView.findViewById(R.id.txtFeedbackID);
            txtFeedbackTitle =itemView.findViewById(R.id.txtFeedbackTitle);
            txtAdminId=itemView.findViewById(R.id.txtFeedbackAdminID);
        }
    }
}
