package com.example.ascentacademy_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ascentacademy_quiz_app.parent_classes.AnswerAdapter;
import com.example.ascentacademy_quiz_app.parent_classes.Question;
import com.example.ascentacademy_quiz_app.parent_classes.Student;
import com.google.gson.Gson;

import java.util.ArrayList;

public class StudentVerdict extends AppCompatActivity {
    private Student student;
    private ArrayList<Question> questionSetOfStudent;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_verdict);
        context=StudentVerdict.this;

        Gson gson = new Gson();
        String receivedStudentJson = getIntent().getExtras().getString("recentStudent");
        student = gson.fromJson(receivedStudentJson,Student.class);

//        Setting datafields
        TextView namePreview = findViewById(R.id.namePreview);
        TextView gradeCommentTv = findViewById(R.id.grade_comment_tv);
        namePreview.setText(student.getStudentName());
        setRemarks(gradeCommentTv, student.getVerdict());

//        Setting Listview
        ListView lv = findViewById(R.id.answer_showing_lv);
        AnswerAdapter ad = new AnswerAdapter(context,student);
        lv.setAdapter(ad);
    }

//    Sets Remarks accordingly
    private void setRemarks(TextView gradeTv,float verdict){
        if (verdict>=0 & verdict<20)
            gradeTv.setText(R.string.gradeF_comment);
        else if (verdict>=20 & verdict<40)
            gradeTv.setText(R.string.gradeD_comment);
        else if (verdict>=40 & verdict<60)
            gradeTv.setText(R.string.gradeC_comment);
        else if (verdict>=60 & verdict<80)
            gradeTv.setText(R.string.gradeB_comment);
        else if (verdict>=80)
            gradeTv.setText(R.string.gradeA_comment);
    }
}