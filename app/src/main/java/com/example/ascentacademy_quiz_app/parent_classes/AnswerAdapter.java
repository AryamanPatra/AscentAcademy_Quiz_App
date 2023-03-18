package com.example.ascentacademy_quiz_app.parent_classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class AnswerAdapter extends BaseAdapter {

    private Student student;
    private Context context;
    ArrayList<Question> questionSet;
    ArrayList<Short> answerSet;
    LayoutInflater inflater;

    public AnswerAdapter(Context context,Student student){
        this.context = context;
        this.student = student;
        questionSet = (ArrayList<Question>) this.student.getQuestionSet();
        answerSet = (ArrayList<Short>) this.student.getAnswerSet();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return questionSet.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
