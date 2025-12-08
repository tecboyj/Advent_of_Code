package advent_of_code.y2024;

import advent_of_code.Day;

import java.math.BigInteger;
import java.util.*;

public class Day11 extends Day {
    public Day11(int year, int day, boolean runTests) {
        super(year, day, runTests, 55312, 65601038650482L);
    }

    @Override
    public String part1(Scanner scanner) {
        ArrayList<String> stones = new ArrayList<>(List.of(scanner.nextLine().split(" ")));

        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < stones.size(); j++) {
                if (stones.get(j).equals("0")) {
                    stones.set(j, "1");
                    continue;
                }
                if (stones.get(j).length() % 2 == 0) {
                    String stone = stones.get(j);
                    stones.set(j, stone.substring(0, stone.length() / 2));
                    stones.add(j + 1, String.valueOf(Long.parseLong(stone.substring(stone.length() / 2))));
                    j++;
                    continue;
                }

                stones.set(j, String.valueOf(Long.parseLong(stones.get(j)) * 2024));
            }
        }

        return String.valueOf(stones.size());
    }

    @Override
    public String part2(Scanner scanner) {
        String[] input = scanner.nextLine().split(" ");
        HashMap<String, Long> stones = new HashMap<>();
        for (String s : input) stones.put(s, 1L);

        for (int i = 0; i < 75; i++) {
            HashMap<String, Long> newStones = new HashMap<>();

            for (String s : stones.keySet()) {
                String value1;
                String value2 = null;
                if (s.equals("0")) value1 = "1";
                else if (s.length() % 2 == 0) {
                    value1 = s.substring(0, s.length() / 2);
                    value2 = String.valueOf(Long.parseLong(s.substring(s.length() / 2)));
                } else value1 = String.valueOf(Long.parseLong(s) * 2024);

                if (newStones.containsKey(value1)) newStones.put(value1, newStones.get(value1) + stones.get(s));
                else newStones.put(value1, stones.get(s));
                if (value2 != null) {
                    if (newStones.containsKey(value2)) newStones.put(value2, newStones.get(value2) + stones.get(s));
                    else newStones.put(value2, stones.get(s));
                }
            }

            stones = newStones;
        }

        BigInteger sum = BigInteger.ZERO;
        for (String s : stones.keySet()) {
            sum = sum.add(BigInteger.valueOf(stones.get(s)));
        }

        return String.valueOf(sum);
    }
}