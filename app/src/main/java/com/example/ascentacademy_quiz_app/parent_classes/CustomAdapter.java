package com.example.ascentacademy_quiz_app.parent_classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ascentacademy_quiz_app.R;

import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<Question> questionSet;
    LayoutInflater inflater;

    public CustomAdapter(Context context,List<Question> questionSet){
        this.context = context;
        this.questionSet = questionSet;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return questionSet.size();
    }

    @Override
    public Object getItem(int i) {
        return questionSet.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.question_list_item,null);
        TextView question = convertView.findViewById(R.id.questionTv);
        TextView opA = convertView.findViewById(R.id.aTv);
        TextView opB = convertView.findViewById(R.id.bTv);
        TextView opC = convertView.findViewById(R.id.cTv);
        TextView correctAnswer = convertView.findViewById(R.id.correctAnswerTv);
        question.setText(questionSet.get(pos).getQuestion());
        opA.setText(questionSet.get(pos).getOptionA());
        opB.setText(questionSet.get(pos).getOptionB());
        opC.setText(questionSet.get(pos).getOptionC());
        correctAnswer.setText(questionSet.get(pos).getCorrectAnswer());
        return convertView;
    }
}
