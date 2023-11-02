package com.cgvsu.rasterization;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import java.util.Random;

public class Rasterization {

    public static void drawRectangle(
            final GraphicsContext graphicsContext,
            final int x, final int y,
            final int width, final int height,
            final Color color)
    {
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

        for (int row = y; row < y + height; ++row)
            for (int col = x; col < x + width; ++col)
                pixelWriter.setColor(col, row, color);
    }
    public static void drawRandRect(
            final GraphicsContext graphicsContext,
            final int x, final int y,
            final int width, final int height,
            final Color color){
        Random rnd = new Random();
        graphicsContext.setFill(color);
        recDrawRandRect(graphicsContext, x, width, y, y + height, rnd);
    }
    private static void recDrawRandRect(
            final GraphicsContext graphicsContext,
            final int x,
            final int width, final int yUp, final int yDown,
             Random rnd){
        int nYF = rnd.nextInt(yUp, yDown);
        int nYS = rnd.nextInt(yUp, yDown);
        int max = Math.max(nYS,nYS);
        int min = Math.min(nYF, nYS);

        graphicsContext.strokeLine(x+rnd.nextInt(0,width),min,x+ rnd.nextInt(0,width), max);
        if(min - 1 > yUp)
            recDrawRandRect(graphicsContext, x, width, yUp, min - 1, rnd);
        if ((max-1) - (min+1) > 0)
            recDrawRandRect(graphicsContext, x, width, min + 1,max-1,rnd);
        if(yDown - (max+1) > 0)
            recDrawRandRect(graphicsContext, x, width, max + 1, yDown, rnd);

    }
    public static void drawTriangle(
            final GraphicsContext graphicsContext,
            final int v1, final int v2, final int v3,
            final Color c1, final Color c2, final Color c3
    ){
        
    }
}
