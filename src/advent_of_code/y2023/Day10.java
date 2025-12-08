package advent_of_code.y2023;

import advent_of_code.Day;

import java.util.*;

public class Day10 extends Day {
    public Day10(int year, int day, boolean runTests) {
        super(year, day, runTests, 4, 4);
    }

    public boolean contains(ArrayList<Integer[]> list, Integer[] item) {
        for (Integer[] i : list) if (Arrays.equals(i, item)) return true;
        return false;
    }

    @Override
    public String part1(Scanner scanner) {
        ArrayList<String> map = new ArrayList<>();
        while (scanner.hasNextLine()) map.add(scanner.nextLine());

        int row = 0;
        int col = 0;
        outer: for (int i = 0; i < map.size(); i++)
            for (int j = 0; j < map.get(i).length(); j++)
                if (map.get(i).charAt(j) == 'S') {
                    row = i;
                    col = j;
                    break outer;
                }

        ArrayList<Integer[]> seen = new ArrayList<>();
        seen.add(new Integer[]{row, col});
        ArrayList<Integer[]> deque = new ArrayList<>();
        deque.add(new Integer[]{row, col});

        while (!deque.isEmpty()) {
            Integer[] current = deque.remove(0);
            String c = String.valueOf(map.get(current[0]).charAt(current[1]));
            if (current[0] > 0 && "S|JL".contains(c) && "|7F".contains(String.valueOf(map.get(current[0] - 1).charAt(current[1]))) && !contains(seen, new Integer[]{current[0] - 1, current[1]})) {
                seen.add(new Integer[]{ current[0] - 1, current[1] });
                deque.add(new Integer[]{ current[0] - 1, current[1] });
            }

            if (current[0] < map.size() - 1 && "S|7F".contains(c) && "|JL".contains(String.valueOf(map.get(current[0] + 1).charAt(current[1]))) && !contains(seen, new Integer[]{current[0] + 1, current[1]})) {
                seen.add(new Integer[]{ current[0] + 1, current[1] });
                deque.add(new Integer[]{ current[0] + 1, current[1] });
            }

            if (current[1] > 0 && "S-J7".contains(c) && "-LF".contains(String.valueOf(map.get(current[0]).charAt(current[1] - 1))) && !contains(seen, new Integer[]{current[0], current[1] - 1})) {
                seen.add(new Integer[]{ current[0], current[1] - 1 });
                deque.add(new Integer[]{ current[0], current[1] - 1 });
            }

            if (current[1] < map.get(current[0]).length() - 1 && "S-LF".contains(c) && "-J7".contains(String.valueOf(map.get(current[0]).charAt(current[1] + 1))) && !contains(seen, new Integer[]{current[0], current[1] + 1})) {
                seen.add(new Integer[]{ current[0], current[1] + 1 });
                deque.add(new Integer[]{ current[0], current[1] + 1 });

            }
        }

        return String.valueOf(seen.size() / 2);
    }

    public ArrayList<String> compare(ArrayList<String> maybe_s, String[] possible) {
        ArrayList<String> map = new ArrayList<>();
        for (String s : possible) if (maybe_s.contains(s)) map.add(s);
        return map;
    }

    @Override
    public String part2(Scanner scanner) {
        ArrayList<String> map = new ArrayList<>();
        while (scanner.hasNextLine()) map.add(scanner.nextLine());

        int row = 0;
        int col = 0;
        outer: for (int i = 0; i < map.size(); i++)
            for (int j = 0; j < map.get(i).length(); j++)
                if (map.get(i).charAt(j) == 'S') {
                    row = i;
                    col = j;
                    break outer;
                }

        ArrayList<Integer[]> seen = new ArrayList<>();
        seen.add(new Integer[]{row, col});
        ArrayList<Integer[]> deque = new ArrayList<>();
        deque.add(new Integer[]{row, col});

        ArrayList<String> maybe_s = new ArrayList<>(Arrays.asList("|", "-", "J", "L", "7", "F"));

        while (!deque.isEmpty()) {
            Integer[] current = deque.remove(0);
            String c = String.valueOf(map.get(current[0]).charAt(current[1]));
            if (current[0] > 0 && "S|JL".contains(c) && "|7F".contains(String.valueOf(map.get(current[0] - 1).charAt(current[1]))) && !contains(seen, new Integer[]{current[0] - 1, current[1]})) {
                seen.add(new Integer[]{ current[0] - 1, current[1] });
                deque.add(new Integer[]{ current[0] - 1, current[1] });
                if (c.equals("S")) maybe_s = compare(maybe_s, new String[]{"|", "J", "L"});
            }

            if (current[0] < map.size() - 1 && "S|7F".contains(c) && "|JL".contains(String.valueOf(map.get(current[0] + 1).charAt(current[1]))) && !contains(seen, new Integer[]{current[0] + 1, current[1]})) {
                seen.add(new Integer[]{ current[0] + 1, current[1] });
                deque.add(new Integer[]{ current[0] + 1, current[1] });
                if (c.equals("S")) maybe_s = compare(maybe_s, new String[]{"|", "7", "F"});
            }

            if (current[1] > 0 && "S-J7".contains(c) && "-LF".contains(String.valueOf(map.get(current[0]).charAt(current[1] - 1))) && !contains(seen, new Integer[]{current[0], current[1] - 1})) {
                seen.add(new Integer[]{ current[0], current[1] - 1 });
                deque.add(new Integer[]{ current[0], current[1] - 1 });
                if (c.equals("S")) maybe_s = compare(maybe_s, new String[]{"-", "J", "7"});
            }

            if (current[1] < map.get(current[0]).length() - 1 && "S-LF".contains(c) && "-J7".contains(String.valueOf(map.get(current[0]).charAt(current[1] + 1))) && !contains(seen, new Integer[]{current[0], current[1] + 1})) {
                seen.add(new Integer[]{ current[0], current[1] + 1 });
                deque.add(new Integer[]{ current[0], current[1] + 1 });
                if (c.equals("S")) maybe_s = compare(maybe_s, new String[]{"-", "L", "F"});
            }
        }

        for (int i = 0; i < map.size(); i++) map.set(i, map.get(i).replace("S", maybe_s.get(0)));

        for (int i = 0; i < map.size(); i++) {
            char[] chars = map.get(i).toCharArray();
            for (int j = 0; j < chars.length; j++) if (!contains(seen, new Integer[]{i, j})) chars[j] = '.';
            map.set(i, String.valueOf(chars));
        }


        ArrayList<Integer[]> outside = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            boolean within = false;
            String up = null;
            for (int j = 0; j < map.get(i).length(); j++) {
                String ch = String.valueOf(map.get(i).charAt(j));
                if (ch.equals("|")) {
                    assert up == null;
                    within = !within;
                } else if (ch.equals("-")) {
                    assert up != null;
                } else if ("LF".contains(ch)) {
                    assert up == null;
                    up = String.valueOf(ch.equals("L"));
                } else if ("J7".contains(ch)) {
                    assert up != null;
                    if (up.equals("true")) {
                        if (ch.equals("7")) within = !within;
                    } else {
                        if (ch.equals("J")) within = !within;
                    }
                    up = null;
                } else if (ch.equals(".")) {
                    within = within;
                } else {
                    throw new Error("What the Fuck happened");
                }
                if (!within) outside.add(new Integer[]{ i, j });
            }
        }
        ArrayList<Integer[]> out = new ArrayList<>();

        for (Integer[] i : outside) if (!contains(seen, i)) out.add(i);

        return String.valueOf((map.size() * map.get(0).length()) - (seen.size() + out.size()));
    }
}