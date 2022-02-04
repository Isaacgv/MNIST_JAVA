package fr.epita.mnist;

import fr.epita.mnist.services.ImageCsvDAO;

import java.io.File;
import java.io.FileNotFoundException;

public class TestExtractRawData {
    public static void main(String[] args) throws FileNotFoundException {
        ImageCsvDAO reader = new ImageCsvDAO();

        System.out.print("Train Data \n");
        reader.readRawData(new File("./mob-programming/mnist_train.csv"), 100);

        System.out.print("Test Data \n");
        reader.readRawData(new File("./mob-programming/mnist_test.csv"), 100);
        
    }
}
