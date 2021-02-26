package com.douchenilla.science.methods.interpolation.tools.interpolation;

public class LagrangeInterpolation {

    //1. Задать двумерный массив с переменными, array[2][i]
    //гдe array[0] - массив с x, array[1] - массив с y, i - колличество точек
    private final double[][] values;

    public LagrangeInterpolation(double[][] values) {
        this.values = values.clone();
    }

    //2. Создать функцию для получения L(x) в точке x
    //L(x) примерно равен f(x)
    //она принимает x extended, отдаёт extended
    public double getFunctionValue(double x) {
        double res = 0;

        for (int i = 0; i < values[0].length; i++) {
            res += values[1][i] * basisPolynomial(x, i);
        }
        return res;
    }

    //3. Создать процедуру для полинома (она вызывается в getFunctionValue)
    //она принимает x extended, и i integer
    //отдаёт extended
    public double basisPolynomial(double x, int i) {
        double res = 1;
        for (int j = 0; j < values[0].length; j++) {
            if (i == j) {
                continue;
            }
            res *= (x - values[0][j]) / (values[0][i] - values[0][j]);
        }
        return res;
    }
}
