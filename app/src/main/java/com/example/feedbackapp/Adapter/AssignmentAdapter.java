package com.example.feedbackapp.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Assignment.Assignment;
import com.example.feedbackapp.R;

import java.util.ArrayList;
import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder>{
    Context context;
    ArrayList<Assignment> listAssignment;

    public AssignmentAdapter(Context context, ArrayList<Assignment> listAssignment) {
        this.context = context;
        this.listAssignment = listAssignment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.assignment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Gán dữ liêuk
        String Tv = "";
        Assignment assignment = listAssignment.get(position);

        Tv = "<b>" + holder.textView_idAssignment.getText().toString() +"</b>";
        holder.textView_idAssignment.setText(Html.fromHtml(Tv + (position + 1)));

        Tv = "<b>" + holder.textView_moduleName.getText().toString() +"</b>";
        holder.textView_moduleName.setText(Html.fromHtml(Tv + assignment.getModuleName()));

        Tv = "<b>" + holder.textView_className.getText().toString() +"</b>";
        holder.textView_className.setText(Html.fromHtml(Tv + assignment.getClassName()));

        Tv = "<b>" + holder.textView_trainerName.getText().toString() +"</b>";
        holder.textView_trainerName.setText(Html.fromHtml(Tv + assignment.getTrainerName()));

        holder.textView_rCode.setText(assignment.getRegistrationCode());
    }

    @Override
    public int getItemCount() {
        return listAssignment.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_idAssignment, textView_moduleName,
                textView_className, textView_trainerName, textView_rCode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ view
            textView_idAssignment = itemView.findViewById(R.id.textView_idAssignment);
            textView_moduleName = itemView.findViewById(R.id.textView_moduleName);
            textView_className = itemView.findViewById(R.id.textView_className);
            textView_trainerName = itemView.findViewById(R.id.textView_trainerName);
            textView_rCode = itemView.findViewById(R.id.textView_rCode);
        }
    }
}
