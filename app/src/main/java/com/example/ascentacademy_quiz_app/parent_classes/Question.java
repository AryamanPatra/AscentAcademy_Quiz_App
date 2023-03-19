package com.example.ascentacademy_quiz_app.parent_classes;

import androidx.annotation.NonNull;

public class Question {
    private String question,optionA,optionB,optionC;
    private short correctAnswer;

    public Question(){}
    public Question(String question,String optionA,String optionB, String optionC, int correctAnswer){
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
    //    getCorrectAnswers
    public String getOptionString(short i){
        switch (i){
            case 0:
                return getOptionA();
            case 1:
                return getOptionB();
            case 2:
                return getOptionC();
            default:
                return "Error, Option not found!";
        }
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

    public short getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = (short)correctAnswer;
    }


}
