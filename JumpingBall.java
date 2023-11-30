import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.random.*;

public class JumpingBall extends JPanel{
    private static final int widtth = 500;
    private static final int height = 500;
    private static final int sizeOfBall = 15;
    private List<Ball> ListBalls;


    public JumpingBall() {
        ListBalls = new ArrayList<>();
        Random Ran = new Random();
        Ball ball_1 = new Ball(10, 100, sizeOfBall, new Color(Ran.nextInt(255), Ran.nextInt(255), Ran.nextInt(255)), this);
        Ball ball_2 = new Ball(50, 100, sizeOfBall, new Color(Ran.nextInt(255), Ran.nextInt(255), Ran.nextInt(255)), this);
        ListBalls.add(ball_1);
        ListBalls.add(ball_2);
        new Thread(ball_1).start();
        new Thread(ball_2).start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball ball : ListBalls) {
            ball.draw(g);
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Black one running away");
        JumpingBall JumpingBall = new JumpingBall();
        frame.add(JumpingBall);
        frame.setSize(widtth, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


class Ball implements Runnable {
    private int x, y, size;
    private Color color;
    private int xvec1, yvec1;
    private Component container;


    public Ball(int x, int y, int size, Color color, Component container) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        this.container = container;
        xvec1 = 2;
        yvec1 = 2;
    }


    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }


    @Override
    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            onTime();
            container.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void onTime() {
        if (x + xvec1 < 0 || x + xvec1 > container.getWidth() - size) {
            xvec1 = -xvec1;
        }
        if (y + yvec1 < 0 || y + yvec1 > container.getHeight() - size) {
            yvec1 = -yvec1;
        }
        x += xvec1;
        y += yvec1;
    }
}
