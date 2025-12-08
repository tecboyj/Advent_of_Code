package advent_of_code.y2025;

import advent_of_code.Day;
import com.sun.source.tree.Tree;

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

    // 350939917199507
    // 350995057840626

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


//@Override
//    public String part2(Scanner scanner) {
//        Map<Long, Long> ranges = new HashMap<>();
//
//        while (true) {
//            String line = scanner.nextLine();
//            if (line.isEmpty()) break;
//            long[] newRange = Arrays.stream(line.split("-")).mapToLong(Long::parseLong).toArray();
//
//            Long[] keys = ranges.keySet().toArray(new Long[0]);
//            boolean add = true;
//            for (Long key : keys) {
//                if (key <= newRange[0] && newRange[1] <= ranges.get(key)) {
//                    add = false;
//                    break;
//                }
//                if (newRange[0] <= key && ranges.get(key) <= newRange[1]) {
//                    ranges.remove(key);
//                    continue;
//                }
//
//                if (key <= newRange[0] && newRange[0] <= ranges.get(key)) {
//                    if (!add) ranges.remove(newRange[0]);
//                    ranges.put(key, newRange[1]);
//                    newRange[1] = key;
//                    add = false;
//                    continue;
//                }
//                if (key <= newRange[1] && newRange[1] <= ranges.get(key)) {
//                    long right = ranges.get(key);
//                    ranges.put(newRange[0], right);
//                    ranges.remove(key);
//                    newRange[1] = right;
//                    add = false;
//                }
//            }
//
//            if (add) ranges.put(newRange[0], newRange[1]);
//        }
//
//        long count = 0;
//
//        for (Long range : ranges.keySet()) {
//            count += ranges.get(range) - range + 1;
//        }
//
//        return String.valueOf(count);
//    }
}