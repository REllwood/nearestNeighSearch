package nearestNeigh;

import nearestNeigh.Point;

import java.util.Comparator;

/**
 * Created by campbellbrobbel on 16/4/17.
 */
public class PointXComparator implements Comparator<Point>{

    @Override
    public int compare(Point o1, Point o2) {

        if (o1.lat > o2.lat) {
            return 1;
        }
        else if(o1.lat < o2.lat) {
            return -1;
        }
        return 0;
    }
}
