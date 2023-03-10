package com.example.ascentacademy_quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.ascentacademy_quiz_app.databinding.ActivityAdminBinding;
import com.example.ascentacademy_quiz_app.fragments.HistoryFragment;
import com.example.ascentacademy_quiz_app.fragments.QuestionPageFragment;
import com.example.ascentacademy_quiz_app.parent_classes.Question;

import java.util.List;

public class AdminActivity extends AppCompatActivity {
    List<Question> questionSet;
    ActivityAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Settings");
        replaceFragment(new QuestionPageFragment());

        binding.bottomNavBar.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.questions_fragment:
                    replaceFragment(new QuestionPageFragment());
                    break;
                case R.id.history_fragment:
                    replaceFragment(new HistoryFragment());
                    break;
            }
            return true;
        });

    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_admin,fragment);
        fragmentTransaction.commit();
    }
}
