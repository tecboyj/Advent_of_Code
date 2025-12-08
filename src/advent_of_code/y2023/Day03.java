package advent_of_code.y2023;

import advent_of_code.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 extends Day {
    public Day03(int year, int day, boolean runTests) {
        super(year, day, runTests, 4361, 467835);
    }

    //TODO 2023 Day 3

    public boolean checkValidity(ArrayList<String> lines, int i, int index) {
        int beginIndex = 0;
        int endIndex = lines.get(i).length() - 1;

        if (index > 0) {
            beginIndex = index - 1;
            String character = lines.get(i).split("")[beginIndex];

            if (Pattern.compile("[^\\d.]").matcher(character).matches()) return true;
        }
        if (index < lines.get(i).length() - 1) {
            endIndex = index + 1;
            String character = lines.get(i).split("")[endIndex];
            if (Pattern.compile("[^\\d.]").matcher(character).matches()) return true;
        }
        if (i > 0) {
            if (endIndex != lines.get(i).length() - 1) endIndex++;
            String characters = lines.get(i - 1).substring(beginIndex, endIndex);
            for (char c : characters.toCharArray())
                if (Pattern.compile("[^\\d.]").matcher(String.valueOf(c)).matches())
                    return true;
        }
        if (i < lines.size() - 1) {
            if (endIndex != lines.get(i).length() - 1) endIndex++;
            String characters = lines.get(i + 1).substring(beginIndex, endIndex);
            for (char c : characters.toCharArray())
                if (Pattern.compile("[^\\d.]").matcher(String.valueOf(c)).matches())
                    return true;
        }

        return false;
    }

    @Override
    public String part1(Scanner scanner) {
        int sum = 0;
        ArrayList<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) lines.add(scanner.nextLine());

        for (int i = 0; i < lines.size(); i++) {
            Matcher matcher = Pattern.compile("(\\d+)").matcher(lines.get(i));
            while (matcher.find()) {
                int index = matcher.start();
                String number = matcher.group();
                if (checkValidity(lines, i, index) || checkValidity(lines, i, index + number.length() - 1)) sum += Integer.parseInt(number);
            }
        }


        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        int sum = 0;

        return String.valueOf(sum);
    }
}