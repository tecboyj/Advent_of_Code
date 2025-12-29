package advent_of_code.y2025;

import advent_of_code.Day;

import java.util.*;

public class Day05 extends Day {
    public Day05(int year, int day, boolean runTests) {
        super(year, day, runTests, 3, 14);
    }

    @Override
    public String part1(Scanner scanner) {
        ArrayList<long[]> ranges = new ArrayList<>();

        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break;
            ranges.add(Arrays.stream(line.split("-")).mapToLong(Long::parseLong).toArray());
        }

        int count = 0;

        while (scanner.hasNextLine()) {
            long id = Long.parseLong(scanner.nextLine());
            for (long[] range : ranges) {
                if (range[0] <= id && id <= range[1]) {
                    count++;
                    break;
                }
            }
        }

        return String.valueOf(count);
    }

    @Override
    public String part2(Scanner scanner) {
        Map<Long, Long> ranges = new HashMap<>();
        Set<Long> keys = new TreeSet<>();

        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break;
            long[] newRange = Arrays.stream(line.split("-")).mapToLong(Long::parseLong).toArray();

            keys.add(newRange[0]);
            if (ranges.containsKey(newRange[0])) ranges.put(newRange[0], Math.max(newRange[1], ranges.get(newRange[0])));
            else ranges.put(newRange[0], newRange[1]);
        }

        ArrayList<long[]> list = new ArrayList<>();

        for (long key : keys) list.add(new long[]{ key, ranges.get(key) });

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1)[0] <= list.get(i)[0] && list.get(i)[0] <= list.get(i - 1)[1]) {
                list.get(i - 1)[1] = Math.max(list.get(i - 1)[1], list.get(i)[1]);
                list.remove(i);
                i--;
            }
        }

        long count = 0;

        for (long[] range : list) count += range[1] - range[0] + 1;

        return String.valueOf(count);
    }
}