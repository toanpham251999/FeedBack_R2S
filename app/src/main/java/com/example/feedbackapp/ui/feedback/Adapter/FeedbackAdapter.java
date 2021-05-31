package com.example.feedbackapp.ui.feedback.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.ui.feedback.FeedBackFragment;
import com.example.feedbackapp.ui.feedback.Fragment_Edit_Feedback;
import com.example.feedbackapp.ui.feedback.Model.ListFeedback;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> {
    List<ListFeedback> listFeedback;
    public  FeedbackAdapter(List<ListFeedback>listFeedback)
    {
        this.listFeedback=listFeedback;
    }
    FeedBackFragment feedBackFragment = new FeedBackFragment();

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

        holder.imgEditFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_edit_feedback);
            }
        });
        holder.imgDetailFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_detail_feedback);
            }
        });
    }
    public int getItemCount()
    {
        return listFeedback.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtFeedbackId;
        TextView txtFeedbackTitle;
        TextView txtAdminId;
        ImageView imgDetailFeedback;
        ImageView imgEditFeedback;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //Ánh xạ view
            txtFeedbackId =itemView.findViewById(R.id.txtFeedbackID);
            txtFeedbackTitle =itemView.findViewById(R.id.txtFeedbackTitle);
            txtAdminId=itemView.findViewById(R.id.txtFeedbackAdminID);
            imgEditFeedback=itemView.findViewById(R.id.btn_Edit);
            imgDetailFeedback=itemView.findViewById(R.id.btnView);
        }
    }
}
