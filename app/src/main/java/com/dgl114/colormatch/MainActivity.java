/*
 * Author: Reeve Jarvis
 * Course: DGL-114
 * Assignment: #1
 * Due Date: 02-09-2021
 *
 * File: MainActivity.java
 *
 * */

package com.dgl114.colormatch;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declare game model and view instances
    private ColorMatchGame mGame;
    private android.widget.Button[][] mButtonGrid;

    //Unimplemented save state
    private final String GAME_STATE = "gameState";

    //Saved Instance functionality below has not been used, **Attempt at a later date.**
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize view button array to the constraints of the game
        mButtonGrid = new android.widget.Button[ColorMatchGame.NUM_ROWS][ColorMatchGame.NUM_COLS];

        GridLayout gridLayout = findViewById(R.id.color_grid);
        int childIndex = 0;
        for (int row = 0; row < ColorMatchGame.NUM_ROWS; row++) {
            for (int col = 0; col < ColorMatchGame.NUM_COLS; col++) {
                mButtonGrid[row][col] = (Button) gridLayout.getChildAt(childIndex);
                childIndex++;
            }
        }

        //Initialize game logic model
        mGame = new ColorMatchGame();

        //Start game execution
        startGame();


    }

    //Starting the game calls methods to initiate a new game, set the button and target colors and mark current selections
    private void startGame() {
        mGame.newGame();
        setButtonColors();
        setTargetColor();
        markSelection();
    }


    //When a button in the view is clicked, it is found, its state is tracked, and the view representation is marked with an indicator.
    //If selecting a button results in a win, isGameOver is called and a congratulations message is produced.
    public void onColorButtonClick(View view) {
        boolean buttonFound = false;
        // Find the row and col selected
        for (int row = 0; row < ColorMatchGame.NUM_ROWS && !buttonFound; row++) {
            for (int col = 0; col < ColorMatchGame.NUM_COLS && !buttonFound; col++) {
                if (view == mButtonGrid[row][col]) {
                    mGame.toggleButtonSelection(row, col);
                    buttonFound = true;
                }
            }
        }
        markSelection();
        // Congratulate the user if the game is over, Wins is incremented and displayed, and the game begins again.
        if (mGame.isGameOver()) {
            Toast.makeText(this, R.string.congrats, Toast.LENGTH_SHORT).show();
            Button totalWins = findViewById(R.id.wins);
            totalWins.setText(" " + mGame.getNumOfWins()); // Didn't know how to fix this properly
            startGame();
        }
    }

    //Mark each button according to their selected state
    public void markSelection(){
        for (int row = 0; row < ColorMatchGame.NUM_ROWS; row++) {
            for (int col = 0; col < ColorMatchGame.NUM_COLS; col++) {
                if (mGame.isButtonSelected(row, col)) {
                    mButtonGrid[row][col].setText(R.string.selected);
                }else
                    mButtonGrid[row][col].setText(R.string.unselected);
            }
        }
    }

    //Set the buttons in the view according to the button objects color values
    private void setButtonColors() {
        // Set all buttons' background color
        for (int row = 0; row < ColorMatchGame.NUM_ROWS; row++) {
            for (int col = 0; col < ColorMatchGame.NUM_COLS; col++) {
                mButtonGrid[row][col].setBackgroundColor(mGame.getButton(row,col).getColor());
            }
        }
    }

    //Set the color of our target button in the view to its color value
    private void setTargetColor(){
        Button targetButton = findViewById(R.id.target_color);
        targetButton.setBackgroundColor(mGame.getTargetColor());
    }

    //When new game is clicked, start new game and call associated methods
    public void onNewGameClick(View view) {
        startGame();
    }
}