package com.cgvsu.rasterization;

import com.cgvsu.models.figure.triangle.Triangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

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

    public static void drawTriangle(
            final GraphicsContext graphicsContext,
            final int x1, final int y1, final int x2, final int y2, final int x3, final int y3,
            final Color c
    ) {
        PixelWriter pixelWriter = graphicsContext.getPixelWriter();
        Triangle triangle = Triangle.getTriangle(x1, y1, x2, y2, x3, y3);
        for (int y = triangle.getV1().y(); y <= triangle.getH().y(); y++) {
            for (int x = triangle.getUpperLeftBoundAtY(y); x <= triangle.getUpperRightBoundAtY(y); x++) {
                pixelWriter.setColor(x, y, c);
            }
        }
        for (int y = triangle.getH().y(); y <= triangle.getV3().y(); y++) {
            for (int x = triangle.getLowerLeftBoundAtY(y); x <= triangle.getLowerRightBoundAtY(y); x++) {
                pixelWriter.setColor(x, y, c);
            }
        }
    }

    public static void drawTriangle( // todo: move to separated class
            final GraphicsContext graphicsContext,
            final int x1, final int y1, final int x2, final int y2, final int x3, final int y3,
            final Color c1, final Color c2, final Color c3
    ) {
        PixelWriter pixelWriter = graphicsContext.getPixelWriter();
        Triangle triangle = Triangle.getTriangle(x1, y1, x2, y2, x3, y3);
        int auxYL = triangle.getV1().y();
        int auxYH = triangle.getH().y();
        for (int y = auxYL; y <= auxYH; y++) {
            int auxXL = triangle.getUpperLeftBoundAtY(y);
            int auxXR = triangle.getUpperRightBoundAtY(y);
            for (int x = auxXL; x <= auxXR; x++) {
                pixelWriter.setColor(x, y, triangle.getColor(c1, c2, c3, x, y));
            }
        }
        for (int y = triangle.getH().y(); y <= triangle.getV3().y(); y++) {
            for (int x = triangle.getLowerLeftBoundAtY(y); x <= triangle.getLowerRightBoundAtY(y); x++) {
                pixelWriter.setColor(x, y, triangle.getColor(c1, c2, c3, x, y));
            }
        }
    }
}
