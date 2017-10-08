package engine;

import static java.lang.Math.*;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Ball
 *
 * @version 1.1 2017-10-08
 * @author Alex Venger
 */
public class Ball implements Cloneable {

    /**
     * Ellipse of the ball
     */
    private Ellipse2D shape;

    /**
     * Color of the ball
     */
    private Color color;

    /**
     * The angle of the ball flight in degrees (from 0 to 360), relatively down the directed vertical,
     * counter-clockwise
     * The direction of the ball movement
     */
    private double angle;

    /**
     * Step with which the ball goes into the next state
     */
    private double step;

    /**
     * The delay in the movement of the ball in milliseconds, if passed to the stream,
     * can be used to specify the Thread.sleep value of this stream, adjusting this value
     * to the speed of the ball's recalculation and redrawing in the stream, i.e. ball speed
     */
    private int delay;

    public Ellipse2D getShape() {
        return (Ellipse2D) shape.clone();
    }

    public double getX() {
        return shape.getX();
    }

    public double getY() {
        return shape.getY();
    }

    public double getWidth() {
        return shape.getWidth();
    }

    public double getHeight() {
        return shape.getHeight();
    }

    public double getMinX() {
        return shape.getMinX();
    }

    public double getMaxX() {
        return shape.getMaxX();
    }

    public double getMinY() {
        return shape.getMinY();
    }

    public double getMaxY() {
        return shape.getMaxY();
    }

    public Color getColor() {
        return color;
    }

    public double getAngle() {
        return angle;
    }

    public double getStep() {
        return step;
    }

    public int getDelay() {
        return delay;
    }

    public void setShape(Ellipse2D shape) {
        this.shape = (Ellipse2D) shape.clone();
    }

    public void setX(double x) {
        shape.setFrame(x, getY(), getWidth(), getHeight());
    }

    public void setY(double y) {
        shape.setFrame(getX(), y, getWidth(), getHeight());
    }

    public void setLocation(double x, double y) {
        shape.setFrame(x , y, getWidth(), getHeight());
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setAngle(double angle) {
        this.angle = angle < 0 ? 360 + angle % 360 : angle % 360;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setBall(Ball ball) {
        setShape(ball.getShape());
        setColor(ball.getColor());
        setAngle(ball.getAngle());
        setStep(ball.getStep());
        setDelay(ball.getDelay());
    }

    public Ball(Ellipse2D shape, Color color, double angle, double step, int delay) {
        setShape(shape);
        setColor(color);
        setAngle(angle);
        setStep(step);
        setDelay(delay);
    }

    public Ball(double centerX, double centerY, double radius, Color color, double angle, double step, int delay) {
        this(new Ellipse2D.Double(centerX - radius,
                                    centerY - radius,
                                    radius * 2,
                                    radius * 2),
                color,
                angle,
                step,
                delay);
    }

    /**
     * Calculates the next position of the ball
     * Only the coordinates of the ball change, the direction does not change,
     * the ball itself flies to nowhere in the initially specified direction,
     * without changing it
     * Since, for example, there are no walls, then there is nowhere to bounce
     * The rebound from the walls, for example, is realized in the <code>Field</code> class
     * @see engine.Field#nextMove(Ball)
     * @return Copy of the ball in the new position
     */
    public Ball nextMove() {

         // Clone ourselves to get a copy back
        Ball ballNextMove = (Ball) this.clone();


        // Calculate the new coordinates
        double dx = sin(toRadians(getAngle())) * getStep();
        double dy = cos(toRadians(getAngle())) * getStep();

        double nextX = getX() + dx;
        double nextY = getY() + dy;


        // Set the returned value of Ball to the new calculated coordinates
        ballNextMove.setLocation(nextX, nextY);

        /*
         * Return a copy of the Ball ball state with the new state (for the next turn)
         * State - only coordinates change here, but in other methods, it is possible
         * change the angle of flight, if a rebound from the walls, for example
         */
        return ballNextMove;
    }

    /**
     * Draws a ball with its color in the transferred graphic context
     * @param g Graphic context for drawing
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getColor());
        g2.fill(getShape());
    }

    /**
     * Creates a new object of the same class and with the
     * same contents as this object.
     * @return     a clone of this instance.
     * @see        java.lang.Cloneable
     */
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }
}
