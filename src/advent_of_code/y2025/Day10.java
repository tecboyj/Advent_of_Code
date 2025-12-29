package advent_of_code.y2025;

import advent_of_code.Day;

import java.util.*;

public class Day10 extends Day {
    public Day10(int year, int day, boolean runTests) {
        super(year, day, runTests, 7, 33);
    }

    @Override
    public String part1(Scanner scanner) {
        long sum = 0;

        while (scanner.hasNextLine()) {
            ArrayList<String> line = new ArrayList<>(List.of(scanner.nextLine().split(" ")));
            String lightsString = line.getFirst().substring(1, line.getFirst().length() - 1).replace(".", "0").replace("#", "1");
            int lights = Integer.parseInt(lightsString, 2);

            ArrayList<Integer> buttons = new ArrayList<>();
            for (int i = 1; i < line.size() - 1; i++) {
                String[] button = line.get(i).substring(1, line.get(i).length() - 1).split(",");
                StringBuilder value = new StringBuilder("0".repeat(lightsString.length()));

                for (String index : button) value.setCharAt(Integer.parseInt(index), '1');

                buttons.add(Integer.parseInt(value.toString(), 2));
            }


            ArrayDeque<Integer> q = new ArrayDeque<>();
            Map<Integer, Integer> map = new HashMap<>();

            q.add(lights);
            map.put(lights, 0);

            while (!q.isEmpty()) {
                int state = q.remove();
                int depth = map.get(state);

                if (state == 0) {
                    sum += depth;
                    break;
                }

                for (int button : buttons) {
                    int newNode = state ^ button;
                    if (!map.containsKey(newNode)) {
                        q.add(newNode);
                        map.put(newNode, depth + 1);
                    }
                }
            }
        }

        return String.valueOf(sum);
    }

    @Override
    public String part2(Scanner scanner) {
        long sum = 0;

        while (scanner.hasNextLine()) {
            ArrayList<String> line = new ArrayList<>(List.of(scanner.nextLine().split(" ")));
            line.set(line.size() - 1, line.getLast().replace(",", ", "));

            int[] finalArray = new int[line.getLast().substring(1, line.getLast().length() - 1).split(",").length];
            Arrays.fill(finalArray, 0);
            String finalArrayString = Arrays.toString(finalArray);

            ArrayList<int[]> buttons = new ArrayList<>();
            for (int i = 1; i < line.size() - 1; i++) {
                int[] button = Arrays.stream(line.get(i).substring(1, line.get(i).length() - 1).split(",")).mapToInt(Integer::parseInt).toArray();
                buttons.add(button);
            }

            ArrayDeque<String> q = new ArrayDeque<>();
            Map<String, Integer> map = new HashMap<>();

            q.add(line.getLast());
            map.put(line.getLast(), 0);

            while (!q.isEmpty()) {
                String state = q.remove();
                int depth = map.get(state);

                if (state.equals(finalArrayString)) {
                    sum += depth;
                    break;
                }

                for (int[] button : buttons) {
                    int[] newNode = Arrays.stream(state.substring(1, state.length() - 1).split(", ")).mapToInt(Integer::parseInt).toArray();
                    for (int index : button) newNode[index]--;

                    String newNodeString = Arrays.toString(newNode);
                    if (!map.containsKey(newNodeString)) {
                        q.add(newNodeString);
                        map.put(newNodeString, depth + 1);
                    }
                }
            }
        }

        return String.valueOf(sum);
    }
}