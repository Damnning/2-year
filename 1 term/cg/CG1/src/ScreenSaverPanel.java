import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ScreenSaverPanel extends JPanel implements ActionListener {
    private int panelWidth;
    private int panelHeight;
    private final int TIMER_DELAY;
    private int x;
    private int xMult;
    private int yMult;
    private int y;
    private Timer timer;
    private Shindows shindows;
    private final int winSize;
    private final int angle;


    public ScreenSaverPanel(int panelWidth, int panelHeight, int timerDelay) {
        Random rnd = new Random();

        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        TIMER_DELAY = timerDelay;
        angle = 15;
        winSize = 150;
        System.out.println(Math.cos(angle));
        x = 0;
        y = 0;
        xMult = yMult = 1;
        timer = new Timer(timerDelay, this);
        this.shindows = new Shindows(x, y, winSize, winSize, panelWidth, panelHeight, angle,2,3);
        timer.start();
    }

    @Override
    public void paint(final Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        shindows.draw(g);
/*        gr.drawRect(5,5,this.getWidth()-10, this.getHeight()-10);
        gr.drawRect(x-shindows.getWinBoundLeft(),y - shindows.getWinBoundUp(),shindows.getWinBoundLeft()+shindows.getWinBoundRight(),shindows.getWinBoundUp()+shindows.getWinBoundDown());*/
        shindows.setPanelHeight(this.getHeight());
        panelHeight = this.getHeight();
        shindows.setPanelWidth(this.getWidth());
        panelWidth = this.getWidth();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
            shindows.move();
        }


    }
}
