package advent_of_code.y2025;

import advent_of_code.Day;

import java.util.*;

public class Day08 extends Day {
    public Day08(int year, int day, boolean runTests) {
        super(year, day, runTests, 40, 0);
    }

    private record JunctionBox(int x, int y, int z) {

        public double distanceFrom(JunctionBox box) {
            int a = (this.x - box.x) * (this.x - box.x);
            int b = (this.y - box.y) * (this.y - box.y);
            int c = (this.z - box.z) * (this.z - box.z);

            return Math.sqrt(a + b + c);
        }

        public static JunctionBox createBox(String string) {
            String[] point = string.split(",");
            return new JunctionBox(Integer.parseInt(point[0]), Integer.parseInt(point[1]), Integer.parseInt(point[2]));
        }
    }

    @Override
    public String part1(Scanner scanner) {
        ArrayList<JunctionBox> boxes = new ArrayList<>();
        HashMap<Double, ArrayList<JunctionBox[]>> map = new HashMap<>();

        while (scanner.hasNextLine()) {
            JunctionBox previous = boxes.getLast();
            JunctionBox current = JunctionBox.createBox(scanner.nextLine());
            double distance = previous.distanceFrom(current);
            JunctionBox[] boxes1 = new JunctionBox[]{ previous, current };

            if (map.containsKey(distance)) map.get(distance).add(boxes1);
            else {
                ArrayList<JunctionBox[]> newBoxes = new ArrayList<>();
                newBoxes.add(boxes1);
                map.put(distance, newBoxes);
            }

            boxes.add(current);
        }

        ArrayList<ArrayList<JunctionBox>> circuits = new ArrayList<>();
        Object[] distances = map.keySet().stream().sorted().toArray();

        int allowedBoxes;
        if (boxes.size() == 20) allowedBoxes = 10;
        else allowedBoxes = 1000;
        int addedBoxes = 0;
        int i = 0;

        while (addedBoxes < allowedBoxes) {
            ArrayList<JunctionBox[]> boxes1 = map.get(distances[i]);
            i++;
        }

        ArrayList<Integer> sizes = new ArrayList<>();
        for (ArrayList<JunctionBox> circuit : circuits) sizes.add(circuit.size());
        sizes.sort(Comparator.reverseOrder());

        return String.valueOf(sizes.get(0) * sizes.get(1) * sizes.get(2));
    }

    @Override
    public String part2(Scanner scanner) {
        return null;
    }
}