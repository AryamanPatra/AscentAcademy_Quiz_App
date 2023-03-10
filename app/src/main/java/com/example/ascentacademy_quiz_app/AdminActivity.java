package com.example.ascentacademy_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ascentacademy_quiz_app.parent_classes.Question;

import java.util.List;

public class AdminActivity extends AppCompatActivity {
    List<Question> questionSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }
}