package advent_of_code.y2015;

import advent_of_code.Day;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day05 extends Day {
    public Day05(int year, int day, boolean runTests) {
        super(year, day, runTests, 1, 2);
    }

    @Override
    public String part1(Scanner scanner) {
        int count = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.contains("ab") || line.contains("cd") || line.contains("pq") || line.contains("xy")) continue;

            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < line.length(); i++) map.merge(line.charAt(i), 1, Integer::sum);
            int vowelCount = map.getOrDefault('a', 0) + map.getOrDefault('e', 0) + map.getOrDefault('i', 0) + map.getOrDefault('o', 0) + map.getOrDefault('u', 0);
            if (vowelCount < 3) continue;

            boolean doubleLetter = false;
            for (int i = 0; i < line.length() - 1; i++) {
                if (line.charAt(i) == line.charAt(i + 1)) {
                    doubleLetter = true;
                    break;
                }
            }

            if (doubleLetter) count++;
        }

        return String.valueOf(count);
    }

    @Override
    public String part2(Scanner scanner) {
        int count = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            boolean hasPair = false;
            boolean repeatedLetter = false;

            for (int i = 0; i < line.length() - 2; i++) {
                String pair = line.substring(i, i + 2);
                if (line.substring(i + 2).contains(pair)) hasPair = true;

                if (line.charAt(i) == line.charAt(i + 2)) repeatedLetter = true;
            }

            if (hasPair && repeatedLetter) count++;
        }

        return String.valueOf(count);
    }
}