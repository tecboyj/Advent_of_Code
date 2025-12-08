package advent_of_code.y2015;

import advent_of_code.Day;
import java.util.*;

public class Day03 extends Day {
    public Day03(int year, int day, boolean runTests) {
        super(year, day, runTests, 1, 1);
    }

    @Override
    public String part1(Scanner scanner) {
        String[] input = scanner.nextLine().split("");
        if (input[0].equals("x")) return "1";

        int x = 0;
        int y = 0;
        Map<Coordinate, Integer> map = new HashMap<>();
        map.merge(new Coordinate(x, y), 1, Integer::sum);

        for (String s : input) {
            switch (s) {
                case "^" -> y++;
                case ">" -> x++;
                case "v" -> y--;
                case "<" -> x--;
            }

            map.merge(new Coordinate(x, y), 1, Integer::sum);
        }

        return String.valueOf(map.size());
    }

    @Override
    public String part2(Scanner scanner) {
        String[] input = scanner.nextLine().split("");
        if (input[0].equals("x")) return "1";

        int[] santa = { 0, 0 };
        int[] roboSanta = { 0, 0 };
        Map<Coordinate, Integer> map = new HashMap<>();
        map.merge(new Coordinate(0, 0), 1, Integer::sum);

        for (int i = 0; i < input.length; i += 2) {
            switch (input[i]) {
                case "^" -> santa[1]++;
                case ">" -> santa[0]++;
                case "v" -> santa[1]--;
                case "<" -> santa[0]--;
            }
            switch (input[i + 1]) {
                case "^" -> roboSanta[1]++;
                case ">" -> roboSanta[0]++;
                case "v" -> roboSanta[1]--;
                case "<" -> roboSanta[0]--;
            }

            map.merge(new Coordinate(santa[0], santa[1]), 1, Integer::sum);
            map.merge(new Coordinate(roboSanta[0], roboSanta[1]), 1, Integer::sum);
        }

        return String.valueOf(map.size());
    }
}