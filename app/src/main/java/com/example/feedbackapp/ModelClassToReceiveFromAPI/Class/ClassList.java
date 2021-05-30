package com.example.feedbackapp.ModelClassToReceiveFromAPI.Class;

import com.example.feedbackapp.model.Class;

import java.util.ArrayList;

public class ClassList {
    private boolean isSuccess;
    private String message;
    private ArrayList<Class> listClass;

    public ClassList(boolean success, String message, ArrayList<Class> listClass) {
        this.isSuccess = success;
        this.message = message;
        this.listClass = listClass;
    }


    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Class> getListClass() {
        return listClass;
    }

    public void setListClass(ArrayList<Class> listClass) {
        this.listClass = listClass;
    }
}
