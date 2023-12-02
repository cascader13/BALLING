import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Program program = new Program();
        program.run();
    }
}
class Program  {
    private JFrame mainFrame;
    private DrawPanel drawPanel;
    private java.util.List<Ball> balls;

    private int windowWidth = 640;
    private int windowHeight = 480;
    private String windowLabel = "My balls";


    void run() {

        balls = new ArrayList<>();

        /* BAALLS */
        for (int i = 0; i < 1; i++) {
            Ball ball = new Ball(
                    (int) Math.floor(Math.random() * windowWidth),
                    (int) Math.floor(Math.random() * windowHeight),
                    (int) Math.floor(Math.random() * 20) + 10,
                    new Color(
                            (int) Math.floor((Math.random() * 256)),
                            (int) Math.floor((Math.random() * 256)),
                            (int) Math.floor((Math.random() * 256))
                    ),
                    (int) Math.floor((Math.random() * 5)),
                    (int) Math.floor((Math.random() * 5))
            );

            balls.add(ball);
        }

        mainFrame = new JFrame();
        drawPanel = new DrawPanel();
        mainFrame.getContentPane().add(drawPanel);
        mainFrame.setTitle(windowLabel);
        mainFrame.setSize(windowWidth, windowHeight);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.addMouseListener(new CustomListener());

        while (true) {
            for (Ball b: balls) {
                b.update();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mainFrame.repaint();
        }
    }




    class DrawPanel extends JPanel {
        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            for (Ball b: balls) {
                b.draw(graphics);
            }

        }
    }

    class Ball {
        private int posX, posY, size;
        private Color color;

        private int vx = 5;
        private int vy = 5;

        public Ball(int posX, int posY, int size, Color color, int vx, int vy) {
            this.posX = posX;
            this.posY = posY;
            this.size = size;
            this.color = color;
            this.vx = vx;
            this.vy = vy;
        }

        void contact(){
            for(int i = 0; i < balls.size() - 1; ++i){
                if(balls.size() > 2) {
                    for (int j = i + 1; j < balls.size(); ++j) {
                        int lx = balls.get(i).posX - balls.get(j).posX;
                        int ly = balls.get(i).posY - balls.get(j).posY;
                        int length = (int) Math.sqrt(lx * lx + ly * ly);
                        if (length <= (balls.get(i).size + balls.get(j).size)) {
                            int vx = balls.get(i).vx;
                            int vy = balls.get(i).vy;
                            balls.get(i).vx = balls.get(j).vx;
                            balls.get(i).vy = balls.get(j).vy;
                            balls.get(j).vx = vx;
                            balls.get(j).vy = vy;
                        }
                    }
                }
            }
        }

        void update() {
            contact();


            if (posX > mainFrame.getWidth() || posX < 0) {
                vx *= -1;
            }

            if (posY > mainFrame.getHeight() || posY < 0) {
                vy *= -1;
            }

            if (posX > mainFrame.getWidth()) {
                posX = mainFrame.getWidth();
            }

            if (posX < 0) {
                posX = 0;
            }

            if (posY > mainFrame.getHeight()) {
                posY = mainFrame.getHeight();
            }

            if (posY < 0) {
                posY = 0;
            }

            this.posX += vx;
            this.posY += vy;

        }

        void draw(Graphics gr) {
            gr.setColor(color);
            gr.fillOval(posX, posY, size, size);
        }


    }
    public class CustomListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            Ball ball = new Ball(
                    e.getX(),
                    e.getY(),
                    (int) Math.floor(Math.random() * 20) + 10,
                    new Color(
                            (int) Math.floor((Math.random() * 256)),
                            (int) Math.floor((Math.random() * 256)),
                            (int) Math.floor((Math.random() * 256))
                    ),
                    (int) Math.floor((Math.random() * 7) + 1),
                    (int) Math.floor((Math.random() * 7) + 1)
            );
            System.out.println(e.getX());
            System.out.println(e.getY());
            balls.add(ball);
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            Ball ball = new Ball(
                    e.getX(),
                    e.getY(),
                    (int) Math.floor(Math.random() * 20) + 10,
                    new Color(
                            (int) Math.floor((Math.random() * 256)),
                            (int) Math.floor((Math.random() * 256)),
                            (int) Math.floor((Math.random() * 256))
                    ),
                    (int) Math.floor((Math.random() * 7) + 1),
                    (int) Math.floor((Math.random() * 7) + 1)
            );
            balls.add(ball);
        }

        public void mouseReleased(MouseEvent e) {
        }
    }
}