package com.cgvsu.figure;

public record Point(int x, int y) implements Comparable<Point> {

    @Override
    public int compareTo(Point p) {
        if (y > p.y) return 1;
        else if (y < p.y) return -1;
        else {
            if (x > p.x) return 1;
            else if (x < p.x) return -1;
            else return 0;
        }
    }

    static public int getXAtLine(Point p1, Point p2, int y) {
        return (y - p1.y) * (p2.x - p1.x) / (p2.y - p1.y) + p1.x;
    }

    static public Point max(Point p1, Point p2) {
        if (p1.compareTo(p2) >= 0) return p1;
        else return p2;
    }

    static public Point min(Point p1, Point p2) {
        if (p1.compareTo(p2) <= 0) return p1;
        else return p2;
    }
}
