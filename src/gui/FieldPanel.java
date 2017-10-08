package gui;

import engine.Ball;
import engine.Field;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Panel with balls and field
 *
 * @version 1.1 2017-10-08
 * @author Alex Venger
 */
public class FieldPanel extends JPanel {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;
    private static final int DEFAULT_MARGIN = 3;

    /**
     * List of the jumping balls
     */
    private List<Ball> balls;

    /**
     * Field in which the balls are jumping
     */
    private Field field;

    /**
     * Adds a new ball to the panel
     * @param ball New ball
     */
    public void add(Ball ball){
        balls.add(ball);
    }

    public FieldPanel() {
        balls = new LinkedList<>();
        field = new Field(
                DEFAULT_MARGIN,
                DEFAULT_MARGIN,
                DEFAULT_WIDTH - DEFAULT_MARGIN * 2,
                DEFAULT_HEIGHT - DEFAULT_MARGIN * 2,
                Color.BLACK);

        addComponentListener(new ComponentEventHandler());
        addMouseListener(new MouseEventHandler(this));
    }

    /**
     * Calculates the next position of the ball, taking into account the rebound from the walls.
     * Can change, both the position and direction of the movement (rebound), i.e. angle of the ball
     * @param ball The ball for which you need to calculate a new position
     * @return Copy of the ball in the new position
     */
    public Ball nextMove(Ball ball) {
        return field.nextMove(ball);
    }

    public void resizeField() {
        field.setSize(getWidth() - DEFAULT_MARGIN * 2,
                getHeight() - DEFAULT_MARGIN * 2);
    }

    @Override
    public Dimension getPreferredSize () {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        field.paint(g);
        for (Ball ball : balls) {
            ball.paint(g);
        }
    }
}
