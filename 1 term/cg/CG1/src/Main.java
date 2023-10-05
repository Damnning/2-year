import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setSize((int) (screenSize.width * 0.9), (int) (screenSize.height * 0.9)); // 1015 1000
        window.setVisible(true);
    }
}