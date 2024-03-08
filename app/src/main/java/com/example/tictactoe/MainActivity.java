package com.example.tictactoe;


import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //0: X, 1: 0, 2: empty
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameActive = true;
    int activePlayer = 0;

    private TextView playerOneName, playerTwoName;

    public void dropIn(View view){
        TextView turnX = findViewById(R.id.turnX);
        TextView turn0 = findViewById(R.id.turn0);
        //turnX.setVisibility(View.VISIBLE);
        //turn0.setVisibility(View.INVISIBLE);
        ImageView tick = (ImageView) view;

        int tapTick = Integer.parseInt(tick.getTag().toString());
        boolean isNotFull = Arrays.stream(gameState).anyMatch(e -> e == 2);
        if (gameState[tapTick] == 2 && gameActive) {
            gameState[tapTick] = activePlayer;


            if (activePlayer == 0 ) {
                tick.setImageResource(R.drawable.cross);
                turnX.setVisibility(View.INVISIBLE);
                turn0.setVisibility(View.VISIBLE);
                activePlayer = 1;

            } else if(activePlayer == 1 ){
                tick.setImageResource(R.drawable.zero);
                turn0.setVisibility(View.INVISIBLE);
                turnX.setVisibility(View.VISIBLE);
                activePlayer = 0;

            }

            for (int [] winningPosition : winPositions){
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2){

                    gameActive = false;
                    String Winner = "";

                    if (activePlayer == 1){
                        Winner = "X";
                    }
                    else if (activePlayer == 0){
                        Winner = "O";
                    }



                    Button playAgainButton = findViewById(R.id.playAgainButton);

                    TextView winnerTextView = findViewById(R.id.winnerTextView);

                    winnerTextView.setText(Winner + " Chiến thắng");

                    playAgainButton.setVisibility(view.VISIBLE);

                    winnerTextView.setVisibility(view.VISIBLE);

                    turn0.setVisibility(View.INVISIBLE);
                    turnX.setVisibility(View.INVISIBLE);

                }


            }


        }
        else if (gameState[tapTick] != 2 && gameActive && !isNotFull){

            gameActive = false;
            turn0.setVisibility(view.INVISIBLE);
            turnX.setVisibility(view.INVISIBLE);

            String Draw = "";

            Button playAgainButton = findViewById(R.id.playAgainButton);

            TextView winnerTextView = findViewById(R.id.winnerTextView);

            winnerTextView.setText(Draw + " Hòa");

            playAgainButton.setVisibility(view.VISIBLE);

            winnerTextView.setVisibility(view.VISIBLE);


        }


    }


    public void playAgain(View view) {

        Button playAgainButton =  findViewById(R.id.playAgainButton);

        TextView winnerTextView =  findViewById(R.id.winnerTextView);

        TextView turnX = findViewById(R.id.turnX);

        TextView turn0 = findViewById(R.id.turn0);

        turnX.setVisibility(View.VISIBLE);

        turn0.setVisibility(View.INVISIBLE);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout =  findViewById(R.id.gridLayout);

        for(int i = 0; i< gridLayout.getChildCount(); i++){
            ImageView tick = (ImageView) gridLayout.getChildAt(i);

            tick.setImageDrawable(null);
        }

        for (int i = 0; i<gameState.length; i++){

            gameState[i] = 2;
        }

        activePlayer = 0;

        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneName = findViewById(R.id.playerOneName);
        playerTwoName = findViewById(R.id.playerTwoName);

        final String getPalyerOneName = getIntent().getStringExtra("Người chơi 1");
        final String getPalyerTwoName = getIntent().getStringExtra("Người chơi 2");

        playerOneName.setText(getPalyerOneName);
        playerTwoName.setText(getPalyerTwoName);
    }


}