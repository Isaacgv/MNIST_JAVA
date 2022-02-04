package fr.epita.mnist;

import fr.epita.mnist.datamodel.ImageMNIST;
import fr.epita.mnist.services.ImageCsvDAO;

import java.io.File;
import java.util.List;

import static fr.epita.mnist.services.ImageCsvDAO.displayFirstColumn;
import static fr.epita.mnist.services.ImageCsvDAO.showMatrix;
import static fr.epita.mnist.services.MNISTImageProcessor.*;

public class TestReshapingData {
    public static void main(String[] args) throws Exception {

        ImageCsvDAO reader = new ImageCsvDAO();
        List<ImageMNIST> images = reader.getImages(new File("./mob-programming/mnist_test.csv"), 100);
        ImageMNIST image = images.get(23);

        showMatrix(image);
        System.out.print("First Column: \n");
        displayFirstColumn(image);
    }
}
