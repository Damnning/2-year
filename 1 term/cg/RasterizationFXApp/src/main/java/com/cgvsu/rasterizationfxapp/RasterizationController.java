package com.cgvsu.rasterizationfxapp;

import com.cgvsu.models.color.Channel;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;

import com.cgvsu.rasterization.*;
import javafx.scene.paint.Color;

import java.util.Random;

public class RasterizationController {

    Channel mode;
    int x1, y1, x2, y2, x3, y3;
    Color color1, color2, color3;
    final int vertexRange = 20;

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        canvas.setFocusTraversable(true);
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));
        canvas.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::handleMouseDragged);
        x1 = 100;
        y1 = 100;
        x2 = 300;
        y2 = 100;
        x3 = 200;
        y3 = 300;
        color1 = Color.RED;
        color2 = Color.GREEN;
        color3 = Color.BLUE;
        Rasterization.drawTriangle(canvas.getGraphicsContext2D(), x1, y1, x2, y2, x3, y3, color1, color2, color3);
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.R))
            drawRandomTriangle();
        changeMode(keyEvent);
    }

    private void changeMode(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.DIGIT1))
            mode = Channel.RED;
        if (keyEvent.getCode().equals(KeyCode.DIGIT2))
            mode = Channel.GREEN;
        if (keyEvent.getCode().equals(KeyCode.DIGIT3))
            mode = Channel.BLUE;
    }

    private void handleMouseDragged(MouseEvent event) {
        int mouseX = (int) event.getX();
        int mouseY = (int) event.getY();
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (checkRange(mouseX, mouseY, x1, y1)) {
                redraw(mouseX, mouseY, x2, y2, x3, y3, color1, color2, color3);
            }
            if (checkRange(mouseX, mouseY, x2, y2)) {
                redraw(x1, y1, mouseX, mouseY, x3, y3, color1, color2, color3);
            }
            if (checkRange(mouseX, mouseY, x3, y3)) {
                redraw(x1, y1, x2, y2, mouseX, mouseY, color1, color2, color3);
            }
        } else if (event.getButton().equals(MouseButton.SECONDARY)) {
            if (checkRange(mouseX, mouseY, x1, y1)) {
                Color nc = changeColor(mouseX, x1, color1, mode);
                redraw(x1, y1, x2, y2, x3, y3, nc, color2, color3);
            }
            if (checkRange(mouseX, mouseY, x2, y2)) {
                Color nc = changeColor(mouseX, x2, color2, mode);
                redraw(x1, y1, x2, y2, x3, y3, color1, nc, color3);
            }
            if (checkRange(mouseX, mouseY, x3, y3)) {
                Color nc = changeColor(mouseX, x3, color3, mode);
                redraw(x1, y1, x2, y2, x3, y3, color1, color2, nc);
            }
        }
    }


    private boolean checkRange(int x, int y, int xt, int yt) {
        return (Math.abs(x - xt) < vertexRange) && (Math.abs(y - yt) < vertexRange);
    }

    private Color changeColor(double x, int xc, Color c, Channel channel) {
        double cx = (x - xc + vertexRange) / (vertexRange * 2);
        if (cx > 1) cx = 1;
        else if (cx < 0) cx = 0;
        if (channel == Channel.RED)
            return new Color(cx, c.getGreen(), c.getBlue(), 1);
        else if (channel == Channel.GREEN)
            return new Color(c.getRed(), cx, c.getBlue(), 1);
        else
            return new Color(c.getRed(), c.getGreen(), cx, 1);
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
        this.color1 = c1;
        this.color2 = c2;
        this.color3 = c3;
        Rasterization.drawTriangle(gc, x1, y1, x2, y2, x3, y3, c1, c2, c3);
    }

    private void drawRandomTriangle() {
        Random rnd = new Random();
        redraw(rnd.nextInt(0, (int) canvas.getWidth()), rnd.nextInt(0, (int) canvas.getHeight()), rnd.nextInt(0, (int) canvas.getWidth()), rnd.nextInt(0, (int) canvas.getHeight()), rnd.nextInt(0, (int) canvas.getWidth()), rnd.nextInt(0, (int) canvas.getHeight()), color1, color2, color3);
    }
}