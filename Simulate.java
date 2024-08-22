package BRICK_BREAKER;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class Simulate extends JFrame {
    private DraggablePlatform platform;
    private Frame canvas;
    private int N;
    private Object[] grid;
    private int sleep = 15;
    private double dt = 0.02;
    private int points;

    private void createMap(Frame canvas, Object[] grid) {
        if (canvas == null || grid == null)
            throw new IllegalArgumentException("NULL ARGUMENTS");
        System.out.println("HELLO" + grid.length);

        Object temp = new Object(0, 0);
        int WIDTH_OF_BRICK = Frame.boxXtoFrameX(temp.width());
        int HEIGHT_OF_BRICK = Frame.boxYtoFrameY(temp.height());
        int WIDTH_OF_CANVAS = canvas.canvasWidth();
        int HEIGHT_OF_CANVAS = canvas.canvasHeight();

        // System.out.println("W of brick " + WIDTH_OF_BRICK);
        // System.out.println("H of brick " + HEIGHT_OF_BRICK);
        // System.out.println("W OF CANVAS " + WIDTH_OF_CANVAS);
        // System.out.println("H OF CANVAS " + HEIGHT_OF_CANVAS);

        assert WIDTH_OF_CANVAS > 0 : "WIDTH OF CANVAS SIZE TOO SMALL";
        assert HEIGHT_OF_CANVAS > 0 : "HEIGHT OF CANVAS SIZE TOO SMALL";
        int brickGap = 10;
        int cnt = 0;
        int items = grid.length;
        System.out.println("items" + items);
        for (int i = brickGap; i < (HEIGHT_OF_CANVAS) / 2 && items > 0; i += HEIGHT_OF_BRICK + brickGap) {
            for (int j = brickGap; j < WIDTH_OF_CANVAS && items > 0; j += WIDTH_OF_BRICK + brickGap) {
                System.out.println("PAGLUE");
                grid[cnt] = new Object(Frame.FrameXtoBoxX(j), Frame.FrameYtoBoxY(i));
                cnt++;
                items--;
            }
        }
        // for (int i = 0; i < grid.length; i++)
        // System.out.println(grid[i]);
        temp = null;

        for (int i = 0; i < grid.length; i++)
            canvas.addBrick(grid[i]);
    }

    Simulate(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("ILLEGAL ARGUMENT TO CONSTRUCTOR");

        this.N = n;
        this.points = 0;

        this.platform = new DraggablePlatform();
        this.canvas = new Frame(n);
        this.grid = new Object[n];

        createMap(this.canvas, this.grid);

        canvas.add(platform);

        JLabel score = new JLabel("Score=");
        score.setSize(new Dimension(100, 50));
        score.setFont(new Font("MV Boli", Font.BOLD, 20));

        JPanel toPanel = new JPanel();
        toPanel.setLayout(new FlowLayout());
        toPanel.setPreferredSize(new Dimension(600, 50));
        // toPanel.setBackground(Color.DARK_GRAY);
        toPanel.setOpaque(true);
        toPanel.add(score);

        this.setLayout(new FlowLayout());

        this.add(toPanel);
        this.add(canvas);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    public void start() {
        // double dt = 0.4;
        int numBricks = this.N;
        while (true) {
            if (numBricks == 0)
                System.exit(0);
            canvas.ball.move(dt);
            canvas.ball.collsionWithWall(dt);
            for (int i = 0; i < N; i++) {
                if (numBricks == 0)
                    System.exit(0);
                if (grid[i] != null) {
                    Object toRemove = canvas.ball.collsionWithObject(grid[i], dt);
                    if (toRemove != null) {
                        canvas.remove(toRemove);
                        grid[i] = null;
                        numBricks--;
                        this.points++;
                    }
                }
                canvas.repaint();
            }
            canvas.ball.collsionWithPlatform(platform, dt);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            canvas.repaint();
        }
    }

    public static void main(String[] args) {
        Simulate sim = new Simulate(70);
        sim.start();
        // int sleep = 20;

        // // N = 3;
        // Object[] grid = new Object[3];

        // grid[0] = new Object(0.2, 0.2);
        // grid[1] = new Object(0.2, 0.5);
        // grid[2] = new Object(0.6, 0.5);

        // DraggablePlatform platform = new DraggablePlatform();

        // Frame canvas = new Frame(2);
        // for (int i = 0; i < 3; i++)
        // canvas.addBrick(grid[i]);
        // canvas.add(platform);

        // // // JPanel panel = new JPanel();
        // // // panel.setPreferredSize(new Dimension(200, 200));
        // // // panel.setBackground(Color.BLUE);
        // // // panel.setOpaque(true);

        // JFrame frame = new JFrame();
        // // frame.setSize(600, 600);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLayout(new FlowLayout());

        // // // frame.add(panel);
        // frame.add(canvas);

        // frame.pack();
        // frame.setVisible(true);

        // while (true) {
        // canvas.ball.move(0.2);
        // canvas.ball.collsionWithWall(0.2);

        // for (int i = 0; i < 3; i++) {
        // canvas.ball.collsionWithObject(grid[i], 0.2);
        // canvas.repaint();
        // }
        // canvas.ball.collsionWithPlatform(platform, 0.2);
        // try {
        // Thread.sleep(sleep);
        // } catch (InterruptedException e) {

        // e.printStackTrace();
        // }
        // canvas.repaint();
        // }

        // }

    }
}
