package fr.epita.mnist;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.epita.mnist.datamodel.ImageMNIST;
import fr.epita.mnist.services.ImageCsvDAO;

import static fr.epita.mnist.services.ImageCsvDAO.showMatrix;

public class TestMNISTReader {


    public static void main(String[] args) throws Exception {
        ImageCsvDAO reader = new ImageCsvDAO();
        List<ImageMNIST> images = reader.getImages(new File("./mob-programming/mnist_test.csv"), 100);

        Map<Double, List<ImageMNIST>> imagesByLabel = images.stream().collect(Collectors.groupingBy(ImageMNIST::getLabel));


        List<ImageMNIST> listOfOnes = imagesByLabel.get(1.0);

        if ( !(images.get(0).getLabel() == 7)){
            throw new Exception("verification exception, expected 7 and got: "+ images.get(0).getLabel());
        };

        showMatrix(images.get(0));
    }

}
