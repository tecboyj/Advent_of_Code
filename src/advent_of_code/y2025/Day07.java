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
        lines.add(scanner.nextLine().split(""));

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("");
            String[] previous = lines.getLast();

            for (int i = 0; i < line.length; i++) {
                if (previous[i].equals("|") || previous[i].equals("S")) {
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

    public int getCount(List<ArrayList<String>> lines) {
        for (int j = 1; j < lines.size(); j++) {
            ArrayList<String> line = lines.get(j);
            ArrayList<String> previous = lines.get(j - 1);
            for (int i = 0; i < line.size(); i++) {
                if (previous.get(i).equals("|") || previous.get(i).equals("S")) {
                    if (line.get(i).equals("^")){
                        List<ArrayList<String>> left = new ArrayList<>();
                        for (ArrayList<String> newLine : lines.subList(j, lines.size())) {
                            left.add((ArrayList<String>) newLine.clone());
                        }
                        left.getFirst().set(i - 1, "|");

                        List<ArrayList<String>> right = new ArrayList<>();
                        for (ArrayList<String> newLine : lines.subList(j, lines.size())) {
                            right.add((ArrayList<String>) newLine.clone());
                        }
                        right.getFirst().set(i + 1, "|");

                        System.out.println(line);

                        int leftCount = getCount(left);
                        int rightCount = getCount(right);

                        return leftCount + rightCount;
                    } else line.set(i, "|");
                }
            }
        }

        return 1;
    }

    @Override
    public String part2(Scanner scanner) {
        ArrayList<ArrayList<String>> lines = new ArrayList<>();

        while (scanner.hasNextLine()) lines.add(new ArrayList<>(Arrays.asList(scanner.nextLine().split(""))));

        return String.valueOf(getCount(lines));
    }
}