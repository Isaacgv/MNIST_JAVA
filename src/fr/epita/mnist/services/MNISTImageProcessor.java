package fr.epita.mnist.services;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.epita.mnist.datamodel.ImageMNIST;

public class MNISTImageProcessor {

    public static Map<Object, Integer> calculateDistribution(List<ImageMNIST> images) {
        Map<Double, List<ImageMNIST>> imagesByLabel = images.stream().collect(Collectors.groupingBy(ImageMNIST::getLabel));
        Map<Object, Integer> countingMap = new LinkedHashMap<>();
        Object[] keys = imagesByLabel.keySet().stream().sorted().toArray();
        for ( Object key : keys){
            List<ImageMNIST> imagesFilter = imagesByLabel.get(key);
            Integer count = countingMap.get(key);
            for (ImageMNIST image :imagesFilter) {
                if (count == null) {
                    count = 1;
                } else {
                    count += 1;
                }
                countingMap.put(key, count);
            }
        }
        return countingMap;
    }

    public static ImageMNIST computeCentroid(Double label, List<ImageMNIST> images) {
        ImageMNIST centroid = new ImageMNIST(label, new double[28][28]);
        int size = images.size();
        for (ImageMNIST image : images) {
            double[][] pixels = image.getPixels();
            for (int i = 0; i < pixels.length; i++) {
                for (int j = 0; j < pixels[i].length; j++) {
                    centroid.getPixels()[i][j] = centroid.getPixels()[i][j] + pixels[i][j] / size;
                }
            }
        }
        return centroid;
    }

    public static ImageMNIST computeCentroidSTD(Double label, List<ImageMNIST> images, ImageMNIST mean) {
        ImageMNIST centroid = new ImageMNIST(label, new double[28][28]);
        int size = images.size();

        for (ImageMNIST image : images) {
            double[][] pixels = image.getPixels();
            for (int i = 0; i < pixels.length; i++) {
                for (int j = 0; j < pixels[i].length; j++) {
                    centroid.getPixels()[i][j] = centroid.getPixels()[i][j] + Math.pow(pixels[i][j] - mean.getPixels()[i][j], 2);
                }
            }
        }
        double[][] pixels = centroid.getPixels();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                centroid.getPixels()[i][j] = Math.sqrt(centroid.getPixels()[i][j]/(size-1));
            }
        }
        return centroid;
    }

    public double computeDistance(ImageMNIST image1, ImageMNIST image2){
        double distance = 0.0;

        double[][] image1Pixels = image1.getPixels();
        double[][] image2Pixels = image2.getPixels();

        for (int i = 0; i < ImageCsvDAO.MAX_ROW; i++){
            for (int j = 0; j < ImageCsvDAO.MAX_COL; j++){
                 distance = distance + Math.sqrt(Math.pow(image1Pixels[i][j] - image2Pixels[i][j],2));
            }
        }
        return distance;
    }

    public double computeDistanceSTD(ImageMNIST image1, ImageMNIST image2, ImageMNIST mean){
        double distance = 0.0;

        double[][] image1Pixels = image1.getPixels();
        double[][] image2Pixels = image2.getPixels();

        double[][] absDifference = new double[28][28];
        for (int i = 0; i < ImageCsvDAO.MAX_ROW; i++){
            for (int j = 0; j < ImageCsvDAO.MAX_COL; j++){
                absDifference[i][j] = Math.abs(mean.getPixels()[i][j]-image1Pixels[i][j]);

                //distance = distance + Math.sqrt(Math.pow(absDifference[i][j]- image2Pixels[i][j], 2));
                if (absDifference[i][j] > image2Pixels[i][j]) {
                    distance = distance + Math.sqrt(Math.pow(absDifference[i][j] - image2Pixels[i][j], 2));
                }
                else {
                    distance = distance + Math.sqrt(Math.pow(absDifference[i][j] - image2Pixels[i][j], 2))*0.01;
                }
            }
        }
        return distance;
    }

    public static void print(int[][] matrix) {

        System.out.print("\n");
        System.out.print("  |  0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |");
        System.out.print("\n");
        System.out.print(" " + "-".repeat(60));
        System.out.print("\n");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(i+" |");
            for (int j = 0; j < matrix[i].length; j++) {
                String result = ""+ matrix[i][j];
                if (result.length() == 1){
                    System.out.print("  "+result+ "  ");
                }
                else{
                    System.out.print(" "+result+ " ".repeat(5-result.length()-1));
                }

                System.out.print("|");
            }
            System.out.print("\n");
            System.out.print(" " + "-".repeat(60));
            System.out.print("\n");
        }

    }

}
