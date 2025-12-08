package advent_of_code.y2015;

import advent_of_code.Day;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Scanner;

public class Day04 extends Day {
    public Day04(int year, int day, boolean runTests) {
        super(year, day, runTests, 609043, 6742839);
    }

    public boolean correctPrefix(String text, String prefix) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(text.getBytes());
            return HexFormat.of().formatHex(hash).startsWith(prefix);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    @Override
    public String part1(Scanner scanner) {
        String input = scanner.nextLine();

        for (int i = 0; i < 1000000; i++) {
            if (correctPrefix(input + i, "00000")) return String.valueOf(i);
        }

        return null;
    }

    @Override
    public String part2(Scanner scanner) {
        String input = scanner.nextLine();

        for (int i = 0; i < 1000000000; i++) {
            if (correctPrefix(input + i, "000000")) return String.valueOf(i);
        }

        return null;
    }
}