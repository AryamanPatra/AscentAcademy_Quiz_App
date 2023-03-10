package com.example.ascentacademy_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button adminEnterButton = findViewById(R.id.enterAdmin);
        Button studentEnterButton = findViewById(R.id.enterStudent);
        adminEnterButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,AdminActivity.class);
            startActivity(intent);
        });
        studentEnterButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,StudentActivity.class);
            startActivity(intent);
        });
    }
}