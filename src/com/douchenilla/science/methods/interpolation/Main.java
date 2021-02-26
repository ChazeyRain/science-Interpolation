package com.douchenilla.science.methods.interpolation;

import com.douchenilla.science.methods.interpolation.tools.convert.Input;
import com.douchenilla.science.methods.interpolation.tools.interpolation.AkimaSplineInterpolation;
import com.douchenilla.science.methods.interpolation.tools.interpolation.LagrangeInterpolation;
import com.douchenilla.science.methods.interpolation.ui.console.ConsoleInput;
import com.douchenilla.science.methods.interpolation.ui.console.FileInput;
import com.douchenilla.science.methods.interpolation.ui.gui.plot.Plot;

public class Main {
    public static void main(String[] args) {
        String fileName = ConsoleInput.getFileName();
        String[] inputString = FileInput.getInput(fileName);
        double[][] inputValues = Input.toDoubleArray(inputString);
        Plot plot = new Plot();
        plot.createWindow();

        AkimaSplineInterpolation spline = new AkimaSplineInterpolation(inputValues);
        LagrangeInterpolation spline2 = new LagrangeInterpolation(inputValues);


        double[][] a = new double[2][300];
        for (int i = 0; i < 300; i++) {
            a[0][i] = -2 + (double)4/300 * i;
            a[1][i] = spline.getFunctionValue(a[0][i]);
        }

        double[][] c = new double[2][300];
        for (int i = 0; i < 300; i++) {
            c[0][i] = -2 + (double)4/300 * i;
            c[1][i] = spline2.getFunctionValue(a[0][i]);
        }

        double[][] b = {{1, 100}, {1, 100}};
        plot.plotFit(inputValues);
        plot.loadFuncValues(a);
        plot.loadFuncValues(c);
        plot.loadPoints(inputValues);

    }
}
