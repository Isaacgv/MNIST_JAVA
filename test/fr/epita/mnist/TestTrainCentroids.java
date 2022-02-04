package fr.epita.mnist;

import fr.epita.mnist.datamodel.ImageMNIST;
import fr.epita.mnist.services.ImageCsvDAO;
import fr.epita.mnist.services.MNISTImageProcessor;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static fr.epita.mnist.services.CentroidClassifier.trainCentroids;
import static fr.epita.mnist.services.ImageCsvDAO.showMatrix;
import static fr.epita.mnist.services.MNISTImageProcessor.calculateDistribution;

public class TestTrainCentroids {
    public static void main(String[] args) throws Exception {

        ImageCsvDAO reader = new ImageCsvDAO();
        List<ImageMNIST> images = reader.getImages(new File("./mob-programming/mnist_train.csv"), 1000);

        MNISTImageProcessor processor = new MNISTImageProcessor();
        Map<Double, ImageMNIST> imagesCentroid = trainCentroids(images);

        ImageMNIST centroidOne = imagesCentroid.get(1.0);

        showMatrix(centroidOne);

    }
}
