package com.example.feedbackapp.Adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.Module;
import com.example.feedbackapp.ModelClassToReceiveFromAPI.Module.ModuleReturnByID;
import com.example.feedbackapp.R;
import com.example.feedbackapp.RetrofitAPISetvice.ModuleAPIService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {

    Context context;
    ArrayList<Module> listModule;

    public ModuleAdapter(Context context, ArrayList<Module> listModule) {
        this.context = context;
        this.listModule = listModule;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.item_module_manager_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listModule.size(); // trả item tại vị trí postion
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //gán dữ liệu cho từng item
        Module module = listModule.get(position);
        holder.txtLabelModuleID.setText(Html.fromHtml("<b>Module ID: </b>"+ module.getId()));
        holder.txtLabelModuleName.setText(Html.fromHtml("<b>Module Name: </b>"+ module.getModuleName()));
        holder.txtLabelAdminID.setText(Html.fromHtml("<b>Admin ID: </b>"+ module.getAdminId()));
        holder.txtLabelStartDate.setText(Html.fromHtml("<b>Start Date: </b>"+ module.getStartTime()));
        holder.txtLabelEndDate.setText(Html.fromHtml("<b>End Date: </b>"+ module.getEndTime()));
        holder.txtLabelFeedbackTitle.setText(Html.fromHtml("<b>Feedback Title: </b>"+ module.getFeedbackTitle()));
        holder.txtLabelFeedbackStartTime.setText(Html.fromHtml("<b>Feedback Start Time: </b>"+ module.getFeedbackStartTime()));
        holder.txtLabelFeedbackEndTime.setText(Html.fromHtml("<b>Feedback End Time: </b>"+ module.getFeedbackEndTime()));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //khởi tạo các view con trong 1 item
        TextView txtLabelModuleID,
                txtLabelModuleName,
                txtLabelAdminID,
                txtLabelStartDate,
                txtLabelEndDate,
                txtLabelFeedbackTitle,
                txtLabelFeedbackStartTime,
                txtLabelFeedbackEndTime;
        ImageButton btnEdit,
                btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtLabelModuleID = itemView.findViewById(R.id.txt_labelModuleID);
            txtLabelModuleName = itemView.findViewById(R.id.txt_labelModuleName);
            txtLabelAdminID = itemView.findViewById(R.id.txt_labelAdminID);
            txtLabelStartDate = itemView.findViewById(R.id.txt_labelStartDate);
            txtLabelEndDate = itemView.findViewById(R.id.txt_labelendDate);
            txtLabelFeedbackTitle = itemView.findViewById(R.id.txt_labelFeedbackTitle);
            txtLabelFeedbackStartTime = itemView.findViewById(R.id.txt_labelFeedbackStartTime);
            txtLabelFeedbackEndTime = itemView.findViewById(R.id.txt_labelFeedbackEndTime);

            btnEdit = itemView.findViewById(R.id.btn_EditModule);
            btnDelete = itemView.findViewById(R.id.btn_DeleteModule);
        }
    }

    public void DeleteModule(int position){

    }
}


