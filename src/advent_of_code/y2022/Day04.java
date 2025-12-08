package advent_of_code.y2022;

import advent_of_code.Day;

import java.util.*;

public class Day04 extends Day {
    public Day04(int year, int day, boolean runTests) {
        super(year, day, runTests, 2, 4);
    }

    @Override
    public String part1(Scanner scanner) {
        int count = 0;
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            String[][] ranges = {line[0].split("-"), line[1].split("-")};
            if (Integer.parseInt(ranges[0][0]) <= Integer.parseInt(ranges[1][0]) && Integer.parseInt(ranges[1][1]) <= Integer.parseInt(ranges[0][1])) {
                count++;
                continue;
            }
            if (Integer.parseInt(ranges[1][0]) <= Integer.parseInt(ranges[0][0]) && Integer.parseInt(ranges[0][1]) <= Integer.parseInt(ranges[1][1])) count++;
        }
        return String.valueOf(count);
    }

    @Override
    public String part2(Scanner scanner) {
        int count = 0;
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            String[][] ranges = {line[0].split("-"), line[1].split("-")};
            if ((Integer.parseInt(ranges[1][0]) <= Integer.parseInt(ranges[0][1])) && ((Integer.parseInt(ranges[0][1]) <= Integer.parseInt(ranges[1][1])))
                    || (Integer.parseInt(ranges[0][0]) <= Integer.parseInt(ranges[1][1])) && (Integer.parseInt(ranges[1][1]) <= Integer.parseInt(ranges[0][1]))) count++;

        }
        return String.valueOf(count);
    }
}