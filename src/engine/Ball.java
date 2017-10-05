package engine;

import static java.lang.Math.*;
import java.awt.*;

/**
 * Ball
 *
 * @aversion 1.0 2017-10-05
 * @author Alex Venger
 */
public class Ball implements Cloneable {

    /**
     * Rectangle where the inscribed circle of the ball
     */
    private Rectangle ballRectangle;

    /**
     * Color of the ball
     */
    private Color ballColor;

    /**
     * The delay in the movement of the ball in milliseconds, if passed to the stream,
     * can be used to specify the Thread.sleep value of this stream, adjusting this value
     * to the speed of the ball's recalculation and redrawing in the stream, i.e. ball speed
     */
    private int ballDelayMovementMilliseconds;

    /**
     * The angle of the ball in degrees (from 0 to 360), relatively down the directed vertical,
     * counter-clockwise
     * The direction of movement of the ball
     */
    private double ballAngle;

    /**
     * Step with which the ball goes into the next state
     */
    private double ballStep;

    public Rectangle getBallRectangle() {
        return ballRectangle;
    }

    public Color getBallColor() {
        return ballColor;
    }

    public int getBallDelayMovementMilliseconds() {
        return ballDelayMovementMilliseconds;
    }

    public double getBallAngle() {
        return ballAngle;
    }

    public double getBallStep() {
        return ballStep;
    }

    public void setBallRectangle(Rectangle ballRectangle) {
        this.ballRectangle = ballRectangle;
    }

    public void setBallColor(Color ballColor) {
        this.ballColor = ballColor;
    }

    public void setBallDelayMovementMilliseconds(int ballDelayMovementMilliseconds) {
        this.ballDelayMovementMilliseconds = ballDelayMovementMilliseconds;
    }

    public void setBallAngle(double ballAngle) {
        /*
         * TODO It is worth considering that the angle can go beyond 360 degrees and then it is worth taking the remainder of the division by 360
         */
        this.ballAngle = ballAngle;
    }

    public void setBallStep(double ballStep) {
        this.ballStep = ballStep;
    }

    public Ball(Rectangle ballRectangle, Color ballColor, int ballDelayMovementMilliseconds, double ballAngle, double ballStep) {
        this.setBallRectangle(ballRectangle);
        this.setBallColor(ballColor);
        this.setBallDelayMovementMilliseconds(ballDelayMovementMilliseconds);
        this.setBallAngle(ballAngle);
        this.setBallStep(ballStep);
    }

    /**
     * Calculates the next position of the ball
     * Only the coordinates of the ball change, the direction does not change,
     * the ball itself flies to nowhere in the initially specified direction,
     * without changing it
     * Since, for example, there are no walls, then there is nowhere to bounce
     * @see engine.Field
     * @see engine.Ball
     * @return Copy of the ball in the new position
     */
    public Ball nextMove() {
        /*
         * We clone ourselves to get a copy back
         */
        Ball resBall = (Ball) this.clone();

        /*
         * Calculate the new coordinates
         */
        double dx = sin(toRadians(this.getBallAngle())) * this.getBallStep();
        double dy = cos(toRadians(this.getBallAngle())) * this.getBallStep();

        double nextX = this.getBallRectangle().getX() + dx;
        double nextY = this.getBallRectangle().getY() + dy;

        /*
         * Set the returned value of Ball to the new calculated coordinates
         */
        resBall.getBallRectangle().setLocation((int)round(nextX), (int)round(nextY));

        /*
         * Return a copy of the Ball ball state with the new state (for the next turn)
         * State - only coordinates change here, but in other methods, it is possible
         * change the angle of flight, if a rebound from the walls, for example
         */
        return resBall;
    }

    /**
     * Draws the ball with the specified color in the transferred graphic context
     * If the color is NULL, draws the color of the ball
     * @param g Graphic context for drawing
     * @param color Color for drawing, if = null, then the color of the ball
     */
    public void paint(Graphics g, Color color) {
        if(color != null) {
            g.setColor(color);
        } else {
            g.setColor(this.getBallColor());
        }
        g.fillOval((int)round(this.getBallRectangle().getX()),
                (int)round(this.getBallRectangle().getY()),
                (int)round(this.getBallRectangle().getWidth()),
                (int)round(this.getBallRectangle().getHeight()));
    }

    /**
     * Draws a ball with its color in the transferred graphic context
     * @param g Graphic context for drawing
     */
    public void paint(Graphics g) {
        this.paint(g, null);
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
