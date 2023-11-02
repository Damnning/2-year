package ru.vsu.cs.shindows.forms;

import ru.vsu.cs.shindows.drawable.Shindows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenSaverPanel extends JPanel implements ActionListener {
    private final int x;
    private final int y;
    private final Timer timer;
    private final Shindows shindows;
    private final int winSize;
    private final int angle;


    public ScreenSaverPanel(int panelWidth, int panelHeight, int timerDelay) {
        angle = 15;
        winSize = 150;
        x = 0;
        y = 0;
        timer = new Timer(timerDelay, this);
        this.shindows = new Shindows(x, y, winSize, winSize, panelWidth, panelHeight, angle,2,2);
        timer.start();
    }

    @Override
    public void paint(final Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        shindows.draw(g);
        shindows.setPanelHeight(this.getHeight());
        this.getHeight();
        shindows.setPanelWidth(this.getWidth());
        this.getWidth();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
            shindows.move();
        }
    }
}
