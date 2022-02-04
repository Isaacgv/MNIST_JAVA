package fr.epita.mnist.launchers;

import fr.epita.mnist.datamodel.ImageMNIST;
import fr.epita.mnist.services.ImageCsvDAO;
import fr.epita.mnist.services.MNISTImageProcessor;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static fr.epita.mnist.services.CentroidClassifier.*;

public class PredictListConfusionMatrix {
    public static void main(String[] args) throws Exception {
        ImageCsvDAO reader = new ImageCsvDAO();
        List<ImageMNIST> imagesTrain = reader.getAllImages(new File("./mnist_train.csv"));

        MNISTImageProcessor processor = new MNISTImageProcessor();
        Map<Double, ImageMNIST> imagesCentroidTrain = trainCentroids(imagesTrain);

        List<ImageMNIST> imagesTest = reader.getAllImages(new File("./mnist_test.csv"));
        Map<Double, List<ImageMNIST>> imagesByLabelTest = imagesTest.stream().collect(Collectors.groupingBy(ImageMNIST::getLabel));

        int[][] resultLabel = predictList(imagesCentroidTrain, imagesByLabelTest);

    }
}

