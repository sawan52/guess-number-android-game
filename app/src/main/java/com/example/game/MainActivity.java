package com.example.game;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView messageTextView;
    private Button startGameButton, checkNumberButton, resetButton;
    private EditText enterNumberEditText;
    private int count = 10;
    private int randomNumber;
    private boolean hasWon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageTextView = findViewById(R.id.instructions);
        startGameButton = findViewById(R.id.start_game_button);
        checkNumberButton = findViewById(R.id.check_number_button);
        enterNumberEditText = findViewById(R.id.enter_number_edit_text);
        resetButton = findViewById(R.id.reset_button);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        checkNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGuessedNumber();
            }
        });
    }

    private void checkGuessedNumber() {

        String str = enterNumberEditText.getText().toString();
        if (str.isEmpty()) {
            Toast.makeText(this, "Enter a number first!", Toast.LENGTH_SHORT).show();
        } else {
            int guess = Integer.parseInt(str);
            if (count >= 1) {
                if (randomNumber < guess) {
                    messageTextView.setText("The number is smaller than " + guess + "\n\nYou have only " + (count - 1) + " attempts left.");
                } else if (randomNumber > guess) {
                    messageTextView.setText("The number is greater than " + guess + "\n\nYou have only " + (count - 1) + " attempts left.");
                } else {
                    hasWon = true;
                    checkWinner();
                }
                count--;
                if (count == 0) {
                    checkWinner();
                }
            }
        }
    }

    private void startGame() {
        randomNumber = (int) ((Math.random() * 100) + 1);
        count = 10;
        hasWon = false;
        startGameButton.setVisibility(View.GONE);
        checkNumberButton.setVisibility(View.VISIBLE);
        enterNumberEditText.setVisibility(View.VISIBLE);
        resetButton.setVisibility(View.VISIBLE);
        enterNumberEditText.setText("");

        runAnimation();
        messageTextView.setText("A random number has been generated between 1 to 100 and you have to guess that number." + "\n\nYou have only " + count + " attempts to do this.");
    }

    private void checkWinner() {
        if (hasWon) {
            messageTextView.setText("Hurray you guessed it correctly.\nYOU WON!");
        } else {
            messageTextView.setText("Oops you haven't guessed it.\nSorry, YOU LOSE!\n\nThe correct number was " + randomNumber);
        }
        checkNumberButton.setVisibility(View.GONE);
        enterNumberEditText.setVisibility(View.GONE);
    }

    private void runAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.tv_anim);
        anim.reset();
        messageTextView.clearAnimation();
        messageTextView.startAnimation(anim);
    }
}
