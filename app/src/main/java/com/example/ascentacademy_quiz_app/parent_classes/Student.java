package com.example.ascentacademy_quiz_app.parent_classes;

import java.util.List;

public class Student {
    private String studentName;
    private float verdict;
    private List<String> answerSet;
    private List<Question> questionSet;

    public Student(){}
    public Student(String studentName,float verdict,List<String> answerSet,List<Question> questionSet){
        setStudentName(studentName);
        setVerdict(verdict);
        setAnswerSet(answerSet);
        setQuestionSet(questionSet);
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

    public void setVerdict(float verdict) {
        this.verdict = verdict;
    }

    public List<String> getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(List<String> answerSet) {
        this.answerSet = answerSet;
    }

    public List<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(List<Question> questionSet) {
        this.questionSet = questionSet;
    }
}
