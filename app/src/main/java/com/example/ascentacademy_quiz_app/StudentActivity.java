package com.example.ascentacademy_quiz_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ascentacademy_quiz_app.local_database.DbHandler;
import com.example.ascentacademy_quiz_app.parent_classes.Question;
import com.example.ascentacademy_quiz_app.parent_classes.Student;
import com.google.gson.Gson;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    ArrayList<Question> questionSet;
    private int currentQuestionIndex;
    private Context context;
    Student student;
    short choice;
    ArrayList<Short> answerSet = new ArrayList<>();
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
            final String[] name = {"no name"};
            student = new Student();

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Enter Name");
            EditText editText = new EditText(context);
            builder.setView(editText);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    name[0] = editable.toString();
                }
            });
            builder.setPositiveButton("Add Student", (dialogInterface, i) -> {
                     student.setStudentName(name[0]);
            });
            if (name[0].equals("no name"))
                student.setStudentName(name[0]);
            builder.create().show();
            setNextQuestionInLayout();
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
            builder.create().show();
            Intent intent = new Intent(context,MainActivity.class);
            startActivity(intent);
        }
    }
    private void setNextQuestionInLayout(){
        textViews[0].setText(questionSet.get(currentQuestionIndex).getQuestion());
        textViews[1].setText(questionSet.get(currentQuestionIndex).getOptionA());
        textViews[2].setText(questionSet.get(currentQuestionIndex).getOptionB());
        textViews[3].setText(questionSet.get(currentQuestionIndex).getOptionC());
    }
    private void executeSubmission(){
        student.setQuestionSet(questionSet);
        student.setAnswerSet(answerSet);
        student.calculateVerdict();
        Gson gson = new Gson();
        String sentStudentJson = gson.toJson(student);
        Intent intent = new Intent(context,StudentVerdict.class);
        intent.putExtra("recentStudent",sentStudentJson);
        startActivity(intent);
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setButtonSelected(int i){
        textViews[i].setBackground(getDrawable(R.drawable.rounded_corner_selected));
        textViews[i].setTextColor(getColor(R.color.white));
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setButtonUnselected(int i){
        textViews[i].setBackground(getDrawable(R.drawable.rounded_corner));
        textViews[i].setTextColor(getColor(R.color.black));
    }

/*
*       ON CLICK METHODS
* */

    @SuppressLint({"UseCompatLoadingForDrawables", "NonConstantResourceId"})
    public void clickingOptions(View view){
        TextView textView = (TextView)view;

        switch (textView.getId()){
            case R.id.optionA_student:
                setButtonSelected(1);
                setButtonUnselected(2);
                setButtonUnselected(3);
                choice=0;
                break;
            case R.id.optionB_student:
                setButtonSelected(2);
                setButtonUnselected(1);
                setButtonUnselected(3);
                choice=1;
                break;
            case R.id.optionC_student:
                setButtonSelected(3);
                setButtonUnselected(1);
                setButtonUnselected(2);
                choice=2;
                break;
        }

        Button button = findViewById(R.id.nextQuestion_student);
        button.setBackgroundColor(getColor(R.color.purple_500));
    }

    public void nextQuestion(View view){
        Button button = (Button)view;

        cond:if (choice!=-1){
    //        Where new Question is loaded
            currentQuestionIndex++;
            answerSet.add(choice);
            if (currentQuestionIndex==questionSet.size()){
                //Here I need to start an activity.
                executeSubmission();
                break cond;
            }
            setButtonUnselected(choice+1);
            setNextQuestionInLayout();

    //        After new question is loaded
            if(currentQuestionIndex==(questionSet.size()-1)){
                button.setText(R.string.submit);
            }
            button.setBackgroundColor(getColor(R.color.grey));
            choice=-1;
        }
        else{
            Toast.makeText(context, "Plz select an option to proceed", Toast.LENGTH_SHORT).show();
        }
    }
}