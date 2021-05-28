package com.example.feedbackapp.ui.statisticfeedback;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.Adapter.ClassDataUtils;
import com.example.feedbackapp.Adapter.CustomAdapter;
import com.example.feedbackapp.Adapter.CustomApdapterModule;
import com.example.feedbackapp.R;
import com.example.feedbackapp.model.Class;
import com.example.feedbackapp.model.Module;
import com.example.feedbackapp.model.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedbackDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedbackDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedbackDetailFragment.
     */

    //My parameters
    // Initialize variable
    RecyclerView rcvDetail;
    ArrayList<Topic> arrayListTopic;
    LinearLayoutManager layoutManagerTopic;
    TopicAdpDetail adapterTopic;

    private Spinner spinner;// for clss
    private Spinner spinnerModule;
    private List<Class> classes;
    private List<Module> modules;
    private Button showOverview;
    private Button viewComment;
    //for zoom in/out
    private float mScale = 1f;
    private ScaleGestureDetector mScaleGestureDetector;
    GestureDetector gestureDetector;
    // TODO: Rename and change types and number of parameters
    public static FeedbackDetailFragment newInstance(String param1, String param2) {
        FeedbackDetailFragment fragment = new FeedbackDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_feedback_detail, container, false);
// Code for spinner class
        // Data:
        this.classes = ClassDataUtils.getClasss();
        this.spinner = (Spinner) v.findViewById(R.id.spinner_class);

        // Adapter"
        CustomAdapter adapter = new CustomAdapter(this.getActivity(),
                R.layout.spinner_item_layout,
                R.id.textView_item_name,
                this.classes);
        this.spinner.setAdapter(adapter);
        // End for spinner class
        //code for spinner module
        //data
        this.modules = ClassDataUtils.getModule();

        this.spinnerModule = (Spinner) v.findViewById(R.id.spinner_module);

        // Adapter"
        CustomApdapterModule adapterModule = new CustomApdapterModule(this.getActivity(),
                R.layout.spinner_item_layout,
                R.id.textView_item_name,
                this.modules);

        this.spinnerModule.setAdapter(adapterModule);
        // get data from feedbackfragment

        this.spinner.setSelection(getArguments().getInt("class", 0));
        this.spinnerModule.setSelection(getArguments().getInt("module", 0));

// Click event for button View Detail
        showOverview = (Button) v.findViewById(R.id.show_overview);
        this.showOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("class", spinner.getSelectedItemPosition());
                bundle.putInt("module",spinnerModule.getSelectedItemPosition());
                //Navigation.findNavController(v).navigate(R.id.action_feedbackdetail_to_feedback, bundle);
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_statisticdofeedback, bundle);
            }
        });

// Click event for button View Comment
        viewComment = (Button) v.findViewById(R.id.view_comment);
        this.viewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bundle bundle = new Bundle();
                // bundle.putInt("class", spinner.getSelectedItemPosition());
                //bundle.putInt("module",spinnerModule.getSelectedItemPosition());
                //Navigation.findNavController(v).navigate(R.id.action_feedback_to_feedbackdetail, bundle);
            }
        });
// When user select a List-Item on spinner Class
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //spinner.setSelection(o_onchange);
// When user select a List-Item on spinner Module
        this.spinnerModule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandlerModule(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
// For lisstview

// Code for rycycle view
        //Assign variable
        rcvDetail = v.findViewById(R.id.rcvDetail);

        // Using for loop to add multiple group
        arrayListTopic = new ArrayList<>();
        arrayListTopic = ClassDataUtils.getTopicForDetail();
       /* for (int i = 1; i <=10;i++)
        {
            arrayListTopic.add("Topic " + (i - 1));
        }*/
        //Initialize topic adapter
        adapterTopic = new TopicAdpDetail(getActivity(), arrayListTopic);
        // Initailize layout manager
        layoutManagerTopic = new LinearLayoutManager(getContext());
        // Set layout manager
        rcvDetail.setLayoutManager(layoutManagerTopic);
        //set adapter
        rcvDetail.setAdapter(adapterTopic);
        return v;
    }
// handler click spinner
    // Class
    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Class clas = (Class) adapter.getItem(position);
        String itemName = clas.getClassName();
    }

// module
    private void onItemSelectedHandlerModule(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Module module = (Module) adapter.getItem(position);
        String itemModule =module.getModuleName();
    }

}