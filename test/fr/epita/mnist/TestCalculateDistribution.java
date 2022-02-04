package fr.epita.mnist;

import fr.epita.mnist.datamodel.ImageMNIST;
import fr.epita.mnist.services.ImageCsvDAO;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static fr.epita.mnist.services.MNISTImageProcessor.*;

public class TestCalculateDistribution {
    public static void main(String[] args) throws Exception {

        ImageCsvDAO reader = new ImageCsvDAO();
        List<ImageMNIST> imagesTrain = reader.getImages(new File("./mob-programming/mnist_train.csv"), 1000);
        Map<Object, Integer> distributionTrain = calculateDistribution(imagesTrain);
        System.out.print("Train Dataset: \n");
        System.out.print(distributionTrain);

        List<ImageMNIST> imagesTest = reader.getImages(new File("./mob-programming/mnist_test.csv"), 1000);
        Map<Object, Integer> distributionTest = calculateDistribution(imagesTest);
        System.out.print("\nTest Dataset: \n");
        System.out.print(distributionTest);

    }
}
