package advent_of_code.y2015;

import advent_of_code.Day;

import java.util.*;

public class Day02 extends Day {
    public Day02(int year, int day, boolean runTests) {
        super(year, day, runTests, 101, 48);
    }

    @Override
    public String part1(Scanner scanner) {
        int total = 0;

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("x");
            int l = Integer.parseInt(line[0]);
            int w = Integer.parseInt(line[1]);
            int h = Integer.parseInt(line[2]);

            int[] surfaces = { l*w, w*h, h*l };
            total += Arrays.stream(surfaces).min().getAsInt();
            total += Arrays.stream(surfaces).sum() * 2;
        }

        return String.valueOf(total);
    }

    @Override
    public String part2(Scanner scanner) {
        int total = 0;

        while (scanner.hasNextLine()) {
            int[] line = Arrays.stream(scanner.nextLine().split("x")).mapToInt(Integer::parseInt).toArray();

            total += (Arrays.stream(line).sum() - Arrays.stream(line).max().getAsInt()) * 2;
            total += line[0] * line[1] * line[2];
        }

        return String.valueOf(total);
    }
}
