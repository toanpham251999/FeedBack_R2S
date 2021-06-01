package com.example.feedbackapp.ModelClassToSendAPI;

public class LoadQuestionByTopicIdInfo {
    private String TopicId;

    public LoadQuestionByTopicIdInfo(String topicId) {
        TopicId = topicId;
    }

    public String getTopicId() {
        return TopicId;
    }

    public void setTopicId(String topicId) {
        TopicId = topicId;
    }
}
