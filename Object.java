package BRICK_BREAKER;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/*OBJECT WILL BE SQUARE */
public class Object extends JPanel {
    private double x;
    private double y;
    private double width;
    private double height;
    private Color color;
    // public JPanel panel;

    // public Object() {
    // this.x = Frame.FrameXtoBoxX(this.getX());
    // this.y = Frame.FrameYtoBoxY(this.getY());

    // System.out.println("OBJECT\t" + this.x + " " + this.y);
    // this.width = 0.2;
    // this.color = Color.BLUE;
    // this.height = 0.05;

    // this.setBackground(color);
    // this.setOpaque(true);
    // this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    // this.setPreferredSize(new Dimension(Frame.boxXtoFrameX(width),
    // Frame.boxYtoFrameY(height)));
    // }

    public Object(double x, double y) {
        this.x = x;
        this.y = y;
        this.width = 0.06;
        this.color = Color.BLUE;
        this.height = 0.07;

        this.setBackground(color);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        int xC = Frame.boxXtoFrameX(x);
        System.out.println(xC);
        int yC = Frame.boxYtoFrameY(y);
        System.out.println(yC);
        int w = Frame.boxXtoFrameX(width);
        System.out.println(w);
        int h = Frame.boxYtoFrameY(height);
        System.out.println(h);

        this.setBounds(xC, yC, w, h);
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public double width() {
        return this.width;
    }

    public double height() {
        return this.height;
    }

    @Override
    public String toString() {
        StringBuilder dimens = new StringBuilder();
        dimens.append("X=" + this.x + "\tY=" + this.y);
        dimens.append("\n");
        dimens.append("Width=" + this.width + " " + "Height=" + this.height);

        return dimens.toString();
    }

    // public void draw() {
    // StdDraw.setPenColor(Color.BLUE);
    // StdDraw.filledRectangle(this.x, this.y, this.width / 2.0, height / 2.0);
    // // Frame.FrameXtoBoxX(500);
    // }
}
