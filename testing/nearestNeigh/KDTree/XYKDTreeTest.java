package nearestNeigh.KDTree;

import nearestNeigh.Category;
import nearestNeigh.KDTreeNN;
import nearestNeigh.Point;
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
        Collections.sort(points);
        scanner.close();

        return points;
    }

    @Test
    private void test() {

        XYKDTree tree = new XYKDTree(points);

        KDNode node = tree.getRoot();
        System.out.println(node.toString());
        node = node.getLeft();
        System.out.println(node.toString());
        node = node.getLeft();
        System.out.println(node.toString());
        node = node.getRight();
        System.out.println(node.toString());
    }

}