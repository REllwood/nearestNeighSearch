package nearestNeigh;

import nearestNeigh.KDTree.KDNode;
import nearestNeigh.KDTree.XYKDTree;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by campbellbrobbel on 10/4/17.
 */
public class NaiveNNTest {


    private List<Point> initPoints() throws FileNotFoundException {
        List<Point> points = new ArrayList<Point>();
        File dataFile = new File("src/test 100");
        Scanner scanner = new Scanner(dataFile);
        while (scanner.hasNext()) {
            String id = scanner.next();
            Category cat = Point.parseCat(scanner.next());
            Point point = new Point(id, cat, scanner.nextDouble(), scanner.nextDouble());
            points.add(point);
        }
        Collections.sort(points, new PointXComparator());
        scanner.close();

        return points;
    }
    private List<Point> initSearchPoints() throws FileNotFoundException {
        List<Point> points = new ArrayList<Point>();
        File dataFile = new File("src/test 10");
        Scanner scanner = new Scanner(dataFile);
        while (scanner.hasNext()) {
            String id = scanner.next();
            Category cat = Point.parseCat(scanner.next());
            Point point = new Point(id, cat, scanner.nextDouble(), scanner.nextDouble());
            points.add(point);
        }
        Collections.sort(points, new PointXComparator());
        scanner.close();

        return points;
    }

    public NaiveNN naive = new NaiveNN();

    @Test
    public void test() throws FileNotFoundException {
        List<Point> searchPoints = initSearchPoints();
        List<Point> listOfPoints = initPoints();

        NaiveNN naive = new NaiveNN();
        naive.buildIndex(listOfPoints);


        for (Point searchPoint : searchPoints) {

            List<Point> nearestPoints = naive.search(searchPoint, 10);

            for (Point p : nearestPoints) {
                System.out.println(p.toString());
            }
            System.out.println();
        }

    }

}