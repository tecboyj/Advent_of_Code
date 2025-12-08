package advent_of_code.y2024;

import advent_of_code.Day;

import java.util.*;

public class Day09 extends Day {
    public Day09(int year, int day, boolean runTests) {
        super(year, day, runTests, 1928, 2858);
    }

    @Override
    public String part1(Scanner scanner) {
        String[] input = scanner.nextLine().split("");
        ArrayList<String> result = new ArrayList<>();
        int idNum = 0;
        for (int i = 0; i < input.length; i++) {
            int x = Integer.parseInt(input[i]);
            if (i % 2 == 0) {
                for (int j = 0; j < x; j++) result.add(String.valueOf(idNum));
                idNum++;
            } else for (int j = 0; j < x; j++) result.add(".");
        }

        for (int i = result.size() - 1; i >= 0; i--) {
            if (result.get(i).equals(".")) continue;
            for (int j = 0; j < i; j++) {
                if (result.get(j).equals(".")) {
                    result.set(j, result.get(i));
                    result.set(i, ".");
                    break;
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).equals(".")) break;
            sum += (long) Integer.parseInt(result.get(i)) * i;
        }

        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        String[] input = scanner.nextLine().split("");
        ArrayList<String> files = new ArrayList<>();
        int idNum = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals("0")) continue;
            if (i % 2 == 0) {
                files.add(idNum + "," + input[i]);
                idNum++;
            } else files.add(".," + input[i]);
        }

        for (int i = files.size() - 1; i >= 0; i--) {
            String[] file = files.get(i).split(",");
            if (file[0].equals(".")) continue;

            for (int j = 0; j < i; j++) {
                String[] space = files.get(j).split(",");
                if (!space[0].equals(".")) continue;

                if (Integer.parseInt(space[1]) >= Integer.parseInt(file[1])) {
                    files.set(j, files.get(i));
                    files.set(i, ".," + file[1]);

                    int difference = Integer.parseInt(space[1]) - Integer.parseInt(file[1]);
                    if (difference > 0) files.add(j + 1, ".," + difference);

                    i = files.size() - 1;
                    break;
                }
            }
        }

        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            String[] file = files.get(i).split(",");
            for (int j = 0; j < Integer.parseInt(file[1]); j++) result.add(file[0]);
        }

        long sum = 0;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).equals(".")) continue;
            sum += (long) Integer.parseInt(result.get(i)) * i;
        }

        return String.valueOf(sum);
    }
}