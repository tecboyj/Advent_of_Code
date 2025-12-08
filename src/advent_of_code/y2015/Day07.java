package advent_of_code.y2015;

import advent_of_code.Day;

import java.util.*;

public class Day07 extends Day {
    private Map<String, Integer> cache;

    public Day07(int year, int day, boolean runTests) {
        super(year, day, runTests, 65412, 65412);
    }


    private int evalCommand(Map<String, String[]> map, String key) {
        if (cache.containsKey(key)) return cache.get(key);
        try {
            return Integer.parseInt(key);
        } catch (NumberFormatException ignored) {}

        String[] command = map.get(key);

        return switch (command.length) {
            case 1 -> {
                int value = evalCommand(map, command[0]);
                cache.put(key, value);
                yield value;
            }
            case 2 -> {
                int value = evalCommand(map, command[1]);
                int result = ~value  & 0xFFFF;
                cache.put(key, result);
                yield result;
            }
            case 3 -> {
                int a = evalCommand(map, command[0]);
                cache.put(command[0], a);
                int b = evalCommand(map, command[2]);
                cache.put(command[2], b);

                yield switch (command[1]) {
                    case "AND" -> a & b;
                    case "OR" -> a | b;
                    case "LSHIFT" -> (a << b) & 0xFFFF;
                    case "RSHIFT" -> a >> b;

                    default -> throw new IllegalArgumentException();
                };
            }
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    public String part1(Scanner scanner) {
        Map<String, String[]> map = new HashMap<>();
        cache = new HashMap<>();

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" -> ");
            String[] command = line[0].split(" ");
            map.put(line[1], command);
        }

        return String.valueOf(evalCommand(map, "a"));
    }

    @Override
    public String part2(Scanner scanner) {
        Map<String, String[]> map = new HashMap<>();
        cache = new HashMap<>();

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" -> ");
            String[] command = line[0].split(" ");
            map.put(line[1], command);
        }

        map.put("b", String.valueOf(evalCommand(map, "a")).split(" "));
        cache = new HashMap<>();

        return String.valueOf(evalCommand(map, "a"));
    }
}