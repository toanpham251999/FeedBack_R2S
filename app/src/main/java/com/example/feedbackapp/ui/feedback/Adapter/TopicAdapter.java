package com.example.feedbackapp.ui.feedback.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.ui.feedback.Interface.ICheckBoxListener;
import com.example.feedbackapp.ui.feedback.Model.ListQuestion;
import com.example.feedbackapp.ui.feedback.Model.ListTopic;
import com.example.feedbackapp.ui.feedback.Model.TopicModel;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {
    private RecyclerView.RecycledViewPool viewPool =new RecyclerView.RecycledViewPool();
    List<ListTopic> listTopics;
    private ICheckBoxListener iCheckBoxListener;
    public TopicAdapter (List<ListTopic>listTopics,  ICheckBoxListener iCheckBoxListener)
    {
        this.listTopics=listTopics;
        this.iCheckBoxListener = iCheckBoxListener;
    }

    @NonNull
    @Override
    public TopicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_create_feedback_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicAdapter.ViewHolder holder, int position) {
        ListTopic listTopic = listTopics.get(position);
        holder.txt_Topic.setText(listTopic.getTopicName());


        // Create layout manager with initial prefetch item count
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rcv_subItem.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(listTopic.getListQuestion().size());
        //Create subItem view adapter
        QuestionAdapter questionAdapter = new QuestionAdapter(listTopic.getListQuestion(),iCheckBoxListener);
        holder.rcv_subItem.setLayoutManager(layoutManager);
        holder.rcv_subItem.setAdapter(questionAdapter);
        holder.rcv_subItem.setRecycledViewPool(viewPool);

    }

    @Override
    public int getItemCount() {
        return listTopics.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_Topic;
        RecyclerView rcv_subItem;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //Ánh xạ view
            txt_Topic = itemView.findViewById(R.id.txt_topic);
            rcv_subItem = itemView.findViewById(R.id.rcv_question);
        }
    }
}
