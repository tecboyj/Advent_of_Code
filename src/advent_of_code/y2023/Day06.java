package advent_of_code.y2023;

import advent_of_code.Day;

import java.util.*;

public class Day06 extends Day {
    public Day06(int year, int day, boolean runTests) {
        super(year, day, runTests, 288, 71503);
    }

    @Override
    public String part1(Scanner scanner) {
        String[] times = scanner.nextLine().split(": ")[1].split(" ");
        String[] distances = scanner.nextLine().split(": ")[1].split(" ");

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < times.length; i++) {
            Stack<Integer> temp = new Stack<>();
            int x = Integer.parseInt(times[i]);
            for (int j = 1; j < x; j++) {
                int distance = 0;
                for (int k = j; k < x; k++) distance += j;
                if (distance > Integer.parseInt(distances[i])) temp.push(j);
            }
            stack.push(temp.size());
        }
        int product = 1;
        while (!stack.isEmpty()) product *= stack.pop();

        return String.valueOf(product);
    }

    @Override
    public String part2(Scanner scanner) {
        long time = Long.parseLong(scanner.nextLine().split(": ")[1].replace(" ", ""));
        long distance = Long.parseLong(scanner.nextLine().split(": ")[1].replace(" ", ""));

        long ways = 0;
        for (long i = 1; i < time; i++) if (i * (time - i) > distance) ways++;

        return String.valueOf(ways);
    }
}