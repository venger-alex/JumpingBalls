package engine.concurrent;

import engine.Ball;
import engine.Field;

import java.awt.*;

/**
 * Thread that recalculates the position of the ball and calls the panel to be repaint
 *
 * @version 1.0 2017-10-12
 * @author Alex Venger
 */
public class BallAnimator extends Thread {
    private static boolean isPaused = false;
    private static Object monitor = new Object();

    private static Field field;
    private static Component component;

    private Ball ball;

    public Ball getBall() {
        return ball;
    }

    public static Field getField() {
        return field;
    }

    public static void setField(Field field) {
        BallAnimator.field = field;
    }

    public static void setComponent(Component component) {
        BallAnimator.component = component;
    }

    public static boolean isPaused() {
        synchronized (monitor) {
            return isPaused;
        }
    }

    public static void setPause(boolean flag) {
        synchronized (monitor) {
            isPaused = flag;
            if(flag == false) {
                monitor.notifyAll();
            }
        }
    }

    public BallAnimator(Ball ball) {
        this.ball = ball;
        start();
    }

    @Override
    public void run() {
        try {
            for ( ; !isInterrupted(); ) {
                synchronized (monitor) {
                    while (isPaused) {
                        monitor.wait();
                    }
                }

                Ball nextMove;
                if(field != null) {
                    nextMove = field.nextMove(ball);
                } else {
                    nextMove = ball.nextMove();
                }
                ball.setBall(nextMove);

                if(component != null) {
                    component.repaint();
                }

                Thread.sleep(ball.getDelay());
            }
        } catch (InterruptedException err) {
            return;
        }
    }
}
