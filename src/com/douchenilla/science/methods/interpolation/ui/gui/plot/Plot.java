package com.douchenilla.science.methods.interpolation.ui.gui.plot;

import com.douchenilla.science.methods.interpolation.ui.gui.tools.GUIConstants;
import com.douchenilla.science.methods.interpolation.ui.gui.tools.GUIOperations;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Plot extends GUIOperations {
    JFrame frame = new JFrame("Plot");

    private PlotPanel canvas;

    ArrayList<double[][]> listFuncValues = new ArrayList<>();
    ArrayList<int[][]> listScreenFuncValues = new ArrayList<>();

    ArrayList<double[][]> listPoints = new ArrayList<>();
    ArrayList<int[][]> listScreenPoints = new ArrayList<>();

    public void createWindow() {
        frame.setSize(getWindowWidth() + 17, getWindowHeight() + 40);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        canvas = new PlotPanel();
        frame.add(canvas);
    }

    public void loadFuncValues(double[][] array) {
        listFuncValues.add(array);
        listScreenFuncValues.add(convertToScreen(array));
        canvas.update();
    }

    public void loadPoints(double[][] array) {
        listPoints.add(array);
        listScreenPoints.add(convertToScreen(array));
        canvas.update();
    }

    public class PlotPanel extends JPanel {
        public PlotPanel() {
            update();
        }

        public void update() {
            repaint();
        }

        @Override
        public void paint(Graphics g){

            if(listFuncValues != null) {
                for (int[][] values : listScreenFuncValues) {
                    for (int i = 1; i < values[0].length; i++) {
                        g.drawLine(values[0][i - 1], values[1][i - 1], values[0][i], values[1][i]);
                        System.out.println(values[0][i - 1] + " " + values[1][i - 1] + " " + values[0][i] + " " + values[1][i]);
                    }
                }
            }

            if(listPoints != null) {
                for (int[][] values : listScreenPoints) {
                    for (int i = 0; i < values[0].length; i++) {
                        g.setColor(Color.red);
                        g.fillOval(values[0][i] - 3, values[1][i] - 3, 6, 6);
                        System.out.println(values[0][i] + " " + values[1][i]);
                    }
                }
            }
        }
    }

}
