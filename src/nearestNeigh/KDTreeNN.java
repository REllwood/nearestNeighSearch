package nearestNeigh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This class is required to be implemented.  Kd-tree implementation.
 *
 * @author Jeffrey, Youhan
 */


public class KDTreeNN implements NearestNeigh{

    Node root;

    List<Point> pointsArray = new ArrayList<Point>();

    @Override
    public void buildIndex(List<Point> points) {
        // To be implemented.

        Collections.sort(points);
        this.pointsArray = points;

    }

    @Override
    public List<Point> search(Point searchTerm, int k) {
        // To be implemented.
        return new ArrayList<Point>();



    }

    @Override
    public boolean addPoint(Point point) {
        // To be implemented.

        Node node = new Node(point);
        if (root == null)
            root = node;




        return false;
    }

    @Override
    public boolean deletePoint(Point point) {
        // To be implemented.
        return false;
    }

    @Override
    public boolean isPointIn(Point point) {
        // To be implemented.
        return false;
    }

}
