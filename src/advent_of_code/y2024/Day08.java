package advent_of_code.y2024;

import advent_of_code.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day08 extends Day {
    public Day08(int year, int day, boolean runTests) {
        super(year, day, runTests, 14, 34);
    }

    public boolean doTheThing1(ArrayList<String[]> lines, ArrayList<int[]> found, int y, int x) {
        try {
            String value = lines.get(y)[x];
            if (value.equals(".")) lines.get(y)[x] = "#";
            if (!contains(found, y, x)) {
                found.add(new int[]{ y, x });
                return true;
            }
        } catch (IndexOutOfBoundsException ignored) {}
        return false;
    }

    @Override
    public String part1(Scanner scanner) {
        ArrayList<String[]> lines = new ArrayList<>();
        ArrayList<int[]> points = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher matcher = Pattern.compile("([^.])").matcher(line);
            while (matcher.find()) {
                int index = matcher.start();
                points.add(new int[]{lines.size(), index});
            }
            lines.add(line.split(""));
        }

        ArrayList<int[]> found = new ArrayList<>();
        int sum = 0;

        for (int i = 0; i < points.size(); i++) {
            int y1 = points.get(i)[0];
            int x1 = points.get(i)[1];
            String antenna = lines.get(y1)[x1];
            if (Objects.equals(antenna, ".")) throw new Error();
            for (int j = i + 1; j < points.size(); j++) {
                int y2 = points.get(j)[0];
                int x2 = points.get(j)[1];
                if (!antenna.equals(lines.get(y2)[x2])) continue;

                int dx = Math.abs(x1 - x2);
                int dy = Math.abs(y1 - y2);
                if (y2 < y1) throw new Error();

                if (x1 < x2) {
                    if (doTheThing1(lines, found, y1 - dy, x1 - dx)) sum++;
                    if (doTheThing1(lines, found, y2 + dy, x2 + dx)) sum++;
                } else {
                    if (doTheThing1(lines, found, y1 - dy, x1 + dx)) sum++;
                    if (doTheThing1(lines, found, y2 + dy, x2 - dx)) sum++;
                }
            }
        }

        return String.valueOf(sum);
    }

    public boolean doTheThing2(ArrayList<String[]> lines, int y, int x) {
        try {
            String value = lines.get(y)[x];
            if (value.equals(".")) lines.get(y)[x] = "#";
            return true;
        } catch (IndexOutOfBoundsException ignored) {}
        return false;
    }

    @Override
    public String part2(Scanner scanner) {
        ArrayList<String[]> lines = new ArrayList<>();
        ArrayList<int[]> points = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher matcher = Pattern.compile("([^.])").matcher(line);
            while (matcher.find()) {
                int index = matcher.start();
                points.add(new int[]{lines.size(), index});
            }
            lines.add(line.split(""));
        }

        ArrayList<int[]> found = new ArrayList<>();
        int sum = 0;

        for (int i = 0; i < points.size(); i++) {
            int y1 = points.get(i)[0];
            int x1 = points.get(i)[1];
            String antenna = lines.get(y1)[x1];
            if (Objects.equals(antenna, ".")) throw new Error();
            for (int j = i + 1; j < points.size(); j++) {
                int y2 = points.get(j)[0];
                int x2 = points.get(j)[1];
                if (!antenna.equals(lines.get(y2)[x2])) continue;

                int dx = Math.abs(x1 - x2);
                int dy = Math.abs(y1 - y2);
                if (y2 < y1) throw new Error();

                if (x1 < x2) {
                    int y = y1;
                    int x = x1;
                    while (doTheThing2(lines, y, x)) {
                        if (!contains(found, y, x)) {
                            sum++;
                            found.add(new int[]{y, x});
                        }
                        y -= dy;
                        x -= dx;
                    }

                    y = y2;
                    x = x2;
                    while (doTheThing2(lines, y, x)) {
                        if (!contains(found, y, x)) {
                            sum++;
                            found.add(new int[]{y, x});
                        }
                        y += dy;
                        x += dx;
                    }
                } else {
                    int y = y1;
                    int x = x1;
                    while (doTheThing2(lines, y, x)) {
                        if (!contains(found, y, x)) {
                            sum++;
                            found.add(new int[]{y, x});
                        }
                        y -= dy;
                        x += dx;
                    }

                    y = y2;
                    x = x2;
                    while (doTheThing2(lines, y, x)) {
                        if (!contains(found, y, x)) {
                            sum++;
                            found.add(new int[]{y, x});
                        }
                        y += dy;
                        x -= dx;
                    }
                }
            }
        }

        return String.valueOf(sum);
    }
}