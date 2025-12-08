package advent_of_code.y2022;

import advent_of_code.Day;

import java.util.*;

public class Day03 extends Day {
    public Day03(int year, int day, boolean runTests) {
        super(year, day, runTests, 157, 70);
    }

    @Override
    public String part1(Scanner scanner) {
        int sum = 0;
        String alphabet = "0abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("");
            int[] chars = new int[line.length];
            for (int i = 0; i < line.length; i++) chars[i] = alphabet.indexOf(line[i]);

            int[] first = Arrays.copyOfRange(chars, 0, chars.length / 2);
            int[] second = Arrays.copyOfRange(chars, chars.length / 2, chars.length);
            outer: for (int i : first) for (int j : second) if (i == j) {
                    sum += i;
                    break outer;
            }

        }
        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        int sum = 0;
        String alphabet = "0abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        while (scanner.hasNextLine()) {
            String[] elf1 = scanner.nextLine().split("");
            String[] elf2 = scanner.nextLine().split("");
            String[] elf3 = scanner.nextLine().split("");
            outer: for (String a : elf1) for (String b : elf2) if (a.equals(b)) for (String c : elf3) if (a.equals(c)) {
                sum += alphabet.indexOf(a);
                break outer;
            }
        }
        return String.valueOf(sum);
    }
}