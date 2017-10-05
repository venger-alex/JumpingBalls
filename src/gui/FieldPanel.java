package gui;

import engine.Ball;
import engine.Field;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel with balls and field
 *
 * @aversion 1.0 2017-10-05
 * @author Alex Venger
 */
public class FieldPanel extends JPanel {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;

    private List<Ball> balls = new ArrayList<>();
    private Field field = new Field(new Rectangle(3, 3, DEFAULT_WIDTH - 6, DEFAULT_HEIGHT - 6),
            Color.LIGHT_GRAY, Color.BLUE);

    public Field getField() {
        return field;
    }

    @Override
    public Dimension getPreferredSize () {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Adds a new ball to the panel
     * @param ball New ball
     */
    public void add(Ball ball){
        balls.add(ball);
    }

    public FieldPanel() {
        addComponentListener(new ComponentEventHandler());
        addMouseListener(new MouseEventHandler(this));
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
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
