package advent_of_code.y2023;

import advent_of_code.Day;

import java.util.*;

public class Day08 extends Day {
    public Day08(int year, int day, boolean runTests) {
        super(year, day, runTests, 2, 6);
    }

    @Override
    public String part1(Scanner scanner) {
        ArrayList<String> instructions = new ArrayList<>(Arrays.asList(scanner.nextLine().split("")));
        scanner.nextLine();
        HashMap<String, String[]> map = new HashMap<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" = \\(");
            map.put(line[0], line[1].replace(")", "").split(", "));
        }

        int steps = 0;
        String current = "AAA";
        while (!current.equals("ZZZ")) {
            steps++;
            if (instructions.get(0).equals("L")) current = map.get(current)[0];
            else current = map.get(current)[1];
            instructions.add(instructions.remove(0));
        }

        return String.valueOf(steps);
    }

    public long findGCD(long a, long b) {
        if (b > a) {
            long temp = a;
            a = b;
            b = temp;
        }

        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    @Override
    public String part2(Scanner scanner) {
        ArrayList<String> instructions = new ArrayList<>(Arrays.asList(scanner.nextLine().split("")));
        scanner.nextLine();
        HashMap<String, String[]> map = new HashMap<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" = \\(");
            map.put(line[0], line[1].replace(")", "").split(", "));
        }

        ArrayList<String> start = new ArrayList<>();
        for (String key : map.keySet()) {
            if (key.endsWith("A")) start.add(key);
        }

        Stack<Node> nodes = new Stack<>();
        for (String key : start) {
            nodes.add(new Node((ArrayList<String>) instructions.clone(), (HashMap<String, String[]>) map.clone(), key));
            nodes.peek().start();
        }


        ArrayList<Integer> cycles = new ArrayList<>();
        for (Node node : nodes) {
            try {
                node.join();
                cycles.add(node.steps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long lcm = 1;
        for (int c : cycles) lcm = (lcm * c) / findGCD(lcm, c);

        return String.valueOf(lcm);
    }

    public static class Node extends Thread {
        private final ArrayList<String> instructions;
        private final HashMap<String, String[]> map;
        private String current;

        public int steps = 0;

        public Node(ArrayList<String> instructions, HashMap<String, String[]> map, String current) {
            this.instructions = instructions;
            this.map = map;
            this.current = current;
        }
        @Override
        public void run() {
            while (!current.endsWith("Z")) {
                steps++;
                if (instructions.get(0).equals("L")) current = map.get(current)[0];
                else current = map.get(current)[1];
                instructions.add(instructions.remove(0));
            }
        }
    }
}