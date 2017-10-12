package gui;

import engine.Ball;
import engine.concurrent.BallAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Menu event handler
 *
 * @version 1.0 2017-10-12
 * @author Alex Venger
 */
public class MenuEventHandler implements ActionListener {
    FieldPanel fieldPanel;

    public MenuEventHandler(FieldPanel fieldPanel) {
        this.fieldPanel = fieldPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add" :
                if(BallAnimator.isPaused()) return;

                int radius = ThreadLocalRandom.current().nextInt(5, 15 + 1);

                Color color = new Color(ThreadLocalRandom.current().nextInt(0, 255 + 1),
                        ThreadLocalRandom.current().nextInt(0, 255 + 1),
                        ThreadLocalRandom.current().nextInt(0, 255 + 1));

                int angle = ThreadLocalRandom.current().nextInt(0, 360 + 1);

                int delay = ThreadLocalRandom.current().nextInt(3, 30 + 1);

                Insets insets = fieldPanel.getInsets();

                int x = ThreadLocalRandom.current().nextInt(fieldPanel.DEFAULT_MARGIN + insets.left + radius,
                        fieldPanel.DEFAULT_WIDTH - fieldPanel.DEFAULT_MARGIN * 2 - insets.right - insets.left - 1 - radius + 1);

                int y = ThreadLocalRandom.current().nextInt(fieldPanel.DEFAULT_MARGIN + insets.top + radius,
                        fieldPanel.DEFAULT_HEIGHT - fieldPanel.DEFAULT_MARGIN * 2 - insets.bottom - insets.top - 1 - radius + 1);

                Ball ball = new Ball(x, y, radius, color, angle, 1, delay);

                Thread ballAnimator = new BallAnimator(ball);
                fieldPanel.addBall(ballAnimator);

                break;

            case "Pause" :
                Boolean state = ((JCheckBoxMenuItem) e.getSource()).getState();
                BallAnimator.setPause(state);
                fieldPanel.showLabelPause(state);
                // Menu item add ball
                ((JMenuItem)((JRootPane)fieldPanel.getParent().getParent().getParent()).getJMenuBar().getMenu(0).getItem(0)).setEnabled(!state);
                break;

            case "Clear" :
                fieldPanel.clearBalls();
                BallAnimator.setPause(false);
                // Menu item pause
                ((JCheckBoxMenuItem)((JRootPane)fieldPanel.getParent().getParent().getParent()).getJMenuBar().getMenu(0).getItem(1)).setState(false);
                fieldPanel.showLabelPause(false);
                // Menu item add ball
                ((JMenuItem)((JRootPane)fieldPanel.getParent().getParent().getParent()).getJMenuBar().getMenu(0).getItem(0)).setEnabled(true);
                break;

            case "Exit" :
                System.exit(0);
                break;
        }
    }
}
