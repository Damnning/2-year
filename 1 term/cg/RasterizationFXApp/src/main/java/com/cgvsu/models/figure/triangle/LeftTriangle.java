package com.cgvsu.models.figure.triangle;

import com.cgvsu.models.figure.Point;
import com.cgvsu.models.figure.triangle.Triangle;

public class LeftTriangle extends Triangle { // Triangle counts as left if x coord of vertex v2 is less than x coord of vertex h
    public LeftTriangle(Point v1, Point v2, Point v3) {
        super(v1, v2, v3);
    }

    @Override
    public int getUpperLeftBoundAtY(int y) {
        return Point.getXAtLine(getV1(), getV2(), y);
    }

    @Override
    public int getUpperRightBoundAtY(int y) {
        return Point.getXAtLine(getV3(), getV1(), y);
    }

    @Override
    public int getLowerLeftBoundAtY(int y) {
        return Point.getXAtLine(getV2(), getV3(), y);
    }

    @Override
    public int getLowerRightBoundAtY(int y) {
        return Point.getXAtLine(getV3(), getV1(), y);
    }

}
