package advent_of_code.y2022;

import advent_of_code.Day;

import java.util.*;

public class Day02 extends Day {
    public Day02(int year, int day, boolean runTests) {
        super(year, day, runTests, 15, 12);
    }

    @Override
    public String part1(Scanner scanner) {
        int sum = 0;
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine()
                    .replace("X", "A")
                    .replace("Y", "B")
                    .replace("Z", "C")
                    .split(" ");
            switch (line[1]) {
                case "A" -> sum +=1;
                case "B" -> sum +=2;
                case "C" -> sum +=3;
            }
            if (line[0].equals(line[1])) sum += 3;
            else if (line[0].equals("A") && line[1].equals("B")) sum += 6;
            else if (line[0].equals("B") && line[1].equals("C")) sum += 6;
            else if (line[0].equals("C") && line[1].equals("A")) sum += 6;
        }
        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        int sum = 0;
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            switch (line[1]) {
                case "X" -> {
                    switch (line[0]) {
                        case "A" -> sum += 3;
                        case "B" -> sum += 1;
                        case "C" -> sum += 2;
                    }
                }
                case "Y" -> {
                    sum +=3;
                    switch (line[0]) {
                        case "A" -> sum += 1;
                        case "B" -> sum += 2;
                        case "C" -> sum += 3;
                    }
                }
                case "Z" -> {
                    sum +=6;
                    switch (line[0]) {
                        case "A" -> sum += 2;
                        case "B" -> sum += 3;
                        case "C" -> sum += 1;
                    }
                }
            }
        }
        return String.valueOf(sum);
    }
}
