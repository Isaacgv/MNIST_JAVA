package fr.epita.mnist.datamodel;

public class ImageMNIST {

    double label;
    double[][] dataMatrix = new double[28][28];

    public ImageMNIST(double label, double[][] pixels) {
        this.label = label;
        this.dataMatrix = pixels;
    }

    public double[][] getPixels() {
        return dataMatrix;
    }

    public void setPixels(double[][] pixels) {
        this.dataMatrix = dataMatrix;
    }

    public double getLabel() {
        return label;
    }

    public void setLabel(double label) {
        this.label = label;
    }
}
