package advent_of_code.y2024;

import advent_of_code.Day;

import java.util.*;

public class Day05 extends Day {
    public Day05(int year, int day, boolean runTests) {
        super(year, day, runTests, 143, 123);
    }

    @Override
    public String part1(Scanner scanner) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("\\|");
            if (line.length == 1) break;

            if (!map.containsKey(line[0])) map.put(line[0], new ArrayList<>());
            map.get(line[0]).add(line[1]);
        }

        int sum = 0;

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            boolean valid = true;
            for (int i = 1; i < line.length; i++) {
                if (!map.containsKey(line[i])) continue;

                ArrayList<String> list = map.get(line[i]);
                for (int j = i - 1; j > -1; j--) {
                    if (list.contains(line[j])) {
                        valid = false;
                        break;
                    }
                }
                if (!valid) break;
            }
            if (valid) sum += Integer.parseInt(line[line.length / 2]);
        }

        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("\\|");
            if (line.length == 1) break;

            if (!map.containsKey(line[0])) map.put(line[0], new ArrayList<>());
            map.get(line[0]).add(line[1]);
        }

        int sum = 0;

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            boolean valid = true;
            for (int i = 1; i < line.length; i++) {
                if (!map.containsKey(line[i])) continue;

                ArrayList<String> list = map.get(line[i]);
                for (int j = i - 1; j > -1; j--) {
                    if (list.contains(line[j])) {
                        valid = false;
                        String temp = line[j];
                        line[j] = line[i];
                        line[i] = temp;
                        i = 1;
                    }
                }
            }
            if (!valid) sum += Integer.parseInt(line[line.length / 2]);
        }


        return String.valueOf(sum);
    }
}