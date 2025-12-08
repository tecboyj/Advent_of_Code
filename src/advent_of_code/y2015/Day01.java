package advent_of_code.y2015;

import advent_of_code.Day;

import java.util.Scanner;

public class Day01 extends Day {
    public Day01(int year, int day, boolean runTests) {
        super(year, day, runTests, -1, 2);
    }

    @Override
    public String part1(Scanner scanner) {
        String[] input = scanner.nextLine().split("");

        int floor = 0;

        for (String s : input) {
            if (s.equals("(")) floor++;
            else floor--;
        }

        return String.valueOf(floor);
    }

    @Override
    public String part2(Scanner scanner) {
        String[] input = scanner.nextLine().split("");

        int floor = 0;

        for (int i = 0; i < input.length; i++) {
            if (input[i].equals("(")) floor++;
            else floor--;
            if (floor == -1) return String.valueOf(i + 1);
        }

        return String.valueOf(0);
    }
}
