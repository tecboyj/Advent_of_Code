package advent_of_code.y2023;

import advent_of_code.Day;

import java.util.*;

public class Day02 extends Day {
    public Day02(int year, int day, boolean runTests) {
        super(year, day, runTests, 8, 2286);
    }

    public int[] problem(String string) {
        int[] colors = {0, 0, 0};
        for (String round : string.split("; ")) {
            for (String color : round.split(", ")) {
                int x = Integer.parseInt(color.substring(0, 2).replace(" ", ""));
                if (color.contains("red") && x > colors[0]) colors[0] = x;
                if (color.contains("green") && x > colors[1]) colors[1] = x;
                if (color.contains("blue") && x > colors[2]) colors[2] = x;
            }
        }
        return colors;
    }

    @Override
    public String part1(Scanner scanner) {
        int sum = 0;
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(": ");
            int gameNum = Integer.parseInt(line[0].replace("Game ", ""));
            int[] colors = problem(line[1]);
            if (colors[0] < 13 && colors[1] < 14 && colors[2] < 15) sum += gameNum;
        }
        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        int sum = 0;
        while (scanner.hasNextLine()) {
            sum += multiply(problem(scanner.nextLine().split(": ")[1]));
        }
        return String.valueOf(sum);
    }

    public int multiply(int[] colors) {
        return colors[0] * colors[1] * colors[2];
    }
}
