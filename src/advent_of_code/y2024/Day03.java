package advent_of_code.y2024;

import advent_of_code.Day;

import java.util.*;

public class Day03 extends Day {
    public Day03(int year, int day, boolean runTests) {
        super(year, day, runTests, 161, 48);
    }

    @Override
    public String part1(Scanner scanner) {
        int sum = 0;
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split("mul\\(");
            for (String s : input) {
                String[] split = s.split("\\)");
                String[] split2 = split[0].split(",");
                try {
                    int x = Integer.parseInt(split2[0]);
                    int y = Integer.parseInt(split2[1]);
                    sum += x * y;
                } catch (NumberFormatException e) {}
            }
        }
        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        int sum = 0;
        StringBuilder lines = new StringBuilder();
        while (scanner.hasNextLine()) {
            lines.append(scanner.nextLine());
        }
        String[] input = lines.toString().split("do\\(\\)");

        for (String s : input) {
            String[] input2 = s.split("don't\\(\\)")[0].split("mul\\(");
            for (String s2 : input2) {
                String[] split = s2.split("\\)");
                String[] split2 = split[0].split(",");

                try {
                    int x = Integer.parseInt(split2[0]);
                    int y = Integer.parseInt(split2[1]);
                    sum += x * y;
                } catch (NumberFormatException e) {}
            }
        }
        return String.valueOf(sum);
    }
}