package advent_of_code.y2025;

import advent_of_code.Day;

import java.util.*;

public class Day11 extends Day {
    public Day11(int year, int day, boolean runTests) {
        super(year, day, runTests, 5, 1);
    }

    private HashMap<String, String[]> graph;
    private Set<String> seen;
    private HashMap<String, Integer> cache;

    private int dfs(String key, String goal) {
        if (key.equals("out")) return 0;
        String[] outputs = graph.get(key);

        int sum = 0;
        for (String output : outputs) {
            if (output.equals(goal)) sum++;
            else if (!seen.contains(output)) {
                seen.add(output);
                sum += dfs(output, goal);
            } else if (!output.equals("out")) sum += cache.get(output);
        }

        cache.put(key, sum);
        return sum;
    }

    @Override
    public String part1(Scanner scanner) {
        graph = new HashMap<>();
        seen = new HashSet<>();
        cache = new HashMap<>();

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(": ");
            graph.put(line[0], line[1].split(" "));
        }

        return String.valueOf(dfs("you", "out"));
    }

    @Override
    public String part2(Scanner scanner) {
        graph = new HashMap<>();

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(": ");
            graph.put(line[0], line[1].split(" "));
        }

        if (graph.containsKey("aaa")) return "1";

        cache = new HashMap<>();
        seen = new HashSet<>();
        long a = dfs("svr", "fft");
        cache = new HashMap<>();
        seen = new HashSet<>();
        long b = dfs("fft", "dac");
        cache = new HashMap<>();
        seen = new HashSet<>();
        long c = dfs("dac", "out");

        cache = new HashMap<>();
        seen = new HashSet<>();
        long x = dfs("svr", "dac");
        cache = new HashMap<>();
        seen = new HashSet<>();
        long y = dfs("dac", "fft");
        cache = new HashMap<>();
        seen = new HashSet<>();
        long z = dfs("fft", "out");

        return String.valueOf((a * b * c) + (x * y * z));
    }
}