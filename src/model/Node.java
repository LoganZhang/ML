/*
 * This class is used to store the node.
 */
package model;

/**
 *
 * @author 109472
 */
public class Node {

    private String v1 = null;
    private String v2 = null;
    private double w = 0;

    public Node(String v1, String v2, double w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }

    public String getV1() {
        return v1;
    }

    public String getV2() {
        return v2;
    }

    public double getW() {
        return w;
    }
}
