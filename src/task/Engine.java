/*
 * This class is main class which is used to start the task.
 */
package task;

import java.util.HashMap;
import tan.TAN;
import utilities.DataSource;

/**
 *
 * @author 109472
 */
public class Engine {

    public static void main(String[] args) {
        Engine t = new Engine();
        t.run();
    }

    public void run() {
        TAN tan = new TAN();
        HashMap<String, String> tree = tan.buildTAN();
        System.out.println(tree);

        Predict predict = new Predict(tree);
        predict.testTAN();
        predict.testNaiveBayes();
    }
}
