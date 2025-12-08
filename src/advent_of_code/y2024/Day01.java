package advent_of_code.y2024;

import advent_of_code.Day;

import java.util.*;

public class Day01 extends Day {
    public Day01(int year, int day, boolean runTests) {
        super(year, day, runTests, 11, 31);
    }

    @Override
    public String part1(Scanner scanner) {
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("   ");
            left.add(Integer.parseInt(line[0]));
            right.add(Integer.parseInt(line[1]));
        }
        left.sort(Integer::compareTo);
        right.sort(Integer::compareTo);


        int sum = 0;
        for (int i = 0; i < left.size(); i++) {
            if (left.get(i) > right.get(i)) sum += left.get(i) - right.get(i);
            else sum += right.get(i) - left.get(i);
        }

        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("   ");
            left.add(Integer.parseInt(line[0]));
            right.add(Integer.parseInt(line[1]));
        }
        left.sort(Integer::compareTo);
        right.sort(Integer::compareTo);

        ArrayList<Integer> completed = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < left.size(); i++) {
            int target = left.get(i);
            if (completed.contains(target)) continue;
            completed.add(target);
            sum += Collections.frequency(left, target) * Collections.frequency(right, target) * target;
        }

        return String.valueOf(sum);
    }
}
