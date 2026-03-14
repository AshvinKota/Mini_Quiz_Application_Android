package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MCQActivity extends AppCompatActivity {

    private String subject;
    private List<Question> questionList;
    private List<RadioGroup> radioGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq);

        subject = getIntent().getStringExtra("SUBJECT");
        TextView tvSubjectTitle = findViewById(R.id.tvSubjectTitle);
        tvSubjectTitle.setText(subject);

        LinearLayout llQuestionsContainer = findViewById(R.id.llQuestionsContainer);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        loadQuestions();
        radioGroups = new ArrayList<>();

        for (int i = 0; i < questionList.size(); i++) {
            Question q = questionList.get(i);
            
            TextView tvQuestion = new TextView(this);
            tvQuestion.setText((i + 1) + ". " + q.getQuestionText());
            tvQuestion.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            tvQuestion.setPadding(0, 32, 0, 16);
            tvQuestion.setTextColor(getResources().getColor(android.R.color.black));
            llQuestionsContainer.addView(tvQuestion);

            RadioGroup rg = new RadioGroup(this);
            rg.setOrientation(RadioGroup.VERTICAL);
            for (int j = 0; j < q.getOptions().length; j++) {
                RadioButton rb = new RadioButton(this);
                rb.setText(q.getOptions()[j]);
                rb.setId(j);
                rg.addView(rb);
            }
            llQuestionsContainer.addView(rg);
            radioGroups.add(rg);
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    private void loadQuestions() {
        questionList = new ArrayList<>();
        if ("Android".equals(subject)) {
            questionList.add(new Question("Which layer forms the base of the Android architecture and manages memory, power, and device drivers?", new String[]{"A) Application Framework", "B) Native Libraries", "C) Linux Kernel", "D) Android Runtime"}, 2));
            questionList.add(new Question("Which company exclusively developed the closed-source iOS mobile operating system?", new String[]{"A) Google", "B) Microsoft", "C) BlackBerry", "D) Apple Inc."}, 3));
            questionList.add(new Question("What is the official Integrated Development Environment (IDE) used for Android app development?", new String[]{"A) Eclipse", "B) Android Studio", "C) Xamarin", "D) Kony"}, 1));
            questionList.add(new Question("In the Android Activity Lifecycle, which method is triggered when an activity is no longer visible to the user?", new String[]{"A) onPause()", "b) onStop()", "C) onStart()", "D) onCreate()"}, 1));
            questionList.add(new Question("What does AVD stand for in the context of Android development tools?", new String[]{"A) Android Visual Display", "B) Application View Design", "C) Android Virtual Device", "D) Automated Virtual Debugger"}, 2));
        } else if ("Digital Marketing".equals(subject)) {
            questionList.add(new Question("Digital marketing mainly uses which platform?", new String[]{"a) Print media", "b) Television", "c) Internet", "d) Radio"}, 2));
            questionList.add(new Question("Which of the following best defines Digital Marketing?", new String[]{"a) Door-to-door selling", "b) Marketing using digital channels", "c) Newspaper advertising", "d) Cold calling"}, 1));
            questionList.add(new Question("The evolution of digital marketing started mainly after the growth of:", new String[]{"a) Newspapers", "b) Internet", "c) Magazines", "d) Telephone"}, 1));
            questionList.add(new Question("Which is NOT a digital marketing channel?", new String[]{"a) Email", "b) Social media", "c) Billboard", "d) Search engines"}, 2));
            questionList.add(new Question("Traditional marketing is mostly:", new String[]{"a) Interactive", "b) One-way communication", "c) Cost-effective", "d) Personalized"}, 1));
        } else if ("Machine Learning".equals(subject)) {
            questionList.add(new Question("What is the two-dimensional, tabular data structure consisting of rows and columns called in the Pandas library?", new String[]{"A) Series", "B) List", "C) DataFrame", "D) Array"}, 2));
            questionList.add(new Question("Which Matplotlib chart is used to present numerical proportions in a circular graphic using slices?", new String[]{"A) Bar Chart", "B) Histogram", "C) Pie Chart", "D) Line Plot"}, 2));
            questionList.add(new Question("What is the primary, high-performance data structure provided by the NumPy library?", new String[]{"A) Series", "B) DataFrame", "C) List", "D) ndarray (N-dimensional array)"}, 3));
            questionList.add(new Question("In the Pandas library, what is the two-dimensional, tabular data structure consisting of rows and columns called?", new String[]{"A) Series", "B) DataFrame", "C) ndarray", "D) CSV"}, 1));
            questionList.add(new Question("Which Matplotlib graph is best used to represent the distribution of a dataset by displaying the frequencies of observations within specific intervals (bins)?", new String[]{"A) Scatter Plot", "B) Line Plot", "C) Histogram", "D) Pie Chart"}, 2));
        }
    }

    private void calculateResult() {
        int score = 0;
        boolean allAnswered = true;
        for (int i = 0; i < radioGroups.size(); i++) {
            int selectedId = radioGroups.get(i).getCheckedRadioButtonId();
            if (selectedId == -1) {
                allAnswered = false;
                break;
            }
            if (selectedId == questionList.get(i).getCorrectAnswerIndex()) {
                score++;
            }
        }

        if (!allAnswered) {
            Toast.makeText(this, "Please answer all questions", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MCQActivity.this, ResultActivity.class);
        intent.putExtra("SUBJECT", subject);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL", questionList.size());
        startActivity(intent);
        finish();
    }
}