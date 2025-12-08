package advent_of_code.y2023;

import advent_of_code.Day;

import java.util.*;

public class Day09 extends Day {
    public Day09(int year, int day, boolean runTests) {
        super(year, day, runTests, 114, 2);
    }

    public int[] difference(int[] line) {
        int[] diff = new int[line.length - 1];
        for (int i = 0; i < line.length - 1; i++) diff[i] = line[i + 1] - line[i];
        return diff;
    }
    public boolean isZero(int[] line) {
        for (int i : line) if (i != 0) return true;
        return false;
    }

    @Override
    public String part1(Scanner scanner) {
        int sum = 0;
        while (scanner.hasNextLine()) {
            ArrayList<int[]> line = new ArrayList<>();
            line.add(Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray());
            while (isZero(line.get(line.size() - 1))) line.add(difference(line.get(line.size() - 1)));
            int add = 0;
            for (int i = line.size() - 2; i >= 0; i--) {
                int last = line.get(i).length - 1;
                add += line.get(i)[last];
            }
            sum += add;
        }
        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        int sum = 0;
        while (scanner.hasNextLine()) {
            ArrayList<int[]> line = new ArrayList<>();
            line.add(Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray());
            while (isZero(line.get(line.size() - 1))) line.add(difference(line.get(line.size() - 1)));
            int add = 0;
            for (int i = line.size() - 2; i >= 0; i--) add = line.get(i)[0] - add;
            sum += add;
        }
        return String.valueOf(sum);
    }
}