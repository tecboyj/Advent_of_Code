package advent_of_code;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Day {
    protected int year;
    protected int day;

    protected boolean test = true;

    File test1;
    File test2;
    File main;
    public Day(int year, int day, boolean runTests, Object p1, Object p2) {
        test1 = new File("res/" + year + "/Day" + day + "/test1.txt");
        test2 = new File("res/" + year + "/Day" + day + "/test2.txt");
        main = new File("res/" + year + "/Day" + day + "/input.txt");

        if (p1.equals(0)) return;

        if (runTests) {
            String test1Output = part1(makeScanner(test1));
            if (String.valueOf(p1).equals(test1Output)) System.out.println("Test 1 Passed");
            else {
                System.out.println("Test 1 Failed");
                System.out.println(test1Output);
                return;
            }
        }

        test = false;
        System.out.println(year + " Day " + day + " Part 1: " + part1(makeScanner(main)));

        if (p2.equals(0)) return;
        if (runTests) {
            test = true;
            String test21Output = part2(makeScanner(test1));
            if (String.valueOf(p2).equals(test21Output)) System.out.println("Test 2 Passed");
            else {
                String test22Output = "0";
                try {
                    test22Output = part2(makeScanner(test2));
                } catch (Exception ignored) {}
                if (String.valueOf(p2).equals(test22Output)) System.out.println("Test 2 Passed");
                else {
                    System.out.println("Test 2 Failed");
                    System.out.println(test21Output);
                    System.out.println(test22Output);
                    return;
                }
            }
        }

        test = false;
        System.out.println(year + " Day " + day + " Part 2: " + part2(makeScanner(main)));
    }

    public record Point2D(long x, long y) {
        public long distanceFrom(Point2D coordinate) {
            long a = (this.x() - coordinate.x()) * (this.x() - coordinate.x());
            long b = (this.y() - coordinate.y()) * (this.y() - coordinate.y());

            return a + b;
        }

        public long areaBetween(Point2D coordinate) {
            long x = Math.abs(this.x() - coordinate.x()) + 1;
            long y = Math.abs(this.y() - coordinate.y()) + 1;
            return x * y;
        }

        public static Point2D createCoordinate2d(String string) {
            String[] point = string.split(",");
            return new Point2D(Long.parseLong(point[0]), Long.parseLong(point[1]));
        }
    }

    public record Point3D(long x, long y, long z) {

        public long distanceFrom(Point3D coordinate) {
            long a = (this.x() - coordinate.x()) * (this.x() - coordinate.x());
            long b = (this.y() - coordinate.y()) * (this.y() - coordinate.y());
            long c = (this.z() - coordinate.z()) * (this.z() - coordinate.z());

            return a + b + c;
        }

        public static Point3D createCoordinate3d(String string) {
            String[] point = string.split(",");
            return new Point3D(Long.parseLong(point[0]), Long.parseLong(point[1]), Long.parseLong(point[2]));
        }
    }

    public final Scanner makeScanner(File file) {
        try {
            return new Scanner(file);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public final ArrayList<String[]> loadFile(Scanner scanner) {
        ArrayList<String[]> lines = new ArrayList<>();
        while (scanner.hasNextLine()) lines.add(scanner.nextLine().split(""));
        return lines;
    }

    public final <T> T getElement(ArrayList<T> array, int index) {
        if (index >= array.size()) return array.get(index - array.size());
        else if (index < 0) return array.get(array.size() + index);
        else return array.get(index);
    }

    public final boolean contains(ArrayList<int[]> values, int y, int x) {
        for (int[] value : values) if (value[0] == y && value[1] == x) return true;
        return false;
    }

    public abstract String part1(Scanner scanner);
    public abstract String part2(Scanner scanner);
}
