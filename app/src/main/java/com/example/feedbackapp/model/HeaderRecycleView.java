package com.example.feedbackapp.model;

public class HeaderRecycleView {
    String name;
    String module;
    String clas;

    public HeaderRecycleView(String name, String module, String clas) {
        this.name = name;
        this.module = module;
        this.clas = clas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }
}
