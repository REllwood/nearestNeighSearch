package nearestNeigh.KDTree;

import nearestNeigh.Point;
import org.omg.PortableInterceptor.ACTIVE;

import java.util.Collections;
import java.util.List;

/**
 * Created by campbellbrobbel on 11/4/17.
 */
public class XYKDTree {

    private KDNode root;
    private int depth;

    public XYKDTree(List<Point> points) {

        Collections.sort(points);
        this.root = buildTree(points, true);

    }

//    KDNode buildTree(List<Point> points, boolean bXDim) {
//
//
//        sortedPoints = sort(points, bXDim);
//        // find the median from sorted points
//        median = findMedian(sortedPoints);
//
//        // construct a node for the median point
//        currParent = buildNode(sortedPoints[median]);
//        leftChild = null;
//        rightChild = null;
//        // Check if there is a left partition (indexing starts at 0).  If so, recursively partition it
//        if median > 0
//        // flip() inverts the boolean value (effectively changing the dimension we split on next)
//        leftChild = buildTree(sortedPoints[0..median-1], flip(bXDim));
//        // check if there is a right partition
//        if median < length(points)
//                // flip() inverts the boolean value (effectively changing the dimension we split on next)
//                rightChild = buildTree(sortedPoints[median+1...length(points)-1], flip(bXDim));
//        currParent.setLeft(leftChild);
//        currParent.setRight(rightChild);
//
//        return currRoot;
//    }

    private KDNode buildTree(List<Point> points , boolean bxDim) {

        if (points.size() == 0) {
            return null;
        }

        int median = points.size()/2;
        KDNode parent = new KDNode(getAxis(bxDim), points.get(median));
        KDNode leftChild = null;
        KDNode rightChild = null;

        if (median > 1) {
            List<Point> leftPoints = parent.getLeftPoints(points, median);
            leftChild = buildTree(leftPoints, !bxDim);
        }
        if (median < points.size()-1 || median == 1) {
            List<Point> rightPoints = parent.getRightPoints(points, median);
            rightChild = buildTree(rightPoints, !bxDim);
        }

        parent.setLeft(leftChild);
        parent.setRight(rightChild);

        return parent;

    }

    public KDNode getRoot() {
        return root;
    }

    private AxisDivider getAxis(boolean bxDim) {
        AxisDivider axis;
        if (bxDim) {
            axis = AxisDivider.x;
        }
        else {
            axis = AxisDivider.y;
        }
        return axis;
    }
    public void addNode(KDNode newNode) {

            if (root == null) {

                root = newNode;

            } else {

                // Set root as the Node we will start
                // with as we traverse the tree

                KDNode traverseNode = root;

                // Future parent for our new Node

                KDNode parent;

                while (true) {

                    // root is the top parent so we start
                    // there

                    parent = traverseNode;

                    // Check if the new node should go on
                    // the left side of the parent node

                    if (newNode.getPoint().compareTo(traverseNode.getPoint()) == 1) {

                        // Switch focus to the left child

                        traverseNode = traverseNode.getLeft();

                        // If the left child has no children

                        if (traverseNode == null) {

                            // then place the new node on the left of it

                            parent.setLeft(newNode);
                            return; // All Done

                        }

                    } else { // If we get here put the node on the right

                        traverseNode = traverseNode.getRight();

                        // If the right child has no children

                        if (traverseNode == null) {

                            // then place the new node on the right of it

                            parent.setRight(newNode);
                            return; // All Done

                        }

                    }

                }
            }
    }
    public void printTree() {


    }



}
