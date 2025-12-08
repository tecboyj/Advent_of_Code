package advent_of_code.y2025;

import advent_of_code.Day;

import java.util.*;

public class Day06 extends Day {
    public Day06(int year, int day, boolean runTests) {
        super(year, day, runTests, 4277556, 3263827);
    }

    @Override
    public String part1(Scanner scanner) {
        ArrayList<ArrayList<String>> problems = new ArrayList<>();

        while (scanner.hasNextLine()) {
            ArrayList<String> line = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));
            line.removeAll(Collections.singleton(""));

            if (problems.isEmpty()) for (int i = 0; i < line.size(); i++) problems.add(new ArrayList<>());

            for (int i = 0; i < line.size(); i++) problems.get(i).add(line.get(i));
        }

        long sum = 0;

        for (ArrayList<String> problem : problems) {
            String operation = problem.getLast();
            long solution = Long.parseLong(problem.getFirst());

            for (int i = 1; i < problem.size() - 1; i++) {
                if (operation.equals("*")) solution *= Long.parseLong(problem.get(i));
                else solution += Long.parseLong(problem.get(i));
            }

            sum += solution;
        }

        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        ArrayList<ArrayList<String>> problems = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (problems.isEmpty()) {
                ArrayList<String> problem = new ArrayList<>(Arrays.asList(line.split(" ")));
                problem.removeAll(Collections.singleton(""));
                for (int i = 0; i < problem.size(); i++) problems.add(new ArrayList<>());
            }

            int j = 0;
            for (int i = 0; i < line.length() - 4; i += 4) {
                problems.get(i / 4).add(line.substring(i, i + 3));
                j = i;
            }

            problems.getLast().add(line.substring(j + 4));
        }

        long sum = 0;

        for (ArrayList<String> problem : problems) {
            String operation = problem.getLast().strip();
            StringBuilder[] values = new StringBuilder[3];
            values[0] = new StringBuilder();
            values[1] = new StringBuilder();
            values[2] = new StringBuilder();

            for (int i = 0; i < problem.size() - 1; i++) {
                String[] value = problem.get(i).split("");
                if (!value[0].equals(" ")) values[0].append(value[0]);
                if (!value[1].equals(" ")) values[1].append(value[1]);
                if (!value[2].equals(" ")) values[2].append(value[2]);
            }

            System.out.println(Arrays.toString(values));

            long solution = Long.parseLong(String.valueOf(values[0]));

            if (operation.equals("*")) {
                solution *= Long.parseLong(String.valueOf(values[1]));
                solution *= Long.parseLong(String.valueOf(values[2]));
            } else {
                solution += Long.parseLong(String.valueOf(values[1]));
                solution += Long.parseLong(String.valueOf(values[2]));
            }

            sum += solution;
        }

        return String.valueOf(sum);
    }
}