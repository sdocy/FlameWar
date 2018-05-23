package com.example.android.flamewar;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int trollScore = 0;
    int knightScore = 0;
    boolean battleEnd = false;

    // views for scores
    TextView scoreViewTroll;
    TextView scoreViewKnight;

    // color string
    int flame_red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    void init() {
        scoreViewTroll = findViewById(R.id.troll_score);
        scoreViewKnight = findViewById(R.id.knight_score);

        flame_red = ContextCompat.getColor(getApplicationContext(), R.color.flame_red);
    }

    // returns score to original color after being set to WHITE, to implement
    // the score flashing when it is updated
    Handler handler = new Handler();
    Runnable executeRunnable(final TextView view) {
        return new Runnable() {
            public void run() {
                view.setTextColor(flame_red);
            }
        };
    }

    public void info(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.info),
                Toast.LENGTH_LONG);
        TextView toastMessage = toast.getView().findViewById(android.R.id.message);
        toastMessage.setTextColor(flame_red);
        toast.show();
    }

    // update displayed troll score
    public void displayForTroll() {
        scoreViewTroll.setTextColor(Color.WHITE);
        scoreViewTroll.setText(String.valueOf(trollScore));
        handler.postDelayed(executeRunnable(scoreViewTroll), 100);
    }

    // +3 button pressed for troll
    public void trollPlusThree(View view) {
        if (battleEnd)      return;

        trollScore += 3;
        displayForTroll();
    }

    // +2 button pressed for troll
    public void trollPlusTwo(View view) {
        if (battleEnd)      return;

        trollScore += 2;
        displayForTroll();
    }

    // +1 button pressed for troll
    public void trollPlusOne(View view) {
        if (battleEnd)
            return;

        trollScore += 1;
        displayForTroll();
    }

    // update displayed knight score
    public void displayForKnight() {
        scoreViewKnight.setTextColor(Color.WHITE);
        scoreViewKnight.setText(String.valueOf(knightScore));
        handler.postDelayed(executeRunnable(scoreViewKnight), 100);
    }

    // +3 button pressed for knight
    public void knightPlusThree(View view) {
        if (battleEnd)
            return;

        knightScore += 3;
        displayForKnight();
    }

    // +2 button pressed for knight
    public void knightPlusTwo(View view) {
        if (battleEnd)
            return;

        knightScore += 2;
        displayForKnight();
    }

    // +1 button pressed for knight
    public void knightPlusOne(View view) {
        if (battleEnd)
            return;

        knightScore += 1;
        displayForKnight();
    }

    // reset score keeper
    public void resetScore(View view) {
        TextView scoreView = findViewById(R.id.result_text);
        Button resultButton = findViewById(R.id.result_button);

        if (!battleEnd) {
            battleEnd = true;

            if (knightScore > trollScore) {
                scoreView.setText(getString(R.string.knight_wins));
            } else if (trollScore > knightScore) {
                scoreView.setText(getString(R.string.troll_wins));
            } else {
                scoreView.setText(getString(R.string.draw));
            }

            resultButton.setText(getString(R.string.newBattle));
        } else {
            battleEnd = false;

            resultButton.setText(getString(R.string.endBattle));
            scoreView.setText("");

            knightScore = 0;
            trollScore = 0;

            displayForKnight();
            displayForTroll();
        }
    }
}
