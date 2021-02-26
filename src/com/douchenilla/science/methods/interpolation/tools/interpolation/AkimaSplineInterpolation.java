package com.douchenilla.science.methods.interpolation.tools.interpolation;

public class AkimaSplineInterpolation {

    private final double[][] values;

    double a;
    double b;
    double c;
    double d;

    double s1;
    double s2;
    double x1;
    double x2;
    double y1;
    double y2;

    public AkimaSplineInterpolation(double[][] values) {
        this.values = values.clone();
    }

    public double getFunctionValue(double x) {
        int index = getIndex(x);

        //P(x) = a * x^3 + b * x^2 + c * x + d
        a = getA(index);
        b = getB(index);
        c = getC(index);
        d = getD(index);

        return a * Math.pow(x - x1, 3) + b * Math.pow(x - x1, 2) + c * (x - x1) + d;
    }

    private int getIndex(double x) {
        for (int i = 0; i < values[0].length; i++) {
            if (values[0][i] > x) {
                return Math.max(i - 1, 0);
            }
        }
        return values[0].length - 2;
    }

    //Si
    private double getSlope(int n) {
        double m_2 = getSegmentSlope(n - 2);
        double m_1 = getSegmentSlope(n - 1);
        double m = getSegmentSlope(n);
        double m1 = getSegmentSlope(n + 1);

        return (Math.abs(m1 - m) * m_1 + Math.abs(m_1 - m_2) * m) /
                (Math.abs(m1 - m) + Math.abs(m_1 - m_2));
    }

    // (y1 - y0) / (x1 - x0)
    private double getSegmentSlope(int n) {
        if (n == -2) {
            System.out.println("good");
            return 2 * getSegmentSlope(-1) - getSegmentSlope(0);
        } else if (n == -1) {
            return 2 * getSegmentSlope(0) - getSegmentSlope(1);
        } else if (n == values[0].length - 1) {
            return 2 * getSegmentSlope(n - 2) - getSegmentSlope(n - 3);
        } else if (n == values[0].length) {
            return 2 * getSegmentSlope(n - 1) - getSegmentSlope(n - 2);
        }
        return (values[1][n + 1] - values[1][n]) / (values[0][n + 1] - values[0][n]);
    }

    private double getA(int n) {
        s1 = getSlope(n);
        s2 = getSlope(n + 1);
        x1 = values[0][n];
        x2 = values[0][n + 1];
        y1 = values[1][n];
        y2 = values[1][n + 1];

        return (s1 + s2 - 2 * (y2 - y1) / (x2 - x1)) / Math.pow(x2 - x1, 2);
    }

    private double getB(int n) {
        return (3 * (y2 - y1) / (x2 - x1) - 2 * s1 - s2) / (x2 - x1);
    }

    private double getC(int n) {
        return s1;
    }

    private double getD(int n) {
        return y1;
    }
}
