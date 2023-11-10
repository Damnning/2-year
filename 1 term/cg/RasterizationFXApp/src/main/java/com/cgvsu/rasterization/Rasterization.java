package com.cgvsu.rasterization;

import com.cgvsu.figure.Point;
import com.cgvsu.figure.Triangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.util.Random;

public class Rasterization {

    public static void drawRectangle(
            final GraphicsContext graphicsContext,
            final int x, final int y,
            final int width, final int height,
            final Color color) {
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

        for (int row = y; row < y + height; ++row)
            for (int col = x; col < x + width; ++col)
                pixelWriter.setColor(col, row, color);
    }

    public static void drawRandRect(
            final GraphicsContext graphicsContext,
            final int x, final int y,
            final int width, final int height,
            final Color color) {
        Random rnd = new Random();
        graphicsContext.setFill(color);
        recDrawRandRect(graphicsContext, x, width, y, y + height, rnd);
    }

    private static void recDrawRandRect(
            final GraphicsContext graphicsContext,
            final int x,
            final int width, final int yUp, final int yDown,
            Random rnd) {
        int nYF = rnd.nextInt(yUp, yDown);
        int nYS = rnd.nextInt(yUp, yDown);
        int max = Math.max(nYS, nYS);
        int min = Math.min(nYF, nYS);

        graphicsContext.strokeLine(x + rnd.nextInt(0, width), min, x + rnd.nextInt(0, width), max);
        if (min - 1 > yUp)
            recDrawRandRect(graphicsContext, x, width, yUp, min - 1, rnd);
        if ((max - 1) - (min + 1) > 0)
            recDrawRandRect(graphicsContext, x, width, min + 1, max - 1, rnd);
        if (yDown - (max + 1) > 0)
            recDrawRandRect(graphicsContext, x, width, max + 1, yDown, rnd);

    }
    public static void drawTriangle(
            final GraphicsContext graphicsContext,
            final int x1, final int y1, final int x2, final int y2, final int x3, final int y3,
            final Color c
    ){
        PixelWriter pixelWriter = graphicsContext.getPixelWriter();
        Triangle triangle = new Triangle(x1, y1, x2, y2, x3, y3);
        for (int y = triangle.getV1().y(); y <= triangle.getV3().y(); y++) {
            for (int x = triangle.getLeftBoundAtY(y); x <= triangle.getRightBoundAtY(y); x++) {
                pixelWriter.setColor(x, y, c);
            }
        }
    }

    public static void drawTriangle(
            final GraphicsContext graphicsContext,
            final int x1, final int y1, final int x2, final int y2, final int x3, final int y3,
            final Color c1, final Color c2, final Color c3
    ) {
        PixelWriter pixelWriter = graphicsContext.getPixelWriter();
        Triangle triangle = new Triangle(x1, y1, x2, y2, x3, y3);
        for (int y = triangle.getV1().y(); y <= triangle.getV3().y(); y++) {
            for (int x = triangle.getLeftBoundAtY(y); x <= triangle.getRightBoundAtY(y); x++) {
                pixelWriter.setColor(x, y, triangle.getColor(c1, c2, c3, x, y));
            }
        }
    }
}
