/*
 * The class is used to store the feature tye and the value of this feature.
 */
package model;

/**
 *
 * @author 109472
 */
public class FV {

    private String feature = null;
    private String value = null;

    public FV(String f, String v) {
        this.feature = f;
        this.value = v;
    }

    public String getFeature() {
        return feature;
    }

    public String getValue() {
        return value;
    }
}
