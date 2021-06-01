package com.example.feedbackapp.ui.feedback.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListTopic {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("TopicName")
    @Expose
    private String topicName;
    @SerializedName("listQuestion")
    @Expose
    private List<ListQuestion> listQuestion = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<ListQuestion> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<ListQuestion> listQuestion) {
        this.listQuestion = listQuestion;
    }

}
