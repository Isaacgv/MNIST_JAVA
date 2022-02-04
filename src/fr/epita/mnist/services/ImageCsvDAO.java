package fr.epita.mnist.services;

import fr.epita.mnist.datamodel.ImageMNIST;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ImageCsvDAO {
// double[][]  =>
//    [
//     [0,2,3],
//     [1,2,3],
//     [4,5,6]
//    ]

    public static final int MAX_COL = 28;
    public static final int MAX_ROW = 28;

    //Read .csv file and convert list of array in a 28x28 matrix
    public List<ImageMNIST> getAllImages(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<ImageMNIST> images = new ArrayList<>();
        scanner.nextLine(); //skip the first line to avoid reading headers

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            double[] lineAsDoubleArray = loadLine(line); // read a line under the form of a double array
            if (lineAsDoubleArray.length == 0) {
                continue;
            }
            double[][] pixels = new double[MAX_COL][MAX_ROW];

            for (int i = 0; i < MAX_ROW; i++) {
                for (int j = 0; j < MAX_COL; j++) {
                    pixels[i][j] = lineAsDoubleArray[i * MAX_ROW + j + 1];
                }
            }
            images.add(new ImageMNIST(lineAsDoubleArray[0], pixels));
        }
        scanner.close();
        return images;
    }

    public List<ImageMNIST> getImages(File file, int maxLines) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int counter = 0;

        List<ImageMNIST> images = new ArrayList<>();
        scanner.nextLine(); //skip the first line to avoid reading headers
        while (scanner.hasNext() &&  maxLines > 0 && counter < maxLines) {
            String line = scanner.nextLine();
            double[] lineAsDoubleArray = loadLine(line); // read a line under the form of a double array
            double[][] pixels = new double[MAX_COL][MAX_ROW];

            for (int i = 0; i < MAX_ROW; i++) {
                for (int j = 0; j < MAX_COL; j++) {
                    pixels[i][j] = lineAsDoubleArray[i * MAX_ROW + j + 1];
                }
            }
            images.add(new ImageMNIST(lineAsDoubleArray[0], pixels));
            counter++;
        }
        scanner.close();
        return images;
    }

    //Read .csv file line by line and convert the array of String inyo an array of double
    public void readRawData(File file, int maxLines) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<ImageMNIST> images = new ArrayList<>();
        scanner.nextLine();

        int counter = 0;
        while (scanner.hasNext() &&  maxLines > 0 && (counter < maxLines) ) {
            String line = scanner.nextLine();
            double[] lineAsDoubleArray = loadLine(line); // read a line under the form of a double array
            if (counter <=1) {
                print(lineAsDoubleArray);
            }
            counter++;
        }
        scanner.close();
    }

    //Load line and convert entry to double format
    private static double[] loadLine(String sample) {
        String[] entries = sample.split(",");

        double[] entriesAsDouble = new double[entries.length];

        for (int i = 0; i < entriesAsDouble.length; i++){
            entriesAsDouble[i] = Double.parseDouble(entries[i]);
        }
        return entriesAsDouble;
    }

    //print line in form of a list
    private static void print(double[] vector) {
        System.out.print("[");
        System.out.print(vector[0]);
        for (double v : vector) {
            System.out.print(", " +v);
        }
        System.out.println("]");

    }

    //prints a matrix in the console
    public static void showMatrix(ImageMNIST image) {
        double[][] pixels = image.getPixels();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                if (pixels[i][j] < 100) {
                    System.out.print("..");
                } else {
                    System.out.print("xx");
                }
            }
            System.out.println();
        }
    }

    //Print first column of the image
    public static void displayFirstColumn(ImageMNIST image) {
        double[][] pixels = image.getPixels();
        for (int i = 0; i < pixels.length; i++) {
            System.out.print(pixels[i][0] + "\t" );
        }
    }

}
