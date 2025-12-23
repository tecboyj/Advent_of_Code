package advent_of_code.y2025;

import advent_of_code.Day;

import java.util.*;

public class Day09 extends Day {
    public Day09(int year, int day, boolean runTests) {
        super(year, day, runTests, 50, 24);
    }

    @Override
    public String part1(Scanner scanner) {
        ArrayList<Point2D> tiles = new ArrayList<>();
        while (scanner.hasNextLine()) tiles.add(Point2D.createCoordinate2d(scanner.nextLine()));

        long maxArea = 0;
        for (int i = 0; i < tiles.size() - 1; i++) {
            for (int j = i + 1; j < tiles.size(); j++) {
                long area = tiles.get(i).areaBetween(tiles.get(j));
                if (area > maxArea) maxArea = area;
            }
        }

        return String.valueOf(maxArea);
    }

    public boolean intersections(ArrayList<Point2D[]> edges, Point2D tile1, Point2D tile2) {
        long maxX = Math.max(tile1.x(), tile2.x());
        long minX = Math.min(tile1.x(), tile2.x());
        long maxY = Math.max(tile1.y(), tile2.y());
        long minY = Math.min(tile1.y(), tile2.y());

        for (Point2D[] edge : edges) {
            long iMaxX = Math.max(edge[0].x(), edge[1].x());
            long iMinX = Math.min(edge[0].x(), edge[1].x());
            long iMaxY = Math.max(edge[0].y(), edge[1].y());
            long iMinY = Math.min(edge[0].y(), edge[1].y());

            if (minX < iMaxX && maxX > iMinX && minY < iMaxY && maxY > iMinY) return true;
        }
        return false;
    }

    @Override
    public String part2(Scanner scanner) {
        ArrayList<Point2D> tiles = new ArrayList<>();
        tiles.add(Point2D.createCoordinate2d(scanner.nextLine()));

        ArrayList<Point2D[]> edges = new ArrayList<>();

        long maxArea = 0;

        while (scanner.hasNextLine()) {
            Point2D previous = tiles.getLast();
            Point2D current = Point2D.createCoordinate2d(scanner.nextLine());
            edges.add(new Point2D[]{ previous, current });

//            long area = previous.areaBetween(current);
//            if (area > maxArea) maxArea = area;

            tiles.add(current);
        }

        edges.add(new Point2D[]{ tiles.getLast(), tiles.getFirst() });


        for (int i = 0; i < tiles.size() - 1; i++) {
            Point2D tile1 = tiles.get(i);
            for (int j = i + 1; j < tiles.size(); j++) {
                Point2D tile2 = tiles.get(j);
                long area = tile1.areaBetween(tile2);

                if (!intersections(edges, tile1, tile2) && area > maxArea) maxArea = area;
            }
        }

        return String.valueOf(maxArea);
    }
}