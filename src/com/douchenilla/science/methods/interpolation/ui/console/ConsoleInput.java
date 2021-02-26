package com.douchenilla.science.methods.interpolation.ui.console;

import java.util.Scanner;

public class ConsoleInput {

    private static final Scanner scanner = new Scanner(System.in);

    public static String[] getValues() {
        System.out.println("Enter values: ");
        String[] string = new String[2];
        string[0] = scanner.nextLine().replaceAll("\\s+", "&");
        string[1] = scanner.nextLine().replaceAll("\\s+", "&");
        return string;
    }

    public static String getFileName() {
        System.out.println("Enter file name:");
        return scanner.nextLine();
    }

    public static double inputX() {
        System.out.println("Enter x:");
        return Double.parseDouble(scanner.next());
    }
}
