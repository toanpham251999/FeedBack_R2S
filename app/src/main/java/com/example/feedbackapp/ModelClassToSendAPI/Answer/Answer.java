package com.example.feedbackapp.ModelClassToSendAPI.Answer;

public class Answer {
    private String ClassId;
    private String ModuleId;
    private String QuestionId;
    int Value;

    public Answer(String classId, String moduleId, String questionId, int value) {
        ClassId = classId;
        ModuleId = moduleId;
        QuestionId = questionId;
        Value = value;
    }

       @Override
    public String toString() {
            return "AnswerValue{" +
                    "ClassId='" + ClassId + '\'' +
                    ", ModuleId='" + ModuleId + '\'' +
                    ", QuestionId='" + QuestionId + '\'' +
                    ", Value=" + Value  +
                    '}';
        }
    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String classId) {
        ClassId = classId;
    }

    public String getModuleId() {
        return ModuleId;
    }

    public void setModuleId(String moduleId) {
        ModuleId = moduleId;
    }

    public String getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(String questionId) {
        QuestionId = questionId;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

}
