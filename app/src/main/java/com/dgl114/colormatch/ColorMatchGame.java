/*
 * Author: Reeve Jarvis
 * Course: DGL-114
 * Assignment: #1
 * Due Date: 02-09-2021
 *
 * File: ColorMatchGame.java
 *
 * */

package com.dgl114.colormatch;

import android.graphics.Color;

import java.util.Random;

public class ColorMatchGame {

    //Constants for 2d array dimensions
    public static final int NUM_ROWS = 3;
    public static final int NUM_COLS = 3;

    //Instance variables for the game
    private int numOfWins;
    private int mSelectedButtons = 0;
    private int selectedColor1;
    private int selectedColor2;
    private int targetColor;


    // Array of Button objects that makes up the grid
    private Button[][] mButtons;

    // Constructor, initializing button array to constant dimensions and setting num of wins to 0
    public ColorMatchGame() {
        mButtons = new Button[NUM_ROWS][NUM_COLS];
        numOfWins=0;
    }

    //New game is started, selected buttons set to 0, array is loaded with button objects, target color is set.
    public void newGame() {
        mSelectedButtons = 0;

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++){
                mButtons[row][col] = new Button();
            }
        }
        setTargetColor(chooseWinningCombination().toArgb());
    }

    //Return the selected state of a button at indicated row/column
    public boolean isButtonSelected(int row, int col) {
        return mButtons[row][col].isSelected();
    }

    //Toggle selected state of button at given row/column
    //Check and maintain current number of selected buttons
    public void toggleButtonSelection(int row, int col) {
        if(!mButtons[row][col].isSelected()){
           if(mSelectedButtons == 0) {
               mButtons[row][col].switchSelected();
               mSelectedButtons++;
           }else if(mSelectedButtons == 1) {
               mButtons[row][col].switchSelected();
               mSelectedButtons++;
           }
       }else if(mButtons[row][col].isSelected()) {
            mButtons[row][col].switchSelected();
            mSelectedButtons--;
        }
    }

    //Generate winning color from two randomly selected buttons current colors, return color
    public Color chooseWinningCombination(){
       Button button1 = chooseRandomButton();
       Button button2 = chooseRandomButton();
        while (button1.getColor() == button2.getColor()) {
            button2 = chooseRandomButton();
        }
        Color color1 = Color.valueOf(button1.getColor());
        Color color2 = Color.valueOf(button2.getColor());
        return ColorUtilities.blend(color1,color2);
    }

    //Randomly select a button within array limits, return button coordinates
    private Button chooseRandomButton(){
        Random rand = new Random();
        int targetRow = rand.nextInt(NUM_ROWS);
        int targetCol = rand.nextInt(NUM_COLS);
        return mButtons[targetRow][targetCol];
    }

    //Evaluate win conditions based on selected buttons, and target color, return if won or not. increment wins if won.
    public boolean isGameOver() {
        for (int row = 0; row < ColorMatchGame.NUM_ROWS; row++) {
            for (int col = 0; col < ColorMatchGame.NUM_COLS; col++) {
                if(mButtons[row][col].isSelected())
                    selectedColor1 = mButtons[row][col].getColor();
            }
        }
        for (int row = NUM_ROWS-1; row >= 0; row--) {
            for (int col = NUM_COLS-1; col >= 0; col--) {
                if(mButtons[row][col].isSelected())
                    selectedColor2 = mButtons[row][col].getColor();
            }
        }
        if(ColorUtilities.blend(Color.valueOf(selectedColor1),Color.valueOf(selectedColor2)).toArgb() == Color.valueOf(targetColor).toArgb()) {
            numOfWins++;
            return true;
        }
        return false;
    }

    //Getter to access button from within controller
    public Button getButton(int row, int col) {
        return mButtons[row][col];
    }

    //Getter/setter for using target color
    public int getTargetColor() {
        return targetColor;
    }

    public void setTargetColor(int targetColor) {
        this.targetColor = targetColor;
    }

    //Getter for total wins
    public int getNumOfWins() {
        return numOfWins;
    }

    /*BELOW CODE UNIMPLEMENTED*/

    //I have not implemented the save states. I couldn't figure out how to make it work, and didn't have the time.
    //My app does rotate to landscape however it does not retain its state.
    public String getState() {
        StringBuilder boardString = new StringBuilder();
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                char selected = mButtons[row][col].isSelected() ? 'T' : 'F';
                boardString.append(selected);

            }
        }

        return boardString.toString();
    }

    public void setState(String gameState) {
        int index = 0;
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
               //mButtons[row][col].setSelected(gameState.charAt(index) == 'T');
                index++;
            }
        }
    }
}
