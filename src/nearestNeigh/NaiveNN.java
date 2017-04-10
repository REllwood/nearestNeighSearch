package nearestNeigh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is required to be implemented.  Naive approach implementation.
 *
 * @author Jeffrey, Youhan
 */
public class NaiveNN implements NearestNeigh{

    List<Point> pointsArray = new ArrayList<Point>();

    @Override
    public void buildIndex(List<Point> points) {

        Collections.sort(points);
        this.pointsArray = points;


    }

    @Override
    public List<Point> search(Point searchTerm, int k) {

        List<Point> nearestPoints = new ArrayList<Point>();

        for (Point point : this.pointsArray) {
            if(nearestPoints.size() < k) {
                nearestPoints.add(point);
            }
            else {
                /** Will only add a point if its distance to the searchTerm is less than the distance from the point inside
                 * the nearestPoints list to the searchTerm.
                 */
                Collections.sort(nearestPoints, new Comparator<Point>() {
                    @Override
                    public int compare(Point o1, Point o2) {

                        if(o1.distTo(searchTerm) > o2.distTo(searchTerm)) {
                            return 1;
                        }
                        else if(o1.distTo(searchTerm) < o2.distTo(searchTerm)) {
                            return -1;
                        }
                        return 0;

                    }
                });

                for(Point nearestPoint : nearestPoints) {
                    //If the list is not filled yet, the item is automatically added without checking distance.

                    if (nearestPoints.indexOf(nearestPoint) > -1) {
                        if (point.distTo(searchTerm) < nearestPoint.distTo(searchTerm)) {
                            System.out.println("");
                            nearestPoints.add(nearestPoints.indexOf(nearestPoint), point);
                            nearestPoints.remove(k);
                            break;

                        }
                    }
                    else {
                    }


                }

            }

        }

        for (Point p : nearestPoints) {
            System.out.println(p.toString());
        }
        return nearestPoints;
    }

    private void shiftListContentsDownFromIndex(List<Point> list, int i) {

    }

    private int compareDistances(Point destPoint, Point startPoint1, Point startPoint2) {
        int number;

        double dist1 = startPoint1.distTo(destPoint);
        double dist2 = startPoint2.distTo(destPoint);

        if (dist1 < dist2) {
            number = -1;
        }
        else if (dist1 > dist2) {
            number = 1;
        }
        else {
            number = 0;
        }

        return number;
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
