package com.example.ascentacademy_quiz_app.parent_classes;

import androidx.annotation.NonNull;

public class Question {
    private String question,optionA,optionB,optionC,correctAnswer;

    public Question(){}
    public Question(String question,String optionA,String optionB, String optionC, String correctAnswer){
        setQuestion(question);
        setOptionA(optionA);
        setOptionB(optionB);
        setOptionC(optionC);
        setCorrectAnswer(correctAnswer);
    }

    //Method to check whether correct or not
    public boolean isCorrect(@NonNull String givenAnswer){
        return givenAnswer.equals(correctAnswer);
    }

    //Getters & Setters for data fields
    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


}
