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
                        if (point.distTo(searchTerm) <= nearestPoint.distTo(searchTerm) && point.cat == searchTerm.cat) {
                            nearestPoints.add(nearestPoints.indexOf(nearestPoint), point);
                            nearestPoints.remove(k);
                            break;

                        }
                    }
                    else {
                        System.out.println("Not valid index.");
                    }


                }

            }

        }

        return nearestPoints;
    }

    @Override
    public boolean addPoint(Point point) {

        if (isPointIn(point)) {
            return false;
        }

        this.pointsArray.add(point);
        Collections.sort(pointsArray);

        return true;
    }

    @Override
    public boolean deletePoint(Point point) {

        for (Point p : pointsArray) {
            if(point.equals(p)) {
                this.pointsArray.remove(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isPointIn(Point point) {

        for (Point p : this.pointsArray) {
            if (p.equals(point)) {
                return true;
            }
        }
        return false;
    }

}
