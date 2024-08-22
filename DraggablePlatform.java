package BRICK_BREAKER;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class DraggablePlatform extends JPanel {
    public final int width = this.getWidth();
    public final int height = this.getHeight();
    public Point prevPoint;
    public Point panelCorner;

    DraggablePlatform() {

        this.panelCorner = new Point(Frame.boxXtoFrameX(0.2), Frame.boxYtoFrameY(0.6));

        MouseClicked mouseClicked = new MouseClicked();
        MouseDrag mouseDrag = new MouseDrag();

        this.setBounds(Frame.boxXtoFrameX(0.0), Frame.boxYtoFrameY(0.0), Frame.boxXtoFrameX(0.3),
                Frame.boxYtoFrameY(0.02));

        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.addMouseListener(mouseClicked);
        this.addMouseMotionListener(mouseDrag);

    }

    public void move() {
        this.setLocation(panelCorner);
    }

    private class MouseClicked extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent m) {
            // System.out.println("mouse pressed");
            prevPoint = m.getLocationOnScreen();
        }

    }

    private class MouseDrag extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent m) {
            // System.out.println("MOUSE DRAGGED");
            Point currPoint = m.getLocationOnScreen();

            panelCorner.translate(
                    (int) currPoint.getX() - (int) prevPoint.getX(),
                    (int) currPoint.getY() - (int) prevPoint.getY());

            prevPoint = currPoint;
            move();
        }

    }

}
