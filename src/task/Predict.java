/*
 * This class is used to test data set.
 */
package task;

import java.util.HashMap;
import model.ExampleSet;
import model.FV;
import model.Instance;
import utilities.DataSource;
import utilities.ProbabilityCentre;

/**
 *
 * @author 109472
 */
public class Predict {

    private ExampleSet es = DataSource.getTrainingSet();
    private ProbabilityCentre pc = ProbabilityCentre.getInstance();
    private HashMap<String, String> tree = null;
    /**
     *
     * @param tree TAN model
     */
    public Predict(HashMap<String, String> tree)
    {
        this.tree = tree;
    }

    public void testNaiveBayes()
    {
        ExampleSet testSet = DataSource.getTestingSet();
        int nn = 0;
        int rrr=0;
        for (Instance instance : testSet.getInstance()) {

            double maxP = 0;
            String prediction = "";
            for(String label:DataSource.getTrainingSet().getLabels())
            {
                double probability = 1.0;
                for (String f : es.getFeatures()) {
                    String valueX = instance.getValueByFeature(f);
                        probability *= pc.getPxGz(new FV(f, valueX), label);
                    }
                double t3 =  pc.getPz(label);
                probability *= t3;
                if (probability > maxP) {
                    maxP = probability;
                    prediction = label;
                }
            }
        
            String right = es.getInstance().get(nn).getLabel();
            boolean testr = right.equals(prediction);
            if(testr == true)
            {
                rrr++;
            }
            nn++;
        }
        double rate =1- 1.0*rrr/es.size();
        System.out.println("Naive Bayes Classifier Error Rate: "+rate);
    }
    public void testTAN() {
        ExampleSet testSet = DataSource.getTestingSet();
        int nn = 0;
        int rrr=0;
        for (Instance instance : testSet.getInstance()) {

            double maxP = 0;
            String prediction = "";
            for(String label:DataSource.getTrainingSet().getLabels())
            {
                double probability = 1.0;
                for (String f : es.getFeatures()) {
                    String valueX = instance.getValueByFeature(f);
                    if (tree.containsKey(f)) {
                        String parent = tree.get(f);
                        String valueY = instance.getValueByFeature(parent);
                        double t1 = pc.getPxGyz(new FV(f, valueX), new FV(parent, valueY), label);
                        probability *= t1;
                    } else {
                        double t2 = pc.getPxGz(new FV(f, valueX), label);
                        probability *= t2;
                    }
                }
                double t3 =  pc.getPz(label);
                probability *= t3;
                if (probability > maxP) {
                    maxP = probability;
                    prediction = label;
                }
            }
        
            String right = es.getInstance().get(nn).getLabel();
            boolean testr = right.equals(prediction);
            if(testr == true)
            {
                rrr++;
            }
            nn++;
        }
        double rate =1- 1.0*rrr/es.size();
        System.out.println("TAN Classifier Error Rate: "+rate);
    }
}
