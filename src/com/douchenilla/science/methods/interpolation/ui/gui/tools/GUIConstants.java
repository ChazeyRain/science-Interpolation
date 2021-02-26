package com.douchenilla.science.methods.interpolation.ui.gui.tools;

import java.util.ArrayList;
import java.util.Arrays;

public class GUIConstants {

    private double xWeight = 1;
    private double yWeight = 1;

    private boolean useEqualWeight = false;
    private char mainCoordinate = 'x';

    private int xShift = 0;
    private int yShift = 0;

    private int windowWidth = 600;
    private int windowHeight = 600;

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowSize(int width, int height) {
        this.windowWidth = width;
        this.windowHeight = height;
    }

    private double getXWeight() {
        if (useEqualWeight) {
            return getMainWeight();
        }
        return xWeight;
    }

    private double getYWeight() {
        if (useEqualWeight) {
            return getMainWeight();
        }
        return yWeight;
    }

    public int getXShift() {
        return xShift;
    }

    public int getYShift() {
        return yShift;
    }

    private double getMainWeight() {
        //add case 'z'
        return switch (mainCoordinate) {
            case 'y' -> yWeight;
            case 'z' -> 0; //placeholder for new functions
            default -> xWeight;
        };
    }

    public void plotFit(double[][] coordinates) {
        double xMax = Arrays.stream(coordinates[0]).max().isPresent() ? Arrays.stream(coordinates[0]).max().getAsDouble() : 1;
        double xMin = Arrays.stream(coordinates[0]).min().isPresent() ? Arrays.stream(coordinates[0]).min().getAsDouble() : 1;

        xWeight = (xMax - xMin) / windowWidth;

        double yMax = Arrays.stream(coordinates[1]).max().isPresent() ? Arrays.stream(coordinates[1]).max().getAsDouble() : 1;
        double yMin = Arrays.stream(coordinates[1]).min().isPresent() ? Arrays.stream(coordinates[1]).min().getAsDouble() : 1;

        yWeight = (yMax - yMin) / windowHeight;

        if(useEqualWeight) {
            xShift = (int)(xMin / getMainWeight());
            yShift = (int)(yMin / getMainWeight()) + windowHeight;
        } else {
            xShift = (int)(xMin / getXWeight());
            yShift = (int)(yMin / getYWeight()) + windowHeight;
        }
    }

    public void setMainCoordinate(char c) {
        this.mainCoordinate = c;
    }

    public void setEqualWeight(boolean set) {
        this.useEqualWeight = set;
    }

    public int[][] convertToScreen(double[][] input) {
        int[][] output = new int[2][input[0].length];

        for (int i = 0; i < input[0].length; i++) {
            output[0][i] = (int)(input[0][i] / getXWeight()) - getXShift();
            output[1][i] = (int)(-input[1][i] / getYWeight()) + getYShift();
        }
        return output;
    }
}
