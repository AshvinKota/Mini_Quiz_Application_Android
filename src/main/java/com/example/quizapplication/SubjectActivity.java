package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class SubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        LinearLayout llAndroid = findViewById(R.id.llAndroid);
        LinearLayout llDigitalMarketing = findViewById(R.id.llDigitalMarketing);
        LinearLayout llMachineLearning = findViewById(R.id.llMachineLearning);

        llAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("Android");
            }
        });

        llDigitalMarketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("Digital Marketing");
            }
        });

        llMachineLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("Machine Learning");
            }
        });
    }

    private void startQuiz(String subject) {
        Intent intent = new Intent(SubjectActivity.this, MCQActivity.class);
        intent.putExtra("SUBJECT", subject);
        startActivity(intent);
    }
}