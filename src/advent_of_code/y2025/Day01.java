package advent_of_code.y2025;

import advent_of_code.Day;

import java.util.*;

public class Day01 extends Day {
    public Day01(int year, int day, boolean runTests) {
        super(year, day, runTests, 3, 6);
    }

    @Override
    public String part1(Scanner scanner) {
        int pointer = 50;
        int zero = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int rotation = Integer.parseInt(line.substring(1));

            if (line.charAt(0) == 'R') {
                pointer += rotation;
                if (pointer > 99) {
                    pointer = pointer % 100;
                }
            } else {
                pointer -= rotation;
                while (pointer < 0) {
                    pointer += 100;
                }
            }

            if (pointer == 0) {
                zero++;
            }
        }
        return String.valueOf(zero);
    }

    @Override
    public String part2(Scanner scanner) {
        int pointer = 50;
        int zero = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int rotation = Integer.parseInt(line.substring(1));

            if (line.charAt(0) == 'R') {
                for (int i = 0; i < rotation; i++) {
                    pointer++;
                    if (pointer == 100) {
                        pointer = 0;
                        zero++;
                    }
                }
            } else {
                for (int i = 0; i < rotation; i++) {
                    pointer--;
                    if (pointer == 0) {
                        zero++;
                    }
                    if (pointer == -1) {
                        pointer = 99;
                    }
                }
            }
        }

        return String.valueOf(zero);
    }
}
