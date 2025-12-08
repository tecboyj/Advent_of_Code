package advent_of_code.y2024;

import advent_of_code.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 extends Day {
    private ArrayList<int[]> trailHeads;

    public Day10(int year, int day, boolean runTests) {
        super(year, day, runTests, 36, 81);
    }

    public int solve(ArrayList<int[]> lines, int y, int x, int previous, boolean useContains) {
        if (previous == 9 && (!contains(trailHeads, y, x) || !useContains)) {
            trailHeads.add(new int[]{y, x});
            return 1;
        }

        int sum = 0;

        if (y > 0) {
            int next = lines.get(y - 1)[x];
            if (next - previous == 1) sum += solve(lines, y - 1, x, next, useContains);
        }
        if (y < lines.size() - 1) {
            int next = lines.get(y + 1)[x];
            if (next - previous == 1) sum += solve(lines, y + 1, x, next, useContains);
        }
        if (x > 0) {
            int next = lines.get(y)[x - 1];
            if (next - previous == 1) sum += solve(lines, y, x - 1, next, useContains);
        }
        if (x < lines.get(y).length - 1) {
            int next = lines.get(y)[x + 1];
            if (next - previous == 1) sum += solve(lines, y, x + 1, next, useContains);
        }

        return sum;
    }



    @Override
    public String part1(Scanner scanner) {
        ArrayList<int[]> lines = new ArrayList<>();
        ArrayList<int[]> startPoints = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher matcher = Pattern.compile("(0)").matcher(line);
            while (matcher.find()) startPoints.add(new int[]{lines.size(), matcher.start()});
            lines.add(Arrays.stream(line.split("")).mapToInt(Integer::parseInt).toArray());
        }

        int sum = 0;

        for (int[] startPoint : startPoints) {
            trailHeads = new ArrayList<>();
            sum += solve(lines, startPoint[0], startPoint[1], 0, true);
        }

        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        ArrayList<int[]> lines = new ArrayList<>();
        ArrayList<int[]> startPoints = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher matcher = Pattern.compile("(0)").matcher(line);
            while (matcher.find()) startPoints.add(new int[]{lines.size(), matcher.start()});
            lines.add(Arrays.stream(line.split("")).mapToInt(Integer::parseInt).toArray());
        }

        int sum = 0;

        for (int[] startPoint : startPoints) {
            trailHeads = new ArrayList<>();
            sum += solve(lines, startPoint[0], startPoint[1], 0, false);
        }

        return String.valueOf(sum);
    }
}