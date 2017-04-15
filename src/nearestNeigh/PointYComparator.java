package nearestNeigh;


import java.util.Comparator;

/**
 * Created by campbellbrobbel on 16/4/17.
 */
public class PointYComparator implements Comparator<Point>{

    @Override
    public int compare(Point o1, Point o2) {
        if (o1.lon > o2.lon) {
            return 1;
        }
        else if(o1.lon < o2.lon) {
            return -1;
        }
        return 0;
    }

}
