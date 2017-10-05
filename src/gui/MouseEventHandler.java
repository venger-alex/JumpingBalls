package gui;

import engine.Ball;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Mouse event handler
 *
 * @aversion 1.0 2017-10-05
 * @author Alex Venger
 */
public class MouseEventHandler extends MouseAdapter {
    /**
     * Panel with field and balls
     */
    private FieldPanel fieldPanel;

    public FieldPanel getFieldPanel() {
        return fieldPanel;
    }

    public MouseEventHandler(FieldPanel fieldPanel) {
        this.fieldPanel = fieldPanel;
    }

    /**
     * When the mouse is clicked, a randomly generated ball is generated (color, direction, size, speed)
     * Then the ball is added to the panel and started new thread (for each ball) that recalculates
     * the position of the ball and calls the panel to be repaint
     * @param e Mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int diameter = ThreadLocalRandom.current().nextInt(10, 30 + 1);
        Color color = new Color(ThreadLocalRandom.current().nextInt(0, 255 + 1),
                ThreadLocalRandom.current().nextInt(0, 255 + 1),
                ThreadLocalRandom.current().nextInt(0, 255 + 1));
        int delay = ThreadLocalRandom.current().nextInt(3, 20 + 1);

        int direction = ThreadLocalRandom.current().nextInt(0, 3 + 1) * 90;
        int angle = ThreadLocalRandom.current().nextInt(30, 60 + 1) + direction;

        Ball ball = new Ball(
                new Rectangle(e.getX() - diameter / 2, e.getY() - diameter / 2, diameter, diameter),
                color,
                delay,
                angle,
                1);

        this.getFieldPanel().add(ball);

        /*
         * Start new thread that recalculates the position of the ball and calls the panel to be repaint,
         * for each ball - new thread
         */
        Runnable ballAnimator = () -> {
            try {
                for (; !Thread.currentThread().isInterrupted(); ) {
                    Ball nextMove = this.getFieldPanel().getField().nextMove(ball);
                    ball.setBallRectangle(nextMove.getBallRectangle());
                    ball.setBallAngle(nextMove.getBallAngle());

                    this.getFieldPanel().repaint();

                    Thread.sleep(ball.getBallDelayMovementMilliseconds());
                }
            } catch (InterruptedException err) {
                return;
            }
        };
        Thread thread = new Thread(ballAnimator);
        thread.start();
    }
}
