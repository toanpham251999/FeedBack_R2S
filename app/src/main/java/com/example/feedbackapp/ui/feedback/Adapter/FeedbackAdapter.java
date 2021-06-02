package com.example.feedbackapp.ui.feedback.Adapter;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.UserInfo.UserInfo;
import com.example.feedbackapp.constant.SystemConstant;
import com.example.feedbackapp.ui.feedback.FeedBackFragment;
import com.example.feedbackapp.ui.feedback.Fragment_Edit_Feedback;
import com.example.feedbackapp.ui.feedback.Model.FeedbackEditFeedbackList2;
import com.example.feedbackapp.ui.feedback.Model.FeedbackEditFilterId1;
import com.example.feedbackapp.ui.feedback.Model.FeedbackEditTopic3;
import com.example.feedbackapp.ui.feedback.Model.ListFeedback;
import com.example.feedbackapp.ui.feedback.Service.APIService;
import com.example.feedbackapp.ui.feedback.Service.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        holder.txtFeedbackId.setText(Html.fromHtml("<b>Feedback ID: </b>" + list.getTypeFeedbackId()));
        holder.txtFeedbackTitle.setText(Html.fromHtml("<b>Title: </b>" + list.getTitle()));
        holder.txtAdminId.setText(Html.fromHtml("<b>Admin Id: </b>" + list.getAdminId()));

        holder.imgEditFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataService dataServiceFilter = APIService.getService();
                Bundle bundle = new Bundle();
//                SystemConstant.feedbackTitle=list.getTitle();
//                SystemConstant.feedbackId=list.getId();
                bundle.putString("feedbackId","60aa2adfc2188327980f86d3");
                bundle.putString("feedbackTitle",list.getTitle());
                Call<FeedbackEditFilterId1> callFeedbackFilter = dataServiceFilter.GetDataFilterIdFeedback("Bearer eyJhbGciOiJI" +
                        "UzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI2MGE3MjRiYTk1N2FhNjBjN2M3YzNlYTEiLCJ0eXBlVXNlciI6ImFk" +
                        "bWluIiwiaWF0IjoxNjIxOTU0NDg5fQ.i4JExKXlcmHIi-m3E6O46YEKoj1pV6R0Wi9ezN77GG0","60aa2adfc2188327980f86d3");
                callFeedbackFilter.enqueue(new Callback<FeedbackEditFilterId1>() {
                    @Override
                    public void onResponse(Call<FeedbackEditFilterId1> call, Response<FeedbackEditFilterId1> response) {
                        FeedbackEditFilterId1 feedbackEditFilterId1 = (FeedbackEditFilterId1) response.body();
                        FeedbackEditFeedbackList2 feedbackEditFeedbackList2 =feedbackEditFilterId1.getFeedback();
                         SystemConstant.feedbackEditTopic3 = feedbackEditFeedbackList2.getListTopic();

                        Log.i("TEST GET FEEDBACK OK","OK");
                    }

                    @Override
                    public void onFailure(Call<FeedbackEditFilterId1> call, Throwable t) {

                    }
                });
                Navigation.findNavController(v).navigate(R.id.nav_edit_feedback,bundle);
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
