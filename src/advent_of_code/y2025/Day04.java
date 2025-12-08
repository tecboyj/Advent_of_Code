package advent_of_code.y2025;

import advent_of_code.Day;

import java.util.ArrayList;
import java.util.Scanner;

public class Day04 extends Day {
    public Day04(int year, int day, boolean runTests) {
        super(year, day, runTests, 13, 43);
    }

    @Override
    public String part1(Scanner scanner) {
        ArrayList<String[]> input = this.loadFile(scanner);

        int count = 0;

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length; j++) {
                if (!input.get(i)[j].equals("@")) continue;

                int adjacentRolls = 0;

                ArrayList<String[]> lines = new ArrayList<>();

                if (i > 0) lines.add(input.get(i - 1));
                lines.add(input.get(i).clone());
                lines.getLast()[j] = "x";
                if (i < input.get(i).length - 1) lines.add(input.get(i + 1));

                for (String[] line : lines) {
                    if (j > 0 && line[j - 1].equals("@")) adjacentRolls++;
                    if (line[j].equals("@")) adjacentRolls++;
                    if (j < input.get(i).length - 1 && line[j + 1].equals("@")) adjacentRolls++;
                }

                if (adjacentRolls < 4) count++;
            }
        }

        return String.valueOf(count);
    }

    @Override
    public String part2(Scanner scanner) {
        ArrayList<String[]> input = this.loadFile(scanner);

        int count = 0;

        while(true) {
            ArrayList<int[]> removed = new ArrayList<>();
            for (int i = 0; i < input.size(); i++) {
                for (int j = 0; j < input.get(i).length; j++) {
                    if (!input.get(i)[j].equals("@")) continue;

                    int adjacentRolls = 0;

                    ArrayList<String[]> lines = new ArrayList<>();

                    if (i > 0) lines.add(input.get(i - 1));
                    lines.add(input.get(i).clone());
                    lines.getLast()[j] = "x";
                    if (i < input.get(i).length - 1) lines.add(input.get(i + 1));

                    for (String[] line : lines) {
                        if (j > 0 && line[j - 1].equals("@")) adjacentRolls++;
                        if (line[j].equals("@")) adjacentRolls++;
                        if (j < input.get(i).length - 1 && line[j + 1].equals("@")) adjacentRolls++;
                    }

                    if (adjacentRolls < 4) {
                        count++;
                        removed.add(new int[]{i, j});
                    }
                }
            }

            for (int[] remove: removed) input.get(remove[0])[remove[1]] = "x";

            if (removed.isEmpty()) break;
        }

        return String.valueOf(count);
    }
}