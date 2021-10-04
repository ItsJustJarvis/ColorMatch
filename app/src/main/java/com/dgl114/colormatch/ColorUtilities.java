/*
 * Author: Reeve Jarvis
 * Course: DGL-114
 * Assignment: #1
 * Due Date: 02-09-2021
 *
 * File: ColorUtilities.java
 *
 * */

package com.dgl114.colormatch;

import android.graphics.Color;

//Used to blend two colors to produce a new one.
public class ColorUtilities {
    public static Color blend(Color c0, Color c1) {
        float totalAlpha = c0.alpha() + c1.alpha();
        float weight0 = c0.alpha() / totalAlpha;
        float weight1 = c1.alpha() / totalAlpha;

        float r = weight0 * c0.red() + weight1 * c1.red();
        float g = weight0 * c0.green() + weight1 * c1.green();
        float b = weight0 * c0.blue() + weight1 * c1.blue();
        float a = Math.max(c0.alpha(), c1.alpha());

        return Color.valueOf(Color.argb(a, r, g, b));
    }
}
