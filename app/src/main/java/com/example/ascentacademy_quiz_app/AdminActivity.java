package com.example.ascentacademy_quiz_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.ascentacademy_quiz_app.local_database.DbHandler;
import com.example.ascentacademy_quiz_app.parent_classes.CustomAdapter;
import com.example.ascentacademy_quiz_app.parent_classes.Question;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Objects;

public class AdminActivity extends AppCompatActivity {
    ArrayList<Question> questionSet = new ArrayList<Question>();
    FloatingActionButton fab;
    DbHandler db;
    ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.admin);
        db = new DbHandler(this);

//        List view & adapter
        questionSet=db.fetchRecords(this);
        listView = findViewById(R.id.q_lv);
        CustomAdapter ad = new CustomAdapter(AdminActivity.this,questionSet);
        listView.setAdapter(ad);

//       Loading & Sharing Data using SQLite
        fab = findViewById(R.id.fab_to_add);
        fab.setOnClickListener(view -> {
//            Constructing alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add Question");
            View customLayout = getLayoutInflater().inflate(R.layout.question_alert_box,null);
            builder.setView(customLayout);
            Toast.makeText(this, "A->0,B->1,C->2", Toast.LENGTH_SHORT).show();

//          Textchangers
            EditText questionEd = customLayout.findViewById(R.id.question_edt);
            EditText optionAEd = customLayout.findViewById(R.id.optionA_edt);
            EditText optionBEd = customLayout.findViewById(R.id.optionB_edt);
            EditText optionCEd = customLayout.findViewById(R.id.optionC_edt);
            EditText correctAnswerEd = customLayout.findViewById(R.id.correctAnswer_edt);
            final String[] questionString = new String[1];
            final String[] opAString = new String[1];
            final String[] opBString = new String[1];
            final String[] opCString = new String[1];
            final String[] correctString = new String[1];
            questionEd.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    questionString[0] = editable.toString();
                }
            });
            optionAEd.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    opAString[0] = editable.toString();
                }
            });
            optionBEd.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    opBString[0] = editable.toString();
                }
            });
            optionCEd.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    opCString[0] = editable.toString();
                }
            });
            correctAnswerEd.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    correctString[0] = editable.toString();
                }
            });


//            Add button
            builder.setPositiveButton("Add", (dialogInterface, i) -> {
                int correctNum = Integer.parseInt(correctString[0]);
                if (correctNum>=0 && correctNum<=2){
                    Question question = new Question(questionString[0],opAString[0],opBString[0],opCString[0],correctNum);
                    questionSet.add(question);
                    db.addQuestionToDatabase(this,question);
                    ad.notifyDataSetChanged();
                    Toast.makeText(this, questionSet.size()+"", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Error, plz add within given range A->0,B->1,C->2", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("Clear All", (dialogInterface, i) -> {
                db.emptyTable(this);
                while(questionSet.size()!=0){
                    questionSet.remove(0);
                }
                ad.notifyDataSetChanged();
            });

            builder.create().show();
        });

    }
}