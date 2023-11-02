package ru.vsu.cs.shindows.drawable;

import java.awt.*;
import java.awt.geom.*;

public class Shindows {
    private enum MODE {
        CONVEX,
        CONCAVE
    }
    final private Color ShindowsRed = new Color(248, 104, 40);
    final private Color ShindowsGreen = new Color(146, 196, 0);
    final private Color ShindowsBlue = new Color(0, 181, 241);
    final private Color ShindowsYellow = new Color(255, 196, 0);

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    private int x;
    private int y;
    private int width;
    private int height;

    public int getWinBoundUp() {
        return winBoundUp;
    }

    public int getWinBoundLeft() {
        return winBoundLeft;
    }

    public int getWinBoundDown() {
        return winBoundDown;
    }

    public int getWinBoundRight() {
        return winBoundRight;
    }

    private final int winBoundUp;
    private final int winBoundLeft;
    private final int winBoundDown;
    private final int winBoundRight;

    public void setPanelWidth(int panelWidth) {
        this.panelWidth = panelWidth;
    }

    public void setPanelHeight(int panelHeight) {
        this.panelHeight = panelHeight;
    }

    private int panelWidth;
    private int panelHeight;
    private final double angle;
    private int speedX;
    private int speedY;

    public Shindows(final int x, final int y, final int width, final int height, final int panelWidth, final int panelHeight, final int angle, final int speedX, final int speedY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.width = width;
        this.height = height;
        this.panelHeight = panelHeight;
        this.panelWidth = panelWidth;
        this.angle = angle;
        double d = (width * Math.sin(Math.toRadians(angle)) + height * Math.cos(Math.toRadians(angle)) - width) / 2;
        winBoundUp = (int) d - height / 12;
        winBoundLeft = (int) d;
        winBoundDown = (int) (height + d - height / 6);
        winBoundRight = (int) (width + d);
    }

    public void move() {
        if (x > panelWidth - getWinBoundRight()) {
            speedX = -speedX;
            x = panelWidth - getWinBoundRight();
        }
        if (x < getWinBoundLeft()) {
            speedX = -speedX;
            x = getWinBoundLeft();
        }

        if (y > panelHeight - getWinBoundDown()) {
            speedY = -speedY;
            y = panelHeight - getWinBoundDown();
        }
        if (y < getWinBoundUp()) {
            speedY = -speedY;
            y = getWinBoundUp();
        }
        x += speedX;
        y += speedY;
    }

    public void draw(final Graphics2D g) {
        AffineTransform straight = g.getTransform();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, panelWidth, panelHeight);
        g.rotate(Math.toRadians(angle), x + width / 2, y + height / 2);
        g.setColor(ShindowsRed);
        g.fill(getFlag(x, y + height / 50, width / 2 - width / 20, height / 2 - height / 20, MODE.CONCAVE));
        g.setColor(ShindowsGreen);
        g.fill(getFlag(x + width / 2 + width / 20, y - height / 50, width / 2 - width / 20, height / 2 - height / 20, MODE.CONVEX));
        g.setColor(ShindowsBlue);
        g.fill(getFlag(x, y + height / 50 + height / 2 - height / 16, width / 2 - width / 20, height / 2 - height / 20, MODE.CONCAVE));
        g.setColor(ShindowsYellow);
        g.fill(getFlag(x + width / 2 + width / 20, y - height / 50 + height / 2 - height / 16, width / 2 - width / 20, height / 2 - height / 20, MODE.CONVEX));
        g.setTransform(straight);
    }

    private Area getFlag(final int x, final int y, final int width, final int height, final MODE mode) {
        Area flag = new Area();
        Area upPart = new Area(new Rectangle(x, y, width, height / 2));
        Area downPart = new Area(new Rectangle(x, y + height / 2, width, height / 2));
        if (mode == MODE.CONVEX) {
            Area upSub = new Area(new Ellipse2D.Double(x - width + width / 3, y - height - height / 4 * 3, width * 2, height * 2));
            Area downInt = new Area(new Ellipse2D.Double(x - width + width / 3, y - height, width * 2, height * 2));
            flag.add(downPart);
            flag.intersect(downInt);
            flag.add(upPart);
            flag.subtract(upSub);
        } else if (mode == MODE.CONCAVE) {
            Area upInt = new Area(new Ellipse2D.Double(x - width / 3, y, width * 2, height * 2));
            Area downSub = new Area(new Ellipse2D.Double(x - width / 3, y + height / 4 * 3, width * 2, height * 2));
            flag.add(upPart);
            flag.intersect(upInt);
            flag.add(downPart);
            flag.subtract(downSub);
        }
        return flag;
    }

}
