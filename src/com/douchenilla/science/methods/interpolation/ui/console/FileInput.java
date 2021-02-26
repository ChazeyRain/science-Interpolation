package com.douchenilla.science.methods.interpolation.ui.console;

import javax.lang.model.element.UnknownDirectiveException;
import java.io.File;
import java.util.Scanner;

public class FileInput {
    public static String[] getInput(String fileName) {
        String path = "samples/" + fileName + ".txt";
        File file = new File(path);
        System.out.println(file.getAbsolutePath());
        try {
            String[] output = new String[2];
            Scanner scanner = new Scanner(file);

            output[0] = scanner.nextLine().replaceAll("\\s+", "&");
            output[1] = scanner.nextLine().replaceAll("\\s+", "&");

            return output;

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
