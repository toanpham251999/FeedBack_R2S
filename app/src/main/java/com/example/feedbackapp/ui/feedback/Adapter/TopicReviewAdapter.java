package com.example.feedbackapp.ui.feedback.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.ui.feedback.Model.ListTopic;

import java.util.List;

public class TopicReviewAdapter extends RecyclerView.Adapter<TopicReviewAdapter.ViewHolder> {
    private RecyclerView.RecycledViewPool viewPool =new RecyclerView.RecycledViewPool();
    List<ListTopic> listTopics;
    public TopicReviewAdapter (List<ListTopic>listTopics)
    {
        this.listTopics=listTopics;
    }

    @NonNull
    @Override
    public TopicReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_review_new_feedback_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicReviewAdapter.ViewHolder holder, int position) {
        ListTopic listTopic = listTopics.get(position);
        holder.txt_Topic.setText(listTopic.getTopicName());

        // Create layout manager with initial prefetch item count
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rcv_review_subItem.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(listTopic.getListQuestion().size());
        //Create subItem view adapter
        QuestionReviewAdapter questionReviewAdapter = new QuestionReviewAdapter(listTopic.getListQuestion());
        holder.rcv_review_subItem.setLayoutManager(layoutManager);
        holder.rcv_review_subItem.setAdapter(questionReviewAdapter);
        holder.rcv_review_subItem.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return listTopics.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_Topic;
        RecyclerView rcv_review_subItem;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //Ánh xạ view
            txt_Topic = itemView.findViewById(R.id.txt_topic_review);
            rcv_review_subItem = itemView.findViewById(R.id.rcv_review_sub);
        }
    }
}
