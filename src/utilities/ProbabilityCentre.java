/*
 * This class is used to get the probabilities
 */
package utilities;

import model.ExampleSet;
import model.FV;

/**
 *
 * @author 109472
 */
public class ProbabilityCentre {

    private static final ProbabilityCentre pc = new ProbabilityCentre();
    private ExampleSet es = null;

    public static ProbabilityCentre getInstance() {
        return pc;
    }

    private ProbabilityCentre() {
        es = DataSource.getTrainingSet();
    }

    //get the value of P (x,y,z);
    public double getPxyz(FV x, FV y, String z) {
        ExampleSet esZ = es.getExamples(z);
        ExampleSet esX = esZ.getExamples(x.getFeature(), x.getValue());
        ExampleSet esY = esX.getExamples(y.getFeature(), y.getValue());
        int n = esY.size();
        return n * 1.0 / es.size();
    }

    //get the value of P (x,y |z);
    public double getPxyGz(FV x, FV y, String z) {
        ExampleSet esZ = es.getExamples(z);
        ExampleSet esX = esZ.getExamples(x.getFeature(), x.getValue());
        ExampleSet esY = esX.getExamples(y.getFeature(), y.getValue());
        int n = esY.size();
        if (n == 0) {
            n = 1;
        }
        return n * 1.0 / esZ.size();
    }

    //get the value of P (y|z);
    public double getPyGz(FV y, String z) {
        ExampleSet esZ = es.getExamples(z);
        ExampleSet esY = esZ.getExamples(y.getFeature(), y.getValue());
        int n = esY.size();
        if (n == 0) {
           n = 1;
        }
        return n * 1.0 / esZ.size();
    }

    //get the value of P (x|z);
    public double getPxGz(FV x, String z) {
        ExampleSet esZ = es.getExamples(z);
        ExampleSet esX = esZ.getExamples(x.getFeature(), x.getValue());
        int n = esX.size();
        if (n == 0) {
          n = 1;
        }
        return n * 1.0 / esZ.size();
    }

    //get the value of P (z);
    public double getPz(String z) {
        ExampleSet esZ = es.getExamples(z);
        int n = esZ.size();
        if (n == 0) {
           n = 1;
        }
        return n * 1.0 / es.size();
    }

    //get the value of P (x |y,z);
    public double getPxGyz(FV x, FV y, String z) {
        ExampleSet esZ = es.getExamples(z);
        ExampleSet esY = esZ.getExamples(y.getFeature(), y.getValue());
        ExampleSet esX = esY.getExamples(x.getFeature(), x.getValue());
        int n = esX.size();
        return n * 1.0 / esY.size();
    }
}
