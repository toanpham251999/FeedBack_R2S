package com.example.feedbackapp.ui.feedback;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomFeedbackAdapter extends BaseAdapter  {

    private LayoutInflater flater;
    private ArrayList<TypeFeedbackModel> listTypeFeedback;
    private int listItemLayoutResource;
    private int textViewItemNameId;
    // Arguments example:
    //  @listItemLayoutResource: R.layout.spinner_item_layout_resource
    //        (File: layout/spinner_item_layout_resource.xmll)
    //  @textViewItemNameId: R.id.textView_item_name
    //        (A TextVew in file layout/spinner_item_layout_resource.xmlxml)
    //  @textViewItemPercentId: R.id.textView_item_percent
    //        (A TextVew in file layout/spinner_item_layout_resource.xmll)
    // structure adapter for class
    public CustomFeedbackAdapter(Activity context, int listItemLayoutResource,
                                 int textViewItemNameId,
                                 ArrayList<TypeFeedbackModel> list) {
        this.listItemLayoutResource = listItemLayoutResource;

        this.textViewItemNameId = textViewItemNameId;
        this.listTypeFeedback = list;
        this.flater = context.getLayoutInflater();
    }
    @Override
    public int getCount() {
        if(this.listTypeFeedback == null)  {
            return 0;
        }
        return this.listTypeFeedback.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listTypeFeedback.get(position);
    }

    @Override
    public long getItemId(int position) {
         TypeFeedbackModel typeFeedbackModel =(TypeFeedbackModel) this.getItem(position);
        return typeFeedbackModel.getTypeId();
        // return position; (Return position if you need).
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TypeFeedbackModel typeFeedbackModel = (TypeFeedbackModel) getItem(position);

        // Example: @listItemLayoutResource: R.layout.spinner_item_layout_resource
        // (File: layout/spinner_item_layout_resourcerce.xml)
        View rowView = this.flater.inflate(this.listItemLayoutResource, null,true);

        // Example: @textViewItemNameId: R.id.textView_item_name
        // (A TextView in file layout/spinner_item_layout_resourcerce.xml)
        TextView textViewItemName = (TextView) rowView.findViewById(this.textViewItemNameId);
        textViewItemName.setText(typeFeedbackModel.getTypeName());

        // Example: @textViewItemPercentId: R.id.textView_item_percent
        // (A TextView in file layout/spinner_item_layout_resource.xmlxml)
        return rowView;
    }
}