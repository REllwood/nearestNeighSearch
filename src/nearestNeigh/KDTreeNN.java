package nearestNeigh;

import nearestNeigh.KDTree.XYKDTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is required to be implemented.  Kd-tree implementation.
 *
 * @author Jeffrey, Youhan
 */
public class KDTreeNN implements NearestNeigh{

    XYKDTree tree;

    @Override
    public void buildIndex(List<Point> points) {
        Collections.sort(points, new PointXComparator());
        tree = new XYKDTree(points);
    }

    @Override
    public List<Point> search(Point searchTerm, int k) {
        // To be implemented.
        return new ArrayList<Point>();
    }

    @Override
    public boolean addPoint(Point point) {
        // To be implemented.
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
