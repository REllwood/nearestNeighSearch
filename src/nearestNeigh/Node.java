package nearestNeigh;

/**
 * Created by rhysellwood on 12/4/17.
 */
/**Removed public scope so that tere is no need for getters or setters**/
class Node {


    Point point;
    Node leftChild;
    Node rightChild;


    public Node (Point point) {
        this.point = point;
    }
}
