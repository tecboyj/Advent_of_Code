package advent_of_code.y2024;

import advent_of_code.Day;

import java.util.ArrayList;
import java.util.Scanner;

public class Day04 extends Day {
    public Day04(int year, int day, boolean runTests) {
        super(year, day, runTests, 18, 9);
    }

    @Override
    public String part1(Scanner scanner) {
        int sum = 0;
        ArrayList<String[]> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            sum += line.split("XMAS").length - 1;
            sum += line.split("SAMX").length - 1;
            String last4 = line.substring(line.length() - 4);
            if (last4.equals("XMAS") || last4.equals("SAMX")) sum++;
            lines.add(line.split(""));
        }

        for (int i = 0; i < lines.size() - 3; i++) {
            int length = lines.get(i).length;
            for (int j = 0; j < length; j++) {
                String xmas = lines.get(i)[j] + lines.get(i + 1)[j] + lines.get(i + 2)[j] + lines.get(i + 3)[j];
                if (xmas.equals("XMAS") || xmas.equals("SAMX")) sum++;
                if (j < length - 3) {
                    String xmas2 = lines.get(i)[j] + lines.get(i + 1)[j + 1] + lines.get(i + 2)[j + 2] + lines.get(i + 3)[j + 3];
                    String samx = lines.get(i)[length - (j + 1)] +
                            lines.get(i + 1)[length - (j + 2)] +
                            lines.get(i + 2)[length - (j + 3)] +
                            lines.get(i + 3)[length - (j + 4)];
                    if (xmas2.equals("XMAS") || xmas2.equals("SAMX")) sum++;
                    if (samx.equals("XMAS") || samx.equals("SAMX")) sum++;
                }
            }
        }

        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        int sum = 0;
        ArrayList<String[]> lines = new ArrayList<>();
        while (scanner.hasNextLine()) lines.add(scanner.nextLine().split(""));

        for (int i = 1; i < lines.size() - 1; i++) {
            int length = lines.get(i).length;
            for (int j = 1; j < length - 1; j++) {
                if (lines.get(i)[j].equals("A")) {
                    String char1 = lines.get(i - 1)[j - 1];
                    String char2 = lines.get(i - 1)[j + 1];
                    String char3 = lines.get(i + 1)[j - 1];
                    String char4 = lines.get(i + 1)[j + 1];
                    if (char1.equals("A") || char2.equals("A") || char3.equals("A") || char4.equals("A")) continue;
                    if (char1.equals("X") || char2.equals("X") || char3.equals("X") || char4.equals("X")) continue;
                    if (char1.equals(char4) || char2.equals(char3)) continue;
                    sum++;
                }
            }
        }

        return String.valueOf(sum);
    }
}