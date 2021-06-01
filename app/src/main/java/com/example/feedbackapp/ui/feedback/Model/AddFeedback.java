package com.example.feedbackapp.ui.feedback.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AddFeedback {
    @SerializedName("feedbackTitle")
    @Expose
    private String feedbackTitle;
    @SerializedName("feedbackTypeId")
    @Expose
    private String feedbackTypeId;
    @SerializedName("QuestionId")
    @Expose
    private ArrayList<String> QuestionId;

    public AddFeedback(String feedbackTitle, String feedbackTypeId, ArrayList<String> questionId) {
        this.feedbackTitle = feedbackTitle;
        this.feedbackTypeId = feedbackTypeId;
        QuestionId = questionId;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public String getFeedbackTypeId() {
        return feedbackTypeId;
    }

    public void setFeedbackTypeId(String feedbackTypeId) {
        this.feedbackTypeId = feedbackTypeId;
    }

    public ArrayList<String> getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(ArrayList<String> questionId) {
        QuestionId = questionId;
    }


}
