package advent_of_code.y2023;

import advent_of_code.Day;

import java.util.*;

public class Day01 extends Day {
    public Day01(int year, int day, boolean runTests) {
        super(year, day, runTests, 142, 281);
    }

    public int problem(String[] string) {
        String first = null;
        String last = null;
        for (String s : string) {
            try {
                int i = Integer.parseInt(s);
                if (first == null) first = String.valueOf(i);
                last = String.valueOf(i);
            } catch (Exception ignored) {
            }
        }
        return Integer.parseInt(first + last);
    }

    @Override
    public String part1(Scanner scanner) {
        int sum = 0;
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("");
            sum += problem(line);
        }
        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        int sum = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            StringBuilder string = new StringBuilder();
            for (int i = 0; i < line.length(); i++) {
                if (line.startsWith("one", i)) {
                    string.append("1");
                    continue;
                }
                if (line.startsWith("two", i)) {
                    string.append("2");
                    continue;
                }
                if (line.startsWith("three", i)) {
                    string.append("3");
                    continue;
                }
                if (line.startsWith("four", i)) {
                    string.append("4");
                    continue;
                }
                if (line.startsWith("five", i)) {
                    string.append("5");
                    continue;
                }
                if (line.startsWith("six", i)) {
                    string.append("6");
                    continue;
                }
                if (line.startsWith("seven", i)) {
                    string.append("7");
                    continue;
                }
                if (line.startsWith("eight", i)) {
                    string.append("8");
                    continue;
                }
                if (line.startsWith("nine", i)) {
                    string.append("9");
                    continue;
                }
                string.append(line.charAt(i));
            }
            sum += problem(string.toString().split(""));
        }
        return String.valueOf(sum);
    }

    public String intToString(int x) {
        return switch (x) {
            case 1 -> "one";
            case 2 -> "two";
            case 3 -> "three";
            case 4 -> "four";
            case 5 -> "five";
            case 6 -> "six";
            case 7 -> "seven";
            case 8 -> "eight";
            case 9 -> "nine";
            default -> null;
        };
    }
}
