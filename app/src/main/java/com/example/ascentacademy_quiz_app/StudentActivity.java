package com.example.ascentacademy_quiz_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ascentacademy_quiz_app.local_database.DbHandler;
import com.example.ascentacademy_quiz_app.parent_classes.Question;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    ArrayList<Question> questionSet;
    private int currentQuestionIndex;
    private Context context;
    TextView[] textViews = new TextView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        context=this;

//        Defining non changing textviews
        textViews[0] = findViewById(R.id.question_student);
        textViews[1] = findViewById(R.id.optionA_student);
        textViews[2] = findViewById(R.id.optionB_student);
        textViews[3] = findViewById(R.id.optionC_student);

//        Fetching records
        DbHandler db = new DbHandler(context);
        questionSet = db.fetchRecords(context);

//        Quiz start execution
        if (questionSet.size()!=0){
            currentQuestionIndex=0;
            setQuestionInLayout();
        }
//        The code below is for when there are no question
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Oops!!!");
            TextView textView = new TextView(context);
            textView.setText("There are no questions to take test");
            builder.setView(textView);
            builder.setPositiveButton("Go to Admin Settings", (dialogInterface, i) -> {
                Intent intent = new Intent(context,AdminActivity.class);
                startActivity(intent);
            });
            Intent intent = new Intent(context,MainActivity.class);
            startActivity(intent);
        }
    }
    private void setQuestionInLayout(){
        textViews[0].setText(questionSet.get(currentQuestionIndex).getQuestion());
        textViews[1].setText(questionSet.get(currentQuestionIndex).getOptionA());
        textViews[2].setText(questionSet.get(currentQuestionIndex).getOptionB());
        textViews[3].setText(questionSet.get(currentQuestionIndex).getOptionC());
    }

/*
*       ON CLICK METHODS
* */

    @SuppressLint({"UseCompatLoadingForDrawables", "NonConstantResourceId"})
    public void clickingOptions(View view){
        TextView textView = (TextView)view;

        switch (textView.getId()){
            case R.id.optionA_student:
                textViews[1].setBackground(getDrawable(R.drawable.rounded_corner_selected));
                textViews[1].setTextColor(getColor(R.color.white));
                textViews[2].setBackground(getDrawable(R.drawable.rounded_corner));
                textViews[2].setTextColor(getColor(R.color.black));
                textViews[3].setBackground(getDrawable(R.drawable.rounded_corner));
                textViews[3].setTextColor(getColor(R.color.black));
                break;
            case R.id.optionB_student:
                textViews[2].setBackground(getDrawable(R.drawable.rounded_corner_selected));
                textViews[2].setTextColor(getColor(R.color.white));
                textViews[1].setBackground(getDrawable(R.drawable.rounded_corner));
                textViews[1].setTextColor(getColor(R.color.black));
                textViews[3].setBackground(getDrawable(R.drawable.rounded_corner));
                textViews[3].setTextColor(getColor(R.color.black));
                break;
            case R.id.optionC_student:
                textViews[3].setBackground(getDrawable(R.drawable.rounded_corner_selected));
                textViews[3].setTextColor(getColor(R.color.white));
                textViews[1].setBackground(getDrawable(R.drawable.rounded_corner));
                textViews[1].setTextColor(getColor(R.color.black));
                textViews[2].setBackground(getDrawable(R.drawable.rounded_corner));
                textViews[2].setTextColor(getColor(R.color.black));
                break;
        }

        Button button = findViewById(R.id.nextQuestion_student);
        button.setBackgroundColor(getColor(R.color.purple_500));
    }

    public void nextQuestion(View view){
        Button button = (Button)view;

//        Where new Question is loaded
        currentQuestionIndex++;
        setQuestionInLayout();

//        After new question is loaded
        if(currentQuestionIndex==(questionSet.size()-1)){
            button.setText(R.string.submit);
        }
        button.setBackgroundColor(getColor(R.color.grey));
    }
}