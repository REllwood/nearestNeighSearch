package nearestNeigh.KDTree;

import nearestNeigh.*;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by campbellbrobbel on 12/4/17.
 */
public class XYKDTreeTest {

    private List<Point> points = initPoints();

    public XYKDTreeTest() throws FileNotFoundException {
    }

    private List<Point> initPoints() throws FileNotFoundException {
        List<Point> points = new ArrayList<Point>();
        File dataFile = new File("src/sampleData2.txt");
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

    @Test
    public void test() {


//        Collections.sort(points, new PointXComparator());
//        for (Point point : points) {
//            System.out.println(point);
//        }
//        System.out.println();
//        Collections.sort(points, new PointYComparator());
//        for (Point point : points) {
//            System.out.println(point);
//        }


        XYKDTree tree = new XYKDTree(points);

        KDNode root = tree.getRoot();

        tree.kNearestNeighbours(points.get(0), 10);
    }

    public void testFunction(int number) {

        number = number + 5;
        System.out.println(number);

    }

    @Test
    public void functionTest() {
        System.out.println(3/2);
    }
}