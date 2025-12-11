package advent_of_code.y2025;

import advent_of_code.Day;

import java.util.*;

public class Day07 extends Day {
    public Day07(int year, int day, boolean runTests) {
        super(year, day, runTests, 21, 40);
    }

    @Override
    public String part1(Scanner scanner) {
        int count = 0;

        ArrayList<String[]> lines = new ArrayList<>();
        lines.add(scanner.nextLine().replace("S", "|").split(""));

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("");
            String[] previous = lines.getLast();

            for (int i = 0; i < line.length; i++) {
                if (previous[i].equals("|")) {
                    if (line[i].equals("^")){
                        line[i - 1] = "|";
                        line[i + 1] = "|";
                        count++;
                    } else line[i] = "|";
                }
            }

            lines.add(line);
        }

        return String.valueOf(count);
    }

    @Override
    public String part2(Scanner scanner) {
        ArrayList<String[]> lines = new ArrayList<>();
        lines.add(scanner.nextLine().replace("S", "1").replace(".", "0").split(""));

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().replace(".", "0").split("");
            String[] previous = lines.getLast();

            for (int i = 0; i < line.length; i++) {
                if (!previous[i].equals("0") && !previous[i].equals("^")) {
                    if (line[i].equals("^")){
                        line[i - 1] = String.valueOf(Long.parseLong(line[i - 1]) + Long.parseLong(previous[i]));
                        line[i + 1] = previous[i];
                    } else line[i] = String.valueOf(Long.parseLong(previous[i]) + Long.parseLong(line[i]));
                }
            }

            lines.add(line);
        }

        long count = 0;
        for (String s : lines.getLast()) count += Long.parseLong(s);

        return String.valueOf(count);
    }
}