package nearestNeigh;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by campbellbrobbel on 10/4/17.
 */
public class NaiveNNTest {

    public NaiveNN naive = new NaiveNN();

    @Test
    public void buildIndex() {
        List<Point> points = new ArrayList<Point>();
        Point point = new Point("da", Category.EDUCATION, 1, 1);
        Point point2 = new Point("ad", Category.EDUCATION, 2, 1);
        Point point3 = new Point("fd", Category.EDUCATION, 3, 1);
        Point point4 = new Point("gh", Category.EDUCATION, 4, 1);
        points.add(point3);
        points.add(point4);
        points.add(point);
        points.add(point2);

        Collections.sort(points, new PointXComparator());

        for (Point tempPoint : points) {
            System.out.println(tempPoint.toString());
        }
        System.out.println("\n");

        Point point5 = new Point("da", Category.EDUCATION, 5, 1);
        points.add(2, point5);

        for (Point tempPoint : points) {
            System.out.println(tempPoint.toString());
        }

        System.out.println("\n");

        points.remove(points.size() - 1);
        for (Point tempPoint : points) {
            System.out.println(tempPoint.toString());
        }

        System.out.println("\n");
        Collections.sort(points, new PointXComparator());

        for (Point tempPoint : points) {
            System.out.println(tempPoint.toString());
        }

    }
}