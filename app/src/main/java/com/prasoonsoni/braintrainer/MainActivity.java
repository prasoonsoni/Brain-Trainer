package com.prasoonsoni.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button b0;
    Button b1;
    Button b2;
    Button b3;
    TextView enterName;
    TextView sumTextView;
    TextView opening;
    TextView points;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView result;
    int locationOfCorrectAnswer;
    int score = 0;
    int questions = 0;
    TextView timer;
    Button playAgainButton;
    ConstraintLayout toViewGame;
    TextView prasoonSoni;
    EditText playerName;
    String nameOfPlayer;
    TextView welcome;
    ConstraintLayout instructions;
    Button goButton;
    public void goBack(View view){
        instructions.setVisibility(ConstraintLayout.INVISIBLE);
        startButton.setVisibility(View.VISIBLE);
        opening.setVisibility(View.VISIBLE);
        prasoonSoni.setVisibility(View.VISIBLE);
        playerName.setVisibility(View.VISIBLE);
        enterName.setVisibility(View.VISIBLE);
        welcome.setText("Hello, ");

    }
    public void toGo(View view) {
        instructions.setVisibility(ConstraintLayout.INVISIBLE);
        toViewGame.setVisibility(ConstraintLayout.VISIBLE);
        prasoonSoni.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    public void playAgain(View view) {
        score = 0;
        questions = 0;
        timer.setText("30s");
        points.setText("0/0");
        result.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestions();
        b0.setClickable(true);
        b1.setClickable(true);
        b2.setClickable(true);
        b3.setClickable(true);
        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(Integer.toString((int) (millisUntilFinished / 1000)) + "s");
            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                result.setText("You Scored " + Integer.toString(score) + " out of " + Integer.toString(questions));
                result.setTextSize(30);
                b0.setClickable(false);
                b1.setClickable(false);
                b2.setClickable(false);
                b3.setClickable(false);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();


    }

    public void generateQuestions() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        b0.setText(Integer.toString(answers.get(0)));
        b1.setText(Integer.toString(answers.get(1)));
        b2.setText(Integer.toString(answers.get(2)));
        b3.setText(Integer.toString(answers.get(3)));


    }

    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            result.setText("CORRECT :)");
        } else {
            result.setText("WRONG :(");
        }
        questions++;
        points.setText(Integer.toString(score) + " / " + Integer.toString(questions));
        generateQuestions();
    }

    public void toStart(View view) {

        nameOfPlayer = playerName.getText().toString();
        if (nameOfPlayer.trim().equals("")) {
            Toast.makeText(this, "Please enter name!!", Toast.LENGTH_SHORT).show();
        } else {
            startButton.setVisibility(View.INVISIBLE);
            opening.setVisibility(View.INVISIBLE);
            playerName.setVisibility(View.INVISIBLE);
            enterName.setVisibility(View.INVISIBLE);
            instructions.setVisibility(ConstraintLayout.VISIBLE);
            welcome.setText("Hello, " + nameOfPlayer);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton);
        opening = findViewById(R.id.opening);
        sumTextView = findViewById(R.id.sumTextView);
        points = findViewById(R.id.points);
        result = findViewById(R.id.result);
        b0 = findViewById(R.id.b0);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        timer = findViewById(R.id.timeRemaining);
        playAgainButton = findViewById(R.id.playAgainButton);
        toViewGame = findViewById(R.id.toViewGame);
        prasoonSoni = findViewById(R.id.prasoonSoni);
        enterName = findViewById(R.id.enterName);

        opening.animate().alpha(1f).setDuration(2000);
        startButton.animate().alpha(1f).setDuration(2000);
        prasoonSoni.animate().alpha(1f).setDuration(2000);
        playerName = findViewById(R.id.playerName);
        playerName.animate().alpha(1f).setDuration(2000);
        enterName.animate().alpha(1f).setDuration(2000);
        welcome = findViewById(R.id.welcome);
        instructions = findViewById(R.id.instructions);
        goButton = findViewById(R.id.goButton);

    }
}