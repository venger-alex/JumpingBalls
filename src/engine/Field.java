package engine;

import static java.lang.Math.*;
import java.awt.*;

/**
 * The field within which jumps the balls
 *
 * @aversion 1.0 2017-10-05
 * @author Alex Venger
 */
public class Field {
    /**
     * The rectangle of the field within which the process is going
     */
    private Rectangle fieldShape;

    /**
     * Background and border colors
     */
    private Color backgroundColor;
    private Color foregroundColor;

    public Rectangle getFieldShape() {
        return fieldShape;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setFieldShape(Rectangle fieldShape) {
        this.fieldShape = fieldShape;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public Field(Rectangle fieldShape, Color backgroundColor, Color foregroundColor) {
        this.setFieldShape(fieldShape);
        this.setBackgroundColor(backgroundColor);
        this.setForegroundColor(foregroundColor);
    }

    /**
     * Calculates the next position of the ball, taking into account the rebound from the walls.
     * Can change, both the position and direction of the movement (rebound), i.e. angle of the ball
     * @param ball The ball for which you need to calculate a new position
     * @return Copy of the ball in the new position
     */
    public Ball nextMove(Ball ball) {
        /*
         * Clone the transferred ball to return the copy in a new position
         */
        Ball ballNextMove = (Ball) ball.clone();

        /*
         * Calculate the new coordinates without taking into account the walls and other obstacles
         */
        ballNextMove = ballNextMove.nextMove();

        /*
         * Check whether new coordinates fit into the field limits, if yes, then there is no rebound,
         * simply continue to fly, return immediately the new state of the ball
         */
        if(this.getFieldShape().contains(ballNextMove.getBallRectangle())) {
            return ballNextMove;
        }

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

        double ballAngle = ball.getBallAngle();

        /*
         * Upper wall
         */
        if(ballNextMove.getBallRectangle().getY() < this.getFieldShape().getY()) {
            /*
             * TODO Perhaps getX should not be taken from ball (which was), but in itself, i.e. new, because the ball could move along X, but not go beyond the borders
             */
            ballNextMove.getBallRectangle().setLocation((int)ball.getBallRectangle().getX(), (int)this.getFieldShape().getY());

            if(ballAngle > 180 && ballAngle < 270) {
                ballNextMove.setBallAngle(ballAngle + 90);
            } else if (ballAngle > 90 && ballAngle < 180) {
                ballNextMove.setBallAngle(ballAngle - 90);
            }
        }

        /*
         * Bottom wall
         */
        if((ballNextMove.getBallRectangle().getY() + ballNextMove.getBallRectangle().getHeight()) > (this.getFieldShape().getY() + this.getFieldShape().getHeight())) {
            /*
             *  TODO Perhaps getX should not be taken from ball (which was), but in itself, i.e. new, because the ball could move along X, but not go beyond the borders
             */
            ballNextMove.getBallRectangle().setLocation((int)ball.getBallRectangle().getX(), (int)(this.getFieldShape().getY() + this.getFieldShape().getHeight() - ball.getBallRectangle().height));

            if(ballAngle > 0 && ballAngle < 90) {
                ballNextMove.setBallAngle(ballAngle + 90);
            } else if (ballAngle > 270 && ballAngle < 360) {
                ballNextMove.setBallAngle(ballAngle - 90);
            }
        }

        /*
         * Left wall
         */
        if(ballNextMove.getBallRectangle().getX() <= this.getFieldShape().getX()) {
            /*
             * TODO Perhaps getY should not be taken from ball (which was), but in itself, i.e. new, because the ball could also move along Y, but not go beyond the boundaries
             */
            ballNextMove.getBallRectangle().setLocation((int)this.fieldShape.getX(), (int)(ball.getBallRectangle().getY()));

            if(ballAngle >= 180 && ballAngle <= 270) {
                ballNextMove.setBallAngle(ballAngle - 90);
            } else if (ballAngle >= 270 && ballAngle <= 360) {
                ballNextMove.setBallAngle(ballAngle - 270);
            }
        }

        /*
         * Right wall
         */
        if(ballNextMove.getBallRectangle().getX() + ballNextMove.getBallRectangle().getWidth() > this.getFieldShape().getX() + this.getFieldShape().getWidth()) {
            /*
             * TODO Perhaps getY should not be taken from ball (which was), but in itself, i.e. new, because the ball could also move along Y, but not go beyond the boundaries
             */
            ballNextMove.getBallRectangle().setLocation((int)(this.getFieldShape().getX() + this.getFieldShape().getWidth() - ballNextMove.getBallRectangle().getWidth()),
                    (int)ball.getBallRectangle().getY());

            if(ballAngle >= 0 && ballAngle <= 90) {
                ballNextMove.setBallAngle(ballAngle + 270);
            } else if (ballAngle >= 90 && ballAngle <= 180) {
                ballNextMove.setBallAngle(ballAngle + 90);
            }
        }

        ballNextMove = ballNextMove.nextMove();
        return ballNextMove;
    }

    /**
     * Draws a field with its color in the transferred graphic context
     * @param g Graphic context for drawing
     */
    public void paint(Graphics g) {
        g.setColor(this.getForegroundColor());
        g.drawRect((int)round(this.getFieldShape().getX()),
                (int)round(this.getFieldShape().getY()),
                (int)round(this.getFieldShape().getWidth()),
                (int)round(this.getFieldShape().getHeight()));
    }
}
