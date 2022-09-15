package com.example.animequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewCorrect, textViewWrong, textViewEmpty, textViewQuestion;
    private ImageView imageViewAnime, imageViewNext;
    private Button buttonA, buttonB, buttonC;
    private AnimeDB animeDB;
    private ArrayList<AnimeModel> listQuestions;
    private AnimeModel correctAnime;
    private ArrayList<AnimeModel> wrongOptionsList;
    private HashSet<AnimeModel> mixedOptions = new HashSet<>();
    private ArrayList<AnimeModel> options = new ArrayList<>();
    private int correctAnswers = 0, wrongAnswers = 0, emptyAnswers = 0, question = 0;
    private boolean questionAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewCorrect = findViewById(R.id.textViewCorrect);
        textViewWrong = findViewById(R.id.textViewWrong);
        textViewEmpty = findViewById(R.id.textViewEmpty);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        imageViewAnime = findViewById(R.id.imageViewAnime);
        imageViewNext = findViewById(R.id.imageViewNext);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);

        animeDB = new AnimeDB(QuizActivity.this);
        //Almacenamos las preguntas en el Array
        listQuestions = new DatabaseDAO().getRamdonQuestions(animeDB);
        //Cargamos las preguntas
        loadQuestions();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswers(buttonA);
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswers(buttonB);
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswers(buttonC);
            }
        });

        imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question++;
                if(!questionAnswered && question < 10) {
                    emptyAnswers++;
                    textViewEmpty.setText("Empty: "+emptyAnswers);
                    loadQuestions();
                }
                else if(questionAnswered && question < 10) {
                    loadQuestions();
                    buttonA.setClickable(true);
                    buttonB.setClickable(true);
                    buttonC.setClickable(true);
                    buttonA.setBackgroundColor(getResources().getColor(R.color.blue));
                    buttonB.setBackgroundColor(getResources().getColor(R.color.blue));
                    buttonC.setBackgroundColor(getResources().getColor(R.color.blue));
                }
                else if(question == 10) {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("correct", correctAnswers);
                    intent.putExtra("wrong", wrongAnswers);
                    intent.putExtra("empty", emptyAnswers);
                    startActivity(intent);
                    finish();
                }
                questionAnswered = false;
            }
        });
    }

    public void loadQuestions() {
        textViewQuestion.setText("Question: "+(question+1));
        correctAnime = listQuestions.get(question);
        imageViewAnime.setImageResource(getResources().getIdentifier(correctAnime.getAnime_image(), "drawable", getPackageName()));
        wrongOptionsList = new DatabaseDAO().getRamdonOptions(animeDB, correctAnime.getAnime_ID());

        mixedOptions.clear();
        mixedOptions.add(correctAnime);
        mixedOptions.add(wrongOptionsList.get(0));
        mixedOptions.add(wrongOptionsList.get(1));

        options.clear();
        for(AnimeModel animeModel : mixedOptions) {
            options.add(animeModel);
        }

        //Mostramos las respuestas en cada botón
        buttonA.setText(options.get(0).getAnime_name());
        buttonB.setText(options.get(1).getAnime_name());
        buttonC.setText(options.get(2).getAnime_name());
    }

    public void checkAnswers(Button button) {
        String buttonText = button.getText().toString();
        String correct = correctAnime.getAnime_name();
        if(buttonText.equals(correct)) {
            correctAnswers++;
            button.setBackgroundColor(Color.GREEN);
        }
        else {
            wrongAnswers++;
            button.setBackgroundColor(Color.RED);
            if(buttonA.getText().toString().equals(correct))
                buttonA.setBackgroundColor(Color.GREEN);
            if(buttonB.getText().toString().equals(correct))
                buttonB.setBackgroundColor(Color.GREEN);
            if(buttonC.getText().toString().equals(correct))
                buttonC.setBackgroundColor(Color.GREEN);
            }
        buttonA.setClickable(false);
        buttonB.setClickable(false);
        buttonC.setClickable(false);
        textViewCorrect.setText("Correct: "+correctAnswers);
        textViewWrong.setText("Wrong: "+wrongAnswers);
        questionAnswered = true;
    }

}