package advent_of_code.y2024;

import advent_of_code.Day;

import java.util.*;

public class Day07 extends Day {
    public Day07(int year, int day, boolean runTests) {
        super(year, day, runTests, 3749, 11387);
    }

    @Override
    public String part1(Scanner scanner) {
        long sum = 0;

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(": ");
            long calibrationValue = Long.parseLong(line[0]);
            long[] nums = Arrays.stream(line[1].split(" ")).mapToLong(Long::parseLong).toArray();

            ArrayList<Long> results = new ArrayList<>();
            for (long num : nums) {
                if (results.isEmpty()) {
                    results.add(num);
                    continue;
                }

                ArrayList<Long> temp = new ArrayList<>();
                for (long result : results) {
                    temp.add(result + num);
                    temp.add(result * num);
                }
                results = temp;
            }
            if (results.contains(calibrationValue)) sum += calibrationValue;
        }

        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        long sum = 0;

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(": ");
            long calibrationValue = Long.parseLong(line[0]);
            long[] nums = Arrays.stream(line[1].split(" ")).mapToLong(Long::parseLong).toArray();

            ArrayList<Long> results = new ArrayList<>();
            for (long num : nums) {
                if (results.isEmpty()) {
                    results.add(num);
                    continue;
                }

                ArrayList<Long> temp = new ArrayList<>();
                for (long result : results) {
                    temp.add(result + num);
                    temp.add(result * num);
                    temp.add(Long.parseLong(String.valueOf(result) + num));
                }
                results = temp;
            }
            if (results.contains(calibrationValue)) sum += calibrationValue;
        }

        return String.valueOf(sum);
    }
}