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

    public record Coordinate(int x, int y) {}

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

    public final boolean contains(ArrayList<int[]> values, int y, int x) {
        for (int[] value : values) if (value[0] == y && value[1] == x) return true;
        return false;
    }

    public abstract String part1(Scanner scanner);
    public abstract String part2(Scanner scanner);
}
