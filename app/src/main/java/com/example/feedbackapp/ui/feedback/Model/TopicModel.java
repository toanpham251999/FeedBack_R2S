package com.example.feedbackapp.ui.feedback.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopicModel {
        @SerializedName("isSuccess")
        @Expose
        private Boolean isSuccess;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("listTopic")
        @Expose
        private List<ListTopic> listTopic = null;

        public Boolean getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(Boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<ListTopic> getListTopic() {
            return listTopic;
        }

        public void setListTopic(List<ListTopic> listTopic) {
            this.listTopic = listTopic;
        }
}
