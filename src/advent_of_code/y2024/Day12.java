package advent_of_code.y2024;

import advent_of_code.Day;

import java.util.*;

public class Day12 extends Day {
    public Day12(int year, int day, boolean runTests) {
        super(year, day, runTests, 1930, 0);
    }

    @Override
    public String part1(Scanner scanner) {
        ArrayList<String[]> input = new ArrayList<>();
        while (scanner.hasNextLine()) input.add(scanner.nextLine().split(""));

        int length = input.getFirst().length;

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < length; j++) {}
        }

        return null;
    }

    @Override
    public String part2(Scanner scanner) {
        return null;
    }
}