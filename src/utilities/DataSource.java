/*
 * This class is used to store the global exampleSet
 */
package utilities;

import model.ExampleSet;

/**
 *
 * @author 109472
 */
public class DataSource {

    private static final String fileTrain = "D:/study/ML/Report/DataSets/CarEvaluation/car.data.txt/";
    private static final String fileTest = "D:/study/ML/Report/DataSets/CarEvaluation/car.data.txt/";
//    private static final String fileTrain = "D:/study/ML/Report/DataSets/Tic-Tac-Toe/tic-tac-toe-data.txt/";
//     private static final String fileTest = "D:/study/ML/Report/DataSets/Tic-Tac-Toe/tic-tac-toe-data.txt/";
    private static final ExampleSet esTrain = ExampleSet.getData(fileTrain);
    private static final ExampleSet esTest = ExampleSet.getData(fileTest);

    public static ExampleSet getTrainingSet() {
        return esTrain;
    }

    public static ExampleSet getTestingSet() {
        return esTest;
    }
}
