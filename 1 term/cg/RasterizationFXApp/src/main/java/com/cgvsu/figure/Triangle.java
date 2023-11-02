package com.cgvsu.figure;

public class Triangle {
    private final Point v1;
    private final Point v2;
    private final Point v3;

    public Triangle(final Point v1, final Point v2, final Point v3) {
        this.v1 = Point.min(v1, Point.min(v2, v3));
        this.v2 = Point.max(Point.min(v1, v2), Point.min(v2, v3));
        this.v3 = Point.max(v1, Point.max(v2, v3));
    }

    public Triangle(final int x1, final int y1, final int x2, final int y2, final int x3, final int y3) {
        this(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3));
    }

    public int getLeftBoundAtY(int y) {
        if(v2.x() < v3.x()){
            if(y <= v2.y())
                return Point.getXAtLine(v1, v2, y);
            else
                return Point.getXAtLine(v2, v3, y);
        }
    }
}
