package fr.epita.mnist;

import fr.epita.mnist.datamodel.ImageMNIST;
import fr.epita.mnist.services.CentroidClassifier;
import fr.epita.mnist.services.ImageCsvDAO;
import fr.epita.mnist.services.MNISTImageProcessor;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static fr.epita.mnist.services.CentroidClassifier.predict;
import static fr.epita.mnist.services.CentroidClassifier.trainCentroids;

public class TestPredict {
    public static void main(String[] args) throws Exception {

        ImageCsvDAO reader = new ImageCsvDAO();
        List<ImageMNIST> imagesTrain = reader.getImages(new File("./mob-programming/mnist_train.csv"), 1000);

        MNISTImageProcessor processor = new MNISTImageProcessor();
        Map<Double, ImageMNIST> imagesCentroidTrain = trainCentroids(imagesTrain);

        List<ImageMNIST> imagesTest = reader.getImages(new File("./mob-programming/mnist_test.csv"), 1000);
        Map<Double, List<ImageMNIST>> imagesByLabelTest = imagesTest.stream().collect(Collectors.groupingBy(ImageMNIST::getLabel));

        List<ImageMNIST> listOfZerosTest = imagesByLabelTest.get(0.0).subList(0, 11);

        for (ImageMNIST imageZero:listOfZerosTest) {
            Double resultLabel = predict(imagesCentroidTrain, imageZero);
            System.out.println(resultLabel);
        }
    }
}
