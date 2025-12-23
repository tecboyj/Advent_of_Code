package advent_of_code.y2025;

public class Run2025 {
    public static void run(int year, int day, boolean runTests) {
        switch (day) {
            case 1 -> new Day01(year, day, runTests);
            case 2 -> new Day02(year, day, runTests);
            case 3 -> new Day03(year, day, runTests);
            case 4 -> new Day04(year, day, runTests);
            case 5 -> new Day05(year, day, runTests);
            case 6 -> new Day06(year, day, runTests);
            case 7 -> new Day07(year, day, runTests);
            case 8 -> new Day08(year, day, runTests);
            case 9 -> new Day09(year, day, runTests);
            case 10 -> new Day10(year, day, runTests);
            case 11 -> new Day11(year, day, runTests);
            case 12 -> new Day12(year, day, runTests);
        }
    }
}
