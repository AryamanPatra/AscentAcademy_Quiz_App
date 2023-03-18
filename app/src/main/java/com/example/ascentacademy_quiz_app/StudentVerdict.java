package com.example.ascentacademy_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ascentacademy_quiz_app.parent_classes.Student;
import com.google.gson.Gson;

public class StudentVerdict extends AppCompatActivity {
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_verdict);

        Gson gson = new Gson();
        String receivedStudentJson = getIntent().getExtras().getString("recentStudent");
        student = gson.fromJson(receivedStudentJson,Student.class);
    }
}