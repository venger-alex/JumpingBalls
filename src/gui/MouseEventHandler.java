package gui;

import engine.Ball;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Mouse event handler
 *
 * @version 1.1 2017-10-08
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

    /**
     * When the mouse is clicked, a randomly generated ball is generated (color, direction, size, speed)
     * Then the ball is added to the panel and started new thread (for each ball) that recalculates
     * the position of the ball and calls the panel to be repaint
     * @param e Mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int radius = ThreadLocalRandom.current().nextInt(5, 15 + 1);

        Color color = new Color(ThreadLocalRandom.current().nextInt(0, 255 + 1),
                ThreadLocalRandom.current().nextInt(0, 255 + 1),
                ThreadLocalRandom.current().nextInt(0, 255 + 1));

        int angle = ThreadLocalRandom.current().nextInt(0, 360 + 1);

        int delay = ThreadLocalRandom.current().nextInt(3, 30 + 1);

        Ball ball = new Ball(e.getX(), e.getY(), radius, color, angle, 1, delay);

        fieldPanel.add(ball);

        /*
         * Start new thread that recalculates the position of the ball and calls the panel to be repaint,
         * for each ball - new thread
         */
        Runnable ballAnimator = () -> {
            try {
                for (; !Thread.currentThread().isInterrupted(); ) {
                    Ball nextMove = fieldPanel.nextMove(ball);
                    ball.setBall(nextMove);

                    fieldPanel.repaint();

                    Thread.sleep(ball.getDelay());
                }
            } catch (InterruptedException err) {
                return;
            }
        };
        Thread thread = new Thread(ballAnimator);
        thread.start();
    }
}
