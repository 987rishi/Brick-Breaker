package BRICK_BREAKER;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.RectangularShape;
import java.math.BigDecimal;
import java.util.Random;

import edu.princeton.cs.algs4.StdRandom;

public class Ball {
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double radius;

    public Ball() {
        double maxX = 1 - this.radius;
        double minX = this.radius;
        double maxY = 1 - this.radius;
        double minY = this.radius;

        Random rand = new Random();
        // this.x = minX + rand.nextDouble() * (maxX - minX);
        // this.y = minY + rand.nextDouble() * (maxY - minY);

        this.x = rand.nextDouble() * (maxX - minX) + minX;
        this.y = rand.nextDouble() * (maxY - minY) + minY;
        this.vx = 0.4;
        this.vy = 0.6;
        this.radius = 0.008;
    }

    public void move(double dt) {
        // collsionWithWall(dt);
        this.x += this.vx * dt;
        this.y += this.vy * dt;

    }

    public void collsionWithWall(double dt) {
        if (this.x + this.vx * dt + this.radius > 1 || this.x + this.vx * dt - this.radius < 0) {
            System.out.println("collided V with wall");

            vx = -vx;
        }

        if (this.y + this.vy * dt + this.radius > 1 || this.y + this.vy * dt - this.radius < 0) {
            System.out.println("collided H with wall");
            vy = -vy;
        }
    }

    private boolean IsballInLeftSideOfBrick(Ball ball, Object object) {
        if (ball.x + ball.radius <= object.x())
            return true;
        else
            return false;
    }

    private boolean IsballInRightSideOfBrick(Ball ball, Object object) {
        if (ball.x - ball.radius <= object.x() + object.width())
            return false;
        else
            return true;
    }

    private boolean IsballTopOfBrick(Ball ball, Object object) {
        if (ball.y + ball.radius <= object.y())
            return true;
        else
            return false;
    }

    private boolean IsballBottomOfBrick(Ball ball, Object object) {
        if (ball.y - ball.radius <= object.y() + object.height())
            return false;
        else
            return true;
    }

    public Object collsionWithObject(Object that, double dt) {
        // if (that == null)
        // return null;
        // // System.out.println("collided with brick");
        // if (that.y() > this.y + this.radius) {

        // if (this.y + vy * dt + this.radius >= that.y()
        // && (this.x + vx * dt >= that.x() && this.x + vx * dt <= that.x() +
        // that.width())) {
        // System.out.println(this);
        // System.out.println("collided with top brick");
        // vy = -vy;
        // return that;
        // }
        // }
        // if (that.y() + that.height() <= this.y - this.radius) {
        // if (this.y + vy * dt - this.radius <= that.y() + that.height()
        // && (this.x + vx * dt >= that.x() && this.x + vx * dt <= that.x() +
        // that.width())) {
        // System.out.println(this);

        // System.out.println("collided with bottom brick");

        // vy = -vy;
        // return that;
        // }
        // }
        // if (that.x() >= this.x + this.radius) {
        // if (this.x + vx * dt + this.radius >= that.x()
        // && (this.y <= that.y() + that.height() && this.y >= that.y())) {
        // System.out.println(this);

        // System.out.println("collided with left brick");

        // vx = -vx;
        // return that;
        // }
        // }
        // if (that.x() + that.width() <= this.x - this.radius) {
        // if (this.x + vx * dt - this.radius <= that.x() + that.width()
        // && (this.y + vy * dt <= that.y() + that.height() && this.y + vy * dt >=
        // that.y())) {
        // System.out.println("collided with right brick");
        // System.out.println(this);

        // vx = -vx;
        // return that;
        // }
        // }
        // return null;

        // ----------------------
        // System.out.println(this);

        // System.out.println(that);
        if (IsballTopOfBrick(this, that) == true) {
            // System.out.println("on top");
            // if (this.y + vy * dt >= that.y()
            // && (this.x + vx * dt >= that.x() && this.x + vx * dt <= that.x() +
            // that.width()))
            if (this.y + vy * dt + this.radius >= that.y()
                    && (this.x + vx * dt >= that.x() - this.radius && this.x + vx * dt <= that.x() +
                            that.width() + this.radius)) {
                this.vy = -this.vy;
                // System.out.println("top");
                return that;
            }

        }
        if (IsballBottomOfBrick(this, that) == true) {
            // System.out.println("Hello");
            // System.out.println("on bottom");

            if (this.y + vy * dt - this.radius <= that.y() + that.height()
                    && (this.x + vx * dt >= that.x() - this.radius && this.x + vx * dt <= that.x() +
                            that.width() + this.radius)) {
                this.vy = -this.vy;
                // System.out.println("bottom");
                return that;

            }
        }
        if (IsballInLeftSideOfBrick(this, that) == true) {
            // System.out.println("Hello");
            // System.out.println("on left");

            if (this.x + vx * dt + this.radius >= that.x()
                    && (this.y + vy * dt >= that.y() - this.radius && this.y + vy * dt <= that.y() +
                            that.height() + this.radius)) {
                this.vx = -this.vx;
                // System.out.println("left");
                return that;

            }
        }
        if (IsballInRightSideOfBrick(this, that) == true) {
            // System.out.println("Hello");
            // System.out.println("on right");

            // System.out.println("RIGHT 1st COndition" + (this.x + vx * dt + this.radius <=
            // that.x() + that.width()));
            if (this.x + vx * dt - this.radius <= that.x() + that.width()
                    && (this.y + vy * dt <= that.y() + this.radius + that.height()
                            && this.y + vy * dt >= that.y() - this.radius)) {
                this.vx = -this.vx;
                return that;
                // System.out.println("right");

            }

        }
        return null;
    }

    public void collsionWithPlatform(DraggablePlatform that, double dt) {
        double thatY = Frame.FrameYtoBoxY((int) that.panelCorner.getY());
        double thatX = Frame.FrameXtoBoxX((int) that.panelCorner.getX());
        double thatH = Frame.FrameYtoBoxY((int) that.getHeight());
        double thatW = Frame.FrameXtoBoxX((int) that.getWidth());
        System.out.println("X,Y,H,W\t" + thatX + " " + thatY + " " + thatH + " " + thatW);

        if (thatY + thatH >= this.y + this.radius) {

            if (this.y + vy * dt + this.radius >= thatY
                    && (this.x + vx * dt >= thatX - this.radius && this.x + vx * dt <= thatX + thatW + this.radius)) {
                System.out.println(this);
                System.out.println("collided with PLAt top brick");
                vy = -vy;
            }
        }
        if (thatY + thatH <= this.y - this.radius) {
            if (this.y + vy * dt - this.radius <= thatY + thatH
                    && (this.x + vx * dt >= thatX - this.radius && this.x + vx * dt <= thatX + thatW + this.radius)) {
                System.out.println(this);

                System.out.println("collided PLAt with bottom brick");

                vy = -vy;
            }
        }
        if (thatX >= this.x + this.radius) {
            if (this.x + vx * dt + this.radius >= thatX
                    && (this.y <= thatY + thatH + this.radius && this.y >= thatY - this.radius)) {
                System.out.println(this);

                System.out.println("collided PLAt with left brick");

                vx = -vx;
            }
        }
        if (thatX + thatW <= this.x - this.radius) {
            if (this.x + vx * dt - this.radius <= thatX + thatW
                    && (this.y + vy * dt <= thatY + thatH + this.radius && this.y + vy * dt >= thatY - this.radius)) {
                System.out.println("collided PLAt with right brick");
                System.out.println(this);

                vx = -vx;
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("X=" + this.x + "\tY=" + this.y);

        return string.toString();
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(
                Frame.boxXtoFrameX(x) - Frame.boxXtoFrameX(radius),
                Frame.boxYtoFrameY(y) - Frame.boxYtoFrameY(radius),
                2 * Frame.boxXtoFrameX(radius),
                2 * Frame.boxYtoFrameY(radius));

    }
}
