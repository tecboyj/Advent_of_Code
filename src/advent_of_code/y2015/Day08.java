package advent_of_code.y2015;

import advent_of_code.Day;

import java.util.*;

public class Day08 extends Day {
    public Day08(int year, int day, boolean runTests) {
        super(year, day, runTests, 12, 19);
    }

    @Override
    public String part1(Scanner scanner) {
        int count = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            count += 2;

            String[] arr = line.split("\\\\\\\\");

            count += arr.length - 1;

            StringBuilder builder = new StringBuilder();
            for (String s : arr) builder.append(s);
            line = builder.toString();

            count += (line.split("\\\\x").length - 1) * 3;
            count += line.split("\\\\\"").length - 1;
        }

        return String.valueOf(count);
    }

    @Override
    public String part2(Scanner scanner) {
        int count = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = "a" + line.substring(1, line.length() - 1) + "a";
            count += 4;

            count += line.split("\\\\").length - 1;
            count += line.split("\"").length - 1;
        }

        return String.valueOf(count);
    }
}