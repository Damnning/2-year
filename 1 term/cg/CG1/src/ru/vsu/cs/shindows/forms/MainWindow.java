package ru.vsu.cs.shindows.forms;

import javax.swing.*;

public class MainWindow extends JFrame {
    private final ScreenSaverPanel panel;
    public MainWindow() {
        panel = new ScreenSaverPanel(this.getWidth(), this.getHeight(), 1);
        this.add(panel);

    }
}
