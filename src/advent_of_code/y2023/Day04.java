package advent_of_code.y2023;

import advent_of_code.Day;

import java.util.*;

public class Day04 extends Day {
    public Day04(int year, int day, boolean runTests) {
        super(year, day, runTests, 13, 30);
    }

    @Override
    public String part1(Scanner scanner) {
        int sum = 0;
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(": ")[1].split(" \\| ");
            String[] myNumbers = line[0].split(" ");
            String[] winningNumbers = line[1].split(" ");

            int points = 0;
            for (String myNumber : myNumbers) {
                if (myNumber.isEmpty()) continue;
                if (Arrays.asList(winningNumbers).contains(myNumber)) {
                    if (points == 0) points = 1;
                    else points *= 2;
                }
            }
            sum += points;
        }
        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(": ");
            String[] game = line[0].split(" ");
            int gameNum = Integer.parseInt(game[game.length - 1]);
            map.putIfAbsent(gameNum, 1);
            String[] cardNums = line[1].split(" \\| ");
            String[] myNumbers = cardNums[0].split(" ");
            String[] winningNumbers = cardNums[1].split(" ");

            for (int i = 0; i < map.get(gameNum); i++) {
                int points = 0;
                for (String myNumber : myNumbers) {
                    if (myNumber.isEmpty()) continue;
                    if (Arrays.asList(winningNumbers).contains(myNumber)) points++;
                }
                for (int j = gameNum; j < gameNum + points; j++) map.put(j + 1, map.getOrDefault(j + 1, 1) + 1);
                sum += points;
            }
            if (!scanner.hasNextLine()) sum += gameNum;
        }
        return String.valueOf(sum);
    }
}