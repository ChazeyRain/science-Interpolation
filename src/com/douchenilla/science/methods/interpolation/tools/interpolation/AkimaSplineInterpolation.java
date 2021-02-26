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

        return a * Math.pow(x, 3) + b * x * x + c * x + d;
    }

    private int getIndex(double x) {
        for (int i = 0; i < values[0].length - 3; i++) {
            if (values[0][i] > x) {
                return Math.max(i - 2, 2);
            }
        }
        return values[0].length - 4;
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
        return (values[1][n + 1] - values[1][n]) / (values[0][n + 1] - values[0][n]);
    }

    private double getA(int n) {
        s1 = getSlope(n);
        s2 = getSlope(n + 1);
        x1 = values[0][n];
        x2 = values[0][n + 1];
        y1 = values[1][n];
        y2 = values[1][n + 1];

        return (2 * (s2 * x2 - s1 * x1 - y2 + y1) - (s2 - s1) * (x2 + x1)) /
                (4 * (Math.pow(x2, 3) - Math.pow(x1, 3)) - 3 * (x2 * x2 - x1 * x1) * (x2 + x1));
    }

    private double getB(int n) {
        return (s2 * x2 - s1 * x1 - y2 + y1 - 2 * a * (Math.pow(x2, 3) - Math.pow(x1, 3))) /
                (x2 * x2 - x1 * x1);
    }

    private double getC(int n) {
        //System.out.println(s1 - 3 * a * x1 * x1 - 2 * b * x1);
        //System.out.println(s2 - 3 * a * x2 * x2 - 2 * b * x2);

        return s1 - 3 * a * x1 * x1 - 2 * b * x1;
    }

    private double getD(int n) {
        //System.out.println(y1 - a * Math.pow(x1, 3) - b * x1 * x1 - c * x1);
        //System.out.println(y2 - a * Math.pow(x2, 3) - b * x2 * x2 - c * x2);

        return y1 - a * Math.pow(x1, 3) - b * x1 * x1 - c * x1;
    }
}
