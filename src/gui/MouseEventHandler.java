package gui;

import engine.Ball;
import engine.concurrent.BallAnimator;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Mouse event handler
 *
 * @version 1.3 2017-10-13
 * @author Alex Venger
 */
public class MouseEventHandler extends MouseAdapter {
    /**
     * Panel with field and balls
     */
    private FieldPanel fieldPanel;

    public MouseEventHandler(FieldPanel fieldPanel) {
        this.fieldPanel = fieldPanel;
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.isPopupTrigger() || e.getButton() == MouseEvent.BUTTON3) {
            fieldPanel.popupMenu.show(e.getComponent(), e.getX(), e.getY());
            return;
        }
    }

    /**
     * When the mouse is clicked, a randomly generated ball is generated (color, direction, size, speed)
     * Then the ball is added to the panel and started new thread (for each ball) that recalculates
     * the position of the ball and calls the panel to be repaint
     * @param e Mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {

        if(e.isPopupTrigger() || e.getButton() == MouseEvent.BUTTON3) {
            fieldPanel.popupMenu.show(e.getComponent(), e.getX(), e.getY());
            return;
        }

        if(BallAnimator.isPaused()) return;

        int radius = ThreadLocalRandom.current().nextInt(5, 15 + 1);

        Color color = new Color(ThreadLocalRandom.current().nextInt(0, 255 + 1),
                ThreadLocalRandom.current().nextInt(0, 255 + 1),
                ThreadLocalRandom.current().nextInt(0, 255 + 1));

        int angle = ThreadLocalRandom.current().nextInt(0, 360 + 1);

        int delay = ThreadLocalRandom.current().nextInt(3, 30 + 1);

        Ball ball = new Ball(e.getX(), e.getY(), radius, color, angle, 1, delay);

        /*
         * Start new thread that recalculates the position of the ball and calls the panel to be repaint,
         * for each ball - new thread
         */
        Thread ballAnimator = new BallAnimator(ball);
        fieldPanel.addBall(ballAnimator);

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(fieldPanel.quantityBalls() > 0 || BallAnimator.isPaused()) {
            fieldPanel.setToolTipText(null);
        } else {
            fieldPanel.setToolTipText("Click to add a ball");

        }
    }
}
