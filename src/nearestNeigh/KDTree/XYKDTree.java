package nearestNeigh.KDTree;

import nearestNeigh.Point;
import java.util.List;

/**
 * Created by campbellbrobbel on 11/4/17.
 */
public class XYKDTree {

    private KDNode root;
    private int depth;

    public XYKDTree(List<Point> points) {

    }

    public void addNode(KDNode node) {

        KDNode nodeToAdd = new KDNode(AxisDivider.x, Point.parsePoint("123"));

        if (root == null) {
            root = node;
        }
        else {

            KDNode lastNode = root;
            KDNode parent;

            while (true) {
                parent = lastNode;

            }
        }
    }
    public void printTree() {


    }

    private KDNode traverseAndAddNode (KDNode node, KDNode nodeToAdd){

    }

}
