/*
 * This class is used to calculate the conditional mutual information value.
 */
package tan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import model.ExampleSet;
import model.FV;
import model.Node;
import utilities.DataSource;
import utilities.ProbabilityCentre;

/**
 *
 * @author 109472
 */
public class MutualInformation {

    private ExampleSet exampleSet = DataSource.getTrainingSet();
    private String[] labels = DataSource.getTrainingSet().getLabels();
    private String[] feats = null;
    private HashMap<String, HashSet<String>> values = null;
    private ProbabilityCentre pc = ProbabilityCentre.getInstance();

    public MutualInformation() {
        initializeData();
    }

    //initialize data, get the information of the training data.
    private void initializeData() {
        feats = this.exampleSet.getFeatures();
        values = this.exampleSet.getValues();
    }
    //Return each item of the conditional mutual information value.

    private double getItemValue(double Pxyz, double PxyGz, double PxGz, double PyGz) {
        double result = 1.0*Pxyz * Math.log(PxyGz *1.0/ (1.0*(PyGz * PxGz)));
        return result;
    }

    //get the value of P (x,y,z);
    private double getPxyz(FV x, FV y, String label) {
        return pc.getPxyz(x, y, label);
    }

    //get the value of P (x,y |z);
    private double getPxyGz(FV x, FV y, String label) {
        return pc.getPxyGz(x, y, label);
    }

    //get the value of P (y|z);
    private double getPyGz(FV y, String label) {
        return pc.getPyGz(y, label);
    }

    //get the value of P (x|z);
    private double getPxGz(FV x, String label) {
        return pc.getPxGz(x, label);
    }

    //Return the most related feature.
    public ArrayList<Node> getRelatedFeature(String feature) {
        ArrayList<Node> result = new ArrayList<Node>();
        HashSet<String> valsX = values.get(feature);
        for (String f : feats) {
            
            if (!f.equals(feature)) {
                double CMI = 0;
                for (String label : labels) {
                    HashSet<String> valsY = values.get(f);
                    for (String x : valsX) {
                        FV fvX = new FV(feature, x);
                        for (String y : valsY) {
                            FV fvY = new FV(f, y);
                            double Pxyz = getPxyz(fvX, fvY, label);
                            double PxyGz = getPxyGz(fvX, fvY, label);
                            double PxGz = getPxGz(fvX, label);
                            double PyGz = getPyGz(fvY, label);
                            CMI += getItemValue(Pxyz, PxyGz, PxGz, PyGz);
                        }
                    }
                }
                 Node n = new Node(feature, f, CMI);
                 result.add(n);
            }
        }
        return result;
    }
}
