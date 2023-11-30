import javax.swing.*;
import java.awt.*;

public class Window {
    public static void main() {
        CircleDrawer circle = new CircleDrawer();
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(circle, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}