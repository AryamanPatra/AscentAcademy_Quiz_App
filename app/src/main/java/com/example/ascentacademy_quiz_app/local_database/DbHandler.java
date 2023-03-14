package com.example.ascentacademy_quiz_app.local_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.ascentacademy_quiz_app.parent_classes.Question;

import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper {

    public DbHandler(Context context){
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE IF NOT EXISTS "+ Params.QUESTION_TABLE_NAME + "(" + Params.QUESTION_ID_QUESTION + " VARCHAR(100) PRIMARY KEY," + Params.QUESTION_OPTION_A + " VARCHAR(100),"+Params.QUESTION_OPTION_B+" VARCHAR(100),"+Params.QUESTION_OPTION_C+" VARCHAR(100),"+Params.QUESTION_CORRECT_ANSWER+" INTEGER"+");";
        db.execSQL(create);
        Log.d("dbarya","The query being run is: "+create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addQuestionToDatabase(Context context, Question question){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Params.QUESTION_ID_QUESTION,question.getQuestion());
        values.put(Params.QUESTION_OPTION_A,question.getOptionA());
        values.put(Params.QUESTION_OPTION_B,question.getOptionB());
        values.put(Params.QUESTION_OPTION_C,question.getOptionC());
        values.put(Params.QUESTION_CORRECT_ANSWER,question.getCorrectAnswer());

        db.insert(Params.QUESTION_TABLE_NAME,null,values);
        Toast.makeText(context, "Added to database", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Question> fetchRecords(Context context){
        ArrayList<Question> questionsSet = new ArrayList<Question>();
        SQLiteDatabase db = getReadableDatabase();

        String select ="SELECT * FROM "+Params.QUESTION_TABLE_NAME+";";
        Cursor cursor = db.rawQuery(select,null);

        if (cursor.moveToFirst()){
            do {
                String questionString =cursor.getString(0);
                String optA = cursor.getString(1);
                String optB = cursor.getString(2);
                String optC = cursor.getString(3);
                int correctAnswer = cursor.getInt(4);
                Question question = new Question(questionString,optA,optB,optC,correctAnswer);
                questionsSet.add(question);
            }while (cursor.moveToNext());
            Toast.makeText(context, "Data Fetched!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Something happened in cursor DbHandler!", Toast.LENGTH_SHORT).show();
        }

        return questionsSet;
    }

    public void emptyTable(Context context){
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM "+Params.QUESTION_TABLE_NAME;
        db.execSQL(query);
        Toast.makeText(context, "All data records deleted!", Toast.LENGTH_SHORT).show();
    }
}
