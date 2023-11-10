package com.cgvsu.rasterizationfxapp;

import com.cgvsu.figure.Point;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;

import com.cgvsu.rasterization.*;
import javafx.scene.paint.Color;

import java.util.Random;

public class RasterizationController {

    boolean changeColor;
    int x1, y1, x2, y2, x3, y3;
    Color c1, c2, c3;
    int vertexRange = 20;
    double colorRange = 50;
    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        canvas.setFocusTraversable(true);
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::handleMousePressed);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, this::handleMouseReleased);
        canvas.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::handleMouseDragged);
        canvas.addEventHandler(ScrollEvent.SCROLL, this::handleScroll);
        changeColor = false;
        x1 = 100;
        y1 = 100;
        x2 = 300;
        y2 = 100;
        x3 = 200;
        y3 = 300;
        c1 = Color.RED;
        c2 = Color.GREEN;
        c3 = Color.BLUE;
        Rasterization.drawTriangle(canvas.getGraphicsContext2D(), x1, y1, x2, y2, x3, y3, c1, c2, c3);

    }

    private void handleScroll(ScrollEvent event) {
        if(changeColor){

        }
    }

    private void handleMouseReleased(MouseEvent event) {
        changeColor = false;
    }

    private void handleMousePressed(MouseEvent event) {
        if (event.getButton().equals(MouseButton.SECONDARY)) {
            changeColor = true;
        }
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.R))
            drawRandomTriangle();
    }

    private void handleMouseDragged(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (checkRange(event.getX(), event.getY(), x1, y1)) {
                redraw((int) event.getX(), (int) event.getY(), x2, y2, x3, y3, c1, c2, c3);
            }
            if (checkRange(event.getX(), event.getY(), x2, y2)) {
                redraw(x1, y1, (int) event.getX(), (int) event.getY(), x3, y3, c1, c2, c3);
            }
            if (checkRange(event.getX(), event.getY(), x3, y3)) {
                redraw(x1, y1, x2, y2, (int) event.getX(), (int) event.getY(), c1, c2, c3);
            }
        } else if (event.getButton().equals(MouseButton.SECONDARY) && changeColor) {
            if (checkRange(event.getX(), event.getY(), x1, y1)) {
                Color nc = changeColor(event.getX(), event.getY(), x1, y1, c1);
                redraw(x1, y1, x2, y2, x3, y3, nc, c2, c3);
            }
            if (checkRange(event.getX(), event.getY(), x2, y2)) {
                Color nc = changeColor(event.getX(), event.getY(), x2, y2, c2);
                redraw(x1, y1, x2, y2, x3, y3, c1, nc, c3);
            }
            if (checkRange(event.getX(), event.getY(), x3, y3)) {
                Color nc = changeColor(event.getX(), event.getY(), x3, y3, c3);
                redraw(x1, y1, x2, y2, x3, y3, c1, c2, nc);
            }
        }
    }
    private boolean checkRange(double x, double y, int xt, int yt){
        return (Math.abs(x - xt) < vertexRange) && (Math.abs(y - yt) < vertexRange);
    }
    private Color changeColor(double x, double y, int xc, int yc, Color c){
        double cx = (x - xc + colorRange) / (colorRange * 2);
        if(cx > 1) cx = 1;
        else if(cx < 0) cx = 0;
        double cy = (y - yc + colorRange) / (colorRange * 2);
        if(cy > 1) cy = 1;
        else if(cy < 0) cy = 0;
        return new Color(cx, cy, c.getBlue(), 1);
    }


    private void redraw(final int x1, final int y1, final int x2, final int y2, final int x3, final int y3, final Color c1, final Color c2, final Color c3) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        Rasterization.drawTriangle(gc, x1, y1, x2, y2, x3, y3, c1, c2, c3);

    }

    private boolean checkDistance(final int x1, final int y1, final int x2, final int y2) {
        return Point.distance(new Point(x1, y1), new Point(x2, y2)) > 2 * vertexRange;
    }


    private void drawRandomTriangle() {
        Random rnd = new Random();
        redraw(rnd.nextInt(0, (int) canvas.getWidth()), rnd.nextInt(0, (int) canvas.getHeight()), rnd.nextInt(0, (int) canvas.getWidth()), rnd.nextInt(0, (int) canvas.getHeight()), rnd.nextInt(0, (int) canvas.getWidth()), rnd.nextInt(0, (int) canvas.getHeight()), c1, c2, c3);
    }


}