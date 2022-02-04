
**Project Description**
In src.fr.epita.mnist
&nbsp;
- launchers:
  - PredictListConfusionMatrix: Result of the dataset test using mean centroid
  - PredictSTDConfusionMatrix: Result of the test dataset using STD
  
- datamodel:
  - ImageMNIST: Class that will contain 2 fields : “label” and “dataMatrix”
    - getPixels: return dataMatrix
    - setPixels: set values to dataMatrix

- services:
  - CentroidClassifier
    - trainCentroids: Return a data structure associating a digit to its centroid
    - predict: Predict label of each images
    - predictList: Calculate the confusion matrix
  
  - CentroidClassifierSTD
    - trainCentroidsSTD:  Return a data structure associating a digit to its STD
    - predictSTD:Predict label of each images
    - predictListSTD: Calculate the confusion matrix
  
  - ImageCsvDAO
    - getAllImages: Read .csv file and convert list of array in a 28x28 matrix
    - getImages: Limit the number of lines to read
    - readRawData: Read .csv file line by line and convert the array of String inyo an array of double
    - loadLine: Load line and convert entry to double format
    - print: Print line in form of a list
    - displayFirstColumn: Print first column of the image
    - showMatrix: Print a matrix in the console

  - MNISTImageProcessor
    - calculateDistribution: For each digit, will assign the total count of occurrences in the data set
    - computeCentroid: compute the “average representant” (also called centroid)
    - computeCentroidSTD: compute standard deviation for every pixel
    - computeDistance: decide that this image will be classified as the digit from which the average representant is the closest in terms of distance
    - computeDistanceSTD: classification with standard deviation
    - print: Display the confusion matrix

**Test**
Run in test.fr.epita.mnist.test:

1. TestExtractRawData : Print the 2 first lines in the console in an array of String:
2. TestMNISTReader: Test showMatrix
3. TestReshapingData: showMatrix method on the matrix extracted from line 23 and print the first column
4. TestCalculateDistribution: Total count of occurrences in the data set
5. TestTrainCentroids: Test centroids
6. TestFirstClassification: Test classification of the only the 10 first “0” occurrences in the test dataset
7. TestPredict: Test prediction of the training
