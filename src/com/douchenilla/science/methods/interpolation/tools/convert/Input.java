package com.douchenilla.science.methods.interpolation.tools.convert;

public class Input {
    public static double[][] toDoubleArray(String[] string) {
        String[] firstDimension = string[0].split("&");
        String[] secondDimension = string[1].split("&");

        if (firstDimension.length != secondDimension.length) {
            throw new UnsupportedOperationException();
        }

        double[][] output = new double[2][firstDimension.length];

        for (int i = 0; i < firstDimension.length; i++) {
            output[0][i] = Double.parseDouble(firstDimension[i]);
            output[1][i] = Double.parseDouble(secondDimension[i]);
        }

        return output;
    }
}
