package nearestNeigh.KDTree;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import nearestNeigh.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by campbellbrobbel on 11/4/17.
 */
public class KDNode {

    private KDNode left;
    private KDNode right;
    private AxisDivider axis;
    private Point point;

    public KDNode(AxisDivider axis, Point point) {
        this.axis = axis;
        this.point = point;
    }

    @Override
    public String toString() {
        return "Node {"+ point.toString() + "}";
    }
}
