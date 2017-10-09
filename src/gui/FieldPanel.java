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
 * @version 1.2 2017-10-09
 * @author Alex Venger
 */
public class FieldPanel extends JPanel {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;
    private static final int DEFAULT_BORDER = 4;
    private static final int DEFAULT_MARGIN = 0;
    private static final Color DEFAULT_FIELD_COLOR = Color.GRAY;

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
        setBorder(BorderFactory.createLineBorder(getBackground(), DEFAULT_BORDER));
        Insets insets = getInsets();

        balls = new LinkedList<>();
        field = new Field(
                DEFAULT_MARGIN + insets.left,
                DEFAULT_MARGIN + insets.top,
                DEFAULT_WIDTH - DEFAULT_MARGIN * 2 - insets.right - insets.left - 1,
                DEFAULT_HEIGHT - DEFAULT_MARGIN * 2 - insets.bottom - insets.top - 1,
                DEFAULT_FIELD_COLOR);

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
        Insets insets = getInsets();
        field.setSize(getWidth() - DEFAULT_MARGIN * 2 - insets.right - insets.left - 1,
                getHeight() - DEFAULT_MARGIN * 2 - insets.bottom - insets.top - 1);
    }

    @Override
    public Dimension getPreferredSize () {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        field.paint(g);
        for (Ball ball : balls) {
            ball.paint(g);
        }
    }
}
