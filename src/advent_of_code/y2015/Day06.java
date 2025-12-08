package advent_of_code.y2015;

import advent_of_code.Day;

import java.util.*;

public class Day06 extends Day {
    public Day06(int year, int day, boolean runTests) {
        super(year, day, runTests, 1, 1);
    }

    public Coordinate toCoordinate(String s) {
        String[] split = s.split(",");
        return new Coordinate(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    @Override
    public String part1(Scanner scanner) {
        if (!scanner.hasNextLine()) return "1";

        Map<Coordinate, Boolean> map = new HashMap<>();
        int count = 0;

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" through ");
            String[] command = line[0].split(" ");
            Coordinate start = toCoordinate(command[command.length - 1]);
            Coordinate end = toCoordinate(line[1]);

            for (int i = start.x(); i <= end.x(); i++) {
                for (int j = start.y(); j <= end.y(); j++) {
                    Coordinate coordinate = new Coordinate(i, j);
                    boolean state = map.getOrDefault(coordinate, false);

                    if (command[0].equals("toggle")) {
                        if (state) count--;
                        else count++;
                        map.put(coordinate, !state);
                    } else if (command[1].equals("on")) {
                        if (!state) count++;
                        map.put(coordinate, true);
                    } else {
                        if (state) count--;
                        map.put(coordinate, false);
                    }
                }
            }
        }

        return String.valueOf(count);
    }

    @Override
    public String part2(Scanner scanner) {
        if (!scanner.hasNextLine()) return "1";

        Map<Coordinate, Integer> map = new HashMap<>();
        int count = 0;

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" through ");
            String[] command = line[0].split(" ");
            Coordinate start = toCoordinate(command[command.length - 1]);
            Coordinate end = toCoordinate(line[1]);

            for (int i = start.x(); i <= end.x(); i++) {
                for (int j = start.y(); j <= end.y(); j++) {
                    Coordinate coordinate = new Coordinate(i, j);
                    if (command[0].equals("toggle")) {
                        count += 2;
                        map.merge(coordinate, 2, Integer::sum);
                    } else if (command[1].equals("on")) {
                        count++;
                        map.merge(coordinate, 1,  Integer::sum);
                    } else {
                        map.merge(coordinate, -1,  Integer::sum);
                        if (map.get(coordinate) < 0)  map.put(coordinate, 0);
                        else count--;
                    }
                }
            }
        }

        return String.valueOf(count);
    }
}