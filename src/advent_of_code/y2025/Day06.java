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
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<ArrayList<String>> problems = new ArrayList<>();

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
            ArrayList<String> problem = new ArrayList<>(Arrays.asList(lines.getLast().split(" ")));
            problem.removeAll(Collections.singleton(""));

            if (problems.isEmpty()) for (String _ : problem) problems.add(new ArrayList<>());

            for (int i = 0; i < problem.size(); i++) problems.get(i).add(problem.get(i));
        }

        long total = 0;
        int i = 0;

        for (ArrayList<String> problem : problems) {
            int maxLength = 0;
            for (String num : problem) if (num.length() > maxLength) maxLength = num.length();

            ArrayList<String> newProblem = new ArrayList<>();
            for (int j = 0; j < lines.size() - 1; j++) newProblem.add(lines.get(j).substring(i, i + maxLength));

            ArrayList<Long> nums = new ArrayList<>();
            for (int j = 0; j < maxLength; j++) {
                StringBuilder numBuilder = new StringBuilder();
                for (String s : newProblem) numBuilder.append(s.charAt(j));

                String num = numBuilder.toString();
                nums.add(Long.parseLong(num.strip()));
            }

            long count = nums.getFirst();

            for (int j = 1; j < nums.size(); j++) {
                if (problem.getLast().equals("*")) count *= nums.get(j);
                else count += nums.get(j);
            }

            total += count;

            i += maxLength + 1;
        }


        return String.valueOf(total);
    }
}