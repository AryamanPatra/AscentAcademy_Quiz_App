package com.example.ascentacademy_quiz_app.parent_classes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ascentacademy_quiz_app.R;

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

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.answer_list_item,null);

        TextView answerQuestion = convertView.findViewById(R.id.answer_question);
        TextView answerChosenPreview = convertView.findViewById(R.id.answer_chosen_preview);
        TextView answerCorrectPreview = convertView.findViewById(R.id.answer_correct_preview);
        TextView answerCorrect = convertView.findViewById(R.id.answer_correct);
        TextView answerChosen = convertView.findViewById(R.id.answer_chosen);

        Question curQuestion = questionSet.get(pos);
        answerQuestion.setText(curQuestion.getQuestion());
        answerChosenPreview.setText(R.string.chosen_preview);
        answerCorrectPreview.setText(R.string.correct_preview);
        answerChosen.setText(curQuestion.getOptionString(answerSet.get(pos)));
        answerCorrect.setText(curQuestion.getOptionString(curQuestion.getCorrectAnswer()));

        return convertView;
    }
}
