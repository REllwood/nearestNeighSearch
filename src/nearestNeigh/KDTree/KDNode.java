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

    public KDNode getLeft() {
        return left;
    }

    public KDNode getRight() {
        return right;
    }

    public AxisDivider getAxis() {
        return axis;
    }

    public Point getPoint() {
        return point;
    }

    public void setLeft(KDNode left) {
        this.left = left;
    }

    private void setRight(KDNode right) {
        this.right = right;
    }

    public List<Point> getLeftPoints(List<Point> points, int median) {
        List<Point> leftList = new ArrayList<Point>();

        for (int count = 0; count < median; count++) {
            leftList.add(points.get(count));
        }
        return leftList;
    }

    public List<Point> getRightPoints(List<Point> points, int median) {
        List<Point> rightList = new ArrayList<Point>();

        if (median == 1) {
            rightList.add(points.get(median-1));
        }
        else  {
            for (int count = median + 1; count < points.size(); count++) {
                rightList.add(points.get(count));
            }
        }

        return rightList;
    }
}
