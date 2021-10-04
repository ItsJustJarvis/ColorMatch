/*
* Author: Reeve Jarvis
* Course: DGL-114
* Assignment: #1
* Due Date: 02-09-2021
*
* File: Button.java
*
* */

package com.dgl114.colormatch;

import android.graphics.Color;
import java.util.Random;

public class Button {
    //Button instance Variables
    private int color;
    private boolean selected;

    //Button constructor creates a new button with a randomly generated color and selected set to false
    public Button(){
        color = generateColor();
        selected = false;
    }

    //Get the buttons color value
    public int getColor() {
        return color;
    }

    //Is the button selected or not
    public boolean isSelected() {
        return selected;
    }

    //Switch the selected state of the button
    public void switchSelected() {
        selected = !selected;
    }

    //Random color generator producing an argb value
    public int generateColor(){
        int color;
        Random rand = new Random();
        int alphaValue = rand.nextInt(256);
        int redValue = rand.nextInt(256);
        int greenValue = rand.nextInt(256);
        int blueValue = rand.nextInt(256);
        color = Color.argb(alphaValue,redValue,greenValue,blueValue);
        return color;
    }

}
