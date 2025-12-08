package advent_of_code.y2025;

import advent_of_code.Day;

import java.util.*;

//3122129068900
//3121910778619

public class Day03 extends Day {
    public Day03(int year, int day, boolean runTests) {
        super(year, day, runTests, 357, "3121910778619");
    }

    @Override
    public String part1(Scanner scanner) {
        int sum = 0;

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            int first = Arrays.stream(input.substring(0, input.length() - 1).split(""))
                    .mapToInt(Integer::parseInt).max().orElse(0);
            int second = Arrays.stream(input.substring(input.indexOf(String.valueOf(first)) + 1).split(""))
                    .mapToInt(Integer::parseInt).max().orElse(0);

            int value = first * 10 + second;
            sum += value;
        }

        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        long sum = 0;

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String substring = input;
            String[] chars = new String[12];
            chars[0] = String.valueOf(Arrays.stream(input.substring(0, input.length() - 11).split(""))
                    .mapToInt(Integer::parseInt).max().orElse(0));
            for (int i = 1; i < chars.length; i++) {
                substring = substring.substring(substring.indexOf(String.valueOf(chars[i - 1])) + 1);
                chars[i] = String.valueOf(Arrays.stream(substring.substring(0, substring.length() - (11 - i)).split(""))
                        .mapToInt(Integer::parseInt).max().orElse(0));
            }

            StringBuilder builder = new StringBuilder();
            for (String s : chars) builder.append(s);

            sum += Long.parseLong(builder.toString());
        }

        return String.valueOf(sum);
    }
}