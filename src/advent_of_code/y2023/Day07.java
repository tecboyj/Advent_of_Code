package advent_of_code.y2023;

import advent_of_code.Day;

import java.util.*;

public class Day07 extends Day {
    private ArrayList<String> list;
    public Day07(int year, int day, boolean runTests) {
        super(year, day, runTests, 6440, 5905);
    }


    public void swap(int a, int b) {
        String temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);

    }

    public String[] occurrences(String s) {
        int maxOccur = 0;
        char maxChar = ' ';
        char[] chars = s.toCharArray();
        for (char c1 : chars) {
            int occur = 0;
            for (char c2 : chars) if (c1 == c2) occur++;
            if (occur > maxOccur) {
                maxOccur = occur;
                maxChar = c1;
            }
        }
        return new String[] {String.valueOf(maxOccur), String.valueOf(maxChar)};
    }
    public int strength(String s) {
        String s1 = s.replace("0", "");
        String[] occur = occurrences(s1);
        int occurNum = Integer.parseInt(occur[0]);
        String occurChar = String.valueOf(occur[1].charAt(0));
        if (s.contains("0")) return strength(s.replace("0", occurChar));
        switch (occurNum) {
            case 1 -> {
                return occurNum;
            }
            case 2 -> {
                String[] occur2 = occurrences(s.replace(occurChar, ""));
                return Integer.parseInt(occur2[0]) + 1;
            }
            case 3 -> {
                String[] occur2 = occurrences(s.replace(occurChar, ""));
                return Integer.parseInt(occur2[0]) + 3;
            }
            default -> {
                return occurNum + 2;
            }
        }
    }
    @Override
    public String part1(Scanner scanner) {
        list = new ArrayList<>();
        HashMap<String, Integer> bet = new HashMap<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            line[0] = line[0].replace("A", "E")
                    .replace("T", "A")
                    .replace("J", "B")
                    .replace("Q", "C")
                    .replace("K", "D");
            list.add(line[0]);
            bet.put(line[0], Integer.parseInt(line[1]));
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                int a = strength(list.get(j));
                int b = strength(list.get(j + 1));
                if (a > b) swap(j, j + 1);
                else if (a == b) {
                    String[] occur1 = list.get(j).split("");
                    String[] occur2 = list.get(j + 1).split("");
                    for (int k = 0; k < occur1.length; k++) {
                        if (occur1[k].compareTo(occur2[k]) > 0) {
                            swap(j, j + 1);
                            break;
                        } else if (occur1[k].compareTo(occur2[k]) < 0) break;
                    }
                }
            }
        }


        int sum = 0;
        for (int i = 0; i < list.size(); i++) sum += bet.get(list.get(i)) * (i + 1);
        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        list = new ArrayList<>();
        HashMap<String, Integer> bet = new HashMap<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            line[0] = line[0].replace("A", "E")
                    .replace("T", "A")
                    .replace("J", "0")
                    .replace("Q", "C")
                    .replace("K", "D");
            list.add(line[0]);
            bet.put(line[0], Integer.parseInt(line[1]));
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                int a = strength(list.get(j));
                int b = strength(list.get(j + 1));
                if (a > b) swap(j, j + 1);
                else if (a == b) {
                    String[] occur1 = list.get(j).split("");
                    String[] occur2 = list.get(j + 1).split("");
                    for (int k = 0; k < occur1.length; k++) {
                        if (occur1[k].compareTo(occur2[k]) > 0) {
                            swap(j, j + 1);
                            break;
                        } else if (occur1[k].compareTo(occur2[k]) < 0) break;
                    }
                }
            }
        }


        int sum = 0;
        for (int i = 0; i < list.size(); i++) sum += bet.get(list.get(i)) * (i + 1);
        return String.valueOf(sum);
    }
}