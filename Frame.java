package BRICK_BREAKER;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Frame extends JPanel {
    private static int frameWidth = 600;
    private static int frameHeight = 600;
    public Ball ball = new Ball();

    Frame(int numOfBricks) {

        setPreferredSize(new Dimension(frameWidth, frameHeight));

        this.setLayout(null);
        this.setBackground(Color.GREEN);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    public static int boxXtoFrameX(double num) {
        return (int) Math.ceil((frameWidth * num));
    }

    public static int boxYtoFrameY(double num) {
        return (int) Math.ceil((frameHeight * num));
    }

    public static double FrameXtoBoxX(int num) {
        return (double) ((1.0 / frameWidth) * num);
    }

    public static double FrameYtoBoxY(int num) {
        return (double) ((1.0 / frameHeight) * num);
    }

    public void addBrick(JPanel brick) {
        this.add(brick);

    }

    public int canvasWidth() {
        return Frame.frameWidth;
    }

    public int canvasHeight() {
        return Frame.frameHeight;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.draw(g);
    }

    public static void main(String[] args) {
        Frame frame = new Frame(25);
        System.out.println(Frame.boxXtoFrameX(1.0));
        System.out.println(Frame.boxYtoFrameY(1.0));

    }
}
