package nearestNeigh.KDTree;

import nearestNeigh.Point;
import nearestNeigh.PointXComparator;
import nearestNeigh.PointYComparator;
import org.omg.PortableInterceptor.ACTIVE;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * Created by campbellbrobbel on 11/4/17.
 */
public class XYKDTree {

    private KDNode root;
    private int depth;
    private int totalNodes;
    public XYKDTree(List<Point> points) {
        this.totalNodes = points.size();
        this.root = buildTree(points, true);
    }

    public List<Point> kNearestNeighbours(Point searchPoint, int k) {

        int count = 0;
        List<Point> nearestPoints = new ArrayList<Point>();
        if (this.totalNodes == 1) {
            nearestPoints.add(this.root.getPoint());

        }
        else if(totalNodes > 1){
            while (count < k) {
                Point nearestPoint = nearestPoint(searchPoint);
                nearestPoints.add(nearestPoint);
            }
        }


        return nearestPoints;
    }


    public Point nearestPoint(Point searchPoint) {
        KDNode searchParentNode = parentNodeFor(searchPoint);

        double minDistance = searchPoint.distTo(searchParentNode.getPoint());

        if (searchParentNode == null) {
            System.out.println("List Empty");
            return null;
        }

        Point betterPoint = nearestPoint(this.root, minDistance, searchPoint);

        if (betterPoint != null) {
            return betterPoint;
        }

        return searchParentNode.getPoint();
    }
    private Point nearestPoint(KDNode node, double minDistance, Point searchPoint) {

        if (node != null) {
            System.out.println(node.toString());
        }

        Point resultPoint = null;

        if (node.getPoint().cat == searchPoint.cat) {
            double distance = searchPoint.distTo(node.getPoint());

            if (distance <= minDistance) {
                resultPoint = node.getPoint();
                minDistance = distance;
            }
        }

        Point perpPoint = perpendicularPoint(searchPoint, node);

        double perdDistance = searchPoint.distTo(perpPoint);

        if (perdDistance < minDistance) {
            if (node.getRight() != null) {

                Point rightPoint = nearestPoint(node.getRight(), minDistance, searchPoint);
                if (rightPoint != null) {
                    double rightDist = searchPoint.distTo(rightPoint);
                    if (rightDist < minDistance) {
                        resultPoint = rightPoint;
                        minDistance = rightDist;
                    }
                }

            }
            if (node.getLeft() != null) {

                Point leftPoint = nearestPoint(node.getLeft(), minDistance, searchPoint);
                if (leftPoint != null) {
                    double leftDist = searchPoint.distTo(leftPoint);
                    if (leftDist < minDistance) {
                        resultPoint = leftPoint;
                        minDistance = leftDist;
                    }
                }

            }
        }
        else {
            Point point = null;
                if (node.getPoint().lon > searchPoint.lon) {
                    if (node.getRight() != null) {
                        point = nearestPoint(node.getRight(), minDistance, searchPoint);
                    }
                }
                else {
                    if (node.getLeft() != null) {
                        point = nearestPoint(node.getLeft(), minDistance, searchPoint);
                    }
                }
                if (point != null) {
                    return point;
                }

        }

        return resultPoint;
    }

    private KDNode parentNodeFor(Point searchItem) {

        KDNode traverseNode = this.root;
        Boolean traverse = true;

        while(traverse) {
            if (traverseNode == null || (traverseNode.getLeft() == null && traverseNode.getRight() == null)) {
                traverse = false;
            }
            else if (traverseNode.getAxis() == AxisDivider.x) {
                if (searchItem.lat > traverseNode.getPoint().lat) {
                    if(traverseNode.getRight() == null) {
                        traverse = false;
                    }
                    else {
                        traverseNode = traverseNode.getRight();
                    }
                }
                else if(searchItem.lat < traverseNode.getPoint().lat) {
                    if(traverseNode.getLeft() == null) {
                        traverse = false;
                    }
                    else {
                        traverseNode = traverseNode.getLeft();
                    }
                }
                else if(searchItem.lat == traverseNode.getPoint().lat) {
                    traverse = false;
                }
            }
            else if (traverseNode.getAxis() == AxisDivider.y) {
                if (searchItem.lon > traverseNode.getPoint().lon) {
                    if(traverseNode.getRight() == null) {
                        traverse = false;
                    }
                    else {
                        traverseNode = traverseNode.getRight();
                    }
                }
                else if(searchItem.lon < traverseNode.getPoint().lon) {
                    if(traverseNode.getLeft() == null) {
                        traverse = false;
                    }
                    else {
                        traverseNode = traverseNode.getLeft();
                    }
                }
                else if(searchItem.lon == traverseNode.getPoint().lon) {
                    traverse = false;
                }

            }
        }

        return traverseNode;

    }

    private Point perpendicularPoint (Point searchPoint, KDNode node) {

        double newLat;
        double newLon;

        if (node.getAxis() == AxisDivider.x) {
            newLat = node.getPoint().lat;
            newLon = searchPoint.lon;
        }
        else {
            newLat = searchPoint.lat;
            newLon = node.getPoint().lon;
        }

        Point perp = new Point("", searchPoint.cat, newLat, newLon);

        return perp;
    }
    private KDNode buildTree(List<Point> points , boolean bxDim) {

        if (points.size() == 0) {
            return null;
        }
        // Must sort each array by the appropriate dimension (bxDim)

        if (bxDim) {
            Collections.sort(points, new PointXComparator());
        }
        else {
            Collections.sort(points, new PointYComparator());
        }

        int median;
        if (points.size() % 2 == 0) {
            median = (points.size()/2) - 1;
        }
        else  {
            median = points.size()/2;
        }
        KDNode parent = new KDNode(getAxis(bxDim), points.get(median));
        KDNode leftChild = null;
        KDNode rightChild = null;

        if (median > 0) {
            List<Point> leftPoints = parent.getLeftPoints(points, median);
            leftChild = buildTree(leftPoints, !bxDim);
        }
        if (median < points.size() - 1) {
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

    /*public void addNode(KDNode newNode) {

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
    } */



}
