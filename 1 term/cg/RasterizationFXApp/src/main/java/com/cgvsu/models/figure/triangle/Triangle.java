package com.cgvsu.models.figure.triangle;

import com.cgvsu.models.figure.Point;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.stream.Stream;

public abstract class Triangle {
    private final Point v1;
    private final Point v2;
    private final Point v3;
    private final Point h;
    private boolean isLeft;

    public Point getV1() {
        return v1;
    }
    public Point getV2() {
        return v2;
    }
    public Point getH() {
        return h;
    }
    public Point getV3() {
        return v3;
    }

    public static Triangle getTriangle(final Point v1, final Point v2, final Point v3) {
        if(v2.x() < Point.getXAtLine(v1, v3, v2.y()))
            return new LeftTriangle(v1, v2, v3);
        else
            return new RightTriangle(v1, v3, v2);
    }
    public static Triangle getTriangle(final int x1, final int y1, final int x2, final int y2, final int x3, final int y3) {
        return getTriangle(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3));
    }
    protected Triangle(final Point v1, final Point v2, final Point v3) {
        List<Point> points = Stream.of(v1, v2, v3).sorted().toList();
        this.v1 = points.get(0);
        this.v2 = points.get(1);
        this.v3 = points.get(2);
        h = new Point(Point.getXAtLine(this.v1, this.v3, this.v2.y()), this.v2.y());
        isLeft = v2.x() < h.x();
    }

    protected Triangle(final int x1, final int y1, final int x2, final int y2, final int x3, final int y3) {
        this(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3));
    }

    public abstract int getUpperLeftBoundAtY(final int y);
    public abstract int getUpperRightBoundAtY(final int y);
    public abstract int getLowerLeftBoundAtY(final int y);
    public abstract int getLowerRightBoundAtY(final int y);
/*
    public int getLeftBoundAtY(final int y) { //todo: divide in two methods for upper triangle and lower triangle
        if (v2.x() < h.x()) {
            if (y <= h.y())
                return Point.getXAtLine(v1, v2, y);
            else
                return Point.getXAtLine(v2, v3, y);
        } else {
            return Point.getXAtLine(v1, v3, y);
        }
    }
    public int getUpperLeftBoundAtY(final int y) {
        if(isLeft)
            return Point.getXAtLine(v1, v2, y);
        else
            return Point.getXAtLine(v1, v3, y);
    }
    public int getUpperRightBoundAtY(final int y) {
        if(isLeft)
            return Point.getXAtLine(v3, v1, y);
        else
            return Point.getXAtLine(v2, v1, y);
    }
    public int getLowerLeftBoundAtY(final int y) {
        if(isLeft)
            return Point.getXAtLine(v2, v3, y);
        else
            return Point.getXAtLine(v1, v3, y);
    }
    public int getLowerRightBoundAtY(final int y) {
        if(isLeft)
            return Point.getXAtLine(v3, v1, y);
        else
            return Point.getXAtLine(v3, v2, y);
    }

    public int getRightBoundAtY(final int y) {
        if (v2.x() <= h.x())
            return Point.getXAtLine(v3, v1, y);
        else {
            if (y <= h.y())
                return Point.getXAtLine(v2, v1, y);
            else
                return Point.getXAtLine(v3, v2, y);
        }
    }*/
    public Color getColor(final Color c1, final Color c2, final Color c3, final int x, final int y) { //todo: take out method for getting barycentric coords
        Point p = new Point(x,y);
        Point p0 = Point.minus(v2,v1);
        Point p1 = Point.minus(v3,v1);
        Point p2 = Point.minus(p,v1);
        double d00 = Point.dot(p0,p0);
        double d01 = Point.dot(p0,p1);
        double d11 = Point.dot(p1,p1);
        double d20 = Point.dot(p2,p0);
        double d21 = Point.dot(p2,p1);
        double delta = d01*d01 - d00*d11;
        double alpha = Math.abs((d21*d01-d20*d11)/delta)*0.9;
        double beta = Math.abs((d01*d20-d00*d21)/delta)*0.9;
        double gamma = (1-alpha-beta)*0.9;
        double r = c1.getRed() * alpha + c2.getRed() * beta + c3.getRed() * gamma;
        if(r >= 1) r = 1;
        else if(r <= 0) r = 0;
        double g = c1.getGreen() * alpha + c2.getGreen() * beta + c3.getGreen() * gamma;
        if(g >= 1) g = 1;
        else if(g <= 0) g = 0;
        double b = c1.getBlue() * alpha + c2.getBlue() * beta + c3.getBlue() * gamma;
        if(b >= 1) b = 1;
        else if(b <= 0) b = 0;
        return new Color(r,g,b,1);
    }
}
