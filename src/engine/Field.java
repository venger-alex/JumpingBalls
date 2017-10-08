package engine;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * The field within which jumps the balls
 *
 * @version 1.1 2017-10-08
 * @author Alex Venger
 */
public class Field {
    /**
     * The rectangle of the field within which the process is going
     */
    private Rectangle2D shape;

    /**
     * Color of the walls
     */
    private Color color;

    public Rectangle2D getShape() {
        return (Rectangle2D) shape.clone();
    }

    public double getX() {
        return shape.getX();
    }

    public double getY() {
        return shape.getY();
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

    public void setShape(Rectangle2D shape) {
        this.shape = (Rectangle2D) shape.clone();
    }

    public void setFrame(double x, double y, double width, double height) {
        shape.setFrame(x, y, width, height);
    }

    public void setSize(double width, double height) {
        shape.setFrame(getX(), getY(), width, height);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Field(Rectangle2D shape, Color color) {
        setShape(shape);
        setColor(color);
    }

    public Field(double x, double y, double width, double height, Color color) {
        this(new Rectangle2D.Double(x, y, width, height), color);
    }

    /**
     * Calculates the next position of the ball, taking into account the rebound from the walls.
     * Can change, both the position and direction of the movement (rebound), i.e. angle of the ball
     * @param ball The ball for which you need to calculate a new position
     * @return Copy of the ball in the new position
     */
    public Ball nextMove(Ball ball) {
        // Get copy of the ball with new coordinates without taking into account the walls and other obstacles
        Ball ballNextMove = ball.nextMove();

        /*
         * If the new position of the ball does not fit into the field, it means that they have rested
         * against the wall, there will be a rebound and other coordinates, and an angle (direction)
         */

        /*
         * Check with what wall collided and, depending on this, change the coordinates
         * (shift to the edge of the field backwards if we go beyond), and change the angle (direction)
         * of the movement. Then again start the miscalculation of the new move and return the result
         * of the ball state
         */

        boolean isRebound = false;

        // Left wall
        if(ballNextMove.getMinX() < getMinX()) {
            isRebound = true;
            ballNextMove.setX(getMinX());
            ballNextMove.setAngle(360 - ballNextMove.getAngle());
        }

        // Right wall
        if(ballNextMove.getMaxX() > getMaxX()) {
            isRebound = true;
            ballNextMove.setX(getMaxX() - ballNextMove.getWidth());
            ballNextMove.setAngle(360 - ballNextMove.getAngle());
        }

        // Upper wall
        if(ballNextMove.getMinY() < getMinY()) {
            isRebound = true;
            ballNextMove.setY(getMinY());
            ballNextMove.setAngle(180 - ballNextMove.getAngle());
        }

        // Bottom wall
        if(ballNextMove.getMaxY() > getMaxY()) {
            isRebound = true;
            ballNextMove.setY(getMaxY() - ballNextMove.getHeight());
            ballNextMove.setAngle(180 - ballNextMove.getAngle());
        }

        if(isRebound) {
            ballNextMove = ballNextMove.nextMove();
        }

        return ballNextMove;
    }

    /**
     * Draws a field with its color in the transferred graphic context
     * @param g Graphic context for drawing
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getColor());
        g2.draw(getShape());
    }
}
