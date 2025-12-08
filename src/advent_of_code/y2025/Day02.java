package advent_of_code.y2025;

import advent_of_code.Day;

import java.util.*;

public class Day02 extends Day {
    public Day02(int year, int day, boolean runTests) {
        super(year, day, runTests, 1227775554, "4174379265");
    }

    @Override
    public String part1(Scanner scanner) {
        String[] input = scanner.nextLine().split(",");

        long sum = 0;

        for (String s : input) {
            String[] range = s.split("-");

            for (long i = Long.parseLong(range[0]); i <= Long.parseLong(range[1]); i++) {
                String productId = String.valueOf(i);
                if (productId.substring(0, productId.length() / 2).equals(productId.substring(productId.length() / 2))) sum += i;
            }
        }

        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        String[] input = scanner.nextLine().split(",");

        long sum = 0;

        for (String s : input) {
            String[] range = s.split("-");

            for (long i = Long.parseLong(range[0]); i <= Long.parseLong(range[1]); i++) {
                String productId = String.valueOf(i);

                for (int j = 1; j <= productId.length() / 2; j++) {
                    if (productId.length() % j != 0) continue;
                    String test = productId.substring(0, j).repeat(productId.length() / j);

                    if (test.equals(productId)) {
                        sum += i;
                        break;
                    }
                }
            }
        }

        return String.valueOf(sum);
    }
}
