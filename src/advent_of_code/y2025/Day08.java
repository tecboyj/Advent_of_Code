package advent_of_code.y2025;

import advent_of_code.Day;

import java.util.*;

public class Day08 extends Day {
    public Day08(int year, int day, boolean runTests) {
        super(year, day, runTests, 40, 25272);
    }

    private record JunctionBox(long x, long y, long z) {

        public long distanceFrom(JunctionBox box) {
            long a = (this.x - box.x) * (this.x - box.x);
            long b = (this.y - box.y) * (this.y - box.y);
            long c = (this.z - box.z) * (this.z - box.z);

            return a + b + c;
        }

        public static JunctionBox createBox(String string) {
            String[] point = string.split(",");
            return new JunctionBox(Long.parseLong(point[0]), Long.parseLong(point[1]), Long.parseLong(point[2]));
        }
    }

    @Override
    public String part1(Scanner scanner) {
        ArrayList<JunctionBox> boxes = new ArrayList<>();
        while (scanner.hasNextLine()) boxes.add(JunctionBox.createBox(scanner.nextLine()));

        HashMap<Long, JunctionBox[]> map = new HashMap<>();

        for (int i = 0; i < boxes.size() - 1; i++) {
            JunctionBox box1 = boxes.get(i);
            for (int j = i + 1; j < boxes.size(); j++) {
                JunctionBox box2 = boxes.get(j);
                long distance = box1.distanceFrom(box2);
                if (map.containsKey(distance)) {
                    map.put(distance + 1, new JunctionBox[]{ box1, box2 });
                    System.out.println(distance);
                }
                else map.put(distance, new JunctionBox[]{ box1, box2 });
            }
        }

        ArrayList<Long> distances = new ArrayList<>(map.keySet());
        distances.sort(null);
        ArrayList<HashSet<JunctionBox>> circuits = new ArrayList<>();
        int iterations;
        if (boxes.size() == 20) iterations = 10;
        else iterations = 1000;

        for (int i = 0; i < iterations; i++) {
            JunctionBox[] pair = map.get(distances.get(i));

            int leftIndex = -1;
            int rightIndex = -1;

            for (int j = 0; j < circuits.size(); j++) {
                if (circuits.get(j).contains(pair[0])) {
                    if (rightIndex != -1) {
                        circuits.get(j).addAll(circuits.get(rightIndex));
                        circuits.remove(rightIndex);
                        break;
                    } else {
                        circuits.get(j).add(pair[1]);
                        leftIndex = j;
                    }
                } else if (circuits.get(j).contains(pair[1])) {
                    if (leftIndex != -1) {
                        circuits.get(j).addAll(circuits.get(leftIndex));
                        circuits.remove(leftIndex);
                        break;
                    } else {
                        circuits.get(j).add(pair[0]);
                        rightIndex = j;
                    }
                }
            }

            if (leftIndex == -1 && rightIndex == -1) circuits.add(new HashSet<>(List.of(pair)));
        }

        int x = 0;
        int y = 0;
        int z = 0;

        for (HashSet<JunctionBox> circuit : circuits) {
            int size = circuit.size();
            if (size > x) {
                z = y;
                y = x;
                x = size;
            } else if (size > y) {
                z = y;
                y = size;
            } else if (size > z) z = size;
        }

        return String.valueOf(x * y * z);
    }

    @Override
    public String part2(Scanner scanner) {
        ArrayList<JunctionBox> boxes = new ArrayList<>();
        while (scanner.hasNextLine()) boxes.add(JunctionBox.createBox(scanner.nextLine()));

        HashMap<Long, JunctionBox[]> map = new HashMap<>();

        for (int i = 0; i < boxes.size() - 1; i++) {
            JunctionBox box1 = boxes.get(i);
            for (int j = i + 1; j < boxes.size(); j++) {
                JunctionBox box2 = boxes.get(j);
                long distance = box1.distanceFrom(box2);
                if (map.containsKey(distance)) {
                    map.put(distance + 1, new JunctionBox[]{ box1, box2 });
                    System.out.println(distance);
                }
                else map.put(distance, new JunctionBox[]{ box1, box2 });
            }
        }

        ArrayList<Long> distances = new ArrayList<>(map.keySet());
        distances.sort(null);
        ArrayList<HashSet<JunctionBox>> circuits = new ArrayList<>();
        int iterations;
        if (boxes.size() == 20) iterations = 10;
        else iterations = 1000;

        int i = 0;
        JunctionBox[] pair = map.get(distances.get(i));
        while (circuits.isEmpty() || circuits.getFirst().size() != boxes.size()) {
            pair = map.get(distances.get(i));

            int leftIndex = -1;
            int rightIndex = -1;

            for (int j = 0; j < circuits.size(); j++) {
                if (circuits.get(j).contains(pair[0])) {
                    if (rightIndex != -1) {
                        circuits.get(j).addAll(circuits.get(rightIndex));
                        circuits.remove(rightIndex);
                        break;
                    } else {
                        circuits.get(j).add(pair[1]);
                        leftIndex = j;
                    }
                } else if (circuits.get(j).contains(pair[1])) {
                    if (leftIndex != -1) {
                        circuits.get(j).addAll(circuits.get(leftIndex));
                        circuits.remove(leftIndex);
                        break;
                    } else {
                        circuits.get(j).add(pair[0]);
                        rightIndex = j;
                    }
                }
            }

            if (leftIndex == -1 && rightIndex == -1) circuits.add(new HashSet<>(List.of(pair)));
            i++;
        }

        return String.valueOf(pair[0].x * pair[1].x);
    }
}