package com.douchenilla.science.methods.interpolation.tools.interpolation;

public class NewtonInterpolation {

    //Этот метод работает только с равноудалёнными иксами!

    //Так же, как и в предыдущем методе создаёшь массив
    private final double[][] values;

    //Это вычислиться по ходу программы,
    //в функциях getFunctionValueForward и getFunctionValueBackward
    private double qForward;
    private double qBackward;

    //конструктор, это чисто для джавы, он тебе не нужен
    public NewtonInterpolation(double[][] values) {
        this.values = values.clone();
    }

    //создаёшь функцию, которая будет получать значения игрека в прямом направлении
    public double getFunctionValueF(double x) {
        double res = values[1][0];

        this.qForward = (x - values[0][0]) / (values[0][1] - values[0][0]);

        for (int i = 1; i < values[0].length; i++) {
            res += multiplyCoefficientForward(i) * finiteDifference(i);
        }
        return res;
    }

    //создаёшь функцию, которая будет получать коэффициент для прямого направления
    private double multiplyCoefficientForward(int n) {
        double res = 1;
        for (int i = 0; i < n; i++) {
            res *= qForward - i;
        }
        res /= factorial(n);
        return res;
    }

    //функия, получающая значение игрека в обратном направлении
    public double getFunctionValue(double x) {
        double res = values[1][0];

        this.qBackward = (x - values[0][values[0].length - 1]) / (values[0][1] - values[0][0]);

        for (int i = 1; i < values[0].length; i++) {
            res += multiplyCoefficientBackward(i) * finiteDifference(i);
        }
        return res;
    }

    //функция, получающая коэффициент для обратного направления
    private double multiplyCoefficientBackward(int n) {
        double res = 1;
        for (int i = 0; i < n; i++) {
            res *= qBackward + i;
        }
        res /= factorial(n);
        return res;
    }

    //функция, находящая конечные разности степени n
    private double finiteDifference(int n) {
        double res = 0;
        for (int i = 1; i < n + 1; i++) {
            //System.out.println(k_combinationNumber(n - 1, i));
            //System.out.println(values[1][n - i]);
            res += i % 2 == 0 ? -values[1][n - i] * k_combinationNumber(n - 1, i) : values[1][n - i] * k_combinationNumber(n - 1, i);
        }
        return res;
    }


    //функция возвращающая сочетание, я просто заметил, что это будет коэффициентом в конечных разностях
    private int k_combinationNumber(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    //функция возвращающая факториал
    private int factorial(int num) {
        int res = 1;
        for (int i = 1; i < num + 1; i++){
            res *= i;
        }
        return res;
    }

}
