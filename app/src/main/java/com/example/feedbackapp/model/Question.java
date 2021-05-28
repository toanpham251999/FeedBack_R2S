package com.example.feedbackapp.model;

public class Question {
    int QuestionID;
    int TopicId;
    String QuestionContent;
    boolean IsDeleted;

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    //
    int answer;

    public Question(int questionID, int topicId, String questionContent, boolean isDeleted) {
        QuestionID = questionID;
        TopicId = topicId;
        QuestionContent = questionContent;
        IsDeleted = isDeleted;

    }

    public Question(int questionID, int topicId, String questionContent) {
        QuestionID = questionID;
        TopicId = topicId;
        QuestionContent = questionContent;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    public int getTopicId() {
        return TopicId;
    }

    public void setTopicId(int topicId) {
        TopicId = topicId;
    }

    public String getQuestionContent() {
        return QuestionContent;
    }

    public void setQuestionContent(String questionContent) {
        QuestionContent = questionContent;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }
}
