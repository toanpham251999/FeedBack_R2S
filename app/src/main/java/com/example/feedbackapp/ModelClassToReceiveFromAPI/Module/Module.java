package com.example.feedbackapp.ModelClassToReceiveFromAPI.Module;

public class Module {

    /**
     * Class gửi đi khác với nhận về, vì gửi đi chỉ 7 biến, nhận về 11 biến
     */
    private String Id;
    private String StartTime;
    private String EndTime;
    private Boolean isDeleted;
    private String FeedbackStartTime;
    private String FeedbackEndTime;
    private String AdminName;
    private String AdminId;
    private String ModuleName;
    private String FeedbackId;
    private String FeedbackTitle;

    public Module(String id, String startTime, String endTime, Boolean isDeleted, String feedbackStartTime, String feedbackEndTime, String adminName, String adminId, String moduleName, String feedbackId, String feedbackTitle) {
        Id = id;
        StartTime = startTime;
        EndTime = endTime;
        this.isDeleted = isDeleted;
        FeedbackStartTime = feedbackStartTime;
        FeedbackEndTime = feedbackEndTime;
        AdminName = adminName;
        AdminId = adminId;
        ModuleName = moduleName;
        FeedbackId = feedbackId;
        FeedbackTitle = feedbackTitle;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getFeedbackStartTime() {
        return FeedbackStartTime;
    }

    public void setFeedbackStartTime(String feedbackStartTime) {
        FeedbackStartTime = feedbackStartTime;
    }

    public String getFeedbackEndTime() {
        return FeedbackEndTime;
    }

    public void setFeedbackEndTime(String feedbackEndTime) {
        FeedbackEndTime = feedbackEndTime;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }

    public String getAdminId() {
        return AdminId;
    }

    public void setAdminId(String adminId) {
        AdminId = adminId;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }

    public String getFeedbackId() {
        return FeedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        FeedbackId = feedbackId;
    }

    public String getFeedbackTitle() {
        return FeedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        FeedbackTitle = feedbackTitle;
    }
}
