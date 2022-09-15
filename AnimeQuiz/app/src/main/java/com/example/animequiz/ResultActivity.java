package com.example.animequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewValidAnswers, textViewWrongAnswers, textViewEmptyAnswers, textViewRate;
    private Button buttonRepeat, buttonQuit;
    private int correctAnswers, wrongAnswers, emptyAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewValidAnswers = findViewById(R.id.textViewValidAnswers);
        textViewWrongAnswers = findViewById(R.id.textViewWrongdAnswers);
        textViewEmptyAnswers = findViewById(R.id.textViewEmptyAnswers);
        textViewRate = findViewById(R.id.textViewSuccessRate);
        buttonRepeat = findViewById(R.id.buttonRepeat);
        buttonQuit = findViewById(R.id.buttonQuit);

        correctAnswers = getIntent().getIntExtra("correct", 0);
        wrongAnswers = getIntent().getIntExtra("wrong", 0);
        emptyAnswers = getIntent().getIntExtra("empty", 0);
        textViewValidAnswers.setText("Correct Answers: "+correctAnswers);
        textViewWrongAnswers.setText("Wrong Answers: "+wrongAnswers);
        textViewEmptyAnswers.setText("Empty Answers: "+emptyAnswers);
        textViewRate.setText("Success Rate: "+(correctAnswers*10)+"%");

        buttonRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(Intent.ACTION_MAIN);
                newIntent.addCategory(Intent.CATEGORY_HOME);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(newIntent);
                finish();
            }
        });

    }
}