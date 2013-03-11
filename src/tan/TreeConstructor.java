/*
 * This class is used to transform the UDG to the maximum weighted spanning tree.
 */
package tan;

import java.util.ArrayList;
import java.util.HashMap;
import model.Node;
import utilities.DataSource;

/**
 *
 * @author 109472
 */
public class TreeConstructor {

    private ArrayList<String> U = new ArrayList<String>();
    private ArrayList<Node> list = null;
    private int size = DataSource.getTrainingSet().getFeatures().length;

    public TreeConstructor(ArrayList<Node> l) {
        this.list = l;
    }

    public HashMap<String, String> getTree() {
        HashMap<String, String> result = new HashMap<>();
        U.add(list.get(0).getV1());

        while (U.size() != size) {
            Node maxWeight = new Node(null, null, 0);

            for (Node n : list) {
                if (U.contains(n.getV1()) && !U.contains(n.getV2())) {
                    if (n.getW() > maxWeight.getW()) {
                        maxWeight = n;
                    }
                }

                if (U.contains(n.getV2()) && !U.contains(n.getV1())) {
                    if (n.getW() > maxWeight.getW()) {
                        maxWeight = n;
                    }
                }
            }

            if (maxWeight.getW() != 0) {
                if (!U.contains(maxWeight.getV1())) {
                    U.add(maxWeight.getV1());
                    result.put(maxWeight.getV1(), maxWeight.getV2());
                } else {
                    U.add(maxWeight.getV2());
                    result.put(maxWeight.getV2(), maxWeight.getV1());
                }
            } else {
                size--;
            }

        }

        return result;
    }
}
