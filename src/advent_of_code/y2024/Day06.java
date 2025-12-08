package advent_of_code.y2024;

import advent_of_code.Day;

import java.util.*;

public class Day06 extends Day {
    public Day06(int year, int day, boolean runTests) {
        super(year, day, runTests, 41, 6);
    }

    @Override
    public String part1(Scanner scanner) {
        int y = 0;
        int x = 0;
        ArrayList<String[]> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.contains("^")) {
                y = lines.size();
                x = line.indexOf("^");
            }

            lines.add(line.split(""));
        }

        int sum = 1;

        while (true) {
            String guard = lines.get(y)[x];
            if (guard.equals("^")) {
                if (y == 0) break;
                String nextPos = lines.get(y - 1)[x];
                if (nextPos.equals("#")) {
                    lines.get(y)[x] = ">";
                    continue;
                }
                if (!nextPos.equals("X")) sum++;
                lines.get(y)[x] = "X";
                lines.get(y - 1)[x] = "^";
                y--;
            }
            if (guard.equals(">")) {
                if (x == lines.get(y).length - 1) break;
                String nextPos = lines.get(y)[x + 1];
                if (nextPos.equals("#")) {
                    lines.get(y)[x] = "v";
                    continue;
                }
                if (!nextPos.equals("X")) sum++;
                lines.get(y)[x] = "X";
                lines.get(y)[x + 1] = ">";
                x++;
            }
            if (guard.equals("v")) {
                if (y == lines.size() - 1) break;
                String nextPos = lines.get(y + 1)[x];
                if (nextPos.equals("#")) {
                    lines.get(y)[x] = "<";
                    continue;
                }
                if (!nextPos.equals("X")) sum++;
                lines.get(y)[x] = "X";
                lines.get(y + 1)[x] = "v";
                y++;
            }
            if (guard.equals("<")) {
                if (x == 0) break;
                String nextPos = lines.get(y)[x - 1];
                if (nextPos.equals("#")) {
                    lines.get(y)[x] = "^";
                    continue;
                }
                if (!nextPos.equals("X")) sum++;
                lines.get(y)[x] = "X";
                lines.get(y)[x - 1] = "<";
                x--;
            }
        }

        return String.valueOf(sum);
    }

    public ArrayList<String[]> copy(ArrayList<String[]> lines) {
        ArrayList<String[]> res = new ArrayList<>();
        for (String[] line : lines) res.add(Arrays.copyOf(line, line.length));
        return res;
    }

    @Override
    public String part2(Scanner scanner) {
        int y = 0;
        int yInit = 0;
        int x = 0;
        int xInit = 0;

        ArrayList<String[]> lines = new ArrayList<>();
        ArrayList<String[]> ogLines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.contains("^")) {
                y = lines.size();
                yInit = lines.size();
                x = line.indexOf("^");
                xInit = line.indexOf("^");
            }

            lines.add(line.split(""));
            ogLines.add(line.split(""));
        }

        ArrayList<int[]> points = new ArrayList<>();

        while (true) {
            String guard = lines.get(y)[x];
            if (guard.equals("^")) {
                if (y == 0) break;
                String nextPos = lines.get(y - 1)[x];
                if (nextPos.equals("#")) {
                    lines.get(y)[x] = ">";
                    continue;
                }
                lines.get(y)[x] = ".";
                lines.get(y - 1)[x] = "^";
                y--;
                if (!contains(points, y, x) && !(y == yInit && x == xInit)) points.add(new int[]{y, x});
            }
            if (guard.equals(">")) {
                if (x == lines.get(y).length - 1) break;
                String nextPos = lines.get(y)[x + 1];
                if (nextPos.equals("#")) {
                    lines.get(y)[x] = "v";
                    continue;
                }
                lines.get(y)[x] = ".";
                lines.get(y)[x + 1] = ">";
                x++;
                if (!contains(points, y, x) && !(y == yInit && x == xInit)) points.add(new int[]{y, x});
            }
            if (guard.equals("v")) {
                if (y == lines.size() - 1) break;
                String nextPos = lines.get(y + 1)[x];
                if (nextPos.equals("#")) {
                    lines.get(y)[x] = "<";
                    continue;
                }
                lines.get(y)[x] = ".";
                lines.get(y + 1)[x] = "v";
                y++;
                if (!contains(points, y, x) && !(y == yInit && x == xInit)) points.add(new int[]{y, x});
            }
            if (guard.equals("<")) {
                if (x == 0) break;
                String nextPos = lines.get(y)[x - 1];
                if (nextPos.equals("#")) {
                    lines.get(y)[x] = "^";
                    continue;
                }
                lines.get(y)[x] = ".";
                lines.get(y)[x - 1] = "<";
                x--;
                if (!contains(points, y, x) && !(y == yInit && x == xInit)) points.add(new int[]{y, x});
            }
        }

        int sum = 0;
        for (int[] point : points) {
            ArrayList<String[]> res = copy(ogLines);
            res.get(point[0])[point[1]] = "#";
            if (isLoop(res, yInit, xInit)) sum++;
        }

        return String.valueOf(sum);
    }

    public boolean isLoop(ArrayList<String[]> lines, int y, int x) {
        ArrayList<int[]> seenUp = new ArrayList<>();
        ArrayList<int[]> seenRight = new ArrayList<>();
        ArrayList<int[]> seenDown = new ArrayList<>();
        ArrayList<int[]> seenLeft = new ArrayList<>();

        while (true) {
            String guard = lines.get(y)[x];
            if (guard.equals("^")) {
                if (y == 0) break;
                String nextPos = lines.get(y - 1)[x];
                if (nextPos.equals("#")) {
                    lines.get(y)[x] = ">";
                    continue;
                }

                if (contains(seenUp, y, x)) return true;
                seenUp.add(new int[]{y, x});

                lines.get(y)[x] = ".";
                lines.get(y - 1)[x] = "^";
                y--;
            }
            if (guard.equals(">")) {
                if (x == lines.get(y).length - 1) break;
                String nextPos = lines.get(y)[x + 1];
                if (nextPos.equals("#")) {
                    lines.get(y)[x] = "v";
                    continue;
                }

                if (contains(seenRight, y, x)) return true;
                seenRight.add(new int[]{y, x});

                lines.get(y)[x] = ".";
                lines.get(y)[x + 1] = ">";
                x++;
            }
            if (guard.equals("v")) {
                if (y == lines.size() - 1) break;
                String nextPos = lines.get(y + 1)[x];
                if (nextPos.equals("#")) {
                    lines.get(y)[x] = "<";
                    continue;
                }

                if (contains(seenDown, y, x)) return true;
                seenDown.add(new int[]{y, x});

                lines.get(y)[x] = ".";
                lines.get(y + 1)[x] = "v";
                y++;
            }
            if (guard.equals("<")) {
                if (x == 0) break;
                String nextPos = lines.get(y)[x - 1];
                if (nextPos.equals("#")) {
                    lines.get(y)[x] = "^";
                    continue;
                }

                if (contains(seenLeft, y, x)) return true;
                seenLeft.add(new int[]{y, x});

                lines.get(y)[x] = ".";
                lines.get(y)[x - 1] = "<";
                x--;
            }
        }

        return false;
    }
}