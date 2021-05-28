package com.example.feedbackapp.ModelClassToReceiveFromAPI.Module;

public class ListModule {
    private Boolean isSuccess;
    private String message;
    private Module[] listModule;

    public ListModule(Boolean isSuccess, String message, Module[] listModule) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.listModule = listModule;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Module[] getListModule() {
        return listModule;
    }

    public void setListModule(Module[] listModule) {
        this.listModule = listModule;
    }
}
