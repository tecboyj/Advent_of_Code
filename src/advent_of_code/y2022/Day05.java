package advent_of_code.y2022;

import advent_of_code.Day;

import java.io.File;
import java.util.*;

public class Day05 extends Day {
    public Day05(int year, int day, boolean runTests) {
        super(year, day, runTests, "CMZ", 0);
    }

    public Stack<String> reverse(Stack<String> stack) {
        Stack<String> temp = new Stack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        return temp;
    }

    @Override
    public String part1(Scanner scanner) {
        Scanner stackScanner = null;
        if (this.test) stackScanner = makeScanner(new File("res/" + this.year + "/Day" + this.day + "/test1Stack.txt"));
        else stackScanner = makeScanner(new File("res/" + this.year + "/Day" + this.day + "/mainStack.txt"));

        String line = stackScanner.nextLine();
        int newStack = 0;
        while (stackScanner.hasNextLine()) {
            String nextLine = stackScanner.nextLine();
            if (nextLine.isEmpty()) newStack++;
            line += "\n" + nextLine;
        }
        Stack[] stacks = new Stack[newStack + 1];
        return null;
    }

    @Override
    public String part2(Scanner scanner) {
        return null;
    }
}