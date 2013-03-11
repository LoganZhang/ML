/*
 * This class is used to create the TAN model.
 */
package tan;

import java.util.ArrayList;
import java.util.HashMap;
import model.ExampleSet;
import model.Node;
import utilities.DataSource;

/**
 *
 * @author 109472
 */
public class TAN {

    private ExampleSet es = DataSource.getTrainingSet();
    private String[] feats = null;

    public HashMap<String, String> buildTAN() {
        ArrayList<Node> list = new ArrayList<Node>();
        feats = es.getFeatures();
        MutualInformation mi = new MutualInformation();
        for (String f : feats) {
            {
                ArrayList<Node> l = mi.getRelatedFeature(f);
                list.addAll(l);
            }
        }
        TreeConstructor tc = new TreeConstructor(list);
        HashMap<String, String> tree = tc.getTree();
        return tree;

    }
}
