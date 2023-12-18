package com.cgvsu.models.figure;

public record Point(int x, int y) implements Comparable<Point> {

    @Override
    public int compareTo(final Point p) {
        if (y > p.y) return 1;
        else if (y < p.y) return -1;
        else {
            return Integer.compare(x, p.x);
        }
    }

    static public int getXAtLine(final Point p1, final Point p2, int y) {
        if (p1.y == p2.y) return p1.x;
        else return (y - p1.y) * (p2.x - p1.x) / (p2.y - p1.y) + p1.x;
    }

    public static Point minus(final Point p1, final Point p2) {
        return new Point(p1.x - p2.x, p1.y - p2.y);
    }

    public static int dot(final Point p1, final Point p2) {
        return p1.x * p2.x + p1.y * p2.y;
    }
}
