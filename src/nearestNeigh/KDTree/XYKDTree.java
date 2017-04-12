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

        public void addNode(int key, String name) {

            // Create a new Node and initialize it

            KDNode newNode = new Node(key, name);

            // If there is no root this becomes root

            if (root == null) {

                root = newNode;

            } else {

                // Set root as the Node we will start
                // with as we traverse the tree

                Node focusNode = root;

                // Future parent for our new Node

                Node parent;

                while (true) {

                    // root is the top parent so we start
                    // there

                    parent = focusNode;

                    // Check if the new node should go on
                    // the left side of the parent node

                    if (key < focusNode.key) {

                        // Switch focus to the left child

                        focusNode = focusNode.leftChild;

                        // If the left child has no children

                        if (focusNode == null) {

                            // then place the new node on the left of it

                            parent.leftChild = newNode;
                            return; // All Done

                        }

                    } else { // If we get here put the node on the right

                        focusNode = focusNode.rightChild;

                        // If the right child has no children

                        if (focusNode == null) {

                            // then place the new node on the right of it

                            parent.rightChild = newNode;
                            return; // All Done

                        }

                    }

                }
            }

        }
    }
    public void printTree() {


    }



}
