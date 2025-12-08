package advent_of_code.y2023;

import advent_of_code.Day;

import java.util.*;

public class Day05 extends Day {
    public Day05(int year, int day, boolean runTests) {
        super(year, day, runTests, 35, 46);
    }

    @Override
    public String part1(Scanner scanner) {
        String[] seeds = scanner.nextLine().split(": ")[1].split(" ");
        String[] maps = new String[7];
        scanner.nextLine();
        scanner.nextLine();
        for (int i = 0; i < 7; i++) {
            maps[i] = scanner.nextLine();
            String nextLine = scanner.nextLine();
            while (!nextLine.isEmpty()) {
                maps[i] += "\n" + nextLine;
                if (scanner.hasNextLine()) nextLine = scanner.nextLine();
                else nextLine = "";
            }
            if (scanner.hasNextLine()) scanner.nextLine();
        }
        long least = 0;
        for (String seed : seeds) {
            long key = Long.parseLong(seed);
            for (int i = 0; i < 7; i++) {
                String[] indexes = maps[i].split("\n");
                for (String index : indexes) {
                    long[] indexMap = Arrays.stream(index.split(" ")).mapToLong(Long::parseLong).toArray();
                    if (key >= indexMap[1] && key < indexMap[1] + indexMap[2]) {
                        key = indexMap[0] + (key - indexMap[1]);
                        break;
                    }
                }
            }
            //System.out.println(key);
            if (least == 0) least = key;
            else if (key < least) least = key;
        }

        return String.valueOf(least);
    }
    //TODO 2023 Day 5 Part 2
    @Override
    public String part2(Scanner scanner) {
        return null;
    }
}