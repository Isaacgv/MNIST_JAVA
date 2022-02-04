package fr.epita.mnist.launchers;

import fr.epita.mnist.datamodel.ImageMNIST;
import fr.epita.mnist.services.ImageCsvDAO;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static fr.epita.mnist.services.CentroidClassifier.trainCentroids;
import static fr.epita.mnist.services.CentroidClassifierSTD.*;

public class PredictSTDConfusionMatrix {
    public static void main(String[] args) throws Exception {
        ImageCsvDAO reader = new ImageCsvDAO();
        List<ImageMNIST> imagesTrain = reader.getAllImages(new File("./mob-programming/mnist_train.csv"));

        Map<Double, ImageMNIST> means = trainCentroids(imagesTrain);
        Map<Double, ImageMNIST> imagesCentroidTrain = trainCentroidsSTD(imagesTrain, means);

        List<ImageMNIST> imagesTest = reader.getAllImages(new File("./mob-programming/mnist_test.csv"));
        Map<Double, List<ImageMNIST>> imagesByLabelTest = imagesTest.stream().collect(Collectors.groupingBy(ImageMNIST::getLabel));

        int[][] resultLabel = predictListSTD(imagesCentroidTrain, imagesByLabelTest, means);

    }
}
