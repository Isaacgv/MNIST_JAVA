package fr.epita.mnist.services;

import fr.epita.mnist.datamodel.ImageMNIST;

import java.util.*;
import java.util.stream.Collectors;

public class CentroidClassifier {

    public static Map<Double, ImageMNIST> trainCentroids(List<ImageMNIST> images){
        Map<Double, List<ImageMNIST>> imagesByLabel = images.stream().collect(Collectors.groupingBy(ImageMNIST::getLabel));
        Map<Double, ImageMNIST> centroids = new LinkedHashMap<>();

        for (Map.Entry<Double, List<ImageMNIST>> entry : imagesByLabel.entrySet()){
            ImageMNIST centroid = MNISTImageProcessor.computeCentroid(entry.getKey(), entry.getValue());
            centroids.put(centroid.getLabel(), centroid);
        }
        return centroids;
    }

    public static Double predict(Map<Double, ImageMNIST> centroids, ImageMNIST image) {

        MNISTImageProcessor processor = new MNISTImageProcessor();

        Double label = null;
        Double newCentroid = null;
        int count = 0;
        for (Map.Entry<Double, ImageMNIST> entry : centroids.entrySet()){
            double centroid = processor.computeDistance(image, entry.getValue());
            if (count == 0) {
                newCentroid = centroid;
                label = entry.getKey();
            }
            if (centroid < newCentroid){
                newCentroid = centroid;
                label = entry.getKey();

            }
            count += 1;
        }
        return label;

    }

    public static int[][] predictList(Map<Double, ImageMNIST> centroids, Map<Double, List<ImageMNIST>> imagesByLabelTest) {

        List<Double> predictLabel = new ArrayList<>();
        List<Double> trueLabel = new ArrayList<>();
        int[][] confMatrix = new int[10][10];
        double positive = 0;
        double negative = 0;

        for (Map.Entry<Double, List<ImageMNIST>> entry: imagesByLabelTest.entrySet()) {
            Double label = entry.getKey();
            for (ImageMNIST image:entry.getValue()){
                Double resultLabel = predict(centroids, image);
                predictLabel.add(resultLabel);
                trueLabel.add(label);
                confMatrix[label.intValue()][resultLabel.intValue()] += 1;

                if (label.intValue() == resultLabel.intValue()) {
                    positive += 1;
                }
                else{
                    negative += 1 ;
                }
            }
        }

        MNISTImageProcessor.print(confMatrix);
        int total = (int) (positive+negative);
        System.out.print("N Images:" + total + "\n");
        System.out.print("Accuracy:" + positive*100/(positive+negative)+"%");

        return confMatrix;
    }


}
