package gui;

import engine.Field;
import engine.concurrent.BallAnimator;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Panel with balls and field
 *
 * @version 1.3 2017-10-12
 * @author Alex Venger
 */
public class FieldPanel extends JPanel {
    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 400;
    public static final int DEFAULT_BORDER = 4;
    public static final int DEFAULT_MARGIN = 0;
    private static final Color DEFAULT_FIELD_COLOR = Color.GRAY;

    private JLabel lblPause = new JLabel("PAUSE");

    /**
     * List of the jumping balls
     */
    private List<Thread> balls;

    public void addBall(Thread ball){
        balls.add(ball);
    }

    public void clearBalls() {
        for(Thread ball : balls) {
            ball.interrupt();
        }
        balls.clear();
        repaint();
    }

    public void showLabelPause(boolean flag) {
        if(flag) {
            add(lblPause);
        } else {
            remove(lblPause);
        }
        repaint();
        revalidate();
    }

    public FieldPanel() {
        setBorder(BorderFactory.createLineBorder(getBackground(), DEFAULT_BORDER));
        Insets insets = getInsets();

        balls = new LinkedList<>();

        BallAnimator.setComponent(this);

        Field field = new Field(
                DEFAULT_MARGIN + insets.left,
                DEFAULT_MARGIN + insets.top,
                DEFAULT_WIDTH - DEFAULT_MARGIN * 2 - insets.right - insets.left - 1,
                DEFAULT_HEIGHT - DEFAULT_MARGIN * 2 - insets.bottom - insets.top - 1,
                DEFAULT_FIELD_COLOR);
        BallAnimator.setField(field);

        setLayout(new BorderLayout());

        Font font = new Font(getFont().getFontName(), getFont().getStyle(), 50);
        lblPause.setFont(font);
        lblPause.setForeground(DEFAULT_FIELD_COLOR);
        lblPause.setHorizontalAlignment(SwingConstants.CENTER);
        lblPause.setVerticalAlignment(SwingConstants.CENTER);

        addComponentListener(new ComponentEventHandler());
        addMouseListener(new MouseEventHandler(this));
    }

    public void resizeField() {
        Insets insets = getInsets();
        BallAnimator.getField().setSize(getWidth() - DEFAULT_MARGIN * 2 - insets.right - insets.left - 1,
                getHeight() - DEFAULT_MARGIN * 2 - insets.bottom - insets.top - 1);
    }

    @Override
    public Dimension getPreferredSize () {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BallAnimator.getField().paint(g);
        for (Thread ball : balls) {
            ((BallAnimator)ball).getBall().paint(g);
        }
    }
}
