package com.example.feedbackapp.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Class.Classs;
import com.example.feedbackapp.R;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {
    Context context;
    ArrayList<Classs> listClass;

    public ClassAdapter(Context context, ArrayList<Classs> listClass) {
        this.context = context;
        this.listClass = listClass;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.item_class_manager_layout, parent, false);
        return new ClassAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Classs classs = listClass.get(position);
        holder.txtClassID.setText(Html.fromHtml("<b>Class ID: </b>"+classs.getId()));
        holder.txtClassName.setText(Html.fromHtml("<b>Class Name: </b>"+classs.getClassName()));
        holder.txtClassCapacity.setText(Html.fromHtml("<b>Capacity: </b>"+classs.getCapacity()));
        holder.txtClassStartDate.setText(Html.fromHtml("<b>Start Date: </b>"+classs.getStartTime()));
        holder.txtClassEndDate.setText(Html.fromHtml("<b>End Date: </b>"+classs.getEndTime()));
    }

    @Override
    public int getItemCount() {
        return listClass.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //khởi tạo các view
        TextView txtClassID, txtClassName, txtClassCapacity, txtClassStartDate, txtClassEndDate;
        ImageButton btnEditClass, btnDeleteClass;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtClassID = itemView.findViewById(R.id.txt_labelClassID);
            txtClassName = itemView.findViewById(R.id.txt_labelCLassName);
            txtClassCapacity = itemView.findViewById(R.id.txt_labelClassCapacity);
            txtClassStartDate = itemView.findViewById(R.id.txt_labelClassStartDate);
            txtClassEndDate = itemView.findViewById(R.id.txt_labelClassEndDate);

            btnEditClass = itemView.findViewById(R.id.btn_EditClass);
            btnDeleteClass = itemView.findViewById(R.id.btn_DeleteClass);

            btnEditClass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context.getApplicationContext(),"Click Edit Class!",Toast.LENGTH_LONG).show();
                }
            });
            btnDeleteClass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context.getApplicationContext(),"Click Delete Class!",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
