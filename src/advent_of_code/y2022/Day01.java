package advent_of_code.y2022;

import advent_of_code.Day;

import java.util.*;

public class Day01 extends Day {
    public Day01(int year, int day, boolean runTests) {
        super(year, day, runTests, 24000, 45000);
    }

    public int problem(Scanner scanner, int part) {
        int max = 0;
        int second = 0;
        int third = 0;
        int current = 0;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                if (current > max) {
                    third = second;
                    second = max;
                    max = current;
                } else if (current > second) {
                    third = second;
                    second = current;
                } else if (current > third) {
                    third = current;
                }
                current = 0;
            } else current += Integer.parseInt(line);
        }
        if (part == 1) return max;
        else if (part == 2) return max + second + third;
        else return -1;
    }

    @Override
    public String part1(Scanner scanner) {
        return String.valueOf(problem(scanner, 1));
    }

    @Override
    public String part2(Scanner scanner) {
        return String.valueOf(problem(scanner, 2));
    }
}
