package com.example.ascentacademy_quiz_app.parent_classes;

import java.util.List;

public class Student {
    private String studentName = "no_name(null)";
    private float verdict;
    private List<Short> answerSet;
    private List<Question> questionSet;

    public Student(){}
    public Student(String studentName,float verdict,List<Short> answerSet,List<Question> questionSet){
        setStudentName(studentName);
        setVerdict(verdict);
        setAnswerSet(answerSet);
        setQuestionSet(questionSet);
    }

//  Calculating verdict
    public void calculateVerdict(){
        float total = questionSet.size()+0f;
        float count = 0f;
        for (int i = 0; i < questionSet.size(); i++) {
            if (answerSet.get(i)==questionSet.get(i).getCorrectAnswer())
                count++;
        }
        setVerdict((count/total)*100.0f);
    }

//    Getters & Setters of data field
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public float getVerdict() {
        return verdict;
    }

    private void setVerdict(float verdict) {
        this.verdict = verdict;
    }

    public List<Short> getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(List<Short> answerSet) {
        this.answerSet = answerSet;
    }

    public List<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(List<Question> questionSet) {
        this.questionSet = questionSet;
    }
}
